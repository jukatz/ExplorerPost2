package app.explorerpost2.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.CalendarDayBean;
import app.explorerpost2.bean.EmergencyContactBean;
import app.explorerpost2.bean.EquipmentBean;
import app.explorerpost2.bean.EventBean;
import app.explorerpost2.bean.MenuItem;
import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.exception.NotInsertedException;
import app.explorerpost2.exception.NotUpdatedException;
import app.explorerpost2.form.EmergencyContactFormBean;
import app.explorerpost2.form.EquipmentFormBean;
import app.explorerpost2.form.EventFormBean;
import app.explorerpost2.form.MemberFormBean;


public class MainDaoImpl extends SqlSessionDaoSupport implements MainDao {

	
	public void insertUser(MemberFormBean mf) throws NotInsertedException{

		int count = 0;
		count = getSqlSession().insert("mainDao.insertMemberRec", mf);
		if(count == 0) throw new NotInsertedException("User was not inserted");

		count = getSqlSession().insert("mainDao.insertNotifyPref", mf);
		if(count == 0) throw new NotInsertedException("User was not inserted");

		count = getSqlSession().insert("mainDao.insertGrpXref", mf);
		if(count == 0) throw new NotInsertedException("User was not inserted");
		
		count = getSqlSession().insert("mainDao.insertEmergencyContact",mf);
		if(count == 0) throw new NotInsertedException("User was not inserted");

	}
	
	public void updateNotifyPref(MemberFormBean mf) throws NotUpdatedException{
		
		int count = 0;
		count = getSqlSession().update("mainDao.updateUserEmail", mf);
		if(count == 0) throw new NotUpdatedException("User was not updated");
		
		count = getSqlSession().update("mainDao.updateUserNotifyPref", mf);
		if(count == 0) throw new NotUpdatedException("User was not updated");
		
	}
	
	public void updateRoles(MemberFormBean mf) throws NotUpdatedException{
		
		int count = 0;
		count = getSqlSession().update("mainDao.updateAppRole", mf);
		if(count == 0) throw new NotUpdatedException("User was not updated");
		
		count = getSqlSession().update("mainDao.updateUserRole", mf);
		if(count == 0) throw new NotUpdatedException("User was not updated");
		
	}
	
	public void updateStatus(MemberFormBean mf) throws NotUpdatedException{
		
		int count = 0;
		count = getSqlSession().update("mainDao.updateUserStatus",mf);
		if(count == 0) throw new NotUpdatedException("User was not updated");
		
	}
	
	public void updatePassword(PasswordResetBean prb){
		getSqlSession().update("mainDao.updatePassword",prb);
	}
	
	public String getPassword(String username){
		return (String) getSqlSession().selectOne("mainDao.selectPassword", username);
	}
	
	public void updateLastLogin(String username){
		getSqlSession().update("mainDao.updateLastLogin",username);
	}
	
	public void logFailedLogin(String username){
		getSqlSession().insert("mainDao.logFailedLogin", username);
	}
	
	public void clearFailedLogin(String username){
		getSqlSession().delete("mainDao.clearFailedLogin", username);
	}
	
	public Integer getFailedCount(String username){
		return (Integer) getSqlSession().selectOne("mainDao.selectFailedCount", username);
	}
	
	public void updateMemberStatus(HashMap<String,String> pmParams){
		getSqlSession().update("mainDao.updateMemberStatus", pmParams);
	}
	
	public Integer isContactOutdated(String userId){
		return (Integer) getSqlSession().selectOne("mainDao.isContactOutdated",userId);
	}
	
	public List<EmergencyContactBean> getEmergencyContacts(){
		return (List<EmergencyContactBean>) getSqlSession().selectList("mainDao.selectEmergencyContacts");
	}
	
	public void requestContactUpdate(String userId){
		getSqlSession().update("mainDao.requestContactUpdate", userId);
	}

	/*
	 * These are used in ajax calls for filling in the member information forms
	 */
	public List<MenuItem> getUserList(){
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectUserList");
	}
	public List<MenuItem> getIdUserList(){
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectIdUserList");
	}
	
	public List<MenuItem> getUserGrps(){
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectUserGrps");
	}

	public MemberFormBean getUserPref(String psUserId){
		return (MemberFormBean) getSqlSession().selectOne("mainDao.selectUserPref", psUserId);
	}
	
	public MemberFormBean getUserRole(String psUserId){
		return (MemberFormBean) getSqlSession().selectOne("mainDao.selectUserRole", psUserId);
	}
	
	public String getUserStatus(String psUserId){
		return (String) getSqlSession().selectOne("mainDao.selectUserStatus", psUserId);
	}
	
	public List<CalendarDayBean> getCalendarDaysByUser(HashMap params){
		return (List<CalendarDayBean>) getSqlSession().selectList("mainDao.selectCalendarDaysByUser", params);
	}
	
	public List<EventBean> getEventsByUser(HashMap params){
		return (List<EventBean>) getSqlSession().selectList("mainDao.selectEventsByUser", params);
	}

	public void insertEvent(EventFormBean ef) {
		int count = 0;
		count = getSqlSession().insert("mainDao.insertEventRec", ef);
	
	}
	
	public void updateEvent(EventFormBean ef) {
		int count = 0;
		count = getSqlSession().update("mainDao.updateEventRec", ef);
	
	}

	public List<MenuItem> getEventList() {
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectEventList");
	}
	
	public EventBean getEventByKey(String eventKey){
		return (EventBean) getSqlSession().selectOne("mainDao.selectEventByKey",eventKey);
	}
	
	public List<AttendanceBean> getAttendanceByKey(String eventKey){
		return (List<AttendanceBean>) getSqlSession().selectList("mainDao.selectAttendanceByKey",eventKey);
	}
	
	public void updAttendanceRecord(AttendanceBean attendance){
		int cnt = getSqlSession().update("mainDao.updAttendanceRecord", attendance);
		//if we update 0 rows then we need to insert. Do it this way to avoid key violations
		if(cnt == 0){
			getSqlSession().insert("mainDao.insertAttendanceRecord", attendance);
		}
	}

	public EmergencyContactBean getEmergenctContact(String psUserId){
		return (EmergencyContactBean) getSqlSession().selectOne("mainDao.selectEmergencyContact",psUserId);

	}
	
	public void updEmergenctContact(EmergencyContactFormBean ec){
		getSqlSession().update("mainDao.updEmergenctContact", ec);
	}
	
	public List<MenuItem> getEquipmentStatuses(){
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectEquipmentStatuses");
	}
	
	public List<MenuItem> getEquipmentList(){
		return (List<MenuItem>) getSqlSession().selectList("mainDao.selectEquipmentList");
	}
	
	public EquipmentBean getEquipmentAssignedUser(Integer id){
		return (EquipmentBean) getSqlSession().selectOne("mainDao.selectEquipmentAssignedUser",id);
	}
	
	public EquipmentBean getEquipmentInformation(Integer id){
		return (EquipmentBean) getSqlSession().selectOne("mainDao.selectEquipmentInformation",id);
	}
	
	public void insertEquipment(EquipmentFormBean ef){
		getSqlSession().insert("mainDao.insertEquipment", ef);
	}
	
	public void assignOrUnassign(EquipmentFormBean ef){
		getSqlSession().update("mainDao.assignOrUnassign", ef);
	}
	
	public void updateEquipment(EquipmentFormBean ef){
		getSqlSession().update("mainDao.updateEquipment", ef);
	}

}
