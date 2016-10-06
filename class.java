public class Class{
	
	//instance variables
	private String className;
	private String startTime;
	private String endTime;
	private String location;
	private char slot;
	private String teacher;
	
	//constructor
	public Class(String c, String s, String e, String l, char sl, String t){
		className = c;
		startTime = s;
		endTime = e;
		location = l;
		slot = sl;
		teacher = t;
	}
	
	//methods to retrieve information
	
	public String getName(){
		return className;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public String getLocation(){
		return location;
	}
	public char getSlot(){
		return slot;
	}
	public String getTeacher(){
		return teacher;
	}
	
	//methods to change information
	
	public void cName(String n){
		className = n;
	}
	public void cStartTime(String s){
		startTime = s;
	}
	public void cEndTime(String e){
		endTime = e;
	}
	public void cLocation(String l){
		location = l;
	}
	public void cSlot(char sl){
		slot = sl;
	}
	public void cTeacher(String t){
		teacher = t;
	}
	
}