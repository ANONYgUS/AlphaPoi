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
    
    System.out.println(indexesStudent.size());
    
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
        
        int counter = 0;
        for(String currentLine: infoMondayByString){
            System.out.println(currentLine);
            String[] input = currentLine.split(",");
            String className = input[0];
            String startTime = input[1];
            String endTime = input[2];
            String location = input[3];
            char slot = input[4].charAt(0);
            String teacher = input[5];
            //MondayClasses.get(counter) = new Class_class(className, startTime, endTime, location, slot, teacher);
            counter ++;
        }
        
        
        for(int a = 0; a < 7; a++){
            for(int nn = 0; nn< counter; nn++){
                if(MondayClasses.get(nn).getSlot() == MondaySlots[a]){
                    MondayClasses.get(nn).cPosition(a+1);
                    break;
                }
            }
        }
        System.out.println(c1.getPosition());
        System.out.println(c2.getPosition());
        System.out.println(c3.getPosition());
        System.out.println(c4.getPosition());
        System.out.println(c5.getPosition());
        System.out.println(c6.getPosition());
        System.out.println(c7.getPosition());
        
        
        
        
        
        
        
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