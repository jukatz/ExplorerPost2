package app.explorerpost2.svc;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.dao.MainDao;
import app.explorerpost2.form.MemberFormBean;

@Service("memberManagerSvc")
public class MemberManagerSvcImpl extends MemberManagerGenerator implements MemberManagerSvc{

	private @Value("${app.passSec}") int passSec;
	@Resource(name="mainDao") private MainDao mainDao;
	private Log log = LogFactory.getLog(this.getClass());
	@Resource(name="passwordSvc") private PasswordSvc passwordSvc;
	
	@Override
	public void addMember(MemberFormBean mfb, String pmCrUser) throws Exception {

		mfb.setPasswordEnc(passwordSvc.generatePassword(mfb.getPassword()));
		mfb.setPhoneNumberFull(buildTextAddress(mfb.getPhoneNumber(),mfb.getCarrier()));
		
		try{
			mfb.setAudtUserId(pmCrUser);
			mainDao.insertUser(mfb);
		}catch(MySQLIntegrityConstraintViolationException dke){
			throw new Exception("User already exists!");
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}
	
	@Override
	public void editMemberNotifyPreferences(MemberFormBean mfb, String pmAudtUser) throws Exception{
		
		mfb.setPhoneNumberFull(buildTextAddress(mfb.getPhoneNumber(),mfb.getCarrier()));
		
		try{
			mainDao.updateNotifyPref(mfb);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public void editMemberRoles(MemberFormBean mfb, String pmAudtUser) throws Exception {
		
		try{
			mainDao.updateRoles(mfb);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public void editActiveStatus(MemberFormBean mf) throws Exception {
		
		try{
			mainDao.updateStatus(mf);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public void resetMemberPassword(PasswordResetBean prb) throws Exception{
		try{
			mainDao.updatePassword(prb);
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	protected String buildTextAddress(String psTenDigit, String psCarrier) {

		return StringUtils.trimToNull(psTenDigit) + StringUtils.trimToNull(psCarrier);
		
	}


}
