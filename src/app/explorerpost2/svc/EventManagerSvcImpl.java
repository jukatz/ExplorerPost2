package app.explorerpost2.svc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.EventBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.EventFormBean;

@Service("eventManagerSvc")
public class EventManagerSvcImpl implements EventManagerSvc{

	private @Value("${app.passSec}") int passSec;
	@Resource(name="mainDao") private MainDao mainDao;
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void addEvent(EventFormBean efb, String pmCrUser) throws Exception {

		try{
			efb.setAudtCrId(pmCrUser);
			mainDao.insertEvent(efb);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error interacting with the database.");
		}
		
	}
	
	@Override
	public void updEvent(EventFormBean efb, String pmUpdUser) throws Exception {

		try{
			efb.setAudtUpdId(pmUpdUser);
			mainDao.updateEvent(efb);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error interacting with the database.");
		}
		
	}
	
	public EventBean eventDetails(String eventKey){
		return mainDao.getEventByKey(eventKey);
	}
	
	public List<AttendanceBean> attendanceDetails(String eventKey){
		return mainDao.getAttendanceByKey(eventKey);
	}
	


}
