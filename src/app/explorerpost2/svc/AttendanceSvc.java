package app.explorerpost2.svc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.exclusionStrategy.GsonExclusionStrategy;

@Service("attendanceSvc")
public class AttendanceSvc {
	
	private Log log = LogFactory.getLog(this.getClass());  
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;

	public HashMap<String,String> updateAttendance(String json, Integer eventId, String username){
		String result = null;
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		HashMap<String,String> map = new HashMap<String,String>();
		List<String> errorsFound = null;
		Type attendanceBeanList =  new TypeToken<List<AttendanceBean>>(){}.getType();
		Type attendanceBean =  new TypeToken<AttendanceBean>(){}.getType();
		List<AttendanceBean> attendance = new ArrayList<AttendanceBean>();
		
		map.put("result", "success"); //assume success and correct on exception
		
		try{
			attendance = gson.fromJson(json,attendanceBeanList);
		}catch(Exception e){
			try{
				attendance.add((AttendanceBean) gson.fromJson(json,attendanceBean));
			}catch(Exception ee){
				map.put("result", "failed");
				map.put("errors", "Failed to convert to attendance records!");
				return map;
			}
		}

		//set some missing information before we validate everything
		if(attendance == null){
			map.put("result", "failed");
			map.put("errors", "Failed to update attendance records!");
			return map;
		}
		for ( AttendanceBean att : attendance){
			if(att.getUserId() == null)att.setUserId(username);
			if(att.getEventId() == null)att.setEventId(eventId.toString());
			att.setAudtUserId(username);
			if(att.getUserId().equalsIgnoreCase(att.getAudtUserId())){
				att.setAttendanceTypeCd("U"); //this is the current user's session
			}else{
				att.setAttendanceTypeCd("X"); //admin update so we need to lock user out of changing it
			}
		}


		errorsFound = validationSvc.validateAttendanceRecords(attendance);
		if(!errorsFound.isEmpty()){
			result = gson.toJson(errorsFound);
			map.put("result", "failed");
			map.put("errors", result);
			return map;
		}

		
		for ( AttendanceBean att : attendance){
			try{
				mainDao.updAttendanceRecord(att);
			}catch (DataIntegrityViolationException m){
				log.info("Skipping attendance record. Nothing changed");;
			}catch (Exception e){
				log.error(e.getMessage());
				e.printStackTrace();
				map.put("result", "failed");
			}
		}
		
		return map;
	}
}
