package app.explorerpost2.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import app.explorerpost2.bean.User;
import app.explorerpost2.svc.SessionSvc;


public class SecurityFilter implements Filter {
	Log LOG = LogFactory.getLog(SecurityFilter.class);
	private List<UriMatch> unprotected = new ArrayList<UriMatch>();
	
	public void init(FilterConfig arg0) throws ServletException {
		LOG.info("init()");
		
		unprotected.add(new UriMatch("GET","/index.jsp"));
		unprotected.add(new UriMatch("GET","/login"));
		unprotected.add(new UriMatch("GET","/css/"));
		unprotected.add(new UriMatch("GET","/scripts/"));
		unprotected.add(new UriMatch("GET","/bootstrap/"));
		unprotected.add(new UriMatch("GET","/clockpicker/"));
		unprotected.add(new UriMatch("GET","/datepicker/"));
		unprotected.add(new UriMatch("GET","/images/"));
	}
	
	private boolean isUnprotected(String method, String path){
		boolean match = false;
		Iterator<UriMatch> i = unprotected.iterator();
		while(method != null && path != null && i.hasNext() && !match){
			UriMatch u = i.next();
			match = (method.equalsIgnoreCase(u.getMethod()) && path.indexOf(u.getRegexPath()) != -1);
		}
		return match;
	}

	public void doFilter(ServletRequest rqst, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest loRequest = (HttpServletRequest)rqst;
		HttpServletResponse loResponse = (HttpServletResponse)resp;
		HttpSession loSession = loRequest.getSession();
		String lsUri = loRequest.getRequestURI();
		String method = loRequest.getMethod();
		String path = loRequest.getServletPath();
		boolean isExpired = true;
		
		
		User loUSER = SessionSvc.getUser(loRequest);
		if(loUSER == null) {
			
			if((lsUri.indexOf("forwardLogin") != -1) || (lsUri.indexOf("login") != -1)) {
				loUSER = new User();
			}
		}
		if ((loSession == null || loUSER == null) && !isUnprotected(method,path) ) {
			//log.info("The session no longer exists");
	     	loResponse.sendRedirect(loRequest.getContextPath() + "/login");
	     	return;
	    } 
	    else {
	    	chain.doFilter(loRequest, loResponse);
	    }
	}

	public void destroy() {
		LOG.info("destroy()");
	}

}
