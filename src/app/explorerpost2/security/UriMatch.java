package app.explorerpost2.security;

public class UriMatch {
	private String method;
	private String regexPath;
	
	
	public UriMatch(String method, String regexPath) {
		this.method = method;
		this.regexPath = regexPath;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRegexPath() {
		return regexPath;
	}
	public void setRegexPath(String regexPath) {
		this.regexPath = regexPath;
	}

}
