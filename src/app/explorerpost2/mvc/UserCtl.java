package app.explorerpost2.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import app.explorerpost2.bean.User;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.LoginFormBean;
import app.explorerpost2.svc.PasswordSvc;
import app.explorerpost2.svc.SessionSvc;

@Controller
public class UserCtl {
	private Log log = LogFactory.getLog(this.getClass()); 
	@Resource(name="mainDao") private MainDao mainDao;
	private @Value("${login.success}") String successRedirect = null;
	private @Value("${login.failure}") String failureRedirect = null;
	private @Value("${javascript.version}") String javascriptVersion = null;
	private @Value("${app.version}") String appVersion = null;
	private @Value("${app.release}") String appRelease = null;
	private @Value("${app.passSec}") int passSec;
	
	@Resource(name="passwordSvc") private PasswordSvc passwordSvc;

	//------------------------------------------------------------------------------
	// Spring Controller Methods
	//------------------------------------------------------------------------------

	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView initForm( HttpServletRequest request, ModelMap model){
		if(SessionSvc.okayToClearMessages(request)){
			SessionSvc.clearMessages(request);
		}
		LoginFormBean command = new LoginFormBean();
		return new ModelAndView("login","command",command);
	}

	@RequestMapping( value="/login", method=RequestMethod.POST )
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") LoginFormBean loginForm, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);
		SessionSvc.setOkayToClearMessages(request, false);
		User user = null;

		if( loginForm.getUsername() == null || loginForm.getPassword() == null ) {
			log.debug(loginForm.toString());
			loginForm.setMessage("You are missing login information! ");
			Map<String,Object> map = createMap(loginForm,errors);
			return( new ModelAndView( "login", map) );
		}

		try{

			if(! passwordSvc.isActive(loginForm.getUsername())){
				loginForm.setMessage("Please contact your administrator! ");
				Map<String,Object> map = createMap(loginForm,errors);
				return( new ModelAndView( "login", map) );
			}
			
			if(! passwordSvc.passwordMatch(loginForm.getUsername(), loginForm.getPassword())){
				loginForm.setMessage("You credentials are invalid! ");
				passwordSvc.logFailedLogin(loginForm.getUsername());
				Map<String,Object> map = createMap(loginForm,errors);
				return( new ModelAndView( "login", map) );
			}

			user = new User( loginForm.getUsername() );
			if( user != null ) {
				SessionSvc.clearAll( request );		
				SessionSvc.storeUser( request, user );
				SessionSvc.storeJavascriptVersion( request, javascriptVersion );
				SessionSvc.storeAppVersion( request, appVersion );
				SessionSvc.storeAppRelease( request, appRelease );
				SessionSvc.setOkayToClearMessages(request, true);
				
				passwordSvc.logSuccessfulLogin(loginForm.getUsername());
				passwordSvc.clearFailedLogin(loginForm.getUsername());
				return new ModelAndView( new RedirectView( successRedirect ) );
			} 
		}catch(Exception e){
			log.debug(loginForm.toString());
			loginForm.setMessage("There was an issue during the login process! Please try again later.");
			Map<String,Object> map = createMap(loginForm,errors);
			return( new ModelAndView( "login", map) );
		}
		
		Map<String,Object> map = createMap(loginForm,errors);
		return( new ModelAndView( "login", map) );

	}

	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public ModelAndView showCalendar( HttpServletRequest request, HttpServletResponse response){
		SessionSvc.clearMessages(request);
		return new ModelAndView( "calendar");
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout( HttpServletRequest request, HttpServletResponse response){
		SessionSvc.clearAll(request);
		return new ModelAndView("redirect:/login");
	}

}
