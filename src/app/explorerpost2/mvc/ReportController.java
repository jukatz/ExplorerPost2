package app.explorerpost2.mvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.ReportFormBean;
import app.explorerpost2.svc.MemberManagerSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class ReportController  {

	private Log log;    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="memberManagerSvc") private MemberManagerSvc memberManagerSvc;
	@Resource(name="transactionManager") private DataSourceTransactionManager loDSTransMgr;

	@ModelAttribute("userList")
	public List<MenuItem> getUsers(){
		List<MenuItem> users = new ArrayList();

		users = mainDao.getUserList();
		users.add(0, new MenuItem("-1","All"));

		return users;

	}
	
	@ModelAttribute("eventList")
	public List<MenuItem> getEvents(){
		List<MenuItem> events = new ArrayList();

		events = mainDao.getEventList();
		events.add(0, new MenuItem("-1","All"));

		return events;

	}
	
	@ModelAttribute("equipList")
	public List<MenuItem> getEquip(){
		List<MenuItem> equip = new ArrayList();

		equip = mainDao.getEventList();
		equip.add(0, new MenuItem("-1","All"));

		return equip;

	}


	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}

	@RequestMapping( value="/report", method=RequestMethod.GET )
	public String initForm( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		ReportFormBean command = new ReportFormBean();
		model.put("command", command);	

		return "report";
	}


}