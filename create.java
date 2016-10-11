import java.io.*;
import java.util.*; 

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.text.SimpleDateFormat;
import java.nio.channels.FileChannel;
 
class  create
{
  private ArrayList<ArrayList<Student>> bob = new ArrayList<ArrayList<Student>>();
  
  public create(ArrayList<ArrayList<Student>> master){
      bob = master;
  }
  
  public void printStudentSchedule(String nameOfStudent) throws Exception{
      for(ArrayList<Student> x : bob){
          for(Student y : x){
              if(y.getFullName().indexOf(nameOfStudent) >= 0){
                  System.out.print("Printing "+y.getFullName()+" ");
                  System.out.print(y.getGrade()+" : ");
                  
                  
                  //make a copy of the clean schedule with the name of the selected student
                  File CleanSchedule = new File("QuarterlyCalendar_2016.xls");
                  FileChannel inputChannel = null;
                  FileChannel outputChannel = null;
                  String SNAME = y.getFullName()+" "+y.getGrade()+".xls";
                  try{
                      inputChannel = new FileInputStream(CleanSchedule).getChannel();
                      outputChannel = new FileOutputStream(SNAME).getChannel();
                      outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                  } finally {
                      inputChannel.close();
                      outputChannel.close();
                  }
                  
                  //modify the student schedule
                  
                  //MONDAY
                  writeToCell(SNAME, 4, 1, y.getMonday().getClass(0).getName());
                  writeToCell(SNAME, 15, 1, y.getMonday().getClass(1).getName());
                  writeToCell(SNAME, 26, 1, y.getMonday().getClass(2).getName());
                  writeToCell(SNAME, 44, 1, y.getMonday().getClass(3).getName());
                  writeToCell(SNAME, 55, 1, y.getMonday().getClass(4).getName());
                  writeToCell(SNAME, 66, 1, y.getMonday().getClass(5).getName());
                  writeToCell(SNAME, 77, 1, y.getMonday().getClass(6).getName());
                  
                  //TUESDAY
                  writeToCell(SNAME, 4, 2, y.getTuesday().getClass(0).getName());
                  writeToCell(SNAME, 15, 2, y.getTuesday().getClass(1).getName());
                  writeToCell(SNAME, 26, 2, y.getTuesday().getClass(2).getName());
                  writeToCell(SNAME, 44, 2, y.getTuesday().getClass(3).getName());
                  writeToCell(SNAME, 55, 2, y.getTuesday().getClass(4).getName());
                  writeToCell(SNAME, 66, 2, y.getTuesday().getClass(5).getName());
                  writeToCell(SNAME, 77, 2, y.getTuesday().getClass(6).getName());
                  
                  //WEDNESDAY
                  writeToCell(SNAME, 4, 3, y.getWednesday().getClass(0).getName());
                  writeToCell(SNAME, 15, 3, y.getWednesday().getClass(1).getName());
                  writeToCell(SNAME, 26, 3, y.getWednesday().getClass(2).getName());
                  writeToCell(SNAME, 44, 3, y.getWednesday().getClass(3).getName());
                  writeToCell(SNAME, 55, 3, y.getWednesday().getClass(4).getName());
                  writeToCell(SNAME, 66, 3, y.getWednesday().getClass(5).getName());
                  writeToCell(SNAME, 77, 3, y.getWednesday().getClass(6).getName());
                  
                  //THURSDAY
                  writeToCell(SNAME, 1, 4, y.getThursday().getClass(0).getName());
                  writeToCell(SNAME, 11, 4, y.getThursday().getClass(1).getName());
                  writeToCell(SNAME, 20, 4, y.getThursday().getClass(2).getName());
                  writeToCell(SNAME, 29, 4, y.getThursday().getClass(3).getName());
                  writeToCell(SNAME, 48, 4, y.getThursday().getClass(4).getName());
                  writeToCell(SNAME, 58, 4, y.getThursday().getClass(5).getName());
                  writeToCell(SNAME, 68, 4, y.getThursday().getClass(6).getName());
                  writeToCell(SNAME, 78, 4, y.getThursday().getClass(7).getName());
                  
                  //FRIDAY
                  writeToCell(SNAME, 4, 5, y.getFriday().getClass(0).getName());
                  writeToCell(SNAME, 15, 5, y.getFriday().getClass(1).getName());
                  writeToCell(SNAME, 26, 5, y.getFriday().getClass(2).getName());
                  writeToCell(SNAME, 44, 5, y.getFriday().getClass(3).getName());
                  writeToCell(SNAME, 55, 5, y.getFriday().getClass(4).getName());
                  writeToCell(SNAME, 66, 5, y.getFriday().getClass(5).getName());
                  writeToCell(SNAME, 77, 5, y.getFriday().getClass(6).getName());
                  
                  System.out.println("Success");
              }
          }
      }
  }
  
  public void printClassSchedule(String grade) throws Exception{
      int classindex = 0;
      if(grade.indexOf("9")>=0){
          classindex = 0;
      }else if(grade.indexOf("10")>=0){
          classindex = 1;
      }else if(grade.indexOf("11")>=0){
          classindex = 2;
      }else if(grade.indexOf("12")>=0){
          classindex = 3;
      }
      for(Student y : bob.get(classindex)){
          System.out.print("Printing "+y.getFullName()+" ");
          System.out.print(y.getGrade()+" : ");
          
          
          //make a copy of the clean schedule with the name of the selected student
          File CleanSchedule = new File("QuarterlyCalendar_2016.xls");
          FileChannel inputChannel = null;
          FileChannel outputChannel = null;
          String SNAME = y.getFullName()+" "+y.getGrade()+".xls";
          try{
              inputChannel = new FileInputStream(CleanSchedule).getChannel();
              outputChannel = new FileOutputStream(SNAME).getChannel();
              outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
          } finally {
              inputChannel.close();
              outputChannel.close();
          }
          
          //modify the student schedule
          
          //MONDAY
          writeToCell(SNAME, 4, 1, y.getMonday().getClass(0).getName());
          writeToCell(SNAME, 15, 1, y.getMonday().getClass(1).getName());
          writeToCell(SNAME, 26, 1, y.getMonday().getClass(2).getName());
          writeToCell(SNAME, 44, 1, y.getMonday().getClass(3).getName());
          writeToCell(SNAME, 55, 1, y.getMonday().getClass(4).getName());
          writeToCell(SNAME, 66, 1, y.getMonday().getClass(5).getName());
          writeToCell(SNAME, 77, 1, y.getMonday().getClass(6).getName());
          
          //TUESDAY
          writeToCell(SNAME, 4, 2, y.getTuesday().getClass(0).getName());
          writeToCell(SNAME, 15, 2, y.getTuesday().getClass(1).getName());
          writeToCell(SNAME, 26, 2, y.getTuesday().getClass(2).getName());
          writeToCell(SNAME, 44, 2, y.getTuesday().getClass(3).getName());
          writeToCell(SNAME, 55, 2, y.getTuesday().getClass(4).getName());
          writeToCell(SNAME, 66, 2, y.getTuesday().getClass(5).getName());
          writeToCell(SNAME, 77, 2, y.getTuesday().getClass(6).getName());
          
          //WEDNESDAY
          writeToCell(SNAME, 4, 3, y.getWednesday().getClass(0).getName());
          writeToCell(SNAME, 15, 3, y.getWednesday().getClass(1).getName());
          writeToCell(SNAME, 26, 3, y.getWednesday().getClass(2).getName());
          writeToCell(SNAME, 44, 3, y.getWednesday().getClass(3).getName());
          writeToCell(SNAME, 55, 3, y.getWednesday().getClass(4).getName());
          writeToCell(SNAME, 66, 3, y.getWednesday().getClass(5).getName());
          writeToCell(SNAME, 77, 3, y.getWednesday().getClass(6).getName());
          
          //THURSDAY
          writeToCell(SNAME, 1, 4, y.getThursday().getClass(0).getName());
          writeToCell(SNAME, 11, 4, y.getThursday().getClass(1).getName());
          writeToCell(SNAME, 20, 4, y.getThursday().getClass(2).getName());
          writeToCell(SNAME, 29, 4, y.getThursday().getClass(3).getName());
          writeToCell(SNAME, 48, 4, y.getThursday().getClass(4).getName());
          writeToCell(SNAME, 58, 4, y.getThursday().getClass(5).getName());
          writeToCell(SNAME, 68, 4, y.getThursday().getClass(6).getName());
          writeToCell(SNAME, 78, 4, y.getThursday().getClass(7).getName());
          
          //FRIDAY
          writeToCell(SNAME, 4, 5, y.getFriday().getClass(0).getName());
          writeToCell(SNAME, 15, 5, y.getFriday().getClass(1).getName());
          writeToCell(SNAME, 26, 5, y.getFriday().getClass(2).getName());
          writeToCell(SNAME, 44, 5, y.getFriday().getClass(3).getName());
          writeToCell(SNAME, 55, 5, y.getFriday().getClass(4).getName());
          writeToCell(SNAME, 66, 5, y.getFriday().getClass(5).getName());
          writeToCell(SNAME, 77, 5, y.getFriday().getClass(6).getName());   
          
          System.out.println("Success");
      }
  }
  
  public void switchClasses(String nameOfStudent, String oClassName, String nClassName, char nClassSlot){
	boolean isStudentExist = false;
	boolean isOClassExist = false;
	for(ArrayList<Student> x : bob){
          for(Student y : x){
              if(y.getFullName().indexOf(nameOfStudent) >= 0){
				  
				  isStudentExist = true;
				  
				  //creates an arraylist with all instances in all days of the oClass that the user wants to change
				  ArrayList<Class_class> originalClass = new ArrayList<Class_class>();
				  for(int c = 0; c < 7; c++){
					  if(y.getMonday().getClass(c).getName.equals(oClassName)){
						  originalClass.add(y.getMonday().getClass(c));
					  }
				  }
					  
				  for(int d = 0; d < 7; d++){
					  if(y.getTuesday().getClass(d).getName.equals(oClassName)){
						  originalClass.add(y.getTuesday().getClass(d));
					  }
				  }
				  
				  for(int e = 0; e < 7; e++){
					  if(y.getWednesday().getClass(e).getName.equals(oClassName)){
						  originalClass.add(y.getWednesday().getClass(e));
					  }
				  }
				  
				  for(int f = 0; f < 7; f++){
					  if(y.getThursday().getClass(f).getName.equals(oClassName)){
						  originalClass.add(y.getThursday().getClass(f));
					  }
				  }
				  
				  for(int g = 0; g < 7; g++){
					  if(y.getFriday().getClass(g).getName.equals(oClassName)){
						  originalClass.add(y.getFriday().getClass(g));
					  }
				  }
					  
				  if(!(originalClass.isEmpty())){
					  isOClassExist = true;
				  }
				  
				  boolean isClassExist = false;
				  boolean isSlotExist = false;
				  
				  for(ArrayList<Student> a : bob){
					  for(Student b : a){
						  for(int i = 0; i < 7; i++){
							  if(b.getMonday().getClass(i).getName().equals(nClassName){
								  isClassExist = true;
								  if(b.getMonday().getSlot() == nClassSlot){
									  isSlotExist = true;
									  for(Class_class h: originalClass)
										  if(y.getMonday().getClass(i).getClassName.equals(h.getClassName) && y.getMonday().getClass(i).getSlot() == h.getSlot()){
											  y.getMonday().getClass(i).cName(b.getMonday().getClass(i).getName());
											  y.getMonday().getClass(i).cStartTime(b.getMonday().getClass(i).getStartTime());
											  y.getMonday().getClass(i).cEndTime(b.getMonday().getClass(i).getEndTime());
											  y.getMonday().getClass(i).cLocation(b.getMonday().getClass(i).getLocation());
											  y.getMonday().getClass(i).cSlot(b.getMonday().getClass(i).getSlot());
											  y.getMonday().getClass(i).cTeacher(b.getMonday().getClass(i).getTeacher());
											  y.getMonday().getClass(i).cPosition(b.getMonday().getClass(i).getPosition());
										  }
								  }
							  }
						  }
						  
					  for(int j = 0; j < 7; j++){
							  if(b.getTuesday().getClass(j).getName().equals(nClassName){
								  isClassExist = true;
								  if(b.getTuesday().getSlot() == nClassSlot){
									  isSlotExist = true;
									  for(Class_class h: originalClass)
										  if(y.getTuesday().getClass(j).getClassName.equals(h.getClassName) && y.getTuesday().getClass(j).getSlot() == h.getSlot()){
											  y.getTuesday().getClass(j).cName(b.getTuesday().getClass(j).getName());
											  y.getTuesday().getClass(j).cStartTime(b.getTuesday().getClass(j).getStartTime());
											  y.getTuesday().getClass(j).cEndTime(b.getTuesday().getClass(j).getEndTime());
											  y.getTuesday().getClass(j).cLocation(b.getTuesday().getClass(j).getLocation());
											  y.getTuesday().getClass(j).cSlot(b.getTuesday().getClass(j).getSlot());
											  y.getTuesday().getClass(j).cTeacher(b.getTuesday().getClass(j).getTeacher());
											  y.getTuesday().getClass(j).cPosition(b.getTuesday().getClass(j).getPosition());
										  }
								  }
							  }
						  
					  }
					  
					  for(int k = 0; k < 7; k++){
							  if(b.getWednesday().getClass(k).getName().equals(nClassName){
								  isClassExist = true;
								  if(b.getWednesday().getSlot() == nClassSlot){
									  isSlotExist = true;
									  for(Class_class h: originalClass)
										  if(y.getWednesday().getClass(k).getClassName.equals(h.getClassName) && y.getWednesday().getClass(k).getSlot() == h.getSlot()){
											  y.getWednesday().getClass(k).cName(b.getWednesday().getClass(k).getName());
											  y.getWednesday().getClass(k).cStartTime(b.getWednesday().getClass(k).getStartTime());
											  y.getWednesday().getClass(k).cEndTime(b.getWednesday().getClass(k).getEndTime());
											  y.getWednesday().getClass(k).cLocation(b.getWednesday().getClass(k).getLocation());
											  y.getWednesday().getClass(k).cSlot(b.getWednesday().getClass(k).getSlot());
											  y.getWednesday().getClass(k).cTeacher(b.getWednesday().getClass(k).getTeacher());
											  y.getWednesday().getClass(k).cPosition(b.getWednesday().getClass(k).getPosition());
										  }
								  }
							  }
						  
					  }
					  
					  for(int l = 0; l < 7; l++){
							  if(b.getThursday().getClass(l).getName().equals(nClassName){
								  isClassExist = true;
								  if(b.getThursday().getSlot() == nClassSlot){
									  isSlotExist = true;
									  for(Class_class h: originalClass)
										  if(y.getThursday().getClass(l).getClassName.equals(h.getClassName) && y.getThursday().getClass(l).getSlot() == h.getSlot()){
											  y.getThursday().getClass(l).cName(b.getThursday().getClass(l).getName());
											  y.getThursday().getClass(l).cStartTime(b.getThursday().getClass(l).getStartTime());
											  y.getThursday().getClass(l).cEndTime(b.getThursday().getClass(l).getEndTime());
											  y.getThursday().getClass(l).cLocation(b.getThursday().getClass(l).getLocation());
											  y.getThursday().getClass(l).cSlot(b.getThursday().getClass(l).getSlot());
											  y.getThursday().getClass(l).cTeacher(b.getThursday().getClass(l).getTeacher());
											  y.getThursday().getClass(l).cPosition(b.getThursday().getClass(l).getPosition());
										  }
								  }
							  }
						  
					  }
					  
					  for(int m = 0; m < 7; m++){
							  if(b.getFriday().getClass(m).getName().equals(nClassName){
								  isClassExist = true;
								  if(b.getFriday().getSlot() == nClassSlot){
									  isSlotExist = true;
									  for(Class_class h: originalClass)
										  if(y.getFriday().getClass(m).getClassName.equals(h.getClassName) && y.getFriday().getClass(m).getSlot() == h.getSlot()){
											  y.getFriday().getClass(m).cName(b.getFriday().getClass(m).getName());
											  y.getFriday().getClass(m).cStartTime(b.getFriday().getClass(m).getStartTime());
											  y.getFriday().getClass(m).cEndTime(b.getFriday().getClass(m).getEndTime());
											  y.getFriday().getClass(m).cLocation(b.getFriday().getClass(m).getLocation());
											  y.getFriday().getClass(m).cSlot(b.getFriday().getClass(m).getSlot());
											  y.getFriday().getClass(m).cTeacher(b.getFriday().getClass(m).getTeacher());
											  y.getFriday().getClass(m).cPosition(b.getFriday().getClass(m).getPosition());
										  }
								  }
							  }
						  
					  }
				  }
			    } 
			}
			if(isSlotExist){
				break;
			}
		}		  
	}
	
	if(!isStudentExist){
		System.out.println("Student not found.")
	}
	if(!isOClassExist){
		System.out.println("Original class not found.")
	}
	if(!isClassExist){
		System.out.println("New class not found.")
	}
	if(!isSlotExist){
		System.out.println("Slot not found.")
	}
  }
  
  public void writeToCell(String fn, int r, int c, String str) throws Exception{
    try{
    
        InputStream inp = new FileInputStream(fn);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(4);
        FileOutputStream fileOut = new FileOutputStream(fn);
        Row row = sheet.getRow(r);
        Cell cell = row.getCell(c);
        cell.setCellValue(str+"\n");
        wb.write(fileOut);
        fileOut.close();  
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
  }
  
  
}