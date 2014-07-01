package continuitE;

public class Location {
	private String strLocationName = "dftLocation";
	private boolean locationUsed = false;
	public String getStrLocationName() {
		return strLocationName;
	}
	public void setStrLocationName(String strLocationName) {
		this.strLocationName = strLocationName;
	}
	public boolean isLocationUsed() {
		return locationUsed;
	}
	public void setLocationUsed(boolean locationUsed) {
		this.locationUsed = locationUsed;
	}
	
	public Location(){
		this.strLocationName = "dftLocation";
		this.locationUsed = false;
	}
	
	public Location(String strNewLocation){
		this.strLocationName = strNewLocation;
		this.locationUsed = false;
	}
}
