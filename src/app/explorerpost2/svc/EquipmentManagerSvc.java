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
import app.explorerpost2.bean.EquipmentBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.exclusionStrategy.GsonExclusionStrategy;
import app.explorerpost2.form.EquipmentFormBean;

@Service("equipmentManagerSvc")
public class EquipmentManagerSvc {
	
	private Log log = LogFactory.getLog(this.getClass());  
	@Resource(name="mainDao") private MainDao mainDao;
	@Resource(name="validationSvc") private ValidationSvc validationSvc;

	public void addEquipment(EquipmentFormBean ef) throws Exception{

		try{
			mainDao.insertEquipment(ef);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error creating the equipment record.");
		}
	}
	
	public void updateEquipment(EquipmentFormBean ef) throws Exception{

		try{
			mainDao.updateEquipment(ef);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error updating the equipment record.");
		}
	}
	
	public void assignEquipment(EquipmentFormBean ef) throws Exception{

		try{
			mainDao.assignOrUnassign(ef);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error assigning or unassigning the equipment record.");
		}
	}
	
	public EquipmentBean getAssignedUser(Integer id) throws Exception{
		EquipmentBean result = new EquipmentBean();
		try{
			result = mainDao.getEquipmentAssignedUser(id);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error creating the equipment record.");
		}
		
		return result;
	}
	
	public EquipmentBean getInformation(Integer id) throws Exception{
		EquipmentBean result = new EquipmentBean();
		try{
			result = mainDao.getEquipmentInformation(id);
		}catch(Exception e){
			log.error(e.getMessage());
			throw new Exception("There was an error finding the equipment record.");
		}
		
		return result;
	}

}
