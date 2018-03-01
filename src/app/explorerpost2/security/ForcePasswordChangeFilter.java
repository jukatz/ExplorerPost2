package app.explorerpost2.security;

import java.io.IOException;

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

public class ForcePasswordChangeFilter implements Filter{
	Log LOG = LogFactory.getLog(SecurityFilter.class);
	
	@Override
	public void doFilter(ServletRequest rqst, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest loRequest = (HttpServletRequest)rqst;
		HttpServletResponse loResponse = (HttpServletResponse)resp;
		HttpSession loSession = loRequest.getSession();
		String lsUri = loRequest.getRequestURI();
		String method = loRequest.getMethod();
		String path = loRequest.getServletPath();
		/*
		if (isExpired && !isUnprotected(method,path)){
    		loResponse.sendRedirect(loRequest.getContextPath() + "/password");
	     	return;
    	}else{
    		chain.doFilter(loRequest, loResponse);
    	}
    	*/
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LOG.info("init()");		
	}
	
	@Override
	public void destroy() {
		LOG.info("destroy()");
		
	}

}
