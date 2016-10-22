import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class AlphaPoi_DRV
{
    public static void main(String[] args) throws Exception{
        
        JFileChooser chooseCourseID = new JFileChooser();
        String pathToFile = "";
        if (chooseCourseID.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File courseIDFile = chooseCourseID.getSelectedFile();
            pathToFile = courseIDFile.getAbsolutePath();
        }
        
        String pathToDROPS = "";
        if (chooseCourseID.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File DROPS = chooseCourseID.getSelectedFile();
            pathToDROPS = DROPS.getAbsolutePath();
        }
        
        
        Gui frame = new Gui(pathToFile, pathToDROPS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
