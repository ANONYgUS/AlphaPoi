import java.util.*;

public class Day {
	
	//instance variables
	
	//7 courses per day on normal days
	private Course course1;
	private Course course2;
	private Course course3;
	private Course course4;
	private Course course5;
	private Course course6;
	private Course course7;
	
	ArrayList<Course> courses = new ArrayList<Course>();
	
	//constructor
	public Day(Course c1, Course c2, Course c3, Course c4, Course c5, Course c6, Course c7){
		course1 = c1;
		course2 = c2;
		course3 = c3;
		course4 = c4;
		course5 = c5;
		course6 = c6;
		course7 = c7;
		
		//put all courses into a array for easier referencing
		
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
		courses.add(course5);
		courses.add(course6);
		courses.add(course7);
	}
	
	
	//methods to retreive information
	
	public Course getCourse(int i){
		return courses.get(i);
	}
	
}
