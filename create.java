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
 
class  create
{
  public static void main(String[] args) throws Exception
  {
    //get indexes of where each Student's information starts in the csv file (searched for " character)
    ArrayList<Integer> indexesStudent = new ArrayList<Integer>();
    
    String pathToCSVFile = "studentSchedules16_2.csv";
    Scanner indexfinder = new Scanner(new File(pathToCSVFile));
    int linesInCSVFile = 14146;
    int linecount = 1;
    while(indexfinder.hasNextLine()){
        String cLine = indexfinder.nextLine();
        if(cLine.indexOf("\"") >= 0 && cLine.indexOf("Grade") >= 0){
            indexesStudent.add(linecount);
        }
        linecount++;
    }

    Scanner s = new Scanner(new File(pathToCSVFile));
    
    System.out.println("Number of Students: "+indexesStudent.size());
    
    for(int i = 0; i<indexesStudent.size(); i++){
        int start = indexesStudent.get(i).intValue();
        int end = 0;
        if(i+1 != indexesStudent.size()){
            end = indexesStudent.get(i+1);
        }
        else{
            end = linesInCSVFile;
        }
        //System.out.println(start + " "+ end);
        
        //a String containing the block of information for each student
        String infoStudent = "";
        for(int k = 0; k < (end-start); k++){
            infoStudent += s.nextLine()+"\n";
        }
        
        char[] MondaySlots = {'c','a','d','h','b','g','e'};
        String infoMonday = infoStudent.substring(infoStudent.indexOf("Monday")+76, infoStudent.indexOf("Tuesday"));
        Scanner m = new Scanner(infoMonday);
        
        //how many courses on Monday
        ArrayList<String> infoMondayByString = new ArrayList<String>();
        
        while(m.hasNextLine()){
            infoMondayByString.add(m.nextLine());
        }
        
        Class_class c1 = new Class_class();
        Class_class c2 = new Class_class();
        Class_class c3 = new Class_class();
        Class_class c4 = new Class_class();
        Class_class c5 = new Class_class();
        Class_class c6 = new Class_class();
        Class_class c7 = new Class_class();
        
        ArrayList<Class_class> MondayClasses = new ArrayList<Class_class>();
        MondayClasses.add(c1);
        MondayClasses.add(c2);
        MondayClasses.add(c3);
        MondayClasses.add(c4);
        MondayClasses.add(c5);
        MondayClasses.add(c6);
        MondayClasses.add(c7);
        
        int count = 0;
        for(String currentLine: infoMondayByString){
            char rightSlot = MondaySlots[count];
            
            System.out.println(currentLine);
            String[] input = currentLine.split(",");
            String className = input[0];
            String startTime = input[1];
            String endTime = input[2];
            String location = input[3];
            char slot = input[4].charAt(0);
            String teacher = input[5];
            
            //System.out.println("rightSlot: "+rightSlot);
            //System.out.println("current slot: "+slot);
            
            if(Character.toLowerCase(slot) == rightSlot){
                MondayClasses.get(count).cName(className);
                MondayClasses.get(count).cStartTime(startTime);
                MondayClasses.get(count).cEndTime(endTime);
                MondayClasses.get(count).cLocation(location);
                MondayClasses.get(count).cSlot(slot);
                MondayClasses.get(count).cTeacher(teacher);
                MondayClasses.get(count).cPosition(count+1);
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+1]){
               MondayClasses.get(count+1).cName(className);
               MondayClasses.get(count+1).cStartTime(startTime);
               MondayClasses.get(count+1).cEndTime(endTime);
               MondayClasses.get(count+1).cLocation(location);
               MondayClasses.get(count+1).cSlot(slot);
               MondayClasses.get(count+1).cTeacher(teacher);
               MondayClasses.get(count+1).cPosition(count+2);
               count++;
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+2]){
               MondayClasses.get(count+2).cName(className);
               MondayClasses.get(count+2).cStartTime(startTime);
               MondayClasses.get(count+2).cEndTime(endTime);
               MondayClasses.get(count+2).cLocation(location);
               MondayClasses.get(count+2).cSlot(slot);
               MondayClasses.get(count+2).cTeacher(teacher);
               MondayClasses.get(count+2).cPosition(count+3);
               count+=2;
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+3]){
               MondayClasses.get(count+3).cName(className);
               MondayClasses.get(count+3).cStartTime(startTime);
               MondayClasses.get(count+3).cEndTime(endTime);
               MondayClasses.get(count+3).cLocation(location);
               MondayClasses.get(count+3).cSlot(slot);
               MondayClasses.get(count+3).cTeacher(teacher);
               MondayClasses.get(count+3).cPosition(count+4);
               count+=3;
           }
            count++;
        }
        //System.out.println("counter: "+count);
        
        
        System.out.println("CLASS POSITIONS: ");
        System.out.print(c1.getPosition());
        System.out.print(c2.getPosition());
        System.out.print(c3.getPosition());
        System.out.print(c4.getPosition());
        System.out.print(c5.getPosition());
        System.out.print(c6.getPosition());
        System.out.print(c7.getPosition());
        System.out.println();
        System.out.println();
        
        
        
        
        
        
        
    }
  }
  
  public static void writeToCell(int r, int c, String str) throws Exception{
    try{
    
        InputStream inp = new FileInputStream("C:\\Users\\usstudent\\Desktop\\CS\\t.xls");
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\usstudent\\Desktop\\CS\\output.xls");
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