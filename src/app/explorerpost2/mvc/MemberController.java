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
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.explorerpost2.bean.EmergencyContactBean;
import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.MemberFormBean;
import app.explorerpost2.svc.MemberManagerSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class MemberController  {

	private Log log;    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="memberManagerSvc") private MemberManagerSvc memberManagerSvc;
	@Resource(name="transactionManager") private DataSourceTransactionManager loDSTransMgr;

	@ModelAttribute("memberRoleList")
	public List<MenuItem> getAppRoles(){
		List<MenuItem> roles = new ArrayList();

		roles.add(new MenuItem("User","User"));
		roles.add(new MenuItem("Supervisor","Supervisor"));
		roles.add(new MenuItem("Admin","Admin"));
		roles.add(new MenuItem("System","System"));

		return roles;

	}

	@ModelAttribute("memberTypeList")
	public List<MenuItem> getMemberType(){
		List<MenuItem> roles = new ArrayList();

		roles = mainDao.getUserGrps();

		return roles;

	}

	@ModelAttribute("notificationList")
	public List<MenuItem> getNotificationType(){
		List<MenuItem> notify = new ArrayList();

		notify.add(new MenuItem("Text and Email","B"));
		notify.add(new MenuItem("Text Only","T"));
		notify.add(new MenuItem("Email Only","E"));
		notify.add(new MenuItem("No Notifications","N"));

		return notify;

	}

	@ModelAttribute("carrierList")
	public List<MenuItem> getCarriers(){
		List<MenuItem> notify = new ArrayList();

		notify.add(new MenuItem("Verizon","@vtext.com"));
		notify.add(new MenuItem("Sprint","@messaging.sprintpcs.com"));
		notify.add(new MenuItem("AT&T","@txt.att.net"));

		return notify;

	}

	@ModelAttribute("userList")
	public List<MenuItem> getUsers(){
		List<MenuItem> users = new ArrayList();

		users = mainDao.getUserList();

		return users;

	}
	
	@ModelAttribute("contactList")
	public List<EmergencyContactBean> getEmergency(){
		List<EmergencyContactBean> contacts = new ArrayList();

		contacts = mainDao.getEmergencyContacts();

		return contacts;

	}

	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}

	@RequestMapping( value="/member", method=RequestMethod.GET )
	public String initForm( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		MemberFormBean command = new MemberFormBean();
		model.put("command", command);	

		return "members";
	}
	
	@RequestMapping( value="/member/result", method=RequestMethod.GET )
	public String cleanForm( HttpServletRequest request, ModelMap model) {
		MemberFormBean command = new MemberFormBean();
		model.put("command", command);	

		return "members";
	}


	@RequestMapping( value="/member", method=RequestMethod.POST )
	public ModelAndView processNewUser( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") MemberFormBean mf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		errorsFound = validationSvc.validateAddUser(mf, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to add new user! ", errorStr);
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}
		
		try{
			memberManagerSvc.addMember(mf, SessionSvc.getUserName(request));
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to add new user! ", e.getMessage());
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Added new user");
		return new ModelAndView( new RedirectView( "member/result" ) );
	}



	@RequestMapping( value="/member/preference", method=RequestMethod.POST )
	public ModelAndView processEditUser( HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("command") MemberFormBean mf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;

		errorsFound = validationSvc.validateUpdNotifyPref(mf, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update user preferences! ", errorStr);
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}
		
		try{
			memberManagerSvc.editMemberNotifyPreferences(mf, SessionSvc.getUserName(request));
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update user preferences! ", e.getMessage());
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated user notification preferences");
		return new ModelAndView( new RedirectView( "../member/result#updUserPref" ) );
	}

	@RequestMapping( value="/member/role", method=RequestMethod.POST )
	public ModelAndView processEditRoles( HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("command") MemberFormBean mf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;

		errorsFound = validationSvc.validateUpdRoles(mf, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update user roles! ", errorStr);
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}
		
		try{
			memberManagerSvc.editMemberRoles(mf, SessionSvc.getUserName(request));
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update user roles! ", e.getMessage());
			Map<String,Object> map = createMap(mf,errors);
			return( new ModelAndView( "members", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated user roles");
		return new ModelAndView( new RedirectView( "../member/result#updUserRole" ) );
		
	}
	
	@RequestMapping( value="/member/status", method=RequestMethod.POST )
	public ModelAndView processEditStatus( HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("command") MemberFormBean mf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;

		errorsFound = validationSvc.validateUpdateStatus(mf, errors);
		
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update user status! ", errorStr);
			Map<String,Object> map = createMap(new MemberFormBean(),errors);
			return( new ModelAndView( "members", map) );
		}
		
		try{
			mf.setAudtUserId((SessionSvc.getUserName(request)));
			memberManagerSvc.editActiveStatus(mf);
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update user status! ", e.getMessage());
			Map<String,Object> map = createMap(new MemberFormBean(),errors);
			return( new ModelAndView( "members", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated user status");
		return new ModelAndView( new RedirectView( "../member/result#updUserStat" ) );
		
	}


}