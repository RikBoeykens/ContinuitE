package continuitE;

import java.util.ArrayList;

public class Scene {
	private String strSceneNumber = "dftScNumber";
	private int intLocation = 0;
	private String strSceneIntro = "dftIntro";
	private String strTimeofDay = "dftTimeofDay";
	private String strDescription = "No description";
	private double dblLength = 0.0f;
	private int intTiming = 0;
	private String strContent = "dftContent";
	private ArrayList <Integer> intCharacters = new ArrayList <Integer>();
	private String strNotes = "dftNotes";
	private int intStoryDay = 0;
	private int intStoryTime = 0;
	private boolean omitted = false;
	
	public String getStrSceneNumber() {
		return strSceneNumber;
	}
	public void setStrSceneNumber(String strSceneNumber) {
		this.strSceneNumber = strSceneNumber;
	}
	public void setIntLocation(int intLocation) {
		this.intLocation = intLocation;
	}
	public int getIntLocation() {
		return intLocation;
	}
	public String getStrSceneIntro() {
		return strSceneIntro;
	}
	public void setStrSceneIntro(String strSceneIntro) {
		this.strSceneIntro = strSceneIntro;
	}
	public String getStrTimeofDay() {
		return strTimeofDay;
	}
	public String getStrDescription() {
		return strDescription;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public void setStrTimeofDay(String strTimeofDay) {
		this.strTimeofDay = strTimeofDay;
	}
	public double getDblLength() {
		return dblLength;
	}
	public void setDblLength(double dblLength) {
		this.dblLength = dblLength;
	}
	public int getIntTiming() {
		return intTiming;
	}
	public void setIntTiming(int intTiming) {
		this.intTiming = intTiming;
	}
	public void setIntStoryTime(int intStoryTime) {
		this.intStoryTime = intStoryTime;
	}
	public String getStrContent() {
		return strContent;
	}
	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}
	public ArrayList<Integer> getIntCharacters() {
		return intCharacters;
	}
	public void setIntCharacters(ArrayList<Integer> intCharacters) {
		this.intCharacters = intCharacters;
	}
	public String getStrNotes() {
		return strNotes;
	}
	public void setStrNotes(String strNotes) {
		this.strNotes = strNotes;
	}
	public boolean isOmitted() {
		return omitted;
	}
	public void setOmitted(boolean omitted) {
		this.omitted = omitted;
	}
	public int getIntStoryDay() {
		return intStoryDay;
	}
	public void setIntStoryDay(int intStoryDay) {
		this.intStoryDay = intStoryDay;
	}
	public int getIntStoryTime() {
		return intStoryTime;
	}
	public void setIntHour(int intStoryTime) {
		this.intStoryTime = intStoryTime;
	}
	public Scene(){
		this.strSceneNumber= "dftSceneNumber";
		this.strSceneIntro = "dftIntro";
		this.intLocation = 0;
		this.strTimeofDay = "dftTimeofDay";
		this.strDescription = "No description yet";
		this.dblLength = 0.0f;
		this.intTiming = 0;
		this.strContent = "";
		this.intCharacters = new ArrayList<Integer>();
		this.strNotes = "dftNotes";
		this.omitted = false;
		this.intStoryDay = 0;
		this.intStoryTime = 0;
	}
	
	public Scene(String strNewSceneNumber, String strNewSceneIntro, int intNewLocation, String strNewTimeofDay, String strNewDescription, double dblNewLength, int intNewTiming, 
			String strNewContent, ArrayList<Integer> intNewCharacters, String strNewNotes, boolean NewOmitted, int intNewStoryDay, int intNewStoryTime){
		this.strSceneNumber= strNewSceneNumber;
		this.strSceneIntro = strNewSceneIntro;
		this.intLocation = intNewLocation;
		this.strTimeofDay = strNewTimeofDay;
		this.strDescription = strNewDescription;
		this.dblLength = dblNewLength;
		this.intTiming = intNewTiming;
		this.strContent = strNewContent;
		this.intCharacters = intNewCharacters;
		this.strNotes = strNewNotes;
		this.omitted = NewOmitted;
		this.intStoryDay = intNewStoryDay;
		this.intStoryTime = intNewStoryTime;
	}
	
}
