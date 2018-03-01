package app.explorerpost2.dao;

import java.util.HashMap;
import java.util.List;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.CalendarDayBean;
import app.explorerpost2.bean.EmergencyContactBean;
import app.explorerpost2.bean.EquipmentBean;
import app.explorerpost2.bean.EventBean;
import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.exception.NotInsertedException;
import app.explorerpost2.exception.NotUpdatedException;
import app.explorerpost2.form.EmergencyContactFormBean;
import app.explorerpost2.form.EquipmentFormBean;
import app.explorerpost2.form.EventFormBean;
import app.explorerpost2.form.MemberFormBean;

public interface MainDao {

	//----------------------------------------------------------------------------------
	// Public Methods
	//-----------------------------------------------------------------------------------
	
	/*
	 * user related
	 */
	public void insertUser(MemberFormBean mf) throws NotInsertedException, Exception;	
	public void updateNotifyPref(MemberFormBean mf) throws NotUpdatedException;
	public void updateRoles(MemberFormBean mf) throws NotUpdatedException;
	public void updateStatus(MemberFormBean mf) throws NotUpdatedException;
	
	/*
	 * Login related
	 */
	public void updatePassword(PasswordResetBean prb);
	public String getPassword(String username);
	public void updateLastLogin(String username);
	public void logFailedLogin(String username);
	public void clearFailedLogin(String username);
	public Integer getFailedCount(String username);
	public void updateMemberStatus(HashMap<String,String> pmParams);
	
	/*
	 * MenuItem related
	 */
	public List<MenuItem> getUserList();
	public List<MenuItem> getIdUserList();
	public List<MenuItem> getUserGrps();
	public MemberFormBean getUserPref(String psUserId);
	public MemberFormBean getUserRole(String psUserId);
	public String getUserStatus(String psUserId);
	
	/*
	 * Event related
	 */
	public List<CalendarDayBean> getCalendarDaysByUser(HashMap params);
	public List<EventBean> getEventsByUser(HashMap params);
	public void insertEvent(EventFormBean ef);
	public void updateEvent(EventFormBean ef);
	public List<MenuItem> getEventList();
	public EventBean getEventByKey(String eventKey);
	
	/*
	 * Attendance related
	 */
	public List<AttendanceBean> getAttendanceByKey(String eventKey);
	public void updAttendanceRecord(AttendanceBean attendance);
	
	/*
	 * Contanct Information related
	 */
	public EmergencyContactBean getEmergenctContact(String psUserId);
	public void updEmergenctContact(EmergencyContactFormBean ec);
	public Integer isContactOutdated(String userId);
	public List<EmergencyContactBean> getEmergencyContacts();
	public void requestContactUpdate(String userId);
	
	/*
	 * Equipment related
	 */
	public List<MenuItem> getEquipmentStatuses();
	public List<MenuItem> getEquipmentList();
	public EquipmentBean getEquipmentAssignedUser(Integer id);
	public EquipmentBean getEquipmentInformation(Integer id);
	public void insertEquipment(EquipmentFormBean ef);
	public void assignOrUnassign(EquipmentFormBean ef);
	public void updateEquipment(EquipmentFormBean ef);
	
	
	
} 
