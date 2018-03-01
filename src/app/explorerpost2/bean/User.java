package app.explorerpost2.bean;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;


/**
 * This class was moved out of NewtonCoreJAR so it could be used in a Spring 3 project.
 */
@SuppressWarnings( "serial" )
public class User implements Serializable {
	private String msUserName;
	
	public User() {
	}
	
	public User(String username){
		this.msUserName = StringUtils.trimToNull(username);
	}
	

	
	/**
	 * Returns the set username of the user.
	 * @return
	 */
	public String getUserName() {
		return msUserName;
	}


}
