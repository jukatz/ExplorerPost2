package app.explorerpost2.mvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.PasswordResetFormBean;
import app.explorerpost2.svc.MemberManagerSvc;
import app.explorerpost2.svc.PasswordSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class PasswordController  {

	private Log log;    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="memberManagerSvc") private MemberManagerSvc memberManagerSvc;
	@Resource(name="transactionManager") private DataSourceTransactionManager loDSTransMgr;
	@Resource(name="passwordSvc") private PasswordSvc passwordSvc;


	@ModelAttribute("userList")
	public List<MenuItem> getUsers(){
		List<MenuItem> users = new ArrayList();

		users = mainDao.getUserList();

		return users;

	}

	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}

	@RequestMapping( value="/reset", method=RequestMethod.GET )
	public String adminReset( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		PasswordResetFormBean command = new PasswordResetFormBean();
		model.put("command", command);	

		return "memberReset";
	}
	
	@RequestMapping( value="/password", method=RequestMethod.GET )
	public String userReset( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		PasswordResetFormBean command = new PasswordResetFormBean();
		model.put("command", command);	

		return "passwordReset";
	}
	
	@RequestMapping( value="/reset", method=RequestMethod.POST )
	public ModelAndView resetUserPassword( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") PasswordResetFormBean prf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		
		errorsFound = validationSvc.validatePasswordReset(prf, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update password! ", errorStr);
			Map<String,Object> map = createMap(prf,errors);
			return( new ModelAndView( "memberReset", map) );
		}
		
		
		try{
			PasswordResetBean obj = new PasswordResetBean();
			BeanUtils.copyProperties(obj,prf);
			obj.setExpiresInDays(-1); //set the password as expired
			obj.setUpdId(SessionSvc.getUserName(request));
			obj.setHashed(passwordSvc.generatePassword(obj.getPassword()));
			memberManagerSvc.resetMemberPassword(obj);
			passwordSvc.clearFailedLogin(obj.getUsername());
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update password! ", e.getMessage());
			Map<String,Object> map = createMap(prf,errors);
			return( new ModelAndView( "memberReset", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated password. Don't forget to unlock their account!");

		return new ModelAndView("memberReset");
	}
	
	@RequestMapping( value="/password", method=RequestMethod.POST )
	public ModelAndView resetPassword( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") PasswordResetFormBean prf, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		prf.setUsername(SessionSvc.getUserName(request));
		errorsFound = validationSvc.validateChangePassword(prf, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update password! ", errorStr);
			Map<String,Object> map = createMap(prf,errors);
			return( new ModelAndView( "passwordReset", map) );
		}
		
		
		try{
			PasswordResetBean obj = new PasswordResetBean();
			BeanUtils.copyProperties(obj,prf);
			obj.setExpiresInDays(365); //set the password to expire in 365 days
			obj.setUpdId(SessionSvc.getUserName(request));
			obj.setHashed(passwordSvc.generatePassword(obj.getPassword()));
			memberManagerSvc.resetMemberPassword(obj);
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update password! ", e.getMessage());
			Map<String,Object> map = createMap(prf,errors);
			return( new ModelAndView( "passwordReset", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated password");

		return new ModelAndView("passwordReset");
	}

}