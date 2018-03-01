package app.explorerpost2.bean;

import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;

public class EventBean {
	private Integer eventId = null;
	private String startDate = null;
	private String startTime = null;
	private String endDate = null;
	private String endTime = null;
	private String eventName = null;
	private String eventLocation = null;
	private Integer eventGroup = null;
	private Integer eventStatus = null;
	private String eventNote = null;
	private String eventReminderSent = null;
	private String audtCrId = null;
	private Date audtCrDateTime = null;
	private String eventReminder = null;
	
	private String userId = null;
	private String responseCd = null;
	private String attendanceTypeCd = null;
	
	//you need to do this because SimpleDateFormat is not thread safe
	private static final ThreadLocal<SimpleDateFormat> yyyyMMdd = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> HHmm = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("HH:mm");
        }
    };

	
	public EventBean(){
		
	}
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public Integer getEventGroup() {
		return eventGroup;
	}
	public void setEventGroup(Integer eventGroup) {
		this.eventGroup = eventGroup;
	}
	public Integer getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getEventNote() {
		return eventNote;
	}
	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}
	public String getEventReminderSent() {
		return eventReminderSent;
	}
	public void setEventReminderSent(String eventReminderSent) {
		this.eventReminderSent = eventReminderSent;
	}
	public String getAudtCrId() {
		return audtCrId;
	}
	public void setAudtCrId(String audtCrId) {
		this.audtCrId = audtCrId;
	}
	public Date getAudtCrDateTime() {
		return audtCrDateTime;
	}
	public void setAudtCrDateTime(Date audtCrDateTime) {
		this.audtCrDateTime = audtCrDateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResponseCd() {
		return responseCd;
	}

	public void setResponseCd(String responseCd) {
		this.responseCd = responseCd;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAttendanceTypeCd() {
		return attendanceTypeCd;
	}

	public void setAttendanceTypeCd(String attendanceTypeCd) {
		this.attendanceTypeCd = attendanceTypeCd;
	}

	public String getEventReminder() {
		return eventReminder;
	}

	public void setEventReminder(String eventReminder) {
		this.eventReminder = eventReminder;
	}
	
	
	
}
