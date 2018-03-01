package app.explorerpost2.form;

public class LoginFormBean {
	String usernameEnc = null;
	String passwordEnc = null;
	String message = null;
	
	public LoginFormBean(){
		
	}
	
	public LoginFormBean(String usernameEnc, String passwordEnc) {
		this.usernameEnc = usernameEnc;
		this.passwordEnc = passwordEnc;
	}
	
	public String getUsername() {
		return usernameEnc;
	}
	public void setUsername(String usernameEnc) {
		this.usernameEnc = usernameEnc;
	}
	public String getPassword() {
		return passwordEnc;
	}
	public void setPassword(String passwordEnc) {
		this.passwordEnc = passwordEnc;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LoginFormBean [usernameEnc=" + usernameEnc + ", passwordEnc="
				+ passwordEnc + "]";
	}
	
	
}
