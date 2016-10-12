import java.util.*;
import java.io.*;
/**
 * Write a description of class Organize here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlphaPoi_DRV
{
    public static void main(String[] args) throws Exception{
        AlphaPoi obj = new AlphaPoi("studentSchedules16_2.csv");
        obj.printClassSchedule("11");
    }
}
