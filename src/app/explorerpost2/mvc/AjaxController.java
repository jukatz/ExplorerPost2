package app.explorerpost2.mvc;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.*;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.CalendarDayBean;
import app.explorerpost2.bean.EmergencyContactBean;
import app.explorerpost2.bean.EquipmentBean;
import app.explorerpost2.bean.EventBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.exclusionStrategy.GsonExclusionStrategy;
import app.explorerpost2.form.EmergencyContactFormBean;
import app.explorerpost2.form.MemberFormBean;
import app.explorerpost2.svc.AttendanceSvc;
import app.explorerpost2.svc.EquipmentManagerSvc;
import app.explorerpost2.svc.EventManagerSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class AjaxController  {
	
    private Log log = LogFactory.getLog(this.getClass());    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="eventManagerSvc") private EventManagerSvc eventManagerSvc;
	@Resource(name="attendanceSvc") private AttendanceSvc attendanceSvc;
	@Resource(name="equipmentManagerSvc") private EquipmentManagerSvc equipmentManagerSvc;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping( value="/getEvents.json", method=RequestMethod.GET )
	public String getUserEvents( HttpServletRequest request,
			@RequestParam("month") int liMonth,
			@RequestParam("year") int liYear ) throws Exception {
		Type listType = new TypeToken<HashMap<String,String>>() {}.getType();
		List<CalendarDayBean> days = new ArrayList<CalendarDayBean>();
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		HashMap<String,String> eachDay = new HashMap<String,String>();
		List<JsonObject> fullObj = new ArrayList<JsonObject>();

		HashMap params = new HashMap();
		params.put("month", liMonth);
		params.put("year", liYear);
		params.put("userId", SessionSvc.getUser(request).getUserName());
		
		days = mainDao.getCalendarDaysByUser(params);
		
		for(CalendarDayBean day: days){
			params.remove("startDate"); //reset for each day
			params.put("startDate", day.getEventDate());
			day.setEvents(mainDao.getEventsByUser(params));
			eachDay.put(day.getEventDateString(), gson.toJsonTree(day).toString());
		}
		
		Integer upd = mainDao.isContactOutdated(SessionSvc.getUser(request).getUserName());
		if (upd > 0){
			eachDay.put("updContact", "true");
		}else{
			eachDay.put("updContact", "false");	
		}
		
		String responseStr = gson.toJson(eachDay,listType);
		//String responseStr = gson.toJson(fullObj);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/getUserRole.do", method=RequestMethod.POST )
	public String getUserRole( HttpServletRequest request,
			@RequestParam("username") String lsUserId) throws Exception {
		MemberFormBean result = null;
		
		result = mainDao.getUserRole(lsUserId);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/getUserPref.do", method=RequestMethod.POST )
	public String getUserPref( HttpServletRequest request,
			@RequestParam("username") String lsUserId) throws Exception {
		MemberFormBean result = null;
		
		result = mainDao.getUserPref(lsUserId);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}

	@ResponseBody
	@RequestMapping( value="/getUserStatus.do", method=RequestMethod.POST )
	public String getUserStatus( HttpServletRequest request,
			@RequestParam("username") String lsUserId) throws Exception {
		String result = null;
		HashMap<String,String> map = new HashMap<String,String>(1);
		
		result = mainDao.getUserStatus(lsUserId);
		
		map.put("activeStatus", result);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(map);
		return responseStr;
	}
	
	
	@ResponseBody
	@RequestMapping( value="/event.json", method=RequestMethod.GET )
	public String eventDetails( HttpServletRequest request,
			@RequestParam("key") String eventKey) throws Exception {
		EventBean result = null;
		HashMap<String,String> map = new HashMap<String,String>(1);
		
		result = eventManagerSvc.eventDetails(eventKey);
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/event/{key}/attendance", method=RequestMethod.GET )
	public String attendanceDetails( HttpServletRequest request,
			@PathVariable("key") String eventKey) throws Exception {
		List<AttendanceBean> result = null;
		HashMap<String,String> map = new HashMap<String,String>(1);
		
		result = eventManagerSvc.attendanceDetails(eventKey);
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/attendance", method=RequestMethod.POST )
	public String userAttendanceResponse( HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("attendanceInfo") String json) throws Exception {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		HashMap<String,String> result = attendanceSvc.updateAttendance(json,null,SessionSvc.getUser(request).getUserName());
		String status = result.get("result");
		if(status.equalsIgnoreCase("failed"))
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return gson.toJson(result);
	}
	
	
	@ResponseBody
	@RequestMapping( value="/user/contact", method=RequestMethod.GET )
	public String getEmergenctContact( HttpServletRequest request) throws Exception {
		EmergencyContactBean result = null;
		String lsUserId = SessionSvc.getUser(request).getUserName();
		result = mainDao.getEmergenctContact(lsUserId);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}

	@ResponseBody
	@RequestMapping( value="/user/contact", method=RequestMethod.POST )
	public String updEmergencyContact( HttpServletRequest request,
			EmergencyContactFormBean ecb) throws Exception {
		String result = null;
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		HashMap<String,String> map = new HashMap<String,String>(1);
		List<String> errorsFound = null;
		
		
		errorsFound = validationSvc.validateEmergencyContact(ecb);
		if(!errorsFound.isEmpty()){
			result = gson.toJson(errorsFound);
			map.put("errors", result);
			return gson.toJson(map);
		}
		
		ecb.setUserId(SessionSvc.getUser(request).getUserName());
		try{
			mainDao.updEmergenctContact(ecb);
			map.put("result", "success");	
		}catch (Exception e){
			map.put("result", "failed");
		}
		

		
		String responseStr = gson.toJson(map);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/user/{userId}/contact/request", method=RequestMethod.PUT )
	public String requestContactUpdate( HttpServletRequest request,
			@PathVariable("userId") String userId) throws Exception {
		List<AttendanceBean> result = null;
		HashMap<String,String> map = new HashMap<String,String>(1);
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		
		try{
			mainDao.requestContactUpdate(userId);
			map.put("result", "success");	
		}catch (Exception e){
			map.put("result", "failed");
		}
		return gson.toJson(map);
	}
	
	@ResponseBody
	@RequestMapping( value="/equipment/{id}", method=RequestMethod.GET )
	public String getEquipmentUserAssigned( HttpServletRequest request,
			@PathVariable("id") Integer id) throws Exception {
		EquipmentBean result = null;
		result = equipmentManagerSvc.getAssignedUser(id);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}
	
	@ResponseBody
	@RequestMapping( value="/equipment/{id}/information", method=RequestMethod.GET )
	public String getEquipmentInformation( HttpServletRequest request,
			@PathVariable("id") Integer id) throws Exception {
		EquipmentBean result = null;
		result = equipmentManagerSvc.getInformation(id);

		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).create();
		String responseStr = gson.toJson(result);
		return responseStr;
	}

	
}