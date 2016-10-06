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
	ArrayList indexes = new ArrayList<Int>();
    for row in csvfile{
		if row has "\""{
			indexes.add(csvfile row)
		} 
	}
	
	student
		weekdays
			slots
    
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