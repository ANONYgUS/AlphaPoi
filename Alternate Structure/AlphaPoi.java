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
 
class  AlphaPoi
{
  private ArrayList<ArrayList<Student>> bob;
  
  public AlphaPoi(ArrayList<ArrayList<Student>> MASTER){
      bob = MASTER;
  }
  
  public AlphaPoi(String path_to_studentinfo_csv) throws Exception{
      //get indexes of where each Student's information starts in the csv file (searched for " character)
        ArrayList<Integer> indexesStudent = new ArrayList<Integer>();
        Scanner indexfinder = new Scanner(new File(path_to_studentinfo_csv));
        int linesInCSVFile = 14146;
        int linecount = 1;
        while(indexfinder.hasNextLine()){
            String cLine = indexfinder.nextLine();
            if(cLine.indexOf("\"") >= 0 && cLine.indexOf("Grade") >= 0){
                indexesStudent.add(linecount);
            }
            linecount++;
        }
    
        Scanner s = new Scanner(new File(path_to_studentinfo_csv));
        
        //System.out.println("Number of Students: "+indexesStudent.size());
        
        
        ArrayList<Student> seniors = new ArrayList<Student>();
        ArrayList<Student> juniors = new ArrayList<Student>();
        ArrayList<Student> sophomores = new ArrayList<Student>();
        ArrayList<Student> freshmen = new ArrayList<Student>();
        
        ArrayList<ArrayList<Student>> MASTER = new ArrayList<ArrayList<Student>>();
        
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
            
            
            // MONDAY CODE BLOCK START ----------------------------------------------------------------------------------------
            
            //System.out.println("MONDAY");
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
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                //class names have id's in them... this removes them from the schedule, so that the classname is shorter and fits better on the cell.
                String className = input[0].substring(0, input[0].indexOf("(")).trim();
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
            //System.out.println("CLASS POSITIONS: ");
            //System.out.print(c1M.getPosition());
            //System.out.print(c2M.getPosition());
            //System.out.print(c3M.getPosition());
            //System.out.print(c4M.getPosition());
            //System.out.print(c5M.getPosition());
            //System.out.print(c6M.getPosition());
            //System.out.print(c7M.getPosition());
            //System.out.println();
            //System.out.println();
            
            Day Monday = new Day(c1M, c2M, c3M, c4M, c5M, c6M, c7M);
            // MONDAY CODE BLOCK END
            
            //TUESDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("TUESDAY");
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
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String className = input[0].substring(0, input[0].indexOf("(")).trim();
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
            //System.out.println("CLASS POSITIONS: ");
            //System.out.print(c1TUE.getPosition());
            //System.out.print(c2TUE.getPosition());
            //System.out.print(c3TUE.getPosition());
            //System.out.print(c4TUE.getPosition());
            //System.out.print(c5TUE.getPosition());
            //System.out.print(c6TUE.getPosition());
            //System.out.print(c7TUE.getPosition());
            //System.out.println();
            //System.out.println();
            
            Day Tuesday = new Day(c1TUE, c2TUE, c3TUE, c4TUE, c5TUE, c6TUE, c7TUE);
            // TUESDAY CODE BLOCK END
            
            // WEDNESDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("WEDNESDAY");
            char[] WednesdaySlots = {'e','a','f','h','b','c','g'};
            String infoWednesday = infoStudent.substring(infoStudent.indexOf("Wednesday")+79, infoStudent.indexOf("Thursday"));
            Scanner Wed_scan = new Scanner(infoWednesday);
            
            //how many courses on Wednesday
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
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String className = input[0].substring(0, input[0].indexOf("(")).trim();
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
            //System.out.println("CLASS POSITIONS: ");
            //System.out.print(c1WED.getPosition());
            //System.out.print(c2WED.getPosition());
            //System.out.print(c3WED.getPosition());
            //System.out.print(c4WED.getPosition());
            //System.out.print(c5WED.getPosition());
            //System.out.print(c6WED.getPosition());
            //System.out.print(c7WED.getPosition());
            //System.out.println();
            //System.out.println();
            
            Day Wednesday = new Day(c1WED, c2WED, c3WED, c4WED, c5WED, c6WED, c7WED);
            // WEDNESDAY CODE BLOCK END
            
            // THURSDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("THURSDAY");
            char[] ThursdaySlots = {'f','a','m','z','g','b','c','d'};
            String infoThursday = infoStudent.substring(infoStudent.indexOf("Thursday")+78, infoStudent.indexOf("Friday"));
            Scanner Thu_scan = new Scanner(infoThursday);
            
            //how many courses on Thursday
            ArrayList<String> infoThursdayByString = new ArrayList<String>();
            
            while(Thu_scan.hasNextLine()){
                infoThursdayByString.add(Thu_scan.nextLine());
            }
            
            Class_class c1THU = new Class_class();
            Class_class c2THU = new Class_class();
            Class_class c3THU = new Class_class();
            Class_class c4THU = new Class_class();
            Class_class c5THU = new Class_class();
            Class_class c6THU = new Class_class();
            Class_class c7THU = new Class_class();
            Class_class c8THU = new Class_class();
            
            ArrayList<Class_class> ThursdayClasses = new ArrayList<Class_class>();
            ThursdayClasses.add(c1THU);
            ThursdayClasses.add(c2THU);
            ThursdayClasses.add(c3THU);
            ThursdayClasses.add(c4THU);
            ThursdayClasses.add(c5THU);
            ThursdayClasses.add(c6THU);
            ThursdayClasses.add(c7THU);
            ThursdayClasses.add(c8THU);
            
            count = 0;
            for(String currentLine: infoThursdayByString){
                char rightSlot = ThursdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String className = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
               
               if(Character.toLowerCase(slot) == rightSlot){
                    ThursdayClasses.get(count).cAll(className, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+1]){
                   ThursdayClasses.get(count+1).cAll(className, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+2]){
                   ThursdayClasses.get(count+2).cAll(className, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+3]){
                   ThursdayClasses.get(count+3).cAll(className, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+4]){
                   ThursdayClasses.get(count+4).cAll(className, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+5]){
                   ThursdayClasses.get(count+5).cAll(className, startTime, endTime, location, slot, teacher, count+6);
                   count+=5;
               }
               count++;
            }
            //System.out.println("counter: "+count);
            //System.out.println("CLASS POSITIONS: ");
            //System.out.print(c1THU.getPosition());
            //System.out.print(c2THU.getPosition());
            //System.out.print(c3THU.getPosition());
            //System.out.print(c4THU.getPosition());
            //System.out.print(c5THU.getPosition());
            //System.out.print(c6THU.getPosition());
            //System.out.print(c7THU.getPosition());
            //System.out.print(c8THU.getPosition());
            //System.out.println();
            //System.out.println();
            
            SDay Thursday = new SDay(c1THU, c2THU, c3THU, c4THU, c5THU, c6THU, c7THU, c8THU);
            // THURSDAY CODE BLOCK END
            
            // FRIDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("FRIDAY");
            char[] FridaySlots = {'g','a','e','h','b','d','f'};
            String infoFriday = infoStudent.substring(infoStudent.indexOf("Friday")+76);
            Scanner Fri_scan = new Scanner(infoFriday);
            
            //how many courses on Friday
            ArrayList<String> infoFridayByString = new ArrayList<String>();
            
            while(Fri_scan.hasNextLine()){
                infoFridayByString.add(Fri_scan.nextLine());
            }
            
            Class_class c1FRI = new Class_class();
            Class_class c2FRI = new Class_class();
            Class_class c3FRI = new Class_class();
            Class_class c4FRI = new Class_class();
            Class_class c5FRI = new Class_class();
            Class_class c6FRI = new Class_class();
            Class_class c7FRI = new Class_class();
            
            ArrayList<Class_class> FridayClasses = new ArrayList<Class_class>();
            FridayClasses.add(c1FRI);
            FridayClasses.add(c2FRI);
            FridayClasses.add(c3FRI);
            FridayClasses.add(c4FRI);
            FridayClasses.add(c5FRI);
            FridayClasses.add(c6FRI);
            FridayClasses.add(c7FRI);
            
            count = 0;
            for(String currentLine: infoFridayByString){
                char rightSlot = FridaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String className = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
                
                if(Character.toLowerCase(slot) == rightSlot){
                    FridayClasses.get(count).cAll(className, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+1]){
                   FridayClasses.get(count+1).cAll(className, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+2]){
                   FridayClasses.get(count+2).cAll(className, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+3]){
                   FridayClasses.get(count+3).cAll(className, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
               }
                count++;
            }
            //System.out.println("counter: "+count);
            //System.out.println("CLASS POSITIONS: ");
            //System.out.print(c1FRI.getPosition());
            //System.out.print(c2FRI.getPosition());
            //System.out.print(c3FRI.getPosition());
            //System.out.print(c4FRI.getPosition());
            //System.out.print(c5FRI.getPosition());
            //System.out.print(c6FRI.getPosition());
            //System.out.print(c7FRI.getPosition());
            //System.out.println();
            //System.out.println();
            
            Day Friday = new Day(c1FRI, c2FRI, c3FRI, c4FRI, c5FRI, c6FRI, c7FRI);
            // FRIDAY CODE BLOCK END
            
            //Retreive the name and grade of the student
            String studentName = infoStudent.substring(1, infoStudent.indexOf(" '"));
            String studentGrade = infoStudent.substring(infoStudent.indexOf("Grade: ")+7, infoStudent.indexOf("Grade: ")+9);
            if(studentGrade.equals("9t")){
                studentGrade = "9";
            }
            //System.out.println(studentName);
            //System.out.println(studentGrade+"\n");
            
            //Now we can create the Student object!
            Student thisStudent = new Student(studentName, studentGrade, Monday, Tuesday, Wednesday, Thursday, Friday);
            
            if(thisStudent.getGrade().equals("12")){
               seniors.add(thisStudent);
            }
            else if(thisStudent.getGrade().equals("11")){
                juniors.add(thisStudent);
            }
            else if(thisStudent.getGrade().equals("10")){
                sophomores.add(thisStudent);
            }
            else if(thisStudent.getGrade().equals("9")){
                freshmen.add(thisStudent);
            }
            
            
            
            
            /**
            for(Student y : seniors){
               System.out.println(y.getFullName());
               System.out.println(y.getGrade());
            }
            */
            
            
        }
        MASTER.add(freshmen);
        MASTER.add(sophomores);
        MASTER.add(juniors);
        MASTER.add(seniors);
        
        bob = MASTER;
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
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(1).getName()+"\n");
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(2).getName()+"\n");
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(3).getName()+"\n");
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(4).getName()+"\n");
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(5).getName()+"\n");
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getClass(6).getName()+"\n");
                  
                  //TUESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(0).getName()+"\n");
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(1).getName()+"\n");
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(2).getName()+"\n");
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(4).getName()+"\n");

                  row = sheet.getRow(66);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getClass(6).getName()+"\n");

                  //WEDNESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(0).getName()+"\n");

                  row = sheet.getRow(15);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(1).getName()+"\n");

                  row = sheet.getRow(26);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(2).getName()+"\n");

                  row = sheet.getRow(44);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getClass(6).getName()+"\n");

                  //THURSDAY
                  row = sheet.getRow(1);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(0).getName()+"\n");

                  row = sheet.getRow(11);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(1).getName()+"\n");

                  row = sheet.getRow(20);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(2).getName()+"\n");

                  row = sheet.getRow(29);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(3).getName()+"\n");

                  row = sheet.getRow(48);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(4).getName()+"\n");
 
                  row = sheet.getRow(58);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(5).getName()+"\n");

                  row = sheet.getRow(68);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(6).getName()+"\n");

                  row = sheet.getRow(78);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getClass(7).getName()+"\n");

                  //FRIDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(0).getName()+"\n");

                  row = sheet.getRow(15);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(1).getName()+"\n");

                  row = sheet.getRow(26);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(2).getName()+"\n");

                  row = sheet.getRow(44);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(4).getName()+"\n");

                  row = sheet.getRow(66);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getClass(6).getName()+"\n");
                  
                  //write changes and close fileOut
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
          
          row = sheet.getRow(15);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(1).getName()+"\n");
          
          row = sheet.getRow(26);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(2).getName()+"\n");
          
          row = sheet.getRow(44);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(3).getName()+"\n");
          
          row = sheet.getRow(55);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(4).getName()+"\n");
          
          row = sheet.getRow(66);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(5).getName()+"\n");
          
          row = sheet.getRow(77);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getClass(6).getName()+"\n");
          
          //TUESDAY
          row = sheet.getRow(4);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(0).getName()+"\n");
          
          row = sheet.getRow(15);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(1).getName()+"\n");
          
          row = sheet.getRow(26);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(2).getName()+"\n");
          
          row = sheet.getRow(44);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(4).getName()+"\n");

          row = sheet.getRow(66);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getClass(6).getName()+"\n");

          //WEDNESDAY
          row = sheet.getRow(4);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(0).getName()+"\n");

          row = sheet.getRow(15);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(1).getName()+"\n");

          row = sheet.getRow(26);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(2).getName()+"\n");

          row = sheet.getRow(44);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getClass(6).getName()+"\n");

          //THURSDAY
          row = sheet.getRow(1);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(0).getName()+"\n");

          row = sheet.getRow(11);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(1).getName()+"\n");

          row = sheet.getRow(20);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(2).getName()+"\n");

          row = sheet.getRow(29);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(3).getName()+"\n");

          row = sheet.getRow(48);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(4).getName()+"\n");

          row = sheet.getRow(58);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(5).getName()+"\n");

          row = sheet.getRow(68);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(6).getName()+"\n");

          row = sheet.getRow(78);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getClass(7).getName()+"\n");

          //FRIDAY
          row = sheet.getRow(4);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(0).getName()+"\n");

          row = sheet.getRow(15);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(1).getName()+"\n");

          row = sheet.getRow(26);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(2).getName()+"\n");

          row = sheet.getRow(44);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(4).getName()+"\n");

          row = sheet.getRow(66);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getClass(6).getName()+"\n");
          
          //write changes and close fileOut
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
  
  public void addClass(String studentName, Class_class c1){
      
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
    
    for(ArrayList<Student> x : bob){
           for(Student y : x){
              if (y.getFullName().equals(studentName)){
                  
              }
              }
          }
    }  
  
}