package app.explorerpost2.form;

public class EventFormBean {
	Integer eventId = null;
	String nameTx = null;
	String startDt = null;
	String startTm = null;
	String endDt = null;
	String endTm = null;
	String location = null;
	Integer group = null;
	Integer status = null;
	String reminder = null;
	String notes = null;
	String reminderSent = null;
	String audtCrId = null;
	String audtUpdId = null;
	String attendance = null;
	
	public EventFormBean(){
		
	}
	
	public void resetObject(){
		this.eventId = null;
		this.nameTx = null;
		this.startDt = null;
		this.startTm = null;
		this.endDt = null;
		this.endTm = null;
		this.location = null;
		this.group = null;
		this.status = null;
		this.reminder = null;
		this.notes = null;
		this.reminderSent = null;
		this.audtCrId = null;
		this.audtUpdId = null;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getNameTx() {
		return nameTx;
	}

	public void setNameTx(String nameTx) {
		this.nameTx = nameTx;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getStartTm() {
		return startTm;
	}

	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getEndTm() {
		return endTm;
	}

	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReminderSent() {
		return reminderSent;
	}

	public void setReminderSent(String reminderSent) {
		this.reminderSent = reminderSent;
	}

	public String getAudtCrId() {
		return audtCrId;
	}

	public void setAudtCrId(String audtCrId) {
		this.audtCrId = audtCrId;
	}

	public String getAudtUpdId() {
		return audtUpdId;
	}

	public void setAudtUpdId(String audtUpdId) {
		this.audtUpdId = audtUpdId;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
	
}
