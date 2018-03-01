package app.explorerpost2.svc;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import app.explorerpost2.dao.MainDao;

@Service("passwordSvc")
public class PasswordSvc {
	
	private @Value("${app.passSec}") int passSec;
	private @Value("${app.maxLogin}") int maxLogin;
	private Log log = LogFactory.getLog(this.getClass());
	@Resource(name="mainDao") private MainDao mainDao;
	
	public boolean passwordMatch(String username, String password){
		boolean matches = false;
		
		try{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(passSec);
			//String hashedPassword = encoder.encode(password);
			String dbPass = mainDao.getPassword(username);
			matches = encoder.matches(password, dbPass);
		}catch(Exception e){
			log.error("Failed to retrive hashed password for: " + username);
			return false;
		}


		return matches;
	}
	
	public boolean isActive(String username){
		try{
			String status = mainDao.getUserStatus(username);
			return (status.equalsIgnoreCase("Y"));
		}catch(Exception e){
			log.error("Failed to retrive status for: " + username);
			return false;
		}
		
	}
	
	public void logSuccessfulLogin(String username){
		
		try{
			mainDao.updateLastLogin(username);
		}catch(Exception e){
			log.error("Failed to log successful login for: " + username);
		}
		
	}
	
	public void logFailedLogin(String username){
		Integer failCount = 0;
		HashMap<String,String> pmParams = new HashMap<String,String>(2);
		
		try{
			mainDao.logFailedLogin(username);
			failCount = mainDao.getFailedCount(username);
			if(failCount >= maxLogin ){
				pmParams.put("status", "N");
				pmParams.put("username", username);
				mainDao.updateMemberStatus(pmParams);
			}
		}catch(Exception e){
			log.error("Failed to log failed login for: " + username);
		}
		
	}

	public void clearFailedLogin(String username){
	
		try{
			mainDao.clearFailedLogin(username);
		}catch(Exception e){
			log.error("Failed to clear failed login for: " + username);
		}
	}
	
	/*
	 * Generate a password with default security standards set in app.properties
	 */
	public String generatePassword(String psPlainText) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(passSec);
		String hashedPassword = encoder.encode(psPlainText);
		return hashedPassword;
		
	}

	/*
	 * Generate a password with above standard password standards
	 */
	public String generatePassword(String psPlainText, int piStrength) {
		
		if( piStrength < passSec){
			log.warn("Attempting to set password stength below standards. Using default settings instead.");
			piStrength = passSec;
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(passSec);
		String hashedPassword = encoder.encode(psPlainText);
		return hashedPassword;
		
	}

}
