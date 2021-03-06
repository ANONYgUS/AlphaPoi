public class Course{
    
    //instance variables
    private String courseName;
    /**
    start time and end time seem unnecessary because we can associate them with slot
    */
    private String startTime;
    private String endTime;
    private String location;
    private char slot;
    private String teacher;
    private int position;
    
    //constructor
    public Course(String c, String s, String e, String l, char sl, String t){
        courseName = c;
        startTime = s;
        endTime = e;
        location = l;
        slot = sl;
        teacher = t;
        position = 1;
    }
    
    public Course(String startT, String endT){
        courseName = "";
        startTime = startT;
        endTime = endT;
        location = "";
        slot = 'x';
        teacher = "";
        position = 0;
    }
    
    //constructor for lunch
    public Course(boolean isLunch, boolean isThursday){
        courseName = "Lunch";
        if(isThursday){
            startTime = "11:15 AM";
            endTime = "11:50 AM";
        } else {
            startTime = "11:00 AM";
            endTime = "11:30 AM";
        }
        location = "";
        slot = 'y';
        teacher = "";
        position = 0;
    }
    
    
    
    //methods to retrieve information
    
    public String getName(){
        return courseName;
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
    public int getPosition(){
        return position;
    }
    
    //methods to change information
    
    public void cName(String n){
        courseName = n;
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
    public void cPosition(int p){
        position = p;
    }
    
    public void cAll(String n, String s, String e, String l, char sl, String t, int p){
        courseName = n;
        startTime = s;
        endTime = e;
        location = l;
        slot = sl;
        teacher = t;
        position = p;
    }
    
    
}
