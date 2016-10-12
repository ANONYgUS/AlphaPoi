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
            
            Course c1M = new Course();
            Course c2M = new Course();
            Course c3M = new Course();
            Course c4M = new Course();
            Course c5M = new Course();
            Course c6M = new Course();
            Course c7M = new Course();
            
            ArrayList<Course> MondayCourses = new ArrayList<Course>();
            MondayCourses.add(c1M);
            MondayCourses.add(c2M);
            MondayCourses.add(c3M);
            MondayCourses.add(c4M);
            MondayCourses.add(c5M);
            MondayCourses.add(c6M);
            MondayCourses.add(c7M);
            
            int count = 0;
            for(String currentLine: infoMondayByString){
                char rightSlot = MondaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                //course names have id's in them... this removes them from the schedule, so that the coursename is shorter and fits better on the cell.
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
                
                if(Character.toLowerCase(slot) == rightSlot){
                    MondayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == MondaySlots[count+1]){
                   MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == MondaySlots[count+2]){
                   MondayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == MondaySlots[count+3]){
                   MondayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
               }
               else if(Character.toLowerCase(slot) == MondaySlots[count+4]){
                   MondayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
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
            
            Course c1TUE = new Course();
            Course c2TUE = new Course();
            Course c3TUE = new Course();
            Course c4TUE = new Course();
            Course c5TUE = new Course();
            Course c6TUE = new Course();
            Course c7TUE = new Course();
            
            ArrayList<Course> TuesdayCourses = new ArrayList<Course>();
            TuesdayCourses.add(c1TUE);
            TuesdayCourses.add(c2TUE);
            TuesdayCourses.add(c3TUE);
            TuesdayCourses.add(c4TUE);
            TuesdayCourses.add(c5TUE);
            TuesdayCourses.add(c6TUE);
            TuesdayCourses.add(c7TUE);
            
            count = 0;
            for(String currentLine: infoTuesdayByString){
                char rightSlot = TuesdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
                
                if(Character.toLowerCase(slot) == rightSlot){
                    TuesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == TuesdaySlots[count+1]){
                   TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == TuesdaySlots[count+2]){
                   TuesdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == TuesdaySlots[count+3]){
                   TuesdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
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
            
            Course c1WED = new Course();
            Course c2WED = new Course();
            Course c3WED = new Course();
            Course c4WED = new Course();
            Course c5WED = new Course();
            Course c6WED = new Course();
            Course c7WED = new Course();
            
            ArrayList<Course> WednesdayCourses = new ArrayList<Course>();
            WednesdayCourses.add(c1WED);
            WednesdayCourses.add(c2WED);
            WednesdayCourses.add(c3WED);
            WednesdayCourses.add(c4WED);
            WednesdayCourses.add(c5WED);
            WednesdayCourses.add(c6WED);
            WednesdayCourses.add(c7WED);
            
            count = 0;
            for(String currentLine: infoWednesdayByString){
                char rightSlot = WednesdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
                
                if(Character.toLowerCase(slot) == rightSlot){
                    WednesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == WednesdaySlots[count+1]){
                   WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == WednesdaySlots[count+2]){
                   WednesdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == WednesdaySlots[count+3]){
                   WednesdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
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
            
            Course c1THU = new Course();
            Course c2THU = new Course();
            Course c3THU = new Course();
            Course c4THU = new Course();
            Course c5THU = new Course();
            Course c6THU = new Course();
            Course c7THU = new Course();
            Course c8THU = new Course();
            
            ArrayList<Course> ThursdayCourses = new ArrayList<Course>();
            ThursdayCourses.add(c1THU);
            ThursdayCourses.add(c2THU);
            ThursdayCourses.add(c3THU);
            ThursdayCourses.add(c4THU);
            ThursdayCourses.add(c5THU);
            ThursdayCourses.add(c6THU);
            ThursdayCourses.add(c7THU);
            ThursdayCourses.add(c8THU);
            
            count = 0;
            for(String currentLine: infoThursdayByString){
                char rightSlot = ThursdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
               
               if(Character.toLowerCase(slot) == rightSlot){
                    ThursdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+1]){
                   ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+2]){
                   ThursdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+3]){
                   ThursdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+4]){
                   ThursdayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+5]){
                   ThursdayCourses.get(count+5).cAll(courseName, startTime, endTime, location, slot, teacher, count+6);
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
            
            Course c1FRI = new Course();
            Course c2FRI = new Course();
            Course c3FRI = new Course();
            Course c4FRI = new Course();
            Course c5FRI = new Course();
            Course c6FRI = new Course();
            Course c7FRI = new Course();
            
            ArrayList<Course> FridayCourses = new ArrayList<Course>();
            FridayCourses.add(c1FRI);
            FridayCourses.add(c2FRI);
            FridayCourses.add(c3FRI);
            FridayCourses.add(c4FRI);
            FridayCourses.add(c5FRI);
            FridayCourses.add(c6FRI);
            FridayCourses.add(c7FRI);
            
            count = 0;
            for(String currentLine: infoFridayByString){
                char rightSlot = FridaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = currentLine.split(",");
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                String location = input[3];
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
                //System.out.println("rightSlot: "+rightSlot);
                //System.out.println("current slot: "+slot);
                
                if(Character.toLowerCase(slot) == rightSlot){
                    FridayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+1]){
                   FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                   count++;
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+2]){
                   FridayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+3]){
                   FridayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
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
                  cell.setCellValue(y.getMonday().getCourse(0).getName()+"\n");
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(1).getName()+"\n");
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(2).getName()+"\n");
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(3).getName()+"\n");
                  
                  row = sheet.getRow(55);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(4).getName()+"\n");
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(5).getName()+"\n");
                  
                  row = sheet.getRow(77);
                  cell = row.getCell(1);
                  cell.setCellValue(y.getMonday().getCourse(6).getName()+"\n");
                  
                  //TUESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(0).getName()+"\n");
                  
                  row = sheet.getRow(15);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(1).getName()+"\n");
                  
                  row = sheet.getRow(26);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(2).getName()+"\n");
                  
                  row = sheet.getRow(44);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(4).getName()+"\n");

                  row = sheet.getRow(66);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(2);
                  cell.setCellValue(y.getTuesday().getCourse(6).getName()+"\n");

                  //WEDNESDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(0).getName()+"\n");

                  row = sheet.getRow(15);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(1).getName()+"\n");

                  row = sheet.getRow(26);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(2).getName()+"\n");

                  row = sheet.getRow(44);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(4).getName()+"\n");
                  //wb.write(fileOut);
                  
                  row = sheet.getRow(66);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(3);
                  cell.setCellValue(y.getWednesday().getCourse(6).getName()+"\n");

                  //THURSDAY
                  row = sheet.getRow(1);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(0).getName()+"\n");

                  row = sheet.getRow(11);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(1).getName()+"\n");

                  row = sheet.getRow(20);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(2).getName()+"\n");

                  row = sheet.getRow(29);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(3).getName()+"\n");

                  row = sheet.getRow(48);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(4).getName()+"\n");
 
                  row = sheet.getRow(58);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(5).getName()+"\n");

                  row = sheet.getRow(68);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(6).getName()+"\n");

                  row = sheet.getRow(78);
                  cell = row.getCell(4);
                  cell.setCellValue(y.getThursday().getCourse(7).getName()+"\n");

                  //FRIDAY
                  row = sheet.getRow(4);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(0).getName()+"\n");

                  row = sheet.getRow(15);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(1).getName()+"\n");

                  row = sheet.getRow(26);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(2).getName()+"\n");

                  row = sheet.getRow(44);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(3).getName()+"\n");

                  row = sheet.getRow(55);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(4).getName()+"\n");

                  row = sheet.getRow(66);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(5).getName()+"\n");

                  row = sheet.getRow(77);
                  cell = row.getCell(5);
                  cell.setCellValue(y.getFriday().getCourse(6).getName()+"\n");
                  
                  //write changes and close fileOut
                  wb.write(fileOut);   
                  fileOut.close();  
                  System.out.println("Success");
              }
          }
      }
  }
  
  public void printClassSchedule(String grade) throws Exception{
      int courseindex = 0;
      if(grade.indexOf("9")>=0){
          courseindex = 0;
      }else if(grade.indexOf("10")>=0){
          courseindex = 1;
      }else if(grade.indexOf("11")>=0){
          courseindex = 2;
      }else if(grade.indexOf("12")>=0){
          courseindex = 3;
      }
      for(Student y : bob.get(courseindex)){
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
          cell.setCellValue(y.getMonday().getCourse(0).getName()+"\n");
          
          row = sheet.getRow(15);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(1).getName()+"\n");
          
          row = sheet.getRow(26);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(2).getName()+"\n");
          
          row = sheet.getRow(44);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(3).getName()+"\n");
          
          row = sheet.getRow(55);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(4).getName()+"\n");
          
          row = sheet.getRow(66);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(5).getName()+"\n");
          
          row = sheet.getRow(77);
          cell = row.getCell(1);
          cell.setCellValue(y.getMonday().getCourse(6).getName()+"\n");
          
          //TUESDAY
          row = sheet.getRow(4);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(0).getName()+"\n");
          
          row = sheet.getRow(15);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(1).getName()+"\n");
          
          row = sheet.getRow(26);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(2).getName()+"\n");
          
          row = sheet.getRow(44);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(4).getName()+"\n");

          row = sheet.getRow(66);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(2);
          cell.setCellValue(y.getTuesday().getCourse(6).getName()+"\n");

          //WEDNESDAY
          row = sheet.getRow(4);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(0).getName()+"\n");

          row = sheet.getRow(15);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(1).getName()+"\n");

          row = sheet.getRow(26);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(2).getName()+"\n");

          row = sheet.getRow(44);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(4).getName()+"\n");
          //wb.write(fileOut);
          
          row = sheet.getRow(66);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(3);
          cell.setCellValue(y.getWednesday().getCourse(6).getName()+"\n");

          //THURSDAY
          row = sheet.getRow(1);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(0).getName()+"\n");

          row = sheet.getRow(11);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(1).getName()+"\n");

          row = sheet.getRow(20);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(2).getName()+"\n");

          row = sheet.getRow(29);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(3).getName()+"\n");

          row = sheet.getRow(48);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(4).getName()+"\n");

          row = sheet.getRow(58);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(5).getName()+"\n");

          row = sheet.getRow(68);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(6).getName()+"\n");

          row = sheet.getRow(78);
          cell = row.getCell(4);
          cell.setCellValue(y.getThursday().getCourse(7).getName()+"\n");

          //FRIDAY
          row = sheet.getRow(4);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(0).getName()+"\n");

          row = sheet.getRow(15);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(1).getName()+"\n");

          row = sheet.getRow(26);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(2).getName()+"\n");

          row = sheet.getRow(44);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(3).getName()+"\n");

          row = sheet.getRow(55);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(4).getName()+"\n");

          row = sheet.getRow(66);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(5).getName()+"\n");

          row = sheet.getRow(77);
          cell = row.getCell(5);
          cell.setCellValue(y.getFriday().getCourse(6).getName()+"\n");
          
          //write changes and close fileOut
          wb.write(fileOut);   
          fileOut.close();  
          System.out.println("Success");
      }
  }
  
  public void switchCourses(String nameOfStudent, String oCourseName, String nCourseName, char nCourseSlot){
    boolean isStudentExist = false;
    boolean isOCourseExist = false;
    boolean isCourseExist = false;
    boolean isSlotExist = false;
    
    for(ArrayList<Student> x : bob){
          for(Student y : x){
              
            
              if(y.getFullName().indexOf(nameOfStudent) >= 0){
                  
                  isStudentExist = true;
                  
                  //creates an arraylist with all instances in all days of the oCourse that the user wants to change
                  ArrayList<Course> originalCourse = new ArrayList<Course>();
                  for(int c = 0; c < 7; c++){
                      if(y.getMonday().getCourse(c).getName().equals(oCourseName)){
                          originalCourse.add(y.getMonday().getCourse(c));
                      }
                  }
                      
                  for(int d = 0; d < 7; d++){
                      if(y.getTuesday().getCourse(d).getName().equals(oCourseName)){
                          originalCourse.add(y.getTuesday().getCourse(d));
                      }
                  }
                  
                  for(int e = 0; e < 7; e++){
                      if(y.getWednesday().getCourse(e).getName().equals(oCourseName)){
                          originalCourse.add(y.getWednesday().getCourse(e));
                      }
                  }
                  
                  for(int f = 0; f < 8; f++){
                      if(y.getThursday().getCourse(f).getName().equals(oCourseName)){
                          originalCourse.add(y.getThursday().getCourse(f));
                      }
                  }
                  
                  for(int g = 0; g < 7; g++){
                      if(y.getFriday().getCourse(g).getName().equals(oCourseName)){
                          originalCourse.add(y.getFriday().getCourse(g));
                      }
                  }
                      
                  if(!(originalCourse.isEmpty())){
                      isOCourseExist = true;
                  }
                  
                  
                  
                  for(ArrayList<Student> a : bob){
                      for(Student b : a){
                          for(int i = 0; i < 7; i++){
                              if(b.getMonday().getCourse(i).getName().equals(nCourseName)){
                                  isCourseExist = true;
                                  if(b.getMonday().getCourse(i).getSlot() == nCourseSlot){
                                      isSlotExist = true;
                                      for(Course h: originalCourse)
                                          if(y.getMonday().getCourse(i).getName().equals(h.getName()) && y.getMonday().getCourse(i).getSlot() == h.getSlot()){
                                              y.getMonday().getCourse(i).cName(b.getMonday().getCourse(i).getName());
                                              y.getMonday().getCourse(i).cStartTime(b.getMonday().getCourse(i).getStartTime());
                                              y.getMonday().getCourse(i).cEndTime(b.getMonday().getCourse(i).getEndTime());
                                              y.getMonday().getCourse(i).cLocation(b.getMonday().getCourse(i).getLocation());
                                              y.getMonday().getCourse(i).cSlot(b.getMonday().getCourse(i).getSlot());
                                              y.getMonday().getCourse(i).cTeacher(b.getMonday().getCourse(i).getTeacher());
                                              y.getMonday().getCourse(i).cPosition(b.getMonday().getCourse(i).getPosition());
                                          }
                                  }
                              }
                          }
                          
                          for(int j = 0; j < 7; j++){
                                  if(b.getTuesday().getCourse(j).getName().equals(nCourseName)){
                                      isCourseExist = true;
                                      if(b.getTuesday().getCourse(j).getSlot() == nCourseSlot){
                                          isSlotExist = true;
                                          for(Course h: originalCourse)
                                              if(y.getTuesday().getCourse(j).getName().equals(h.getName()) && y.getTuesday().getCourse(j).getSlot() == h.getSlot()){
                                                  y.getTuesday().getCourse(j).cName(b.getTuesday().getCourse(j).getName());
                                                  y.getTuesday().getCourse(j).cStartTime(b.getTuesday().getCourse(j).getStartTime());
                                                  y.getTuesday().getCourse(j).cEndTime(b.getTuesday().getCourse(j).getEndTime());
                                                  y.getTuesday().getCourse(j).cLocation(b.getTuesday().getCourse(j).getLocation());
                                                  y.getTuesday().getCourse(j).cSlot(b.getTuesday().getCourse(j).getSlot());
                                                  y.getTuesday().getCourse(j).cTeacher(b.getTuesday().getCourse(j).getTeacher());
                                                  y.getTuesday().getCourse(j).cPosition(b.getTuesday().getCourse(j).getPosition());
                                              }
                                      }
                                  }
                              
                          }
                      
                          for(int k = 0; k < 7; k++){
                                  if(b.getWednesday().getCourse(k).getName().equals(nCourseName)){
                                      isCourseExist = true;
                                      if(b.getWednesday().getCourse(k).getSlot() == nCourseSlot){
                                          isSlotExist = true;
                                          for(Course h: originalCourse)
                                              if(y.getWednesday().getCourse(k).getName().equals(h.getName()) && y.getWednesday().getCourse(k).getSlot() == h.getSlot()){
                                                  y.getWednesday().getCourse(k).cName(b.getWednesday().getCourse(k).getName());
                                                  y.getWednesday().getCourse(k).cStartTime(b.getWednesday().getCourse(k).getStartTime());
                                                  y.getWednesday().getCourse(k).cEndTime(b.getWednesday().getCourse(k).getEndTime());
                                                  y.getWednesday().getCourse(k).cLocation(b.getWednesday().getCourse(k).getLocation());
                                                  y.getWednesday().getCourse(k).cSlot(b.getWednesday().getCourse(k).getSlot());
                                                  y.getWednesday().getCourse(k).cTeacher(b.getWednesday().getCourse(k).getTeacher());
                                                  y.getWednesday().getCourse(k).cPosition(b.getWednesday().getCourse(k).getPosition());
                                              }
                                      }
                                  }
                              
                          }
                      
                          for(int l = 0; l < 7; l++){
                                  if(b.getThursday().getCourse(l).getName().equals(nCourseName)){
                                      isCourseExist = true;
                                      if(b.getThursday().getCourse(l).getSlot() == nCourseSlot){
                                          isSlotExist = true;
                                          for(Course h: originalCourse)
                                              if(y.getThursday().getCourse(l).getName().equals(h.getName()) && y.getThursday().getCourse(l).getSlot() == h.getSlot()){
                                                  y.getThursday().getCourse(l).cName(b.getThursday().getCourse(l).getName());
                                                  y.getThursday().getCourse(l).cStartTime(b.getThursday().getCourse(l).getStartTime());
                                                  y.getThursday().getCourse(l).cEndTime(b.getThursday().getCourse(l).getEndTime());
                                                  y.getThursday().getCourse(l).cLocation(b.getThursday().getCourse(l).getLocation());
                                                  y.getThursday().getCourse(l).cSlot(b.getThursday().getCourse(l).getSlot());
                                                  y.getThursday().getCourse(l).cTeacher(b.getThursday().getCourse(l).getTeacher());
                                                  y.getThursday().getCourse(l).cPosition(b.getThursday().getCourse(l).getPosition());
                                              }
                                      }
                                  }
                              
                          }
                      
                          for(int m = 0; m < 7; m++){
                                  if(b.getFriday().getCourse(m).getName().equals(nCourseName)){
                                      isCourseExist = true;
                                      if(b.getFriday().getCourse(m).getSlot() == nCourseSlot){
                                          isSlotExist = true;
                                          for(Course h: originalCourse)
                                              if(y.getFriday().getCourse(m).getName().equals(h.getName()) && y.getFriday().getCourse(m).getSlot() == h.getSlot()){
                                                  y.getFriday().getCourse(m).cName(b.getFriday().getCourse(m).getName());
                                                  y.getFriday().getCourse(m).cStartTime(b.getFriday().getCourse(m).getStartTime());
                                                  y.getFriday().getCourse(m).cEndTime(b.getFriday().getCourse(m).getEndTime());
                                                  y.getFriday().getCourse(m).cLocation(b.getFriday().getCourse(m).getLocation());
                                                  y.getFriday().getCourse(m).cSlot(b.getFriday().getCourse(m).getSlot());
                                                  y.getFriday().getCourse(m).cTeacher(b.getFriday().getCourse(m).getTeacher());
                                                  y.getFriday().getCourse(m).cPosition(b.getFriday().getCourse(m).getPosition());
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
    if(!isOCourseExist){
        System.out.println("Original course not found.");
    }
    if(!isCourseExist){
        System.out.println("New course not found.");
    }
    if(!isSlotExist){
        System.out.println("Slot not found.");
    }
  }
  
  public void addCourse(String studentName, Course c1){
      
    /**
    make char arrays for slots in order for days, for each day of the week;
    
    for each student
        if (names match)
            for each day
                for each course
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