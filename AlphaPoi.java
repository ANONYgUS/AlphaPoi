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
  Set<String> setOfAllCourses = new HashSet<String>();
  Set<String> setOfAllCourseLocations = new HashSet<String>();
  Set<String> setOfAllStudents = new HashSet<String>();
  Set<String> setOfAllStudentsR = new HashSet<String>();
  Map<String, String> ADrops = new HashMap<String, String>();
  
  public AlphaPoi(){
      bob = null;
  }
  
  public AlphaPoi(ArrayList<ArrayList<Student>> MASTER){
      bob = MASTER;
  }
  
  public AlphaPoi(String path_to_studentinfo_csv, String path_to_drops) throws Exception{ 
      
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
        
        //start and end times for normal days
        String[] rightStartTime = {"8:15 AM", "9:10 AM", "10:05 AM", "11:00 AM", "11:35 AM", "12:30 PM", "1:15 PM", "2:10 PM"};
        String[] rightEndTime = {"9:05 AM", "10:00 AM", "10:55 AM", "11:30 AM", "12:25 PM", "1:10 PM", "2:05 PM", "3:00 PM"};
        
        //start and end times for THURSDAY
        String[] SrightStartTime = {"8:00 AM", "8:50 AM", "9:40 AM", "10:25 AM", "11:15 AM", "11:50 AM", "12:40 PM", "1:25 PM", "2:15 PM"};
        String[] SrightEndTime = {"8:45 AM", "9:35 AM", "10:20 AM", "11:1 AM", "11:50 AM", "12:35 PM", "1:20 PM", "2:10 PM", "3:00 PM"};
        
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
            char[] MondaySlots = {'c','a','d','y','h','b','g','e'};
            
            
            
            String infoMonday = infoStudent.substring(infoStudent.indexOf("Monday")+76, infoStudent.indexOf("Tuesday"));
            Scanner Mon_scan = new Scanner(infoMonday);
            
            //how many courses on Monday
            ArrayList<String> infoMondayByString = new ArrayList<String>();
            
            while(Mon_scan.hasNextLine()){
                infoMondayByString.add(Mon_scan.nextLine());
            }
            
            //courses need to be initialized with correct starting times...
            Course c1M = new Course(rightStartTime[0], rightEndTime[0]);
            Course c2M = new Course(rightStartTime[1], rightEndTime[1]);
            Course c3M = new Course(rightStartTime[2], rightEndTime[2]);
            Course c4M = new Course(true, false);
            Course c5M = new Course(rightStartTime[4], rightEndTime[4]);
            Course c6M = new Course(rightStartTime[5], rightEndTime[5]);
            Course c7M = new Course(rightStartTime[6], rightEndTime[6]);
            Course c8M = new Course(rightStartTime[7], rightEndTime[7]);
            
            ArrayList<Course> MondayCourses = new ArrayList<Course>();
            MondayCourses.add(c1M);
            MondayCourses.add(c2M);
            MondayCourses.add(c3M);
            MondayCourses.add(c4M);
            MondayCourses.add(c5M);
            MondayCourses.add(c6M);
            MondayCourses.add(c7M);
            MondayCourses.add(c8M);
            
            
            int count = 0;
            for(int ccc = 0; ccc < infoMondayByString.size(); ccc++){
                char rightSlot = MondaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = infoMondayByString.get(ccc).split(",");
                //course names have id's in them... removes them from the schedule
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                //deal with multiple slot courses (labs)

                String location = input[3];
                if(location.indexOf("-")>0){
                    if(location.indexOf("Hope")>=0){
                        location = "Hope "+location.substring(location.indexOf("-")+2);
                    }
                    else{
                        location = location.substring(location.indexOf("-")+2);
                    }
                }
                setOfAllCourseLocations.add(location);
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
               if(Character.toLowerCase(slot) == rightSlot){
                    MondayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
                    //if this course does not start at the right time
                    if(!(startTime).equals(rightStartTime[count])){
                        MondayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoMondayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if(Character.toLowerCase(slot) == MondaySlots[count+1]){
                   count++;
                   MondayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                   
                   if(!(startTime).equals(rightStartTime[count])){
                        MondayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoMondayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
                   
                   
               }
               else if (count+2 < 8 && Character.toLowerCase(slot) == MondaySlots[count+2]){
                   MondayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
                       if(!(startTime).equals(rightStartTime[count])){
                        MondayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoMondayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+3 < 8 && Character.toLowerCase(slot) == MondaySlots[count+3]){
                   MondayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
                       if(!(startTime).equals(rightStartTime[count])){
                        MondayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoMondayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+4 < 8 && Character.toLowerCase(slot) == MondaySlots[count+4]){
                   MondayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
                       if(!(startTime).equals(rightStartTime[count])){
                        MondayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       MondayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoMondayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
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
            
            Day Monday = new Day(c1M, c2M, c3M, c4M, c5M, c6M, c7M, c8M);
            setOfAllCourses.add(c1M.getName());
            setOfAllCourses.add(c2M.getName());
            setOfAllCourses.add(c3M.getName());
            setOfAllCourses.add(c4M.getName());
            setOfAllCourses.add(c5M.getName());
            setOfAllCourses.add(c6M.getName());
            setOfAllCourses.add(c7M.getName());
            
            // MONDAY CODE BLOCK END
            
            //TUESDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("TUESDAY");
            char[] TuesdaySlots = {'d','a','e','y','h','b','f','c'};
            String infoTuesday = infoStudent.substring(infoStudent.indexOf("Tuesday")+77, infoStudent.indexOf("Wednesday"));
            Scanner Tue_scan = new Scanner(infoTuesday);
            
            //how many courses on Tuesday
            ArrayList<String> infoTuesdayByString = new ArrayList<String>();
            
            while(Tue_scan.hasNextLine()){
                infoTuesdayByString.add(Tue_scan.nextLine());
            }
            
            Course c1TUE = new Course(rightStartTime[0], rightEndTime[0]);
            Course c2TUE = new Course(rightStartTime[1], rightEndTime[1]);
            Course c3TUE = new Course(rightStartTime[2], rightEndTime[2]);
            Course c4TUE = new Course(true, false);
            Course c5TUE = new Course(rightStartTime[4], rightEndTime[4]);
            Course c6TUE = new Course(rightStartTime[5], rightEndTime[5]);
            Course c7TUE = new Course(rightStartTime[6], rightEndTime[6]);
            Course c8TUE = new Course(rightStartTime[7], rightEndTime[7]);
            
            ArrayList<Course> TuesdayCourses = new ArrayList<Course>();
            TuesdayCourses.add(c1TUE);
            TuesdayCourses.add(c2TUE);
            TuesdayCourses.add(c3TUE);
            TuesdayCourses.add(c4TUE);
            TuesdayCourses.add(c5TUE);
            TuesdayCourses.add(c6TUE);
            TuesdayCourses.add(c7TUE);
            TuesdayCourses.add(c8TUE);
            
            count = 0;
            for(int ccc = 0; ccc < infoTuesdayByString.size(); ccc++){
                char rightSlot = TuesdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = infoTuesdayByString.get(ccc).split(",");
                //course names have id's in them... removes them from the schedule
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                //deal with multiple slot courses (labs)

                String location = input[3];
                if(location.indexOf("-")>0){
                    if(location.indexOf("Hope")>=0){
                        location = "Hope "+location.substring(location.indexOf("-")+2);
                    }
                    else{
                        location = location.substring(location.indexOf("-")+2);
                    }
                }
                setOfAllCourseLocations.add(location);
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
               if(Character.toLowerCase(slot) == rightSlot){
                    TuesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
                    //if this course does not start at the right time
                    if(!(startTime).equals(rightStartTime[count])){
                        TuesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoTuesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if(Character.toLowerCase(slot) == TuesdaySlots[count+1]){
                   count++;
                   TuesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                   
                   if(!(startTime).equals(rightStartTime[count])){
                        TuesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoTuesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
                   
                   
               }
               else if (count+2 < 8 && Character.toLowerCase(slot) == TuesdaySlots[count+2]){
                   TuesdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
                       if(!(startTime).equals(rightStartTime[count])){
                        TuesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoTuesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+3 < 8 && Character.toLowerCase(slot) == TuesdaySlots[count+3]){
                   TuesdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
                       if(!(startTime).equals(rightStartTime[count])){
                        TuesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoTuesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+4 < 8 && Character.toLowerCase(slot) == TuesdaySlots[count+4]){
                   TuesdayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
                       if(!(startTime).equals(rightStartTime[count])){
                        TuesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       TuesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoTuesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
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
            
            Day Tuesday = new Day(c1TUE, c2TUE, c3TUE, c4TUE, c5TUE, c6TUE, c7TUE, c8TUE);
            setOfAllCourses.add(c1TUE.getName());
            setOfAllCourses.add(c2TUE.getName());
            setOfAllCourses.add(c3TUE.getName());
            setOfAllCourses.add(c4TUE.getName());
            setOfAllCourses.add(c5TUE.getName());
            setOfAllCourses.add(c6TUE.getName());
            setOfAllCourses.add(c7TUE.getName());
            // TUESDAY CODE BLOCK END
            
            // WEDNESDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("WEDNESDAY");
            char[] WednesdaySlots = {'e','a','f','y','h','b','c','g'};
            String infoWednesday = infoStudent.substring(infoStudent.indexOf("Wednesday")+79, infoStudent.indexOf("Thursday"));
            Scanner Wed_scan = new Scanner(infoWednesday);
            
            //how many courses on Wednesday
            ArrayList<String> infoWednesdayByString = new ArrayList<String>();
            
            while(Wed_scan.hasNextLine()){
                infoWednesdayByString.add(Wed_scan.nextLine());
            }
            
            Course c1WED = new Course(rightStartTime[0], rightEndTime[0]);
            Course c2WED = new Course(rightStartTime[1], rightEndTime[1]);
            Course c3WED = new Course(rightStartTime[2], rightEndTime[2]);
            Course c4WED = new Course(true, false);
            Course c5WED = new Course(rightStartTime[4], rightEndTime[4]);
            Course c6WED = new Course(rightStartTime[5], rightEndTime[5]);
            Course c7WED = new Course(rightStartTime[6], rightEndTime[6]);
            Course c8WED = new Course(rightStartTime[7], rightEndTime[7]);
            
            ArrayList<Course> WednesdayCourses = new ArrayList<Course>();
            WednesdayCourses.add(c1WED);
            WednesdayCourses.add(c2WED);
            WednesdayCourses.add(c3WED);
            WednesdayCourses.add(c4WED);
            WednesdayCourses.add(c5WED);
            WednesdayCourses.add(c6WED);
            WednesdayCourses.add(c7WED);
            WednesdayCourses.add(c8WED);
            
            count = 0;
            for(int ccc = 0; ccc < infoWednesdayByString.size(); ccc++){
                char rightSlot = WednesdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = infoWednesdayByString.get(ccc).split(",");
                //course names have id's in them... removes them from the schedule
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                //deal with multiple slot courses (labs)

                String location = input[3];
                if(location.indexOf("-")>0){
                    if(location.indexOf("Hope")>=0){
                        location = "Hope "+location.substring(location.indexOf("-")+2);
                    }
                    else{
                        location = location.substring(location.indexOf("-")+2);
                    }
                }
                setOfAllCourseLocations.add(location);
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
               if(Character.toLowerCase(slot) == rightSlot){
                    WednesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
                    //if this course does not start at the right time
                    if(!(startTime).equals(rightStartTime[count])){
                        WednesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoWednesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if(Character.toLowerCase(slot) == WednesdaySlots[count+1]){
                   count++;
                   WednesdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                   
                   if(!(startTime).equals(rightStartTime[count])){
                        WednesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoWednesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
                   
                   
               }
               else if (count+2 < 8 && Character.toLowerCase(slot) == WednesdaySlots[count+2]){
                   WednesdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
                       if(!(startTime).equals(rightStartTime[count])){
                        WednesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoWednesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+3 < 8 && Character.toLowerCase(slot) == WednesdaySlots[count+3]){
                   WednesdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
                       if(!(startTime).equals(rightStartTime[count])){
                        WednesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoWednesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+4 < 8 && Character.toLowerCase(slot) == WednesdaySlots[count+4]){
                   WednesdayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
                       if(!(startTime).equals(rightStartTime[count])){
                        WednesdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       WednesdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoWednesdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
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
            
            Day Wednesday = new Day(c1WED, c2WED, c3WED, c4WED, c5WED, c6WED, c7WED, c8WED);
            setOfAllCourses.add(c1WED.getName());
            setOfAllCourses.add(c2WED.getName());
            setOfAllCourses.add(c3WED.getName());
            setOfAllCourses.add(c4WED.getName());
            setOfAllCourses.add(c5WED.getName());
            setOfAllCourses.add(c6WED.getName());
            setOfAllCourses.add(c7WED.getName());
            // WEDNESDAY CODE BLOCK END
            
            // THURSDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("THURSDAY");
            char[] ThursdaySlots = {'f','a','m','z','y','g','b','c','d'};
            String infoThursday = infoStudent.substring(infoStudent.indexOf("Thursday")+78, infoStudent.indexOf("Friday"));
            Scanner Thu_scan = new Scanner(infoThursday);
            
            //how many courses on Thursday
            ArrayList<String> infoThursdayByString = new ArrayList<String>();
            
            while(Thu_scan.hasNextLine()){
                infoThursdayByString.add(Thu_scan.nextLine());
            }
            
            Course c1THU = new Course(SrightStartTime[0], SrightEndTime[0]);
            Course c2THU = new Course(SrightStartTime[1], SrightEndTime[1]);
            Course c3THU = new Course(SrightStartTime[2], SrightEndTime[2]);
            Course c4THU = new Course(SrightStartTime[3], SrightEndTime[3]);
            Course c5THU = new Course(true, true);
            Course c6THU = new Course(SrightStartTime[5], SrightEndTime[5]);
            Course c7THU = new Course(SrightStartTime[6], SrightEndTime[6]);
            Course c8THU = new Course(SrightStartTime[7], SrightEndTime[7]);
            Course c9THU = new Course(SrightStartTime[8], SrightEndTime[8]);
            
            ArrayList<Course> ThursdayCourses = new ArrayList<Course>();
            ThursdayCourses.add(c1THU);
            ThursdayCourses.add(c2THU);
            ThursdayCourses.add(c3THU);
            ThursdayCourses.add(c4THU);
            ThursdayCourses.add(c5THU);
            ThursdayCourses.add(c6THU);
            ThursdayCourses.add(c7THU);
            ThursdayCourses.add(c8THU);
            ThursdayCourses.add(c9THU);
            
            count = 0;
            for(int ccc = 0; ccc < infoThursdayByString.size(); ccc++){
                char rightSlot = ThursdaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = infoThursdayByString.get(ccc).split(",");
                //course names have id's in them... removes them from the schedule
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                //deal with multiple slot courses (labs)

                String location = input[3];
                if(location.indexOf("-")>0){
                    if(location.indexOf("Hope")>=0){
                        location = "Hope "+location.substring(location.indexOf("-")+2);
                    }
                    else{
                        location = location.substring(location.indexOf("-")+2);
                    }
                }
                setOfAllCourseLocations.add(location);
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
               if(Character.toLowerCase(slot) == rightSlot){
                    ThursdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
                    //if this course does not start at the right time
                    if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if(Character.toLowerCase(slot) == ThursdaySlots[count+1]){
                   count++;
                   ThursdayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                   
                   if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
                   
                   
               }
               else if (count+2 < 9 && Character.toLowerCase(slot) == ThursdaySlots[count+2]){
                   ThursdayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
                       if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+3 < 9 && Character.toLowerCase(slot) == ThursdaySlots[count+3]){
                   ThursdayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
                       if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+4 < 9 && Character.toLowerCase(slot) == ThursdaySlots[count+4]){
                   ThursdayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
                       if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+5 < 9 && Character.toLowerCase(slot) == ThursdaySlots[count+5]){
                   ThursdayCourses.get(count+5).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=5;
                       if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+6 < 9 && Character.toLowerCase(slot) == ThursdaySlots[count+6]){
                   ThursdayCourses.get(count+6).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=6;
                       if(!(startTime).equals(SrightStartTime[count])){
                        ThursdayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(SrightEndTime[count])){
                       ThursdayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoThursdayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
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
            
            SDay Thursday = new SDay(c1THU, c2THU, c3THU, c4THU, c5THU, c6THU, c7THU, c8THU, c9THU);
            setOfAllCourses.add(c1THU.getName());
            setOfAllCourses.add(c2THU.getName());
            setOfAllCourses.add(c3THU.getName());
            setOfAllCourses.add(c4THU.getName());
            setOfAllCourses.add(c5THU.getName());
            setOfAllCourses.add(c6THU.getName());
            setOfAllCourses.add(c7THU.getName());
            // THURSDAY CODE BLOCK END
            
            // FRIDAY CODE BLOCK START----------------------------------------------------------------------------
            
            //System.out.println("FRIDAY");
            char[] FridaySlots = {'g','a','e','y','h','b','d','f'};
            String infoFriday = infoStudent.substring(infoStudent.indexOf("Friday")+76);
            Scanner Fri_scan = new Scanner(infoFriday);
            
            //how many courses on Friday
            ArrayList<String> infoFridayByString = new ArrayList<String>();
            
            while(Fri_scan.hasNextLine()){
                infoFridayByString.add(Fri_scan.nextLine());
            }
            
            Course c1FRI = new Course(rightStartTime[0], rightEndTime[0]);
            Course c2FRI = new Course(rightStartTime[1], rightEndTime[1]);
            Course c3FRI = new Course(rightStartTime[2], rightEndTime[2]);
            Course c4FRI = new Course(true, false);
            Course c5FRI = new Course(rightStartTime[4], rightEndTime[4]);
            Course c6FRI = new Course(rightStartTime[5], rightEndTime[5]);
            Course c7FRI = new Course(rightStartTime[6], rightEndTime[6]);
            Course c8FRI = new Course(rightStartTime[7], rightEndTime[7]);
            
            ArrayList<Course> FridayCourses = new ArrayList<Course>();
            FridayCourses.add(c1FRI);
            FridayCourses.add(c2FRI);
            FridayCourses.add(c3FRI);
            FridayCourses.add(c4FRI);
            FridayCourses.add(c5FRI);
            FridayCourses.add(c6FRI);
            FridayCourses.add(c7FRI);
            FridayCourses.add(c8FRI);
            
            count = 0;
            for(int ccc = 0; ccc < infoFridayByString.size(); ccc++){
                char rightSlot = FridaySlots[count];
                
                //System.out.println(currentLine);
                String[] input = infoFridayByString.get(ccc).split(",");
                //course names have id's in them... removes them from the schedule
                String courseName = input[0].substring(0, input[0].indexOf("(")).trim();
                String startTime = input[1];
                String endTime = input[2];
                //deal with multiple slot courses (labs)

                String location = input[3];
                if(location.indexOf("-")>0){
                    if(location.indexOf("Hope")>=0){
                        location = "Hope "+location.substring(location.indexOf("-")+2);
                    }
                    else{
                        location = location.substring(location.indexOf("-")+2);
                    }
                }
                setOfAllCourseLocations.add(location);
                char slot = input[4].charAt(0);
                String teacher = input[5];
                
               if(Character.toLowerCase(slot) == rightSlot){
                    FridayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count+1);
                    //if this course does not start at the right time
                    if(!(startTime).equals(rightStartTime[count])){
                        FridayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoFridayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if(Character.toLowerCase(slot) == FridaySlots[count+1]){
                   count++;
                   FridayCourses.get(count).cAll(courseName, startTime, endTime, location, slot, teacher, count);
                   
                   if(!(startTime).equals(rightStartTime[count])){
                        FridayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoFridayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
                   
                   
               }
               else if (count+2 < 8 && Character.toLowerCase(slot) == FridaySlots[count+2]){
                   FridayCourses.get(count+2).cAll(courseName, startTime, endTime, location, slot, teacher, count+3);
                   count+=2;
                       if(!(startTime).equals(rightStartTime[count])){
                        FridayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoFridayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+3 < 8 && Character.toLowerCase(slot) == FridaySlots[count+3]){
                   FridayCourses.get(count+3).cAll(courseName, startTime, endTime, location, slot, teacher, count+4);
                   count+=3;
                       if(!(startTime).equals(rightStartTime[count])){
                        FridayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoFridayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
               }
               else if (count+4 < 8 && Character.toLowerCase(slot) == FridaySlots[count+4]){
                   FridayCourses.get(count+4).cAll(courseName, startTime, endTime, location, slot, teacher, count+5);
                   count+=4;
                       if(!(startTime).equals(rightStartTime[count])){
                        FridayCourses.get(count-1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                    }
               
                   //if this course does not end at the right time
                   if(!(endTime).equals(rightEndTime[count])){
                       FridayCourses.get(count+1).cAll(courseName, startTime, endTime, location, slot, teacher, count+2);
                       count++;
                       //check for overlapping classes...
                       String[] inputC = infoFridayByString.get(ccc+1).split(",");
                       String startTimeC = inputC[1];
                       String endTimeC = inputC[2];
                       char slotC = inputC[4].charAt(0);
                       if(endTimeC.equals(endTime)){
                           //skip the next class
                           ccc++;
                           count--;
                       }
                   }
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
            
            Day Friday = new Day(c1FRI, c2FRI, c3FRI, c4FRI, c5FRI, c6FRI, c7FRI, c8FRI);
            setOfAllCourses.add(c1FRI.getName());
            setOfAllCourses.add(c2FRI.getName());
            setOfAllCourses.add(c3FRI.getName());
            setOfAllCourses.add(c4FRI.getName());
            setOfAllCourses.add(c5FRI.getName());
            setOfAllCourses.add(c6FRI.getName());
            setOfAllCourses.add(c7FRI.getName());
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
            setOfAllStudents.add(thisStudent.getFullName());
            setOfAllStudentsR.add(
                thisStudent.getFullName().substring(
                    thisStudent.getFullName().indexOf(",")+1)+" "+thisStudent.getFullName().substring(0, thisStudent.getFullName().indexOf(",")
                )
            );
            
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
            
            
        }
        
        //get drops
        InputStream inp1 = new FileInputStream(path_to_drops);
        Workbook w = WorkbookFactory.create(inp1);
        Sheet sheet = w.getSheetAt(0);
      
        for(Row row : sheet){
            String name = row.getCell(0).getStringCellValue();
            String dropday = row.getCell(3).getStringCellValue();
            //System.out.println(name+" "+dropday);
            if(!name.isEmpty() && !dropday.isEmpty()){
                ADrops.put(name, dropday);
            }
        }
        
        
 
        MASTER.add(freshmen);
        MASTER.add(sophomores);
        MASTER.add(juniors);
        MASTER.add(seniors);
        
        bob = MASTER;
        
        for(ArrayList<Student> a : bob){
            for(Student b : a){
                for(int i = 0; i < 8; i++){
                    if(b.getMonday().getCourse(i).getSlot() == 'A'){
                        if(ADrops.containsKey(b.getMonday().getCourse(i).getName())){
                            if(ADrops.get(b.getMonday().getCourse(i).getName()).equals("A Slot No M")){
                                //turns this class into a free
                                b.getMonday().getCourse(i).cAll("", b.getMonday().getCourse(i).getStartTime(), b.getMonday().getCourse(i).getEndTime(), "", b.getMonday().getCourse(i).getSlot(), "", 1);
                            }
                        }
                    }
                }
                for(int i = 0; i < 8; i++){
                    if(b.getTuesday().getCourse(i).getSlot() == 'A'){
                        if(ADrops.containsKey(b.getTuesday().getCourse(i).getName())){
                            if(ADrops.get(b.getTuesday().getCourse(i).getName()).equals("A Slot No T")){
                                //turns this class into a free
                                b.getTuesday().getCourse(i).cAll("", b.getTuesday().getCourse(i).getStartTime(), b.getTuesday().getCourse(i).getEndTime(), "", b.getTuesday().getCourse(i).getSlot(), "", 1);
                            }
                        }
                    }
                }
                for(int i = 0; i < 8; i++){
                    if(b.getWednesday().getCourse(i).getSlot() == 'A'){
                        if(ADrops.containsKey(b.getWednesday().getCourse(i).getName())){
                            if(ADrops.get(b.getWednesday().getCourse(i).getName()).equals("A Slot No W")){
                                //turns this class into a free
                                b.getWednesday().getCourse(i).cAll("", b.getWednesday().getCourse(i).getStartTime(), b.getWednesday().getCourse(i).getEndTime(), "", b.getWednesday().getCourse(i).getSlot(), "", 1);
                            }
                        }
                    }
                }
                for(int i = 0; i < 9; i++){
                    if(b.getThursday().getCourse(i).getSlot() == 'A'){
                        if(ADrops.containsKey(b.getThursday().getCourse(i).getName())){
                            if(ADrops.get(b.getThursday().getCourse(i).getName()).equals("A Slot No Th")){
                                //turns this class into a free
                                b.getThursday().getCourse(i).cAll("", b.getThursday().getCourse(i).getStartTime(), b.getThursday().getCourse(i).getEndTime(), "", b.getThursday().getCourse(i).getSlot(), "", 1);
                            }
                        }
                    }
                }
                for(int i = 0; i < 8; i++){
                    if(b.getFriday().getCourse(i).getSlot() == 'A'){
                        if(ADrops.containsKey(b.getFriday().getCourse(i).getName())){
                            if(ADrops.get(b.getFriday().getCourse(i).getName()).equals("A Slot No F")){
                                //turns this class into a free
                                b.getFriday().getCourse(i).cAll("", b.getFriday().getCourse(i).getStartTime(), b.getFriday().getCourse(i).getEndTime(), "", b.getFriday().getCourse(i).getSlot(), "", 1);
                            }
                        }
                    }
                }
            }
        }
  }
  
  public void printStudentSchedule(String nameOfStudent) throws Exception{
      if(nameOfStudent.equals("")){System.out.println("Please enter a name in the format: \"firstname, lastname\" and try again."); return;}
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
                  
                  //name and grade
                  Row row = sheet.getRow(0);
                  Cell cell = row.getCell(0);
                  cell.setCellValue(y.getFullName().substring(y.getFullName().indexOf(",")+1)+" "+y.getFullName().substring(0, y.getFullName().indexOf(",")));
                  
                  row = sheet.getRow(0);
                  cell = row.getCell(4);
                  cell.setCellValue("Grade: "+y.getGrade());
                  
                  //MONDAY
                  row = sheet.getRow(5);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(0).getName()+"\n"+y.getMonday().getCourse(0).getLocation());
                  
                  row = sheet.getRow(16);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(1).getName()+"\n"+y.getMonday().getCourse(1).getLocation());
                  
                  row = sheet.getRow(27);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(2).getName()+"\n"+y.getMonday().getCourse(2).getLocation());
                  
                  row = sheet.getRow(38);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(3).getName()+"\n"+y.getMonday().getCourse(3).getLocation());
                  
                  row = sheet.getRow(45);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(4).getName()+"\n"+y.getMonday().getCourse(4).getLocation());
                  
                  row = sheet.getRow(56);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(5).getName()+"\n"+y.getMonday().getCourse(5).getLocation());
                  
                  row = sheet.getRow(67);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(6).getName()+"\n"+y.getMonday().getCourse(6).getLocation());
                  
                  row = sheet.getRow(78);
                  cell = row.getCell(1);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(7).getName()+"\n"+y.getMonday().getCourse(7).getLocation());
                  
                  //TUESDAY
                  row = sheet.getRow(5);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(0).getName()+"\n"+y.getTuesday().getCourse(0).getLocation());
                  
                  row = sheet.getRow(16);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(1).getName()+"\n"+y.getTuesday().getCourse(1).getLocation());
                  
                  row = sheet.getRow(27);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(2).getName()+"\n"+y.getTuesday().getCourse(2).getLocation());
                  
                  row = sheet.getRow(38);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(3).getName()+"\n"+y.getTuesday().getCourse(3).getLocation());
                  
                  row = sheet.getRow(45);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(4).getName()+"\n"+y.getTuesday().getCourse(4).getLocation());
        
                  row = sheet.getRow(56);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(5).getName()+"\n"+y.getTuesday().getCourse(5).getLocation());
        
                  row = sheet.getRow(67);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(6).getName()+"\n"+y.getTuesday().getCourse(6).getLocation());
        
                  row = sheet.getRow(78);
                  cell = row.getCell(2);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(7).getName()+"\n"+y.getTuesday().getCourse(7).getLocation());
        
                  //WEDNESDAY
                  row = sheet.getRow(5);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(0).getName()+"\n"+y.getWednesday().getCourse(0).getLocation());
        
                  row = sheet.getRow(16);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(1).getName()+"\n"+y.getWednesday().getCourse(1).getLocation());
        
                  row = sheet.getRow(27);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(2).getName()+"\n"+y.getWednesday().getCourse(2).getLocation());
        
                  row = sheet.getRow(38);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(3).getName()+"\n"+y.getWednesday().getCourse(3).getLocation());
                  
                  row = sheet.getRow(45);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(4).getName()+"\n"+y.getWednesday().getCourse(4).getLocation());
        
                  row = sheet.getRow(56);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(5).getName()+"\n"+y.getWednesday().getCourse(5).getLocation());
                  
                  row = sheet.getRow(67);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(6).getName()+"\n"+y.getWednesday().getCourse(6).getLocation());
        
                  row = sheet.getRow(78);
                  cell = row.getCell(3);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(7).getName()+"\n"+y.getWednesday().getCourse(7).getLocation());
        
                  //THURSDAY
                  row = sheet.getRow(2);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(0).getName()+"\n"+y.getThursday().getCourse(0).getLocation());
        
                  row = sheet.getRow(12);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(1).getName()+"\n"+y.getThursday().getCourse(1).getLocation());
        
                  row = sheet.getRow(21);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(2).getName()+"\n"+y.getThursday().getCourse(2).getLocation());
        
                  row = sheet.getRow(30);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(3).getName()+"\n"+y.getThursday().getCourse(3).getLocation());
        
                  row = sheet.getRow(41);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(4).getName()+"\n"+y.getThursday().getCourse(4).getLocation());
                  
                  row = sheet.getRow(49);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(5).getName()+"\n"+y.getThursday().getCourse(5).getLocation());
        
                  row = sheet.getRow(59);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(6).getName()+"\n"+y.getThursday().getCourse(6).getLocation());
        
                  row = sheet.getRow(69);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(7).getName()+"\n"+y.getThursday().getCourse(7).getLocation());
        
                  row = sheet.getRow(79);
                  cell = row.getCell(4);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(8).getName()+"\n"+y.getThursday().getCourse(8).getLocation());
        
                  //FRIDAY
                  row = sheet.getRow(5);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(0).getName()+"\n"+y.getFriday().getCourse(0).getLocation());
        
                  row = sheet.getRow(16);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(1).getName()+"\n"+y.getFriday().getCourse(1).getLocation());
        
                  row = sheet.getRow(27);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(2).getName()+"\n"+y.getFriday().getCourse(2).getLocation());
                  
                  row = sheet.getRow(38);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(3).getName()+"\n"+y.getFriday().getCourse(3).getLocation());
        
                  row = sheet.getRow(45);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(4).getName()+"\n"+y.getFriday().getCourse(4).getLocation());
        
                  row = sheet.getRow(56);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(5).getName()+"\n"+y.getFriday().getCourse(5).getLocation());
        
                  row = sheet.getRow(67);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(6).getName()+"\n"+y.getFriday().getCourse(6).getLocation());
        
                  row = sheet.getRow(78);
                  cell = row.getCell(5);
                  cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(7).getName()+"\n"+y.getFriday().getCourse(7).getLocation());
                             
                  //write changes and close fileOut
                  wb.write(fileOut);   
                  fileOut.close();  
                  System.out.println("Success");
              }
          }
      }
  }
  
  public void printClassSchedule(String grade) throws Exception{
      int courseindex = -1;
      if(grade.indexOf("9")>=0){
          courseindex = 0;
      }else if(grade.indexOf("10")>=0){
          courseindex = 1;
      }else if(grade.indexOf("11")>=0){
          courseindex = 2;
      }else if(grade.indexOf("12")>=0){
          courseindex = 3;
      }else{System.out.println("Please enter a number from 9 to 12 and try again."); return;}
      
      //make a copy of the clean schedule with the name of the selected student
      File CleanSchedule = new File("QuarterlyCalendar_2016.xls");
      FileChannel inputChannel = null;
      FileChannel outputChannel = null;
      int gradeByCourseIndex = courseindex + 9;
      String SNAME = "Grade "+gradeByCourseIndex+".xls";
      try{
          inputChannel = new FileInputStream(CleanSchedule).getChannel();
          outputChannel = new FileOutputStream(SNAME).getChannel();
          outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
      } finally {
          inputChannel.close();
          outputChannel.close();
      }
      
      InputStream inp = new FileInputStream(SNAME);
      Workbook wb = WorkbookFactory.create(inp);
      FileOutputStream fileOut = new FileOutputStream(SNAME);
          
      int counterOfStudents = 0;
      for(Student y : bob.get(courseindex)){
          counterOfStudents++;
      }
      for(int iii = 1; iii<counterOfStudents; iii++){
          wb.cloneSheet(0);
      }
      int anotherCounter = 0;
      for(Student y : bob.get(courseindex)){
          System.out.print("Printing "+y.getFullName()+" ");
          System.out.println(y.getGrade()+" : ");
          
          Sheet sheet = wb.getSheetAt(anotherCounter);
          wb.setSheetName(anotherCounter, y.getFullName());
          
          //modify the student schedule
          //name and grade
          Row row = sheet.getRow(0);
          Cell cell = row.getCell(0);
          cell.setCellValue(y.getFullName().substring(y.getFullName().indexOf(",")+1)+" "+y.getFullName().substring(0, y.getFullName().indexOf(",")));
          
          row = sheet.getRow(0);
          cell = row.getCell(4);
          cell.setCellValue("Grade: "+y.getGrade());
          
          //MONDAY
          row = sheet.getRow(5);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(0).getName()+"\n"+y.getMonday().getCourse(0).getLocation());
          
          row = sheet.getRow(16);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(1).getName()+"\n"+y.getMonday().getCourse(1).getLocation());
          
          row = sheet.getRow(27);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(2).getName()+"\n"+y.getMonday().getCourse(2).getLocation());
          
          row = sheet.getRow(38);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(3).getName()+"\n"+y.getMonday().getCourse(3).getLocation());
          
          row = sheet.getRow(45);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(4).getName()+"\n"+y.getMonday().getCourse(4).getLocation());
          
          row = sheet.getRow(56);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(5).getName()+"\n"+y.getMonday().getCourse(5).getLocation());
          
          row = sheet.getRow(67);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(6).getName()+"\n"+y.getMonday().getCourse(6).getLocation());
          
          row = sheet.getRow(78);
          cell = row.getCell(1);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getMonday().getCourse(7).getName()+"\n"+y.getMonday().getCourse(7).getLocation());
          
          //TUESDAY
          row = sheet.getRow(5);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(0).getName()+"\n"+y.getTuesday().getCourse(0).getLocation());
          
          row = sheet.getRow(16);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(1).getName()+"\n"+y.getTuesday().getCourse(1).getLocation());
          
          row = sheet.getRow(27);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(2).getName()+"\n"+y.getTuesday().getCourse(2).getLocation());
          
          row = sheet.getRow(38);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(3).getName()+"\n"+y.getTuesday().getCourse(3).getLocation());
          
          row = sheet.getRow(45);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(4).getName()+"\n"+y.getTuesday().getCourse(4).getLocation());

          row = sheet.getRow(56);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(5).getName()+"\n"+y.getTuesday().getCourse(5).getLocation());

          row = sheet.getRow(67);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(6).getName()+"\n"+y.getTuesday().getCourse(6).getLocation());

          row = sheet.getRow(78);
          cell = row.getCell(2);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getTuesday().getCourse(7).getName()+"\n"+y.getTuesday().getCourse(7).getLocation());

          //WEDNESDAY
          row = sheet.getRow(5);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(0).getName()+"\n"+y.getWednesday().getCourse(0).getLocation());

          row = sheet.getRow(16);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(1).getName()+"\n"+y.getWednesday().getCourse(1).getLocation());

          row = sheet.getRow(27);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(2).getName()+"\n"+y.getWednesday().getCourse(2).getLocation());

          row = sheet.getRow(38);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(3).getName()+"\n"+y.getWednesday().getCourse(3).getLocation());
          
          row = sheet.getRow(45);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(4).getName()+"\n"+y.getWednesday().getCourse(4).getLocation());

          row = sheet.getRow(56);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(5).getName()+"\n"+y.getWednesday().getCourse(5).getLocation());
          
          row = sheet.getRow(67);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(6).getName()+"\n"+y.getWednesday().getCourse(6).getLocation());

          row = sheet.getRow(78);
          cell = row.getCell(3);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getWednesday().getCourse(7).getName()+"\n"+y.getWednesday().getCourse(7).getLocation());

          //THURSDAY
          row = sheet.getRow(2);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(0).getName()+"\n"+y.getThursday().getCourse(0).getLocation());

          row = sheet.getRow(12);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(1).getName()+"\n"+y.getThursday().getCourse(1).getLocation());

          row = sheet.getRow(21);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(2).getName()+"\n"+y.getThursday().getCourse(2).getLocation());

          row = sheet.getRow(30);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(3).getName()+"\n"+y.getThursday().getCourse(3).getLocation());

          row = sheet.getRow(41);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(4).getName()+"\n"+y.getThursday().getCourse(4).getLocation());
          
          row = sheet.getRow(49);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(5).getName()+"\n"+y.getThursday().getCourse(5).getLocation());

          row = sheet.getRow(59);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(6).getName()+"\n"+y.getThursday().getCourse(6).getLocation());

          row = sheet.getRow(69);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(7).getName()+"\n"+y.getThursday().getCourse(7).getLocation());

          row = sheet.getRow(79);
          cell = row.getCell(4);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getThursday().getCourse(8).getName()+"\n"+y.getThursday().getCourse(8).getLocation());

          //FRIDAY
          row = sheet.getRow(5);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(0).getName()+"\n"+y.getFriday().getCourse(0).getLocation());

          row = sheet.getRow(16);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(1).getName()+"\n"+y.getFriday().getCourse(1).getLocation());

          row = sheet.getRow(27);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(2).getName()+"\n"+y.getFriday().getCourse(2).getLocation());
          
          row = sheet.getRow(38);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(3).getName()+"\n"+y.getFriday().getCourse(3).getLocation());

          row = sheet.getRow(45);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(4).getName()+"\n"+y.getFriday().getCourse(4).getLocation());

          row = sheet.getRow(56);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(5).getName()+"\n"+y.getFriday().getCourse(5).getLocation());

          row = sheet.getRow(67);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(6).getName()+"\n"+y.getFriday().getCourse(6).getLocation());

          row = sheet.getRow(78);
          cell = row.getCell(5);
          cell.setCellValue(cell.getStringCellValue()+"\n"+y.getFriday().getCourse(7).getName()+"\n"+y.getFriday().getCourse(7).getLocation());
          
          //write changes and close fileOut
             
           
          anotherCounter++;
      }
      wb.write(fileOut);
      fileOut.close(); 
      System.out.println("Success");
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
  
  public void addCourse (String studentName, Course c1, char slot1){
      
    /**
    make char arrays for slots in order for days, for each day of the week;
    
    for each student
        if (names match)
            for each day
                for each class
                    if (slots match up, and the original slot is empty)
                        add c1 in to the spot
                
    */  
    
    ArrayList<Character> MondaySlots = new ArrayList<Character>();
    
    MondaySlots.add('c');
    MondaySlots.add('a');
    MondaySlots.add('d');
    MondaySlots.add('h');
    MondaySlots.add('b');
    MondaySlots.add('g');
    MondaySlots.add('e');
    
    ArrayList<Character> TuesdaySlots = new ArrayList<Character>();
    
    TuesdaySlots.add('d');
    TuesdaySlots.add('a');
    TuesdaySlots.add('e');
    TuesdaySlots.add('h');
    TuesdaySlots.add('b');
    TuesdaySlots.add('f');
    TuesdaySlots.add('c');
    
    ArrayList<Character> WednesdaySlots = new ArrayList<Character>();
    
    WednesdaySlots.add('e');
    WednesdaySlots.add('a');
    WednesdaySlots.add('f');
    WednesdaySlots.add('h');
    WednesdaySlots.add('b');
    WednesdaySlots.add('c');
    WednesdaySlots.add('g');
    
    ArrayList<Character> ThursdaySlots = new ArrayList<Character>();
    
    ThursdaySlots.add('f');
    ThursdaySlots.add('a');
    ThursdaySlots.add('m');
    ThursdaySlots.add('z');
    ThursdaySlots.add('g');
    ThursdaySlots.add('b');
    ThursdaySlots.add('c');
    ThursdaySlots.add('d');
    
    ArrayList<Character> FridaySlots = new ArrayList<Character>();
    
    FridaySlots.add('g');
    FridaySlots.add('a');
    FridaySlots.add('e');
    FridaySlots.add('h');
    FridaySlots.add('b');
    FridaySlots.add('d');
    FridaySlots.add('f');
    
    boolean isClassEmpty = false;
    
    
    for(ArrayList<Student> x : bob){
           for(Student y : x){
              if (y.getFullName().equals(studentName)){
                  
                  for(int i = 0; i < 7; i++){
                      if(MondaySlots.indexOf(slot1) == i &&
                           y.getMonday().getCourse(i).getSlot() == 'x'){
                          isClassEmpty = true;
                          y.getMonday().getCourse(i).cName(c1.getName());
                          y.getMonday().getCourse(i).cStartTime(c1.getStartTime());
                          y.getMonday().getCourse(i).cEndTime(c1.getEndTime());
                          y.getMonday().getCourse(i).cLocation(c1.getLocation());
                          y.getMonday().getCourse(i).cSlot(c1.getSlot());
                          y.getMonday().getCourse(i).cTeacher(c1.getTeacher());
                          y.getMonday().getCourse(i).cPosition(c1.getPosition());
                      }
                      
                      if(TuesdaySlots.indexOf(slot1) == i &&
                           y.getTuesday().getCourse(i).getSlot() == 'x'){
                          isClassEmpty = true;
                          y.getTuesday().getCourse(i).cName(c1.getName());
                          y.getTuesday().getCourse(i).cStartTime(c1.getStartTime());
                          y.getTuesday().getCourse(i).cEndTime(c1.getEndTime());
                          y.getTuesday().getCourse(i).cLocation(c1.getLocation());
                          y.getTuesday().getCourse(i).cSlot(c1.getSlot());
                          y.getTuesday().getCourse(i).cTeacher(c1.getTeacher());
                          y.getTuesday().getCourse(i).cPosition(c1.getPosition());
                      }
                      
                      if(WednesdaySlots.indexOf(slot1) == i &&
                           y.getWednesday().getCourse(i).getSlot() == 'x'){
                          isClassEmpty = true;
                          y.getWednesday().getCourse(i).cName(c1.getName());
                          y.getWednesday().getCourse(i).cStartTime(c1.getStartTime());
                          y.getWednesday().getCourse(i).cEndTime(c1.getEndTime());
                          y.getWednesday().getCourse(i).cLocation(c1.getLocation());
                          y.getWednesday().getCourse(i).cSlot(c1.getSlot());
                          y.getWednesday().getCourse(i).cTeacher(c1.getTeacher());
                          y.getWednesday().getCourse(i).cPosition(c1.getPosition());
                      }
                      
                      if(ThursdaySlots.indexOf(slot1) == i &&
                           y.getThursday().getCourse(i).getSlot() == 'x'){
                          isClassEmpty = true;
                          y.getThursday().getCourse(i).cName(c1.getName());
                          y.getThursday().getCourse(i).cStartTime(c1.getStartTime());
                          y.getThursday().getCourse(i).cEndTime(c1.getEndTime());
                          y.getThursday().getCourse(i).cLocation(c1.getLocation());
                          y.getThursday().getCourse(i).cSlot(c1.getSlot());
                          y.getThursday().getCourse(i).cTeacher(c1.getTeacher());
                          y.getThursday().getCourse(i).cPosition(c1.getPosition());
                      }
                      
                      if(FridaySlots.indexOf(slot1) == i &&
                           y.getFriday().getCourse(i).getSlot() == 'x'){
                          isClassEmpty = true;
                          y.getFriday().getCourse(i).cName(c1.getName());
                          y.getFriday().getCourse(i).cStartTime(c1.getStartTime());
                          y.getFriday().getCourse(i).cEndTime(c1.getEndTime());
                          y.getFriday().getCourse(i).cLocation(c1.getLocation());
                          y.getFriday().getCourse(i).cSlot(c1.getSlot());
                          y.getFriday().getCourse(i).cTeacher(c1.getTeacher());
                          y.getFriday().getCourse(i).cPosition(c1.getPosition());
                      }
                      
                  }
              }
            }
          }
     
    
    if (!isClassEmpty){
        System.out.println("there's already a class during that time");
    }
  }
  
  public void addExistingCourse(String studentName, String newCourse, char courseSlot){
     //the old course is a free so its courseName is an empty String. Therefore the switchClass method works fine here.
     switchCourses(studentName, "", newCourse, courseSlot);
  }
  
  //goes through each slot and repeats the same method
  public void addExistingCourse(String studentName, String newCourse){
     char[] allSlots = {'C','A','D','H','B','G','E','F'};
     for(char x : allSlots){
         switchCourses(studentName, "", newCourse, x);
     }
  } 
  
  //method that removes a course from a Student's schedule (dropping a course)
  public void removeCourse(String studentName, String courseToRemove){
     //do stuff
     
     System.out.println(courseToRemove +" has been removed");
  }
  
  //method that returns all available courses
  public String[] getAllCourses(){
      String[] arrayOfAllCourses = setOfAllCourses.toArray(new String[setOfAllCourses.size()]);
      Arrays.sort(arrayOfAllCourses);
      return arrayOfAllCourses;
  }
  
  //method that returns all students by last name
  public String[] getAllStudents(){
      String[] arrayOfAllStudents = setOfAllStudents.toArray(new String[setOfAllStudents.size()]);
      Arrays.sort(arrayOfAllStudents);
      return arrayOfAllStudents;
  }
  
  //method that returns all students by first name
  public String[] getAllStudentsR(){
      String[] arrayOfAllStudentsR = setOfAllStudentsR.toArray(new String[setOfAllStudentsR.size()]);
      Arrays.sort(arrayOfAllStudentsR);
      return arrayOfAllStudentsR;
  }
  
  //method that returns all course locations
  public String[] getAllCourseLocations(){
      String[] arrayOfAllCourseLocations = setOfAllCourseLocations.toArray(new String[setOfAllCourseLocations.size()]);
      Arrays.sort(arrayOfAllCourseLocations);
      return arrayOfAllCourseLocations;
  }
  
}