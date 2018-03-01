package app.explorerpost2.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.net.SocketTimeoutException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;


public class HttpUtils {
	//private static final int DEFAULT_TIMEOUT = 0; //No timeout - for testing
	private static final int DEFAULT_TIMEOUT = 30000; //30 seconds, 30000 milliseconds
	
	// -------------------------------------------------------------------------------------------
	// Private Methods
	// -------------------------------------------------------------------------------------------

	private static void addParameters( PostMethod httpMethod, Map<String,String> bodyParameters ) {
		if ( bodyParameters != null ) {
			Iterator<String> i = bodyParameters.keySet().iterator();
			while ( i.hasNext() ) {
				String key = i.next();
				String value = bodyParameters.get( key );
				httpMethod.addParameter( key, value );
			}
		}
	}
	
	private static byte[] executeMethod( HttpMethod httpMethod, int timeout ) 
		throws IOException, HttpException, SocketTimeoutException {
		byte[] result = null;
		HttpClient httpClient = new HttpClient();

		// Is providing the retry handler necessary?
		// Note, this adds the handler to the HTTP protocol parameters, not the body parameters.
		httpMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		httpMethod.getParams().setSoTimeout( timeout );
		
		// Execute the method.
		int statusCode = httpClient.executeMethod(httpMethod);
		if (statusCode != HttpStatus.SC_OK) {
			
			String respBody = httpMethod.getResponseBodyAsString();
			result = ( "Method failed: " + httpMethod.getStatusLine() + " - " + respBody).getBytes();
			
			throw new HttpException("Method failed: " + httpMethod.getStatusLine() + " - " + respBody);
		}
		else {
			// Read the response body.
			// Compiler considers getResponseBodyAsString a risky method to use.
			result = httpMethod.getResponseBody();
		}
		// Release the connection.
		if ( httpMethod != null ) {
			httpMethod.releaseConnection();
		}
		return result;
	} 

	// -------------------------------------------------------------------------------------------
	// Public Methods
	// -------------------------------------------------------------------------------------------
	
	public static byte[] httpPostAsJson( String url, String jsonString, int timeout ) 
			throws IOException, HttpException, SocketTimeoutException {
			PostMethod httpMethod = new PostMethod( url );
			
			httpMethod.setRequestEntity( new StringRequestEntity( jsonString, "application/json", "UTF-8" ) );
			//httpMethod.setRequestBody(jsonString);
			//httpMethod.setRequestEntity(requestEntity);
			//Add the parameters to the request body.
			//addParameters( httpMethod, bodyParameters );
			//Execute the method.
			return executeMethod( httpMethod, timeout );
		} 

	
	public static byte[] httpPost( String url, Map<String,String> bodyParameters, int timeout ) 
		throws IOException, HttpException, SocketTimeoutException {
		PostMethod httpMethod = new PostMethod( url );
		//Add the parameters to the request body.
		addParameters( httpMethod, bodyParameters );
		//Execute the method.
		return executeMethod( httpMethod, timeout );
	} 

	public static byte[] httpPost( String url, Map<String,String> bodyParameters ) 
		throws IOException, HttpException, SocketTimeoutException {
		return httpPost( url, bodyParameters, DEFAULT_TIMEOUT );
	}

	public static byte[] httpPostAsJson( String url, String jsonString ) 
		throws IOException, HttpException, SocketTimeoutException {
		return httpPostAsJson( url, jsonString, DEFAULT_TIMEOUT );
	}

	public static byte[] httpGet( String url ) 
		throws IOException, HttpException, SocketTimeoutException {
		GetMethod httpMethod = new GetMethod( url );
		//Execute the method.
		return executeMethod( httpMethod, DEFAULT_TIMEOUT );
	} 
	
	public static byte[] httpPut( String url ) 
		throws IOException, HttpException, SocketTimeoutException {
		PutMethod httpMethod = new PutMethod( url );
		//Execute the method.
		return executeMethod( httpMethod, DEFAULT_TIMEOUT );
	} 
		
	public static Object getObjectFromBytes( byte[] bytes ) {
		Object result = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream( bytes );
			ObjectInputStream input = new ObjectInputStream( bis );
			result = input.readObject();
			input.close();
		}
		catch( IOException ioe ) {
			ioe.printStackTrace();
			result = null;
		}
		catch( ClassNotFoundException cnfe ) {
			cnfe.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public static String getStringFromBytes( byte[] bytes ) {
		String result = "";
		result = new String(bytes);
		return result;
	}
	
	public static void showRequestParams(HttpServletRequest request){
		Enumeration params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
	}

}
