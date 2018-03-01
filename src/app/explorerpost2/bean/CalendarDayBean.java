package app.explorerpost2.bean;

//import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarDayBean{
	private Date eventDate = null;
	private Integer number = null;
	private String badgeClass = "badge-error";
	//private final SimpleDateFormat formatmmddyyyy = new SimpleDateFormat("MM-dd-yyyy");
	private List<EventBean> dayEvents = null;
	
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
	
	public CalendarDayBean(){
		
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	public String getEventDateString(){

		return yyyyMMdd.get().format(eventDate);
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Integer getNumber() {
		return number;
	}
	public void setEventCount(Integer number) {
		this.number = number;
	}
	public List<EventBean> getEvents() {
		return dayEvents;
	}
	public void setEvents(List<EventBean> events) {
		this.dayEvents = events;
	}
	
	public void setBadgeClass(String badgeClass){
		this.badgeClass = badgeClass;
	}
	public String getBadgeClass(){
		return badgeClass;
	}
	
	
}
