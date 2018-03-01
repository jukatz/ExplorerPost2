package app.explorerpost2.svc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.explorerpost2.bean.PersonBean;
import app.explorerpost2.bean.User;


public class SessionSvc {
	public static final String USER = "user";
	public static final String JAVASCRIPT_VERSION = "jsVersion";
	public static final String APP_VERSION = "appVersion";
	public static final String APP_RELEASE = "appRelease";
	public static final String OK_CLEAR = "true";
	
	public static final String MSG_INFO = "msgInfo";
	public static final String MSG_SUCCESS = "msgSuccess";
	public static final String MSG_WARNING = "msgWarning";
	public static final String MSG_DANGER = "msgDanger";
	
	public static final String MSG_INFO_TITLE = "msgInfoTitle";
	public static final String MSG_SUCCESS_TITLE = "msgSuccessTitle";
	public static final String MSG_WARNING_TITLE = "msgWarningTitle";
	public static final String MSG_DANGER_TITLE = "msgDangerTitle";
	
	public static final String EDIT_ALLOWED = "editAllowed";
	
	//--------------------------------------------------------------------------------------------
	//Used for examples. Not really needed for all apps
	//--------------------------------------------------------------------------------------------
	public static final String PEOPLE_LIST = "peopleList";
	
	public static void storePeopleList( HttpServletRequest request, List<PersonBean> peopleList)
	{
		storeValue( request, PEOPLE_LIST, peopleList );
	}
	
	@SuppressWarnings("unchecked")
	public static List<PersonBean> getPeopleList(HttpServletRequest request)
	{
		return (List<PersonBean>) getValue( request, PEOPLE_LIST );
	}
	
	//--------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------
	
	
	
	//--------------------------------------------------------------------------------------------
	// Constructor - Make private.  Classes should use SessionSvc statically.
	//--------------------------------------------------------------------------------------------
	
	private SessionSvc() {
	}

	//--------------------------------------------------------------------------------------------
	// Private Methods
	//--------------------------------------------------------------------------------------------
	
	private static void storeValue( HttpServletRequest request, String key, Object value ) {
		HttpSession session = request.getSession();
		session.setAttribute( key, value );
	}

	private static Object getValue( HttpServletRequest request, String key ) {
		HttpSession session = request.getSession();
		return session.getAttribute( key );
	}

	//--------------------------------------------------------------------------------------------
	// Util Methods
	//--------------------------------------------------------------------------------------------
	
	/**
	 * Clear application data in the session but NOT the user.
	 */
	public static HttpSession clearApp( HttpServletRequest request ) {
		HttpSession session = request.getSession();
		session.removeAttribute(PEOPLE_LIST);
		return session;
	}
	
	/**
	 * Clear application data in the session AND the user.
	 */
	public static void clearAll( HttpServletRequest request ) {
		HttpSession session = clearApp( request );
		session.removeAttribute( USER );
	}
	
	public static void clearValue(HttpServletRequest request, String key)
	{
		HttpSession session = request.getSession();
		session.removeAttribute(key);		
	}

	//--------------------------------------------------------------------------------------------
	// User Methods
	//--------------------------------------------------------------------------------------------
	
	public static User getUser( HttpServletRequest request ) {
		return (User) getValue( request, USER );
	}

	public static String getUserName( HttpServletRequest request ) {
		User user = getUser( request );
		return ( user == null ) ? null : user.getUserName();
	}

	public static void storeUser( HttpServletRequest request, User user ) {
		storeValue( request, USER, user );
	}

	//--------------------------------------------------------------------------------------------
	// Other Methods
	//--------------------------------------------------------------------------------------------
	
	public static boolean okayToClearMessages(HttpServletRequest request){
		Object obj = getValue( request, OK_CLEAR ); 
		boolean ok = (obj == null)? true : obj.toString().equalsIgnoreCase("true");
		
		return ok;
	}
	
	public static void setOkayToClearMessages(HttpServletRequest request, boolean ok){
		storeValue( request, OK_CLEAR, ok );
	}
	
	public static void storeAppRelease( HttpServletRequest request, String release ) {
		storeValue( request, APP_RELEASE, release );
	}

	public static void storeAppVersion( HttpServletRequest request, String version ) {
		storeValue( request, APP_VERSION, version );
	}

	public static void storeJavascriptVersion( HttpServletRequest request, String version ) {
		storeValue( request, JAVASCRIPT_VERSION, version );
	}

	public static void clearMessages(HttpServletRequest request)
	{
		clearValue(request, MSG_INFO);
		clearValue(request, MSG_SUCCESS);
		clearValue(request, MSG_WARNING);
		clearValue(request, MSG_DANGER);
		clearValue(request, MSG_INFO_TITLE);
		clearValue(request, MSG_SUCCESS_TITLE);
		clearValue(request, MSG_WARNING_TITLE);
		clearValue(request, MSG_DANGER_TITLE);
	}
	
	public static void storeInfoMessage( HttpServletRequest request, String title, String msg ) {
		storeValue( request, MSG_INFO_TITLE, title );
		storeValue( request, MSG_INFO, msg );
	}
	
	public static void storeSuccessMessage( HttpServletRequest request, String title, String msg ) {
		storeValue( request, MSG_SUCCESS_TITLE, title );
		storeValue( request, MSG_SUCCESS, msg );
	}
	
	public static void storeWarningMessage( HttpServletRequest request, String title, String msg ) {
		storeValue( request, MSG_WARNING_TITLE, title );
		storeValue( request, MSG_WARNING, msg );
	}
	
	public static void storeDangerMessage( HttpServletRequest request, String title, String msg ) {
		storeValue( request, MSG_DANGER_TITLE, title );
		storeValue( request, MSG_DANGER, msg );
	}
	
	public static void storeEditAllowed( HttpServletRequest request, String editAllowed )
	{
		storeValue( request, EDIT_ALLOWED, editAllowed );
	}
	
	public static void clearEditAllowed( HttpServletRequest request)
	{
		clearValue( request, EDIT_ALLOWED);
	}
}
