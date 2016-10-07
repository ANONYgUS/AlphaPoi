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
                  System.out.println(y.getFullName());
                  System.out.println(y.getGrade());
                  
                  
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
                  
                  File StudentSchedule = new File(SNAME);
                  
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
                  
              }
          }
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