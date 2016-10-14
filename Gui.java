import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui extends JFrame
{
    public Gui(String path) throws Exception{
        super("Schedule Manager");
        setLayout(new FlowLayout());
        AlphaPoi obj = new AlphaPoi(path);
        
        //course list sorted alphabetically
        JComboBox cb1 = new JComboBox(obj.getAllCourses());
        cb1.setEditable(true);
        
        JCheckBox switchSearch = new JCheckBox("Search by first name");
        
        JComboBox studentPicker = new JComboBox(obj.getAllStudents());
        AutoCompletion.enable(studentPicker);
        studentPicker.setEditable(true);
        JButton b1 = new JButton("Create student schedule");
        b1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    obj.printStudentSchedule(studentPicker.getSelectedItem().toString());
                } catch (Exception p){System.out.println(p);};
            }
        });
        add(b1);
        add(studentPicker);
        
        String[] grades = {"9","10","11","12"};
        JComboBox classPicker = new JComboBox(grades);
        classPicker.setEditable(true);
        JButton b2 = new JButton("Create class schedule");
        b2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    obj.printClassSchedule(classPicker.getSelectedItem().toString());
                } catch (Exception p){System.out.println(p);};
            }
        });
        add(b2);
        add(classPicker);
        
        JButton b3 = new JButton("Drop a course");
        
        

        
    }   
}
