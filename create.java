import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import jxl.write.*;
import java.text.SimpleDateFormat;
 
class  create
{
  public static void main(String[] args) throws Exception
  {
	//get indexes of where each Student's information starts in the csv file (searched for " character)
	ArrayList indexesStudent = new ArrayList<Int>();
    for row in csvfile{
		if row has "\""{
			indexes.add(csvfile row)
		} 
	}
	
	Scanner s = new Scanner(pathToCSVFile);
	
	for(int i = 0; i<indexesStudent.size(); i++){
		int start = indexesStudent.get(i);
		int end = indexesStudent.get(i+1);
		
		//a String containing the block of information for each student
		String infoStudent;
		for(k = 0; k < (end-start); k++){
			infoStudent += s.nextLine()+"\n"
		}
		
		String infoMonday = infoStudent.substring(infoStudent.indexOf("Monday")+74, infoStudent.indexOf("Tuesday");
		Scanner m = new Scanner(infoMonday);
		
		//how many courses on Monday
		ArrayList infoMondayByString = new ArrayList<String>();
		
		while(m.hasNextLine()){
			infoMondayByString.add(m.nextLine());
		}
		
		for(String currentLine: infoMondayByString){
			String[] input = currentLine.split(",");
            className = input[0];
            startTime = input[1];
            endTime = input[2];
			location = input[3];
			slot = input[4];
			teacher = input[5];
		}
		
		
		
	}
	
	
	
	Student class
		every student object has 5 instance variables, for each weekday.
		Day monday
		Day tuesday
		Day wednesday
		SDay thursday
		Day friday
	
	Day class
		every day has 7 instance variables, for each class
		String class1
		String class2
		String class3
		String class4
		String class5
		String class6
		String class7
	
	SDay class
		every day has 8 instance variables, for each class
		String class1
		String class2
		String class3
		String class4
		String class5
		String class6
		String class7
		String class8
    
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