package continuitE;

import java.util.Date;

public class StoryDay {
	private int intStoryDay = 1;
	private String strSummary = "dftSummary";
	private Date date = new Date();
	private boolean dateSet = false;
	
	public int getIntStoryDay() {
		return intStoryDay;
	}
	public void setIntStoryDay(int intStoryDay) {
		this.intStoryDay = intStoryDay;
	}
	public String getStrSummary() {
		return strSummary;
	}
	public void setStrSummary(String strSummary) {
		this.strSummary = strSummary;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDateSet() {
		return dateSet;
	}
	public void setDateSet(boolean dateSet) {
		this.dateSet = dateSet;
	}
	public StoryDay(){
		this.intStoryDay = 1;
		this.strSummary = "No summary yet";
		this.date = new Date();
		this.dateSet = false;
	}
	public StoryDay(int intStoryDay){
		this.intStoryDay = intStoryDay;
		this.strSummary = "No summary yet";
		this.date = new Date();
		this.dateSet = false;
	}
}
