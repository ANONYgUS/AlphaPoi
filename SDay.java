import java.util.*;

public class SDay {
	
	//instance variables
	
	//8 classes per day on special days (thursday)
	private Class_class class1;
	private Class_class class2;
	private Class_class class3;
	private Class_class class4;
	private Class_class class5;
	private Class_class class6;
	private Class_class class7;
	private Class_class class8;
	
	ArrayList<Class_class> classes = new ArrayList<Class_class>();
	
	//constructor
	public SDay(Class_class c1, Class_class c2, Class_class c3, Class_class c4, Class_class c5, Class_class c6, Class_class c7, Class_class c8){
		class1 = c1;
		class2 = c2;
		class3 = c3;
		class4 = c4;
		class5 = c5;
		class6 = c6;
		class7 = c7;
		class8 = c8;
		
		//put all classes into a array for easier referencing
		
		classes.add(class1);
		classes.add(class2);
		classes.add(class3);
		classes.add(class4);
		classes.add(class5);
		classes.add(class6);
		classes.add(class7);
		classes.add(class8);
	}
	
	
	//methods to retreive information
	
	public Class_class getClass(int i){
		return classes.get(i);
	}
	
}