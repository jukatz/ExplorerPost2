package app.explorerpost2.svc;

import java.util.List;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.EventBean;
import app.explorerpost2.form.EventFormBean;

public interface EventManagerSvc {

	public void addEvent(EventFormBean mfb, String pmCrUser) throws Exception;
	public void updEvent(EventFormBean mfb, String pmUpdUser) throws Exception;
	public EventBean eventDetails(String eventKey);
	
	public List<AttendanceBean> attendanceDetails(String eventKey);
	

}
