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
          
                  InputStream inp = new FileInputStream(SNAME);
                  Workbook wb = WorkbookFactory.create(inp);
                  Sheet sheet = wb.getSheetAt(0);
                  FileOutputStream fileOut = new FileOutputStream(SNAME);
                  
                  //MONDAY
                  Row row = sheet.getRow(4);
                  Cell cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(0).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(1).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(2).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(3).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(5).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(6).getName()+"\n");
                  //wb.write(fileOut);
                  
                  //TUESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(0).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(1).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(2).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(3).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(5).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(6).getName()+"\n");
                  //wb.write(fileOut);
                  
                  //WEDNESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(0).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(1).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(2).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(3).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(5).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(6).getName()+"\n");
                  //wb.write(fileOut);
                  
                  //THURSDAY
                  
                  row = sheet.getRow(1);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(0).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(11);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(1).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(20);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(2).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(29);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(3).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(48);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(58);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(5).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(68);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(6).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(78);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(7).getName()+"\n");
                  //wb.write(fileOut);
                  
                  //FRIDAY
                  
                  row = sheet.getRow(4);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(0).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(1).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(2).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(3).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(5).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(6).getName()+"\n");
                  
                  wb.write(fileOut);   
                  fileOut.close();  
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
          
          InputStream inp = new FileInputStream(SNAME);
          Workbook wb = WorkbookFactory.create(inp);
          Sheet sheet = wb.getSheetAt(0);
          FileOutputStream fileOut = new FileOutputStream(SNAME);
          
          //MONDAY
          Row row = sheet.getRow(4);
          Cell cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(0).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(15);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(1).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(26);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(2).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(44);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(3).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(55);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(5).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(77);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(6).getName()+"\n");
          //wb.write(fileOut);
          
          //TUESDAY
          row = sheet.getRow(4);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(0).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(15);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(1).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(26);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(2).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(44);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(3).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(55);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(5).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(77);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(6).getName()+"\n");
          //wb.write(fileOut);
          
          //WEDNESDAY
          row = sheet.getRow(4);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(0).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(15);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(1).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(26);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(2).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(44);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(3).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(55);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(5).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(77);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(6).getName()+"\n");
          //wb.write(fileOut);
          
          //THURSDAY
          
          row = sheet.getRow(1);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(0).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(11);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(1).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(20);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(2).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(29);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(3).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(48);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(58);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(5).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(68);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(6).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(78);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(7).getName()+"\n");
          //wb.write(fileOut);
          
          //FRIDAY
          
          row = sheet.getRow(4);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(0).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(15);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(1).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(26);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(2).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(44);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(3).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(55);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(5).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(77);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(6).getName()+"\n");
          
          wb.write(fileOut);   
          fileOut.close();  
          System.out.println("Success");
      }
  }
  
  public void switchClasses(String nameOfStudent, String oClassName, String nClassName, char nClassSlot){
    boolean isStudentExist = false;
    boolean isOClassExist = false;
    boolean isClassExist = false;
    boolean isSlotExist = false;
    
    for(ArrayList<Student> x : bob){
          for(Student y : x){
              
            
              if(y.getFullName().indexOf(nameOfStudent) >= 0){
                  
                  isStudentExist = true;
                  
                  //creates an arraylist with all instances in all days of the oClass that the user wants to change
                  ArrayList<Class_class> originalClass = new ArrayList<Class_class>();
                  for(int c = 0; c < 7; c++){
                      if(y.getMonday().getClass(c).getName().equals(oClassName)){
                          originalClass.add(y.getMonday().getClass(c));
                      }
                  }
                      
                  for(int d = 0; d < 7; d++){
                      if(y.getTuesday().getClass(d).getName().equals(oClassName)){
                          originalClass.add(y.getTuesday().getClass(d));
                      }
                  }
                  
                  for(int e = 0; e < 7; e++){
                      if(y.getWednesday().getClass(e).getName().equals(oClassName)){
                          originalClass.add(y.getWednesday().getClass(e));
                      }
                  }
                  
                  for(int f = 0; f < 8; f++){
                      if(y.getThursday().getClass(f).getName().equals(oClassName)){
                          originalClass.add(y.getThursday().getClass(f));
                      }
                  }
                  
                  for(int g = 0; g < 7; g++){
                      if(y.getFriday().getClass(g).getName().equals(oClassName)){
                          originalClass.add(y.getFriday().getClass(g));
                      }
                  }
                      
                  if(!(originalClass.isEmpty())){
                      isOClassExist = true;
                  }
                  
                  
                  
                  for(ArrayList<Student> a : bob){
                      for(Student b : a){
                          for(int i = 0; i < 7; i++){
                              if(b.getMonday().getClass(i).getName().equals(nClassName)){
                                  isClassExist = true;
                                  if(b.getMonday().getClass(i).getSlot() == nClassSlot){
                                      isSlotExist = true;
                                      for(Class_class h: originalClass)
                                          if(y.getMonday().getClass(i).getName().equals(h.getName()) && y.getMonday().getClass(i).getSlot() == h.getSlot()){
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
                              if(b.getTuesday().getClass(j).getName().equals(nClassName)){
                                  isClassExist = true;
                                  if(b.getTuesday().getClass(j).getSlot() == nClassSlot){
                                      isSlotExist = true;
                                      for(Class_class h: originalClass)
                                          if(y.getTuesday().getClass(j).getName().equals(h.getName()) && y.getTuesday().getClass(j).getSlot() == h.getSlot()){
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
                              if(b.getWednesday().getClass(k).getName().equals(nClassName)){
                                  isClassExist = true;
                                  if(b.getWednesday().getClass(k).getSlot() == nClassSlot){
                                      isSlotExist = true;
                                      for(Class_class h: originalClass)
                                          if(y.getWednesday().getClass(k).getName().equals(h.getName()) && y.getWednesday().getClass(k).getSlot() == h.getSlot()){
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
                              if(b.getThursday().getClass(l).getName().equals(nClassName)){
                                  isClassExist = true;
                                  if(b.getThursday().getClass(l).getSlot() == nClassSlot){
                                      isSlotExist = true;
                                      for(Class_class h: originalClass)
                                          if(y.getThursday().getClass(l).getName().equals(h.getName()) && y.getThursday().getClass(l).getSlot() == h.getSlot()){
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
                              if(b.getFriday().getClass(m).getName().equals(nClassName)){
                                  isClassExist = true;
                                  if(b.getFriday().getClass(m).getSlot() == nClassSlot){
                                      isSlotExist = true;
                                      for(Class_class h: originalClass)
                                          if(y.getFriday().getClass(m).getName().equals(h.getName()) && y.getFriday().getClass(m).getSlot() == h.getSlot()){
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
        System.out.println("Student not found.");
    }
    if(!isOClassExist){
        System.out.println("Original class not found.");
    }
    if(!isClassExist){
        System.out.println("New class not found.");
    }
    if(!isSlotExist){
        System.out.println("Slot not found.");
    }
  }
  
  public void addClass (String studentName, Class_class c1, char slot1){
 	  
 	/**
 	make char arrays for slots in order for days, for each day of the week;
 	
 	for each student
 		if (names match)
 			for each day
 				for each class
 					if (slots match up, and the original slot is empty)
 						add c1 in to the spot
 				
 	*/	
 	
 	char[] MondaySlots = {'c','a','d','h','b','g','e'};
 	char[] TuesdaySlots = {'d','a','e','h','b','f','c'};
 	char[] WednesdaySlots = {'e','a','f','h','b','c','g'};
 	char[] ThursdaySlots = {'f','a','m','z','g','b','c','d'};
 	char[] FridaySlots = {'g','a','e','h','b','d','f'};
 	
	boolean isClassEmpty = false;
	
	
 	for(ArrayList<Student> x : bob){
           for(Student y : x){
 			  if (y.getFullName().equals(studentName)){
				  
				  for(Class_class classs: y.getMonday()){
					  if(!(MondaySlots.indexOf(classs.getSlot(c1)) == -1)){
						  isClassEmpty = true;
					  }
				  }
 			  }
 			}
 		  }
 	}  
	
	if (!isClassEmpty){
		System.out.println("there's already a class during that time")
	}
  
  public void writeToCell(String fn, int r, int c, String str) throws Exception{
    try{
    
        InputStream inp = new FileInputStream(fn);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
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