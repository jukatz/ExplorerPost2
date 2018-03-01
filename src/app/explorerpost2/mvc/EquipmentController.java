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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.EquipmentFormBean;
import app.explorerpost2.form.EventFormBean;
import app.explorerpost2.svc.EquipmentManagerSvc;
import app.explorerpost2.svc.MemberManagerSvc;
import app.explorerpost2.svc.SessionSvc;
import app.explorerpost2.svc.ValidationSvc;

@Controller
public class EquipmentController  {

	private Log log;    
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;
	@Resource(name="equipmentManagerSvc") private EquipmentManagerSvc equipmentManagerSvc;
	@Resource(name="transactionManager") private DataSourceTransactionManager loDSTransMgr;

	@ModelAttribute("userList")
	public List<MenuItem> getUsers(){
		List<MenuItem> users = new ArrayList();

		users = mainDao.getIdUserList();

		users.add(0,new MenuItem("-1",""));
		return users;

	}
	
	@ModelAttribute("statusList")
	public List<MenuItem> getStatus(){
		List<MenuItem> stat = new ArrayList();

		stat = mainDao.getEquipmentStatuses();

		return stat;

	}
	
	@ModelAttribute("equipList")
	public List<MenuItem> getEquipment(){
		List<MenuItem> equip = new ArrayList();

		equip = mainDao.getEquipmentList();

		return equip;

	}


	private Map<String,Object> createMap( Object obj, BindingResult errors ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "command", obj );
		map.put( "errors",  errors );
		return map;
	}

	@RequestMapping( value="/equipment", method=RequestMethod.GET )
	public String initForm( HttpServletRequest request, ModelMap model) {
		SessionSvc.clearMessages(request);
		EquipmentFormBean command = new EquipmentFormBean();
		model.put("command", command);	

		return "equipment";
	}
	
	@RequestMapping( value="/equipment", method=RequestMethod.POST )
	public ModelAndView addEquipment( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") EquipmentFormBean ef, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		errorsFound = validationSvc.validateAddEquipment(ef, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to add new equipment! ", errorStr);
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}
		
		
		try{
			ef.setAudtUserId(SessionSvc.getUserName(request));
			equipmentManagerSvc.addEquipment(ef);
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to add new equipment! ", e.getMessage());
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Added new equipment");

		ModelMap model = new ModelMap();
		model.put("equipList", getEquipment());
		
		return new ModelAndView("equipment",model);
	}
	
	@RequestMapping( value="/equipment/assign", method=RequestMethod.POST )
	public ModelAndView assignEquipment( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") EquipmentFormBean ef, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		errorsFound = validationSvc.validateAssignEquipment(ef, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to assign or unassign equipment! ", errorStr);
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}
		
		
		try{
			ef.setAudtUserId(SessionSvc.getUserName(request));
			if(ef.getUserId() == -1)ef.setUserId(null);
			equipmentManagerSvc.assignEquipment(ef);
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to assign or unassign equipment! ", e.getMessage());
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Assigned or unassigned equipment");

		ModelMap model = new ModelMap();
		model.put("equipList", getEquipment());
		
		return new ModelAndView("equipment",model);
	}
	
	@RequestMapping( value="/equipment/information", method=RequestMethod.POST )
	public ModelAndView updateEquipment( HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") EquipmentFormBean ef, BindingResult errors) throws Exception {
		SessionSvc.clearMessages(request);	
		List<String> errorsFound = null;
		
		errorsFound = validationSvc.validateUpdateEquipment(ef, errors);
		if(!errorsFound.isEmpty()){
			String errorStr = "<br>";
			for(String err : errorsFound)
				errorStr = errorStr + err + "<br>";

			SessionSvc.storeDangerMessage(request, "Failed to update equipment! ", errorStr);
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}
		
		
		try{
			ef.setAudtUserId(SessionSvc.getUserName(request));
			equipmentManagerSvc.updateEquipment(ef);
		}catch(Exception e){
			SessionSvc.storeDangerMessage(request, "Failed to update equipment! ", e.getMessage());
			Map<String,Object> map = createMap(ef,errors);
			return( new ModelAndView( "equipment", map) );
		}

		SessionSvc.storeSuccessMessage(request,"Successful:","Updated equipment");

		ModelMap model = new ModelMap();
		model.put("equipList", getEquipment());
		
		return new ModelAndView("equipment",model);
	}


}