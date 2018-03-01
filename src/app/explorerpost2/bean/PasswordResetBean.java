package app.explorerpost2.bean;

public class PasswordResetBean{
	private String username = null;
	private String currPassword = null;
	private String password = null;
	private String confirm = null;
	private Integer expiresInDays = null;
	private String hashed = null;
	private String updId = null;
	
	public PasswordResetBean(){}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrPassword() {
		return currPassword;
	}

	public void setCurrPassword(String currPassword) {
		this.currPassword = currPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Integer getExpiresInDays() {
		return expiresInDays;
	}

	public void setExpiresInDays(Integer expiresInDays) {
		this.expiresInDays = expiresInDays;
	}

	public String getHashed() {
		return hashed;
	}

	public void setHashed(String hashed) {
		this.hashed = hashed;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	
	
}
