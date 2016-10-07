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
        System.out.println(start + " "+ end);
        
        //a String containing the block of information for each student
        String infoStudent = "";
        for(int k = 0; k < (end-start); k++){
            infoStudent += s.nextLine()+"\n";
        }
        
        
        // MONDAY CODE BLOCK START ----------------------------------------------------------------------------------------
        
        System.out.println("MONDAY");
        char[] MondaySlots = {'c','a','d','h','b','g','e'};
        String infoMonday = infoStudent.substring(infoStudent.indexOf("Monday")+76, infoStudent.indexOf("Tuesday"));
        Scanner Mon_scan = new Scanner(infoMonday);
        
        //how many courses on Monday
        ArrayList<String> infoMondayByString = new ArrayList<String>();
        
        while(Mon_scan.hasNextLine()){
            infoMondayByString.add(Mon_scan.nextLine());
        }
        
        Class_class c1M = new Class_class();
        Class_class c2M = new Class_class();
        Class_class c3M = new Class_class();
        Class_class c4M = new Class_class();
        Class_class c5M = new Class_class();
        Class_class c6M = new Class_class();
        Class_class c7M = new Class_class();
        
        ArrayList<Class_class> MondayClasses = new ArrayList<Class_class>();
        MondayClasses.add(c1M);
        MondayClasses.add(c2M);
        MondayClasses.add(c3M);
        MondayClasses.add(c4M);
        MondayClasses.add(c5M);
        MondayClasses.add(c6M);
        MondayClasses.add(c7M);
        
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
                MondayClasses.get(count).cAll(className, startTime, endTime, location, slot, teacher, count+1);
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+1]){
               MondayClasses.get(count+1).cAll(className, startTime, endTime, location, slot, teacher, count+2);
               count++;
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+2]){
               MondayClasses.get(count+2).cAll(className, startTime, endTime, location, slot, teacher, count+3);
               count+=2;
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+3]){
               MondayClasses.get(count+3).cAll(className, startTime, endTime, location, slot, teacher, count+4);
               count+=3;
           }
           else if(Character.toLowerCase(slot) == MondaySlots[count+4]){
               MondayClasses.get(count+4).cAll(className, startTime, endTime, location, slot, teacher, count+5);
               count+=4;
           }
            count++;
        }
        //System.out.println("counter: "+count);
        System.out.println("CLASS POSITIONS: ");
        System.out.print(c1M.getPosition());
        System.out.print(c2M.getPosition());
        System.out.print(c3M.getPosition());
        System.out.print(c4M.getPosition());
        System.out.print(c5M.getPosition());
        System.out.print(c6M.getPosition());
        System.out.print(c7M.getPosition());
        System.out.println();
        System.out.println();
        
        Day Monday = new Day(c1M, c2M, c3M, c4M, c5M, c6M, c7M);
        // MONDAY CODE BLOCK END
        
        //TUESDAY CODE BLOCK START----------------------------------------------------------------------------
        
        System.out.println("TUESDAY");
        char[] TuesdaySlots = {'d','a','e','h','b','f','c'};
        String infoTuesday = infoStudent.substring(infoStudent.indexOf("Tuesday")+77, infoStudent.indexOf("Wednesday"));
        Scanner Tue_scan = new Scanner(infoTuesday);
        
        //how many courses on Tuesday
        ArrayList<String> infoTuesdayByString = new ArrayList<String>();
        
        while(Tue_scan.hasNextLine()){
            infoTuesdayByString.add(Tue_scan.nextLine());
        }
        
        Class_class c1TUE = new Class_class();
        Class_class c2TUE = new Class_class();
        Class_class c3TUE = new Class_class();
        Class_class c4TUE = new Class_class();
        Class_class c5TUE = new Class_class();
        Class_class c6TUE = new Class_class();
        Class_class c7TUE = new Class_class();
        
        ArrayList<Class_class> TuesdayClasses = new ArrayList<Class_class>();
        TuesdayClasses.add(c1TUE);
        TuesdayClasses.add(c2TUE);
        TuesdayClasses.add(c3TUE);
        TuesdayClasses.add(c4TUE);
        TuesdayClasses.add(c5TUE);
        TuesdayClasses.add(c6TUE);
        TuesdayClasses.add(c7TUE);
        
        count = 0;
        for(String currentLine: infoTuesdayByString){
            char rightSlot = TuesdaySlots[count];
            
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
                TuesdayClasses.get(count).cAll(className, startTime, endTime, location, slot, teacher, count+1);
           }
           else if(Character.toLowerCase(slot) == TuesdaySlots[count+1]){
               TuesdayClasses.get(count+1).cAll(className, startTime, endTime, location, slot, teacher, count+2);
               count++;
           }
           else if(Character.toLowerCase(slot) == TuesdaySlots[count+2]){
               TuesdayClasses.get(count+2).cAll(className, startTime, endTime, location, slot, teacher, count+3);
               count+=2;
           }
           else if(Character.toLowerCase(slot) == TuesdaySlots[count+3]){
               TuesdayClasses.get(count+3).cAll(className, startTime, endTime, location, slot, teacher, count+4);
               count+=3;
           }
            count++;
        }
        //System.out.println("counter: "+count);
        System.out.println("CLASS POSITIONS: ");
        System.out.print(c1TUE.getPosition());
        System.out.print(c2TUE.getPosition());
        System.out.print(c3TUE.getPosition());
        System.out.print(c4TUE.getPosition());
        System.out.print(c5TUE.getPosition());
        System.out.print(c6TUE.getPosition());
        System.out.print(c7TUE.getPosition());
        System.out.println();
        System.out.println();
        
        Day Tuesday = new Day(c1TUE, c2TUE, c3TUE, c4TUE, c5TUE, c6TUE, c7TUE);
        // TUESDAY CODE BLOCK END
        
        // WEDNESDAY CODE BLOCK START----------------------------------------------------------------------------
        
        System.out.println("WEDNESDAY");
        char[] WednesdaySlots = {'e','a','f','h','b','c','g'};
        String infoWednesday = infoStudent.substring(infoStudent.indexOf("Wednesday")+79, infoStudent.indexOf("Thursday"));
        Scanner Wed_scan = new Scanner(infoWednesday);
        
        //how many courses on Tuesday
        ArrayList<String> infoWednesdayByString = new ArrayList<String>();
        
        while(Wed_scan.hasNextLine()){
            infoWednesdayByString.add(Wed_scan.nextLine());
        }
        
        Class_class c1WED = new Class_class();
        Class_class c2WED = new Class_class();
        Class_class c3WED = new Class_class();
        Class_class c4WED = new Class_class();
        Class_class c5WED = new Class_class();
        Class_class c6WED = new Class_class();
        Class_class c7WED = new Class_class();
        
        ArrayList<Class_class> WednesdayClasses = new ArrayList<Class_class>();
        WednesdayClasses.add(c1WED);
        WednesdayClasses.add(c2WED);
        WednesdayClasses.add(c3WED);
        WednesdayClasses.add(c4WED);
        WednesdayClasses.add(c5WED);
        WednesdayClasses.add(c6WED);
        WednesdayClasses.add(c7WED);
        
        count = 0;
        for(String currentLine: infoWednesdayByString){
            char rightSlot = WednesdaySlots[count];
            
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
                WednesdayClasses.get(count).cAll(className, startTime, endTime, location, slot, teacher, count+1);
           }
           else if(Character.toLowerCase(slot) == WednesdaySlots[count+1]){
               WednesdayClasses.get(count+1).cAll(className, startTime, endTime, location, slot, teacher, count+2);
               count++;
           }
           else if(Character.toLowerCase(slot) == WednesdaySlots[count+2]){
               WednesdayClasses.get(count+2).cAll(className, startTime, endTime, location, slot, teacher, count+3);
               count+=2;
           }
           else if(Character.toLowerCase(slot) == WednesdaySlots[count+3]){
               WednesdayClasses.get(count+3).cAll(className, startTime, endTime, location, slot, teacher, count+4);
               count+=3;
           }
            count++;
        }
        //System.out.println("counter: "+count);
        System.out.println("CLASS POSITIONS: ");
        System.out.print(c1WED.getPosition());
        System.out.print(c2WED.getPosition());
        System.out.print(c3WED.getPosition());
        System.out.print(c4WED.getPosition());
        System.out.print(c5WED.getPosition());
        System.out.print(c6WED.getPosition());
        System.out.print(c7WED.getPosition());
        System.out.println();
        System.out.println();
        
        Day Wednesday = new Day(c1WED, c2WED, c3WED, c4WED, c5WED, c6WED, c7WED);
        // TUESDAY CODE BLOCK END
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