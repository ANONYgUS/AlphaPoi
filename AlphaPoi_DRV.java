import java.util.*;
import java.io.*;

public class AlphaPoi_DRV
{
    public static void main(String[] args) throws Exception{
        AlphaPoi obj = new AlphaPoi("studentSchedules16_2.csv");
        obj.addCourse("Kniesche, Niklas", "Honors Chemistry");
        obj.printStudentSchedule("Kniesche, Niklas");
    }
}
