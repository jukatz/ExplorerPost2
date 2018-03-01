package app.explorerpost2.bean;

import org.apache.commons.lang.StringUtils;

public class AttendanceBean {
	String eventId = null;
	String userId = null;
	String responseCd = null;
	String attendanceTypeCd = null;
	String notes = null;
	String audtUserId = null;
	
	public AttendanceBean(){
		
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = StringUtils.trimToNull(eventId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = StringUtils.trimToNull(userId);
	}

	public String getResponseCd() {
		return responseCd;
	}

	public void setResponseCd(String responseCd) {
		this.responseCd = StringUtils.trimToNull(responseCd);
	}

	public String getAttendanceTypeCd() {
		return attendanceTypeCd;
	}

	public void setAttendanceTypeCd(String attendanceTypeCd) {
		this.attendanceTypeCd = StringUtils.trimToNull(attendanceTypeCd);
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAudtUserId() {
		return audtUserId;
	}

	public void setAudtUserId(String audtUserId) {
		this.audtUserId = StringUtils.trimToNull(audtUserId);
	}

	
	
}
