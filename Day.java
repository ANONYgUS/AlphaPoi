<<<<<<< HEAD
import java.util.*;

=======
/**
I disagree with the existence of this. Instead of making classes containing all the values and allowing us to access the values with
methods, we should just use the giant quadtruple layer arraylist I mentioned to you earlier.
*/
>>>>>>> 121dfeee2a8fc35ead787c57f19b156e332e4ae9
public class Day {
	
	//instance variables
	
	//7 classes per day on normal days
	private Class_class class1;
	private Class_class class2;
	private Class_class class3;
	private Class_class class4;
	private Class_class class5;
	private Class_class class6;
	private Class_class class7;
	
	ArrayList<Class_class> classes = new ArrayList<Class_class>();
	
	//constructor
	public Day(Class_class c1, Class_class c2, Class_class c3, Class_class c4, Class_class c5, Class_class c6, Class_class c7){
		class1 = c1;
		class2 = c2;
		class3 = c3;
		class4 = c4;
		class5 = c5;
		class6 = c6;
		class7 = c7;
		
		//put all classes into a array for easier referencing
		
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		classes.add(class4);
		classes.add(class5);
		classes.add(class6);
		classes.add(class7);
	}
	
	
	//methods to retreive information
	
	public Class_class getClass(int i){
		return classes.get(i);
	}
	
}
