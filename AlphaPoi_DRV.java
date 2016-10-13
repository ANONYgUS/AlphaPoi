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
        
        
        Gui frame = new Gui(pathToFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}
