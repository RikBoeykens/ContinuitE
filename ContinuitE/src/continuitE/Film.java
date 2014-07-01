package continuitE;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Film {
	private FilmInfo info = new FilmInfo();
	private ArrayList <Scene> scenes = new ArrayList<Scene>();
	private ArrayList <Location> locations = new ArrayList<Location>();
	private ArrayList <Character> characters = new ArrayList<Character>();
	private ArrayList <StoryDay> storydays = new ArrayList<StoryDay>();
	
	private ArrayList <String> strSceneIntros = new ArrayList<String>();
	private ArrayList <String> strTimesofDay = new ArrayList<String>();
	
	private String strIntroSeparator = "dftIntroSeparator";
	private String strTimeSeparator = "dftTimeSeparator";
	
	private int intCharacterNumber = 0;

	public FilmInfo getInfo() {
		return info;
	}
	public ArrayList<Scene> getScenes() {
		return scenes;
	}
	public void setScenes(ArrayList<Scene> scenes) {
		this.scenes = scenes;
	}
	public ArrayList<Location> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	public String showAllUsedLocations(){
		StringBuilder sb = new StringBuilder();
		String output = "";
		boolean locationsAreUsed = false;
		for (int i = 0; i<this.locations.size(); i++){
			if (this.locations.get(i).isLocationUsed()){
				sb.append(this.locations.get(i).getStrLocationName() + "\n");
				locationsAreUsed = true;
			}
			if (locationsAreUsed){
				output = sb.toString();
			}
			else{
				output = "no locations used";
			}
		}
		return output;
	}
	public String showLocation(int intLocationIndex){
		return this.locations.get(intLocationIndex).getStrLocationName();
	}
	public ArrayList<Character> getCharacters() {
		return characters;
	}
	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}
	public String showCharacter(int characterNumber){
		return (this.characters.get(characterNumber).getIntCharacterNumber() + ". " + this.characters.get(characterNumber).getStrCharacterName());
	}
	public String showAllUsedCharacters(){
//		StringBuilder sb = new StringBuilder();
//		String output = "";
//		boolean charactersAreUsed = false;
//		for (int i = 0; i<this.characters.size(); i++){
//			if (this.characters.get(i).isCharacterUsed()){
//				sb.append(showCharacter(i) + "\n");
//				charactersAreUsed = true;
//			}
//		}
//		if (charactersAreUsed){
//			output = sb.toString();
//		}
//		else{
//			output = "no characters used";
//		}
//			
//		return output;
		StringBuilder sb = new StringBuilder();
		String output;
		ArrayList <Character> characterstoSort = new ArrayList<Character>();
		for (int i = 0; i<this.characters.size();i++){
			if (this.characters.get(i).isCharacterUsed()){
				characterstoSort.add(this.characters.get(i));
			}
		}
		this.sortCharacterArray(characterstoSort);
		while(characterstoSort.size()!=0){
			sb.append(characterstoSort.get(0).getIntCharacterNumber() + ". " + characterstoSort.get(0).getStrCharacterName() + "\n");			
			characterstoSort.remove(0);
		}
		
		if (sb.toString().equals("")){
			output = "no characters";
		}
		else{
			output = sb.toString();
		}
		return output;
	}
	public ArrayList<String> getStrSceneIntros() {
		return strSceneIntros;
	}
	public void setStrSceneIntros(ArrayList<String> strSceneIntros) {
		this.strSceneIntros = strSceneIntros;
	}
	public ArrayList<String> getStrTimesofDay() {
		return strTimesofDay;
	}
	public void setStrTimesofDay(ArrayList<String> strTimesofDay) {
		this.strTimesofDay = strTimesofDay;
	}
	public String getStrIntroSeparator() {
		return strIntroSeparator;
	}
	public void setStrIntroSeparator(String strIntroSeparator) {
		this.strIntroSeparator = strIntroSeparator;
	}
	public String getStrTimeSeparator() {
		return strTimeSeparator;
	}
	public void setStrTimeSeparator(String strTimeSeparator) {
		this.strTimeSeparator = strTimeSeparator;
	}
	public ArrayList<StoryDay> getStorydays() {
		return storydays;
	}
	public void setStorydays(ArrayList<StoryDay> storydays) {
		this.storydays = storydays;
	}
	public void readFile(String strPath){
		//first read to read locations, characters, times of day and scene intros
		//initialize bufferedreader
		BufferedReader br = null;
		//declare booleans
		boolean locations = false;
		boolean characters = false;
		boolean timesofday = false;
		boolean sceneintros = false;
		
		//open try catch
		try{
			//declares variable line
			String line;
			//loads the text file in br
			br = new BufferedReader(new FileReader(strPath));
			
			//gets the next line and checks if there is one
			while ((line=br.readLine())!=null){
				//closes reading for locations, characters, sceneintros and times of day
				if (line.equals("    </Locations>")){
					locations=false;
				}
				if (line.equals("    </Characters>")){
					characters= false;
				}
				if (line.equals("    </SceneIntros>")){
					sceneintros = false;
				}
				if (line.equals("    </TimesOfDay>")){
					timesofday = false;
				}
				//reads all locations, characters, sceneintros and times of day depending on what is open
				if (locations){
					String strInput = line.substring(16, (line.length()-11));
					Location aLocation = new Location(strInput);
					this.locations.add(aLocation);
				}
				if (characters){
					String strInput = line.substring(17, (line.length()-12));
					Character aCharacter = new Character(strInput);
					this.characters.add(aCharacter);
				}
				if (sceneintros){
					String strInput = line.substring(18, line.length()-13);
					this.strSceneIntros.add(strInput);
				}
				if (timesofday){
					String strInput = line.substring(17, line.length()-12);
					this.strTimesofDay.add(strInput);
				}
				//open reading of locations, characters, sceneintros and times of day + get separators
				if (line.equals("    <Locations>")){
					locations=true;
				}
				if (line.equals("    <Characters>")){
					characters = true;
				}
				if (line.contains("    <SceneIntros Separator=")){
					this.strIntroSeparator = line.substring(28, (line.length()-2));
					sceneintros = true;
				}
				if (line.contains("    <TimesOfDay Separator=")){
					this.strTimeSeparator = line.substring(27, (line.length()-2));
					timesofday = true;
				}

				
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		//closes bufferedreader even if there was an error
		finally{
			try{
				if(br!=null)
					br.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		//after this we have all the possible locations, characters, scene intros, times of day and the separators. With this information we can read the content of the script
		//we create several booleans to differentiate between what we're reading. readingScript is the overall way of reading the script
		boolean readingScript = true;
		boolean readingHeading = false;
		boolean readingDialogue = false;
		boolean readingAction = false;
		boolean readingCharacter = false;
		boolean readingParenthetical = false;
		
		int intScene = -1;
		
		StringBuilder sb = new StringBuilder();
		
		try{
			//declares variable line
			String line;
			//loads the text file in br
			br = new BufferedReader(new FileReader(strPath));
			
			//gets the next line and checks if there is one
			while ((line=br.readLine())!=null&&readingScript){
				//closes booleans
				if (line.equals("    </Paragraph>")){
					readingHeading = false;
					readingDialogue = false;
					readingAction = false;
					readingCharacter = false;
					readingParenthetical = false;
				}
				//reads the script according to which boolean is active
				if (readingHeading){
					//this reads the scene heading including  length, scene intro, location and time of day
					//check for scene length in first line under scene heading
					if (line.contains("      <SceneProperties Length")){
						String [] strScLength =  line.substring(31).split("\"");
						this.scenes.get(intScene).setDblLength(this.convertLengthStringtoDouble(strScLength[0]));
					}
					//checks for scene intro, location, time of day
					if (line.contains("      <Text")){
						//checks for the conventional setup of int/ext. location - day/night
						if (line.contains(this.strIntroSeparator)&&line.contains(this.strTimeSeparator)){
							//this works by getting the scene intro and the time of day, then getting the location as whatever is in between them
							//get starting index of intro
							int intIntroStart =  line.indexOf(">") + 1;
							//get temporary values of locationstart and locationend (in case scene intro or time of day can't be found)
							//the reason these are not taken as the actual ones is because the intro might have the intro separator or the time of day might have the time separator messing this up
							int intLocationStart = line.indexOf(this.strIntroSeparator, intIntroStart) + this.strIntroSeparator.length();
							int intLocationEnd = line.lastIndexOf(this.strTimeSeparator);
							//time of day end is determined by going back 7 spaces (</text>)
							int intTimeofDayEnd = line.length()-7;
							
							//get intro
							for (int i = 0; i<this.strSceneIntros.size();i++){
								//check if the later substring will not go out of bounds
								if (intIntroStart + this.strSceneIntros.get(i).length()<line.length()){
									int intIntroEnd = intIntroStart + this.strSceneIntros.get(i).length();
									//gets what's on the line on the place where the intro should be
									String strIntro = line.substring(intIntroStart, intIntroEnd);
									//gets what's on the line at the place where the separator should be
									String strSeparatorinLine = line.substring(intIntroEnd, intIntroEnd + this.strIntroSeparator.length());
									//checks if strIntro is the same as the current intro in scene intros and if it's followed by the separator (this is to prevent a result like INT being chosen if it's actually int/ext)
									if (this.strSceneIntros.get(i).toLowerCase().equals(strIntro.toLowerCase())
											&&this.strIntroSeparator.toLowerCase().equals(strSeparatorinLine.toLowerCase())){
										this.scenes.get(intScene).setStrSceneIntro(strIntro.toUpperCase());
										intLocationStart = intIntroEnd + this.strIntroSeparator.length();
									}
								}
							}
							
							//get time of day
							for (int i = 0; i<this.strTimesofDay.size(); i++){
								if (line.length()-7-this.strTimesofDay.get(i).length()-this.strTimeSeparator.length()>0){
									int intTimeofDayStart = intTimeofDayEnd - this.strTimesofDay.get(i).length();
									String strTimeofDay = line.substring (intTimeofDayStart, intTimeofDayEnd);
									String strTimeofDaySeparatorinLine = line.substring(intTimeofDayStart - this.strTimeSeparator.length(), intTimeofDayStart);
									if (this.strTimesofDay.get(i).toLowerCase().equals(strTimeofDay.toLowerCase())
											&&this.strTimeSeparator.equals(strTimeofDaySeparatorinLine)){
										this.scenes.get(intScene).setStrTimeofDay(strTimeofDay.toUpperCase());
										intLocationEnd = intTimeofDayStart-this.strTimeSeparator.length();
									}
								}
							}
							
							//get location
							//check if substring will work
							if (intLocationStart<intLocationEnd){
								String strLocation = line.substring(intLocationStart, intLocationEnd);
								int intLocationIndex = this.getIntforLocation(strLocation);
								this.scenes.get(intScene).setIntLocation(intLocationIndex);
								this.locations.get(intLocationIndex).setLocationUsed(true);
							}
						}
						//checks if the scene is omitted
						else if(line.equals("      <Text>OMIT</Text>")){
							this.scenes.get(intScene).setStrSceneIntro("");
							this.scenes.get(intScene).setIntLocation(this.getIntforLocation("OMIT"));
							this.scenes.get(intScene).setStrTimeofDay("");
							this.scenes.get(intScene).setOmitted(true);
						}
						//if it's not the conventional setup it puts nothing in scene intro and time of day and puts the entire content as the location
						else{
							int intContentStart = line.indexOf('>') + 1;
							int intContentStop = line.lastIndexOf('<');
							String strContent = line.substring(intContentStart, intContentStop);
							int intLocationIndex = this.getIntforLocation(strContent);
							this.scenes.get(intScene).setStrSceneIntro("");
							this.scenes.get(intScene).setIntLocation(intLocationIndex);
							this.scenes.get(intScene).setStrTimeofDay("");
							this.locations.get(intLocationIndex).setLocationUsed(true);
						}
						int intContentStart = line.indexOf('>') + 1;
						int intContentStop = line.lastIndexOf('<');
						String strContent = "";
						if (intContentStart < intContentStop){
							strContent = line.toUpperCase().substring(intContentStart, intContentStop);
						}
						sb.append("\n\n" + this.scenes.get(intScene).getStrSceneNumber() + ". " + strContent);
						//close reading heading
						readingHeading = false;
					}
					
				}
				if (readingCharacter){
					if (line.contains("      <Text")&&!(line.contains(" (</Text>"))){
						int intCharacterStart = line.indexOf('>') + 1;
						int intCharacterEnd = line.lastIndexOf('<');
						String strCharacter = line.substring(intCharacterStart, intCharacterEnd);
						this.addCharacterNametoScene(strCharacter, intScene);
						sb.append("\n\n\t\t\t" + strCharacter);
						//close readingcharacter
						readingCharacter = false;
					}
				}
				if (readingDialogue){
					if (line.contains("      <Text")){
						int intContentStart = line.indexOf('>') + 1;
						int intContentStop = line.lastIndexOf('<');
						if (intContentStart < intContentStop){
							String strContent = line.substring(intContentStart, intContentStop);
							sb.append("\n\t\t" + strContent);
						}
					}
				}
				if (readingAction){
					if (line.contains("      <Text")){
						int intContentStart = line.indexOf('>') + 1;
						int intContentStop = line.lastIndexOf('<');
						if (intContentStart < intContentStop){
							String strContent = line.substring(intContentStart, intContentStop);
							sb.append("\n" + strContent);
						}
					}
				}
				if (readingParenthetical){
					if (line.contains("      <Text")){
						int intContentStart = line.indexOf('>') + 1;
						int intContentStop = line.lastIndexOf('<');
						if (intContentStart < intContentStop){
							String strContent = line.substring(intContentStart, intContentStop);
							sb.append(" " + strContent);
						}
					}
				}
				//opens booleans
				//opens sceneheading + gets scene number
				if (line.contains("Type=\"Scene Heading\"")){
					//creates a new scene
					Scene aScene = new Scene();
					this.scenes.add(aScene);
					//puts sb of previous scene in scene content
					if (intScene>-1){
						this.scenes.get(intScene).setStrContent(sb.toString());
						//delete content of sb
						sb.delete(0, sb.length());
					}
					//counts up intScene
					intScene++;
					//opens sceneheading
					readingHeading = true;
					//puts a scene number on the scene
					if (line.contains("Paragraph Number=")){
						String [] strScNumber = line.substring(23).split("\"");
						this.scenes.get(intScene).setStrSceneNumber(strScNumber[0]);
					}
					else{
						this.scenes.get(intScene).setStrSceneNumber("#");
					}
				}
				//opens the rest of the booleans
				if (line.equals("    <Paragraph Type=\"Character\">")){
					readingCharacter = true;
				}
				if (line.equals("    <Paragraph Type=\"Dialogue\">")){
					readingDialogue = true;
				}
				if (line.equals("    <Paragraph Type=\"Action\">")){
					readingAction = true;
				}
				if (line.equals("    <Paragraph Type=\"Parenthetical\">")){
					readingParenthetical = true;
				}
			}
			
		
		}catch(IOException e){
			e.printStackTrace();
		}
		//closes bufferedreader even if there was an error
		finally{
			try{
				if(br!=null)
					br.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}

	}
	private int getIntforLocation(String newLocation){
		int intOutput = 0;
		boolean locationfound = false;
		for (int i = 0; i<this.locations.size();i++){
			if (newLocation.toLowerCase().equals(this.locations.get(i).getStrLocationName().toLowerCase())){
				locationfound = true;
				intOutput = i;
			}
		}
		if (locationfound == false){
			Location aLocation = new Location(newLocation);
			this.locations.add(aLocation);
			intOutput = this.locations.size()-1;
		}
		return intOutput;
	}
	
	private int getIntforCharacter(String newCharacter){
		int intOutput = 0;
		boolean characterfound = false;
		for(int i = 0; i<this.characters.size();i++){
			if (newCharacter.toLowerCase().equals(this.characters.get(i).getStrCharacterName().toLowerCase())){
				characterfound = true;
				intOutput = i;
			}
		}
		if (characterfound == false){
			Character aCharacter = new Character(newCharacter);
			this.characters.add(aCharacter);
			intOutput = this.characters.size()-1;
		}
		return intOutput;
	}
	
	public String showEntireScript(){
		StringBuilder sb = new StringBuilder();
		String strOutput = "";
		for (int i = 0; i<this.scenes.size();i++){
			sb.append(this.scenes.get(i).getStrContent());
		}
		strOutput = sb.toString();
		return strOutput;
	}
	public String showCharactersinScene(int intSceneNo){
		StringBuilder sb = new StringBuilder();
		String output;
		
		if (this.scenes.get(intSceneNo).getIntCharacters().size()!=0){
			ArrayList <Character> characterstoSort = new ArrayList<Character>();
			for (int i = 0; i<this.scenes.get(intSceneNo).getIntCharacters().size();i++){
				int intCharacterIndex = this.scenes.get(intSceneNo).getIntCharacters().get(i);
				int intCharacterNumber = this.characters.get(intCharacterIndex).getIntCharacterNumber();
				String strCharacterName = this.characters.get(intCharacterIndex).getStrCharacterName();
				characterstoSort.add(new Character(intCharacterNumber, strCharacterName));
			}
			this.sortCharacterArray(characterstoSort);
			while(characterstoSort.size()!=0){
				sb.append(characterstoSort.get(0).getIntCharacterNumber() + ". " + characterstoSort.get(0).getStrCharacterName() + "\n");
				characterstoSort.remove(0);
			}
		}
		if (sb.toString().equals("")){
			output = "no characters";
		}
		else{
			output = sb.toString();
		}
		return output;
	}
	public String showSingleCharacters(int intSceneNo, int intCharacterNo){
		return this.characters.get(this.scenes.get(intSceneNo).getIntCharacters().get(intCharacterNo)).getStrCharacterName();
	}
	public String showCharacterWithoutNo(int intCharacterIndex){
		return this.characters.get(intCharacterIndex).getStrCharacterName();
	}
	public String showLocationofScene(int intSceneNo){
		return this.locations.get(this.scenes.get(intSceneNo).getIntLocation()).getStrLocationName();
	}
	public String showSceneNumber(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrSceneNumber();
	}
	public String showSceneIntro(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrSceneIntro();
	}
	public String showSceneTimeofDay(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrTimeofDay();
	}
	public String showSceneHeading(int intSceneNo){
		return (this.showSceneNumber(intSceneNo) + ". " + this.showSceneIntro(intSceneNo) + this.strIntroSeparator + this.showLocationofScene(intSceneNo) + this.strTimeSeparator + this.showSceneTimeofDay(intSceneNo));
	}
	public String showSceneDescription(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrDescription();
	}
	public String showSceneLength(int intSceneNo){
		return this.convertLengthDoubletoString(this.scenes.get(intSceneNo).getDblLength());
	}
	public String showSceneTiming(int intSceneNo){
		int intTiming = this.scenes.get(intSceneNo).getIntTiming();
		String strTiming = this.convertTimingInttoString(intTiming);
		return strTiming;
	}
	public String showSceneContent(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrContent();
	}
	public String showSceneNotes(int intSceneNo){
		return this.scenes.get(intSceneNo).getStrNotes();
	}
	public int showSceneStoryDay(int intSceneNo){
		int intIndex = this.scenes.get(intSceneNo).getIntStoryDay();
		return this.storydays.get(intIndex).getIntStoryDay();
	}
	public String showSceneStoryTime(int intSceneNo){
		return this.convertTimeInttoString(this.scenes.get(intSceneNo).getIntStoryTime());
	}
	public String showSceneProperties(int intSceneNo){
		StringBuilder sb = new StringBuilder();
		sb.append(this.showSceneHeading(intSceneNo));
		sb.append("\n\nDescription: " + this.showSceneDescription(intSceneNo));
		sb.append("\n\nLength: " + this.showSceneLength(intSceneNo));
		sb.append("\tTiming: " + this.showSceneTiming(intSceneNo));
		sb.append("\n\nStory Day: " + this.showSceneStoryDay(intSceneNo));
		sb.append("\tTime: " + this.showSceneStoryTime(intSceneNo));
		sb.append("\nCharacters: \n" + this.showCharactersinScene(intSceneNo));
		sb.append("\nNotes: " + this.showSceneNotes(intSceneNo));
		sb.append("\n\n" + this.showSceneContent(intSceneNo));
		return sb.toString();
	}
	public String showSceneswithCharacter(int intCharacterNo){
		String strOutput = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<this.scenes.size();i++){
				for (int j = 0; j<this.scenes.get(i).getIntCharacters().size();j++){
					if (this.scenes.get(i).getIntCharacters().get(j)==intCharacterNo){
						sb.append(this.showSceneHeading(i) + "\n");
					}
				}
		}
		if (sb.toString().equals("")){
			strOutput = "Character does not appear in any scene";
		}
		else{
			strOutput = sb.toString();
		}
		return strOutput;
	}
	//showSceneswithLocation
	public String showScenesWithLocation(int intLocationNo){
		String strOutput = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<this.scenes.size();i++){
			if (this.scenes.get(i).getIntLocation()==intLocationNo){
				sb.append(this.showSceneHeading(i) + "\n");
			}
		}
		if (sb.toString().equals("")){
			strOutput = "Location does not appear in any scene";
		}
		else{
			strOutput = sb.toString();
		}
		return strOutput;
	}
	public String showScenesWithStoryDayIndex(int intStoryDayIndex){
		StringBuilder sb = new StringBuilder();
		String strOutput;
		for (int i = 0; i<this.scenes.size(); i++){
			if (this.scenes.get(i).getIntStoryDay()==intStoryDayIndex&&this.scenes.get(i).isOmitted()==false){
				sb.append(this.showSceneHeading(i) + "\n");
			}
		}
		if (sb.toString().equals("")){
			strOutput = "No scenes found.";
		}else{
			strOutput = sb.toString();
		}
		return strOutput;
	}
	public int showStoryDayNo(int intStoryDayIndex){
		return this.storydays.get(intStoryDayIndex).getIntStoryDay();
	}
	public String showStoryDayDate(int intStoryDayIndex){
		String strOutput = "No date set";
		if (this.storydays.get(intStoryDayIndex).isDateSet()){
			strOutput = this.convertDatetoString(this.storydays.get(intStoryDayIndex).getDate());
		}
		return strOutput;
	}
	public String showStoryDayDates(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<this.storydays.size(); i++){
			sb.append(this.storydays.get(i).getIntStoryDay() +  ". ");
			sb.append(this.showStoryDayDate(i) + "\n");
		}
		return sb.toString();
	}
	public String showStoryDayDayoftheWeek(int intStoryDayIndex){
		String strOutput = "No date set";
		if (this.storydays.get(intStoryDayIndex).isDateSet()){
			SimpleDateFormat sdf = new SimpleDateFormat("E");
			strOutput = sdf.format(this.storydays.get(intStoryDayIndex).getDate());
		}
		return strOutput;
	}
	public String showStoryDaySummary(int intStoryDayIndex){
		return this.storydays.get(intStoryDayIndex).getStrSummary();
	}
	public String showStoryDaySummary(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<this.storydays.size();i++){
			sb.append(this.showStoryDayNo(i) + ". ");
			sb.append(this.showStoryDaySummary(i) + "\n");
		}
		return sb.toString();
	}
	public String lengthofScriptString(){
		return this.convertLengthDoubletoString(this.lengthofScriptDouble());
	}
	public double lengthofScriptDouble(){
		double dblCount = 0.0f;
		for (Scene scenes: this.scenes){
			if(scenes.isOmitted()==false){
				dblCount+=scenes.getDblLength();
			}
		}
		return dblCount;
	}
	//searchCharacter
	public int searchCharacter(String strCharacterName){
		int intCharacterIndex = -1;
		for (int i = 0; i<this.characters.size();i++){
			if (this.characters.get(i).getStrCharacterName().toLowerCase().equals(strCharacterName.toLowerCase())){
				intCharacterIndex = i;
			}
		}
		return intCharacterIndex;
	}	
	public int searchCharacterwithNumber(int intCharacterNumber){
		int intCharacterIndex = -1;
		for (int i = 0; i<this.characters.size(); i++){
			if (this.characters.get(i).getIntCharacterNumber()==intCharacterNumber){
				intCharacterIndex = i;
			}
		}
		return intCharacterIndex;
	}
	public int searchScene(String strSceneNumber){
		int intSceneIndex = -1;
		for (int i = 0; i<this.scenes.size();i++){
			if (this.scenes.get(i).getStrSceneNumber().toLowerCase().equals(strSceneNumber.toLowerCase())){
				intSceneIndex = i;
			}
		}
		return intSceneIndex;
	}
	public int searchLocation(String strLocation){
		int intLocationIndex = -1;
		for (int i = 0; i<this.locations.size();i++){
			if (this.locations.get(i).getStrLocationName().toLowerCase().equals(strLocation.toLowerCase())){
				intLocationIndex = i;
			}
		}
		return intLocationIndex;
	}
	public int searchStoryDay(int intStoryDay){
		int intStoryDayIndex = -1;
		for (int i = 0; i<this.storydays.size(); i++){
			if (this.storydays.get(i).getIntStoryDay()==intStoryDay){
				intStoryDayIndex = i;
			}
		}
		return intStoryDayIndex;
	}
	//assign character number
	public void assignCharacterNumber(int intNewCharacterIndex){
		if (this.characters.get(intNewCharacterIndex).getIntCharacterNumber()==0){
			this.intCharacterNumber++;
			this.characters.get(intNewCharacterIndex).setIntCharacterNumber(this.intCharacterNumber);
		}
	}
	public int assignStoryDayIndex(int intStoryDay){
		int intStoryDayIndex = 0;
		boolean storydayfound = false;
		for (int i= 0; i<this.storydays.size();i++){
			if (this.storydays.get(i).getIntStoryDay()==intStoryDay){
				intStoryDayIndex = i;
				storydayfound = true;
			}
		}
		if (storydayfound==false){
			//add story day
			StoryDay sd = new StoryDay(intStoryDay);
			int intInsertIndex = 0;
		
			while (intStoryDay>this.storydays.get(intInsertIndex).getIntStoryDay()&&intInsertIndex<this.storydays.size()-1){
				intInsertIndex++;
			}
			if (intStoryDay>this.storydays.get(intInsertIndex).getIntStoryDay()){
				this.storydays.add(sd);
				intInsertIndex = this.storydays.size()-1;
			}else{
				this.storydays.add(intInsertIndex, sd);
			}
			//change value of story days higher
			for (int i = 0; i<this.scenes.size();i++){
				int intSceneStoryDayIndex = this.scenes.get(i).getIntStoryDay();
				if (intSceneStoryDayIndex>=intInsertIndex){
					this.scenes.get(i).setIntStoryDay(intSceneStoryDayIndex+1);
				}
			}
			intStoryDayIndex = intInsertIndex;
		}
		return intStoryDayIndex;
	}
	//add character to scene with character index
	public void addCharacterNoToScene(int intNewCharacter, int intSceneNo){
		boolean characterFound = false;
		for (int i=0; i<this.scenes.get(intSceneNo).getIntCharacters().size();i++){
			if (this.scenes.get(intSceneNo).getIntCharacters().get(i)==intNewCharacter){
				characterFound = true;
			}
		}
		if(characterFound == false){
			this.scenes.get(intSceneNo).getIntCharacters().add(intNewCharacter);
		}
	}
	//add character name to scene
	public void addCharacterNametoScene(String strNewCharacter, int intSceneNo){
		//get index for character
		int intCharacterIndex = this.getIntforCharacter(strNewCharacter);
		//add character to scene with character number
		this.addCharacterNoToScene(intCharacterIndex, intSceneNo);
		//assign character number
		this.assignCharacterNumber(intCharacterIndex);
		this.characters.get(intCharacterIndex).setCharacterUsed(true);
	}
	public void addScene (int intSceneIndex){
		Scene aScene = new Scene();
		this.scenes.add(intSceneIndex, aScene);
	}
	public void addStoryDayDate(String strNewDate, int intStoryDayIndex){
		this.storydays.get(intStoryDayIndex).setDate(this.convertStringtoDate(strNewDate));
		this.storydays.get(intStoryDayIndex).setDateSet(true);
	}
	//make date the same day by day (counting up+counting down)
	public void consolidateStoryDayDateUp(int intStoryDayIndex){
		if (this.storydays.get(intStoryDayIndex).isDateSet()){
			int intIndex2 = intStoryDayIndex + 1;
			int intDifference = this.showStoryDayNo(intIndex2)-this.showStoryDayNo(intStoryDayIndex);
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.storydays.get(intStoryDayIndex).getDate());
			cal.add(Calendar.DATE, intDifference);
			Date newDate = cal.getTime();
			this.storydays.get(intIndex2).setDate(newDate);
			this.storydays.get(intIndex2).setDateSet(true);
		}else{
			System.out.println("No date set");
		}
	}
	public void consolidateStoryDayDateDown(int intStoryDayIndex){
		if (this.storydays.get(intStoryDayIndex).isDateSet()){
			int intIndex2 = intStoryDayIndex - 1;
			int intDifference = this.showStoryDayNo(intIndex2)-this.showStoryDayNo(intStoryDayIndex);
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.storydays.get(intStoryDayIndex).getDate());
			cal.add(Calendar.DATE, intDifference);
			Date newDate = cal.getTime();
			this.storydays.get(intIndex2).setDate(newDate);
			this.storydays.get(intIndex2).setDateSet(true);
		}else{
			System.out.println("No date set");
		}
	}
	public void consolidateAllStoryDayDates(int intStoryDayIndex){
		for (int i = intStoryDayIndex; i<this.storydays.size()-1;i++){
			this.consolidateStoryDayDateUp(i);
		}
		for (int i = intStoryDayIndex; i>0; i--){
			this.consolidateStoryDayDateDown(i);
		}
	}
	public void insertStoryDay(int intStoryDayIndex){
		int intStoryDay = this.storydays.get(intStoryDayIndex).getIntStoryDay();
		StoryDay sd = new StoryDay(intStoryDay);
		storydays.add(intStoryDayIndex, sd);
		for (int i = intStoryDayIndex + 1; i<this.storydays.size();i++){
			int intStoryDayPlus1 = this.storydays.get(i).getIntStoryDay()+1;
			this.storydays.get(i).setIntStoryDay(intStoryDayPlus1);
		}
		for (Scene scene: this.scenes){
			if (scene.getIntStoryDay()>=intStoryDayIndex){
				int intStoryDayIndexPlus1 = scene.getIntStoryDay()+1;
				scene.setIntStoryDay(intStoryDayIndexPlus1);
			}
		}
	}
	//make all dates the same
	//delete character from scene
	public void deleteCharacterNoFromScene(int intCharacterIndex, int intSceneNo){
		for (int i = 0; i<this.scenes.get(intSceneNo).getIntCharacters().size();i++){
			if (this.scenes.get(intSceneNo).getIntCharacters().get(i)==intCharacterIndex){
				this.scenes.get(intSceneNo).getIntCharacters().remove(i);
			}
		}
		//check if the character is still used in the film
		if (this.showSceneswithCharacter(intCharacterIndex).equals("Character does not appear in any scene")){
			this.deleteCharacterNoFromFilm(intCharacterIndex);
		}
	}
	//delete character from film
	public void deleteCharacterNoFromFilm(int intCharacterIndex){
		this.characters.get(intCharacterIndex).setCharacterUsed(false);
		for (int i = 0; i<this.scenes.size(); i++){
			for (int j = 0; j<this.scenes.get(i).getIntCharacters().size(); j++){
				if (this.scenes.get(i).getIntCharacters().get(j)==intCharacterIndex){
					this.scenes.get(i).getIntCharacters().remove(j);
				}
			}
		}
		//get rid of characternumber and add up numbers underneath
		if (this.characters.get(intCharacterIndex).getIntCharacterNumber()!=0){
			for (int i = this.characters.get(intCharacterIndex).getIntCharacterNumber()+1;i<=this.intCharacterNumber;i++){
				if (this.searchCharacterwithNumber(i)!=-1){
					this.characters.get(this.searchCharacterwithNumber(i)).setIntCharacterNumber(i-1);
				}
			}
			this.intCharacterNumber--;
		}
	}
	public void deleteCharacterNameFromFilm(String strCharacterName){
		int intCharacterIndex = this.searchCharacter(strCharacterName);
		if(intCharacterIndex!=-1){
			this.deleteCharacterNoFromFilm(intCharacterIndex);
		}
	}
	public void changeCharacterSpelling(String strNewSpelling, int intCharacterIndex){
		this.characters.get(intCharacterIndex).setStrCharacterName(strNewSpelling);
	}
	//change location
	public void changeLocation(String strNewLocation, int intSceneNo){
		int intLocationIndex = this.getIntforLocation(strNewLocation);
		this.scenes.get(intSceneNo).setIntLocation(intLocationIndex);
	}
	public void changeLocationSpelling(String strNewSpelling, int intLocationIndex){
		this.locations.get(intLocationIndex).setStrLocationName(strNewSpelling);
	}
	//change intro
	public void changeSceneIntro(String strNewSceneIntro, int intSceneNo){
		this.scenes.get(intSceneNo).setStrSceneIntro(strNewSceneIntro);
	}
	//change time of day
	public void changeTimeofDay(String strNewTimeofDay, int intSceneNo){
		this.scenes.get(intSceneNo).setStrTimeofDay(strNewTimeofDay);
	}
	public void changeDescription(String strDescription, int intSceneno){
		this.scenes.get(intSceneno).setStrDescription(strDescription);
	}
	//change notes
	public void changeNotes(String strNewNotes, int intSceneNo){
		this.scenes.get(intSceneNo).setStrNotes(strNewNotes);
	}
	public void changeTiming(String strTiming, int intSceneNo){
		int intTiming = this.convertTimingStringtoInt(strTiming);
		this.scenes.get(intSceneNo).setIntTiming(intTiming);
	}
	public void changeSceneLengthDouble(double dblNewLength, int intSceneNo){
		this.scenes.get(intSceneNo).setDblLength(dblNewLength);
	}
	//change scene length with string
	public void changeSceneLengthString(String strNewSceneLength, int intSceneNo){
		this.changeSceneLengthDouble(this.convertLengthStringtoDouble(strNewSceneLength), intSceneNo);
	}
	public void changeCharacterNumber(int intCharacterIndex, int intNewCharacterNumber){
		int intPreviousCharacterNumber = this.characters.get(intCharacterIndex).getIntCharacterNumber();
		if (intPreviousCharacterNumber==0){
			for (int i = intCharacterNumber;i>=intNewCharacterNumber;i--){
				int intCurrentIndex = this.searchCharacterwithNumber(i);
				if (intCurrentIndex!=0){
					this.characters.get(intCurrentIndex).setIntCharacterNumber(i+1);
				}
			}
			this.characters.get(intCharacterIndex).setIntCharacterNumber(intNewCharacterNumber);
			this.intCharacterNumber++;
		}
		else if (intPreviousCharacterNumber>intNewCharacterNumber){
			for (int i = intPreviousCharacterNumber-1; i>=intNewCharacterNumber; i--){
				int intCurrentIndex = this.searchCharacterwithNumber(i);
				if (intCurrentIndex!=0){
					this.characters.get(intCurrentIndex).setIntCharacterNumber(i+1);
				}
			}
			this.characters.get(intCharacterIndex).setIntCharacterNumber(intNewCharacterNumber);
		}
		else if (intPreviousCharacterNumber<intNewCharacterNumber){
			for (int i= intPreviousCharacterNumber+1;i<=intNewCharacterNumber;i++){
				int intCurrentIndex = this.searchCharacterwithNumber(i);
				if (intCurrentIndex!=0){
					this.characters.get(intCurrentIndex).setIntCharacterNumber(i-1);
				}
			}
			this.characters.get(intCharacterIndex).setIntCharacterNumber(intNewCharacterNumber);
		}
	}
	public void changeStoryDayIndex(int intNewStoryDayIndex, int intSceneNo){
		this.scenes.get(intSceneNo).setIntStoryDay(intNewStoryDayIndex);
	}
	public void changeStoryDayInt(int intNewStoryDay, int intSceneNo){
		int intStoryDayIndex = this.assignStoryDayIndex(intNewStoryDay);
		this.changeStoryDayIndex(intStoryDayIndex, intSceneNo);
	}
	public void changeStoryTimeInt(int intNewStoryTime, int intSceneNo){
		this.scenes.get(intSceneNo).setIntHour(intNewStoryTime);
	}
	public void changeStoryTimeString(String strNewStoryTime, int intSceneNo){
		int intStoryTime = this.convertTimeStringtoInt(strNewStoryTime);
		this.changeStoryTimeInt(intStoryTime, intSceneNo);
	}
	//consolidate characters
	public void consolidateCharacterIndex(int intFirstCharacterIndex, int intSecondCharacterIndex){
		for (int i = 0; i<this.scenes.size();i++){
			for (int j=0; j<this.scenes.get(i).getIntCharacters().size();j++){
				if (this.scenes.get(i).getIntCharacters().get(j)==intSecondCharacterIndex){
					this.addCharacterNoToScene(intFirstCharacterIndex, i);
					this.deleteCharacterNoFromScene(intSecondCharacterIndex, i);
				}
			}
		}
	}
	//consolidate locations
	public void consolidateLocations(int intFirstLocationIndex, int intSecondLocationIndex){
		for (int i = 0; i<this.scenes.size();i++){
			if (this.scenes.get(i).getIntLocation()==intSecondLocationIndex){
				this.scenes.get(i).setIntLocation(intFirstLocationIndex);
			}
		}
	}
	public void consolidateScenes(int intFirstSceneIndex, int intSecondSceneIndex){
		if (intFirstSceneIndex==intSecondSceneIndex-1){
			//add characters to scene
			while (this.scenes.get(intSecondSceneIndex).getIntCharacters().size()!=0){
				this.addCharacterNoToScene(this.scenes.get(intSecondSceneIndex).getIntCharacters().get(0), intFirstSceneIndex);
				this.scenes.get(intSecondSceneIndex).getIntCharacters().remove(0);
			}
			//add content of second scene to first scene
			StringBuilder sb = new StringBuilder();
			sb.append(this.scenes.get(intFirstSceneIndex) + "\n");
			sb.append(this.scenes.get(intSecondSceneIndex));
			this.scenes.get(intFirstSceneIndex).setStrContent(sb.toString());
			this.scenes.get(intSecondSceneIndex).setOmitted(true);
			this.changeSceneIntro("", intSecondSceneIndex);
			this.changeLocation("", intSecondSceneIndex);
			this.changeTimeofDay("", intSecondSceneIndex);
			this.changeNotes("", intSecondSceneIndex);
			this.changeSceneLengthDouble(0.0f, intSecondSceneIndex);
		}
	}
	//convert length string to double
	private double convertLengthStringtoDouble(String strNewLength){
		double dblLength = 0.0f;
		StringBuffer sb = new StringBuffer();
		sb.append(strNewLength);
		try{
			if (sb.toString().contains("/")){
				if (sb.toString().contains(" ")){
					String[]strLengthArray = sb.toString().split(" ");
					dblLength += Double.parseDouble(strLengthArray[0]);
					sb.delete(0, sb.length());
					sb.append(strLengthArray[1]);
				}
				String[]strLengthArray = sb.toString().split("/");
				double dblFraction = Double.parseDouble(strLengthArray[0])/8.0;
				dblLength+=dblFraction;
			}
			else{
				dblLength += Double.parseDouble(strNewLength);
			}
		}catch(Exception ex){
			dblLength = 0.0f;
		}
		return dblLength;
	}
	private String convertLengthDoubletoString(double dblNewDouble){
		StringBuffer sb = new StringBuffer();
		int intRounded = (int) dblNewDouble;
		double dblFraction = dblNewDouble - intRounded;
		if (intRounded != 0){
			sb.append(intRounded);
			if (dblFraction!=0){
				sb.append(" ");
			}
		}
		if (dblFraction != 0){
			sb.append((int)(dblFraction*8));
			sb.append("/8");
		}
		return sb.toString();
	}
	private int convertTimeStringtoInt(String strNewTime){
		int intTime = 0;
		try{
			String[]strTimeArray = strNewTime.split(":");
			if (Integer.parseInt(strTimeArray[0])<24&&Integer.parseInt(strTimeArray[1])<60){
				intTime += Integer.parseInt(strTimeArray[0])*60;
				intTime += Integer.parseInt(strTimeArray[1]);
			}
		}catch(Exception ex){
			
		}
		return intTime;
	}
	private String convertTimeInttoString(int intNewTime){
		while(intNewTime>=1440){
			intNewTime -= 1440;
		}
		int intHours = intNewTime/60;
		int intMinutes = intNewTime%60;
		StringBuilder sb = new StringBuilder();
		sb.append(intHours + ":");
		if (intMinutes<10){
			sb.append("0");
		}
		sb.append(intMinutes);
		return sb.toString();
	}
	private int convertTimingStringtoInt(String strTime){
		int intTime = 0;
		
		try{
			String [] strArray = strTime.split(":");
		
			int intLastIndex = strArray.length-1;
		
			intTime+= Integer.parseInt(strArray[intLastIndex]);
		
			intTime += Integer.parseInt(strArray[intLastIndex-1])*60;
	
			if (strArray.length>2){
				intTime += Integer.parseInt(strArray[intLastIndex-2])*3600;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return intTime;
	}
	private String convertTimingInttoString(int intTiming){
		StringBuilder sb = new StringBuilder();
		
		if (intTiming>=360){
			int intHours = intTiming/3600;
			sb.append(intHours + ":");
			intTiming = intTiming%3600;
		}
		
		int intMinutes = intTiming/60;
		
		if (intMinutes<10){
			sb.append("0");
		}
		sb.append(intMinutes + ":");
		
		int intSeconds = intTiming%60;
		
		if (intSeconds<10){
			sb.append("0");
		}
		
		sb.append(intSeconds);
		
		return sb.toString();
	}
	private String convertDatetoString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strOutput = sdf.format(date);
		return strOutput;
	}
	private Date convertStringtoDate(String strInput){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try{
			date = sdf.parse(strInput);
		}catch(Exception e){
			System.out.println("Unable to read string. Please use format dd/MM/YY");
		}
		return date;
	}
	public boolean canConvertLength(String strLength){
		boolean canConvert = true;
		@SuppressWarnings("unused")
		double dblLength = 0.0f;
		StringBuffer sb = new StringBuffer();
		sb.append(strLength);
		try{
			if (sb.toString().contains("/")){
				if (sb.toString().contains(" ")){
					String[]strLengthArray = sb.toString().split(" ");
					dblLength += Double.parseDouble(strLengthArray[0]);
					sb.delete(0, sb.length());
					sb.append(strLengthArray[1]);
				}
				String[]strLengthArray = sb.toString().split("/");
				double dblFraction = Double.parseDouble(strLengthArray[0])/8.0;
				dblLength+=dblFraction;
			}
			else{
				dblLength += Double.parseDouble(strLength);
			}
		}catch(Exception ex){
			canConvert = false;
		}
		return canConvert;
	}
	public boolean canConvertTime(String strNewTime){
		boolean canConvert = true;
		@SuppressWarnings("unused")
		int intTime = 0;
		try{
			String[]strTimeArray = strNewTime.split(":");
			if (Integer.parseInt(strTimeArray[0])<24&&Integer.parseInt(strTimeArray[1])<60){
				intTime += Integer.parseInt(strTimeArray[0])*60;
				intTime += Integer.parseInt(strTimeArray[1]);
			}
		}catch(Exception ex){
			canConvert = false;
		}
		return canConvert;
	}
	public boolean canConvertDate(String strNewDate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		@SuppressWarnings("unused")
		Date date = new Date();
		boolean canConvert = true;
		try{
			date = sdf.parse(strNewDate);
		}catch(Exception e){
			canConvert = false;
		}
		return canConvert;
	}
	public boolean canConvertTiming(String strTime){
		@SuppressWarnings("unused")
		int intTime = 0;
		boolean canConvert = true;
		try{
			String [] strArray = strTime.split(":");
		
			int intLastIndex = strArray.length-1;
		
			intTime+= Integer.parseInt(strArray[intLastIndex]);
		
			intTime += Integer.parseInt(strArray[intLastIndex-1])*60;
	
			if (strArray.length>2){
				intTime += Integer.parseInt(strArray[intLastIndex-2])*3600;
			}
		}catch(Exception e){
			canConvert = false;
		}
		return canConvert;
	}
	public void automaticallyAssignStoryDays(){
		String strCurrentTime = this.simplifyTimeofDay(this.scenes.get(0).getStrTimeofDay());
		String strPreviousTime = strCurrentTime;
		int intDayCount = 1;
		for (int i = 0; i<this.scenes.size(); i++){
			strCurrentTime = this.simplifyTimeofDay(this.scenes.get(i).getStrTimeofDay());
			if (strCurrentTime.equals("DAY")&&strPreviousTime.equals("NIGHT")){
				intDayCount++;
			}
			strPreviousTime = strCurrentTime;
			this.changeStoryDayInt(intDayCount, i);
		}
	}
	private String simplifyTimeofDay(String strTimeofDay){
		String strOutput;
		if (strTimeofDay.toLowerCase().equals("night")||strTimeofDay.toLowerCase().equals("evening")||strTimeofDay.equals("sunset")){
			strOutput = "NIGHT";
		}else if (strTimeofDay.equals("")){
			strOutput = "";
		}else{
			strOutput = "DAY";
		}
		return strOutput;
	}
	//to make sort function work
	private ArrayList<Character> sortCharacterArray(ArrayList<Character> characterstoSort){
		Collections.sort(characterstoSort, new Comparator<Character>(){
			@Override
			public int compare(Character character1, Character character2){
				return ((Integer)character1.getIntCharacterNumber()).compareTo(character2.getIntCharacterNumber());
			}
		});
		return characterstoSort;
	}
	
	public Film(){
		this.info = new FilmInfo();
		this.characters = new ArrayList<Character>();
		this.locations = new ArrayList <Location>();
		this.scenes = new ArrayList <Scene>();
		this.storydays = new ArrayList<StoryDay>();
		this.storydays.add(new StoryDay());
		this.strIntroSeparator = "dftIntroSeparator";
		this.strSceneIntros = new ArrayList <String>();
		this.strTimeSeparator = "dftTimeSeparator";
		this.strTimesofDay = new ArrayList <String>();
	}
}
/*
More ideas:
add notes classes with subclasses for different kind of notes
add day class for shooting day
add shot class that has information for each shot
add actual timings, scene shot/partially shot/not shot
put transcription of script in a third read, add in that final draft code is also recorded
add ability to load in new version of the script
*/