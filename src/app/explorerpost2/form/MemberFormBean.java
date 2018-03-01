package app.explorerpost2.form;

public class MemberFormBean {
	String username = null;
	String password = null;
	String passwordCnfm = null;
	String passwordEnc= null;
	String email = null;
	String phoneNumber = null;
	String carrier = null;
	String phoneNumberFull = null;
	String appRole = null;
	String memRole = null;
	String notifyPref = null;
	String status = null;
	String audtUserId = null;
	
	public MemberFormBean(){
		
	}

	public MemberFormBean(String username, String password, String passwordCnfm, String passwordEnc,
			String email, String phoneNumber, String carrier, String appRole,
			String memRole, String notifyPref, String status) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCnfm = passwordCnfm;
		this.passwordEnc = passwordEnc;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.carrier = carrier;
		this.appRole = appRole;
		this.memRole = memRole;
		this.notifyPref = notifyPref;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCnfm() {
		return passwordCnfm;
	}

	public void setPasswordCnfm(String passwordCnfm) {
		this.passwordCnfm = passwordCnfm;
	}

	public String getPasswordEnc() {
		return passwordEnc;
	}

	public void setPasswordEnc(String passwordEnc) {
		this.passwordEnc = passwordEnc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPhoneNumberFull(String psFullPhone){
		this.phoneNumberFull = psFullPhone;
	}
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getAppRole() {
		return appRole;
	}

	public void setAppRole(String appRole) {
		this.appRole = appRole;
	}

	public String getMemRole() {
		return memRole;
	}

	public void setMemRole(String memRole) {
		this.memRole = memRole;
	}

	public String getNotifyPref() {
		return notifyPref;
	}

	public void setNotifyPref(String notifyPref) {
		this.notifyPref = notifyPref;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}

	public String getAudtUserId() {
		return audtUserId;
	}

	public void setAudtUserId(String audtUserId) {
		this.audtUserId = audtUserId;
	}

	@Override
	public String toString() {
		return "MemberFormBean [username=" + username + ", password="
				+ password + ", passwordCnfm=" + passwordCnfm 
				+ ", passwordEnc=" + passwordEnc + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", carrier="
				+ carrier + ", appRole=" + appRole + ", memRole=" + memRole
				+ ", notifyPref=" + notifyPref + "]";
	}
	

	
	
	
}
