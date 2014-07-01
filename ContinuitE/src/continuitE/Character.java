package continuitE;

public class Character {
	private String strCharacterName = "dftName";
	private int intCharacterNumber = 0;
	private boolean characterUsed = false;

	public String getStrCharacterName() {
		return strCharacterName;
	}
	public void setStrCharacterName(String strCharacterName) {
		this.strCharacterName = strCharacterName;
	}
	public int getIntCharacterNumber() {
		return intCharacterNumber;
	}
	public void setIntCharacterNumber(int intCharacterNumber) {
		this.intCharacterNumber = intCharacterNumber;
	}
	public boolean isCharacterUsed() {
		return characterUsed;
	}
	public void setCharacterUsed(boolean characterUsed) {
		this.characterUsed = characterUsed;
	}
	
	public String toString(){
		return (this.intCharacterNumber + ". " + this.strCharacterName);
	}
	
	public Character(){
		this.intCharacterNumber = 0;
		this.strCharacterName = "dftName";
		this.characterUsed = false;
	}
	
	public Character(String strNewName){
		this.intCharacterNumber = 0;
		this.strCharacterName = strNewName;
		this.characterUsed = false;
	}
	public Character(int intNewNumber, String strNewName){
		this.intCharacterNumber = intNewNumber;
		this.strCharacterName = strNewName;
		this.characterUsed = true;
	}
}
