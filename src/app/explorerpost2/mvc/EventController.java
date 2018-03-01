package app.explorerpost2.mvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.EventFormBean;
import app.explorerpost2.svc.AttendanceSvc;
import app.explorerpost2.svc.EventManagerSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class EventController  {

	private Log log;    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="transactionManager") private DataSourceTransactionManager loDSTransMgr;
	@Resource(name="eventManagerSvc") private EventManagerSvc eventManagerSvc;
	@Resource(name="attendanceSvc") private AttendanceSvc attendanceSvc;

	@ModelAttribute("eventList")
	public List<MenuItem> getEvents(){
		List<MenuItem> events = new ArrayList();

		events = mainDao.getEventList();

		return events;

	}

	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}

	@RequestMapping( value="/event", method=RequestMethod.GET )
	public String initForm( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		EventFormBean command = new EventFormBean();
		model.put("command", command);	

		return "events";
	}
	
	@RequestMapping( value="/event/result", method=RequestMethod.GET )
	public String cleanForm( HttpServletRequest request, ModelMap model) {
		EventFormBean command = new EventFormBean();
		model.put("command", command);	

		return "events";
	}


	@RequestMapping( value="/event", method=RequestMethod.POST )
	public ModelAndView processNewEvent( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") EventFormBean ef, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		//we hid the end date field on create event... assume the start date is the end date
		//You can adjust this in the edit event tab
		if(ef.getEndDt() == null) ef.setEndDt(ef.getStartDt());
		
		errorsFound = validationSvc.validateAddEvent(ef, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to add new event! ", errorStr);
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "events", map) );
		}
		
		
		try{
			eventManagerSvc.addEvent(ef, SessionSvc.getUserName(request));
			ef.resetObject();
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to add new event! ", e.getMessage());
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "events", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Added new event");

		ModelMap model = new ModelMap();
		model.put("eventList", getEvents());
		
		//return new ModelAndView( "events");
		return new ModelAndView("events",model);
	}

	@RequestMapping( value="/event/update", method=RequestMethod.POST )
	public ModelAndView processUpdEvent( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") EventFormBean ef, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		
		errorsFound = validationSvc.validateAddEvent(ef, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update event! ", errorStr);
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "events", map) );
		}
		
		
		try{
			eventManagerSvc.updEvent(ef, SessionSvc.getUserName(request));
			ef.resetObject();
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update event! ", e.getMessage());
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "events", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated event");

		ModelMap model = new ModelMap();
		model.put("eventList", getEvents());
		
		//return new ModelAndView( "events");
		return new ModelAndView("events",model);
	}
	

	@RequestMapping( value="event/attendance", method=RequestMethod.POST )
	public ModelAndView userAttendanceResponse(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("command") EventFormBean efb, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);
		HashMap<String,String> result = attendanceSvc.updateAttendance(efb.getAttendance(),efb.getEventId(),SessionSvc.getUser(request).getUserName());
		
		String status = result.get("result");
		if(status.equalsIgnoreCase("failed")){
			SessionSvc.storeDangerMessage(request, "Unable to process request:", "Failed to update attendance!");
			Map<String,Object> map = createMap(efb,errors);
			return( new ModelAndView( "events", map) );
		}
		
		SessionSvc.storeSuccessMessage(request,"Successful:","Updated attendance");

		ModelMap model = new ModelMap();
		model.put("eventList", getEvents());
		
		return new ModelAndView("events",model);
	}
	


}