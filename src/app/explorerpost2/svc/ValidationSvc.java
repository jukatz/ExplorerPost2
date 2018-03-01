package app.explorerpost2.svc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import app.explorerpost2.bean.AttendanceBean;
import app.explorerpost2.bean.PasswordResetBean;
import app.explorerpost2.form.EmergencyContactFormBean;
import app.explorerpost2.form.EquipmentFormBean;
import app.explorerpost2.form.EventFormBean;
import app.explorerpost2.form.MemberFormBean;
import app.explorerpost2.form.PasswordResetFormBean;

@Service("validationSvc")
public class ValidationSvc {
	private Log log = LogFactory.getLog(this.getClass());
	@Resource(name="passwordSvc") private PasswordSvc passwordSvc;

	/*
	 * Methods to handle errors found
	 */
	private void handleErrorMsg(String msg, List<String> errorList){
		log.debug(msg);
		errorList.add(msg);
	}
	private String getFieldDisplayName( String fieldName ){
		//give ability to specify a "clean" display name to the user
		if(fieldName == null) return "";
		return fieldName;
	}

	protected void validateFieldErrors( Errors errors, List<String> errorList ){
		//Turn all field errors into a generic global error
		String result = null;
		String comma = "";
		Iterator<FieldError> i = errors.getFieldErrors().iterator();
		while ( i.hasNext() ){
			if ( result == null ) result = "Data type erroron field(s): ";
			FieldError fe = i.next();
			result = result + comma + getFieldDisplayName( fe.getField() );
			comma = ", ";
		}
		if ( result != null ) handleErrorMsg( result + ".", errorList );
	}

	/*
	 * 
	 * high level validation tasks
	 * 
	 */
	
	/*
	 * User validation
	 */
	public List<String> validateAddUser(Object obj, Errors errors){
		MemberFormBean mf = (MemberFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(mf.getUsername(),errorList);
		validateEmail(mf.getEmail(),errorList);
		validatePhone(mf.getPhoneNumber(),mf.getCarrier(),errorList);
		validatePasswordMatch(mf.getPassword(),mf.getPasswordCnfm(),errorList);

		return errorList;

	}
	
	public List<String> validateUpdNotifyPref(Object obj, Errors errors){
		MemberFormBean mf = (MemberFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(mf.getUsername(),errorList);
		validateEmail(mf.getEmail(),errorList);
		validatePhone(mf.getPhoneNumber(),mf.getCarrier(),errorList);

		return errorList;
	}
	
	public List<String> validateUpdRoles(Object obj, Errors errors){
		MemberFormBean mf = (MemberFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(mf.getUsername(),errorList);

		return errorList;
	}
	
	public List<String> validateUpdateStatus(Object obj, Errors errors){
		MemberFormBean mf = (MemberFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateStatus(mf.getStatus(),errorList);

		return errorList;
	}
	
	public List<String> validatePasswordReset(Object obj, Errors errors){
		PasswordResetFormBean prf = (PasswordResetFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(prf.getUsername(),errorList);
		validatePasswordMatch(prf.getPassword(),prf.getConfirm(),errorList);

		return errorList;
	}
	
	public List<String> validatePassword(Object obj, Errors errors){
		PasswordResetBean prb = (PasswordResetBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(prb.getUsername(),errorList);
		validatePassword(prb.getUsername(),prb.getPassword(),errorList);

		return errorList;
	}
	
	public List<String> validateChangePassword(Object obj, Errors errors){
		PasswordResetFormBean prf = (PasswordResetFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateUsername(prf.getUsername(),errorList);
		validatePassword(prf.getUsername(),prf.getCurrPassword(),errorList);
		validatePasswordMatch(prf.getPassword(),prf.getConfirm(),errorList);
		validateUsername(prf.getUsername(),errorList);

		return errorList;
	}
	
	/*
	 * Add/Update Event validation
	 */
	public List<String> validateAddEvent(Object obj, Errors errors){
		EventFormBean ef = (EventFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateEventTitle(ef.getNameTx(), errorList);
		validateEventLocation(ef.getLocation(),errorList);
		validateEventDateTime(ef,errorList);
		
		return errorList;
	}
	
	public List<String> validateAddEquipment(Object obj, Errors errors){
		EquipmentFormBean ef = (EquipmentFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateEquipmentId(ef.getEquipId(),errorList);
		validateEquipmentType(ef.getEquipType(),errorList);
		validateEquipmentBrand(ef.getBrand(),errorList);
		validateEquipmentModel(ef.getModel(),errorList);
		
		return errorList;
	}
	
	public List<String> validateUpdateEquipment(Object obj, Errors errors){
		EquipmentFormBean ef = (EquipmentFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateEquipmentSerial(ef.getSerial().toString(),errorList);
		validateEquipmentType(ef.getEquipType(),errorList);
		validateEquipmentBrand(ef.getBrand(),errorList);
		validateEquipmentModel(ef.getModel(),errorList);
		
		return errorList;
	}
	
	public List<String> validateAssignEquipment(Object obj, Errors errors){
		EquipmentFormBean ef = (EquipmentFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateEquipmentSerial(ef.getSerial().toString(),errorList);
		validateIdUser(ef.getUserId().toString(),errorList);
		
		return errorList;
	}
	
	public List<String> validateEmergencyContact(Object obj){
		EmergencyContactFormBean ecf = (EmergencyContactFormBean) obj;
		List<String> errorList = new ArrayList<String>();
		
		validateName(ecf.getFirstName(),ecf.getLastName(),errorList);
		validateRelation(ecf.getRelationship(),errorList);
		validatePhone(ecf.getPhone(),errorList);
		validateEmail(ecf.getEmail(),errorList);
		
		return errorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> validateAttendanceRecords(List<AttendanceBean> attendanceRecords){
		List<String> errorList = new ArrayList<String>();
		
		for(AttendanceBean attendanceRecord : attendanceRecords){
			validateUsername(attendanceRecord.getUserId(),errorList);
			validateResponse(attendanceRecord.getResponseCd(),attendanceRecord.getAttendanceTypeCd(),
					attendanceRecord.getUserId(),errorList);
			validateNotes(attendanceRecord.getNotes(),attendanceRecord.getUserId(),errorList);
		}
		
		return errorList;
	}
	

	/*
	 * 
	 * element level validation tasks
	 * 
	 */
	private void validateUsername(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlpha(value) )
			handleErrorMsg( "The username: " + value + " is invalid.", errorList );
	}
	
	private void validateStatus(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isYN(value) )
			handleErrorMsg( "The status: " + value + " is invalid.", errorList );
	}

	private void validateEmail(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		try {
			if(value == null) value = "";
			InternetAddress emailAddr = new InternetAddress(value);
			emailAddr.validate();
		} catch (AddressException ex) {
			handleErrorMsg( "The email address entered is invalid.", errorList );
		}		
	}

	private void validatePhone(String numbers, String carrier, List<String> errorList){
		numbers = StringUtils.trimToNull(numbers);
		carrier = StringUtils.trimToNull(carrier);
		if(!isNumber(numbers) || !(numbers.length() == 10) )
			handleErrorMsg( "The phone number entered is invalid.", errorList );
		
		try {
			InternetAddress emailAddr = new InternetAddress(numbers + carrier);
			emailAddr.validate();
		} catch (AddressException ex) {
			handleErrorMsg( "The phone number entered is invalid.", errorList );
		}
	}
	private void validatePhone(String numbers, List<String> errorList){
		numbers = StringUtils.trimToNull(numbers);
		Pattern p = Pattern.compile("^\\([0-9]{3}\\)-[0-9]{3}-[0-9]{4}$");
		if (numbers == null) numbers = "";
		Matcher m = p.matcher(numbers);
		if( ! m.matches()){
			handleErrorMsg( "The phone number entered is invalid. Please format like (xxx)-xxx-xxxx", errorList );
		}
	}
	
	private void validatePasswordCriteria(String password, List<String> errorList){
		password = StringUtils.trimToNull(password);
		if(!containsLower(password) || !containsUpper(password) || !containsNumber(password) || password.length() < 8)
			handleErrorMsg( "The password does not mean the minimum criteria.", errorList );
	}
	
	private void validatePasswordMatch(String password, String confirm, List<String> errorList){
		if(!password.equals(confirm))
			handleErrorMsg( "The password and confirmation do not match.", errorList );
	}
	
	private void validatePassword(String username, String password, List<String> errorList){
		if(!passwordSvc.passwordMatch(username, password))
			handleErrorMsg( "Failed to validate current credentials.", errorList );
	}
	
	private void validateEventTitle(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlphaNumericSpace(value) )
			handleErrorMsg( "The event title entered is invalid.", errorList );
	}
	private void validateEventLocation(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlphaNumericSpace(value) )
			handleErrorMsg( "The event location entered is invalid.", errorList );
	}
	private void validateEventComments(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isSafe(value) )
			handleErrorMsg( "The event comment entered is invalid. We do not accept special characters.", errorList );
	}
	
	private void validateEventDateTime(EventFormBean ef, List<String> errorList){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start = null;
		Date end = null;
		try {
			start = formatter.parse(ef.getStartDt() + " " + ef.getStartTm());
			end = formatter.parse(ef.getEndDt() + " " + ef.getEndTm());
			if(start.compareTo(end) > 0)
				handleErrorMsg( "The start date time is after the end date time.", errorList );
		} catch (ParseException e) {
			handleErrorMsg("There is an issue formatting your start or end date/time", errorList);
		}
		
		
			
	}
	
	private void validateRelation(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlphaWhite(value) )
			handleErrorMsg( "The relationship entered is invalid.", errorList );
	}
	
	private void validateName(String first, String last, List<String> errorList){
		first = StringUtils.trimToNull(first);
		last = StringUtils.trimToNull(last);
		if( isNullEmpty(first) || !isAlpha(first) )
			handleErrorMsg( "The first name entered is invalid.", errorList );
		if( isNullEmpty(last) || !isAlpha(last) )
			handleErrorMsg( "The last name entered is invalid.", errorList );
	}
	
	private void validateResponse(String response, String type, String user, List<String> errorList){
		response = StringUtils.trimToNull(response);
		type = StringUtils.trimToNull(type);
		
		if( isNullEmpty(response) || !isResponseCode(response) )
			handleErrorMsg( "The response for user: " + user + " is invalid.", errorList );
		if( isNullEmpty(type) || !isResponseType(type) )
			handleErrorMsg( "The response type for user: " + user + " is invalid.", errorList );
		
	}
	
	private void validateNotes(String notes, String user, List<String> errorList){
		notes = StringUtils.trimToNull(notes);
		if(notes == null)return;
		
		if( !isSafe(notes) )
			handleErrorMsg( "The notes for user: " + user + " are invalid.", errorList );
	}
	
	private void validateEquipmentId(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlphaNumeric(value) )
			handleErrorMsg( "The equipment Id entered is invalid.", errorList );
	}
	
	private void validateEquipmentType(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isAlpha(value) )
			handleErrorMsg( "The equipment type entered is invalid.", errorList );
	}
	
	private void validateEquipmentBrand(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isSafe(value) )
			handleErrorMsg( "The equipment brand entered is invalid.", errorList );
	}
	
	private void validateEquipmentModel(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isSafe(value) )
			handleErrorMsg( "The equipment model entered is invalid.", errorList );
	}
	
	private void validateEquipmentSerial(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isNumber(value) )
			handleErrorMsg( "The equipment id entered is invalid.", errorList );
	}
	
	private void validateIdUser(String value, List<String> errorList){
		value = StringUtils.trimToNull(value);
		if( isNullEmpty(value) || !isNumber(value) )
			handleErrorMsg( "The user id entered is invalid.", errorList );
	}
	
	/*
	 * 
	 * value level validation tasks
	 * 
	 */
	private boolean isNullEmpty(String value){
		value = StringUtils.trimToNull(value);
		if( value == null || value.equals("") || value.equals(" "))
			return true;

		return false;
	}

	private boolean isAlpha(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[a-zA-Z-]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isAlphaWhite(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[a-zA-Z- ]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isResponseCode(String value){
		value = StringUtils.trimToNull(value);

		return value.equalsIgnoreCase("A") || value.equalsIgnoreCase("P") || value.equalsIgnoreCase("E");
	}
	
	private boolean isResponseType(String value){
		value = StringUtils.trimToNull(value);

		return value.equalsIgnoreCase("U") || value.equalsIgnoreCase("X");
	}
	
	private boolean isNumber(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[0-9\\-]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isAlphaNumericSpace(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[A-Za-z0-9 ]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isAlphaNumeric(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[A-Za-z0-9]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isSafe(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[A-Za-z0-9  -_,.]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	private boolean isYN(String value){
		value = StringUtils.trimToNull(value);
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[YN]+", value.substring(i,i+1)) == false)
				return false;
		}

		return true;
	}
	
	/*
	 * 
	 * checks to see if value contains a pattern
	 * 
	 */
	private boolean containsLower(String value){
		value = StringUtils.trimToNull(value);
		int count = 0;
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[a-z]+", value.substring(i,i+1)) )
				count ++;
		}

		return (count > 0);
	}
	
	private boolean containsUpper(String value){
		value = StringUtils.trimToNull(value);
		int count = 0;
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[A-Z]+", value.substring(i,i+1)) )
				count ++;
		}

		return (count > 0);
	}
	
	private boolean containsNumber(String value){
		value = StringUtils.trimToNull(value);
		int count = 0;
		for(int i=0; i<value.length(); i++){
			if( Pattern.matches("[0-9]+", value.substring(i,i+1)) )
				count ++;
		}

		return (count > 0);
	}

}
