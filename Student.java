public class Student
{
	
	private String grade;
	private String fullName;
	private Day monday;
	private Day tuesday;
	private Day wednesday;
	private SDay thursday;
	private Day friday;
	
	//constructors
	public Student(String f, String g){
		grade = g;
		fullName = f;
	}
	
	public Student(String f, int g){
		grade = ""+g;
		fullName = f;
	}
	
	public Student(String f, String g, Day d1, Day d2, Day d3, SDay d4, Day d5){
		grade = g;
		fullName = f;
		monday = d1;
		tuesday = d2;
		wednesday = d3;
		thursday = d4;
		friday = d5;
	}
	
	public Student(Day d1, Day d2, Day d3, SDay d4, Day d5){
		monday = d1;
		tuesday = d2;
		wednesday = d3;
		thursday = d4;
		friday = d5;
	}
	// returns the grade the student is in
	public String getGrade(){
		return grade;
	}

	//returns the full name of the student
	public String getFullName(){
		return fullName;
	}
	
	//methods for getting each Day object
	public Day getMonday(){
		return monday;
	}
	public Day getTuesday(){
		return tuesday;
	}
	public Day getWednesday(){
		return wednesday;
	}
	public SDay getThursday(){
		return thursday;
	}
	public Day getFriday(){
		return friday;
	}
	
}
