import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Gui extends JFrame
{
    public Gui(String p) throws Exception{
        super("Schedule Manager");
        setLayout(new FlowLayout());
        AlphaPoi obj = new AlphaPoi(p);
        
        
        
        JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(250, 24));
        add(t1);
        JButton b1 = new JButton("print student schedule");
        b1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    obj.printStudentSchedule(t1.getText());
                } catch (Exception p){System.out.println(p);};
            }
        });
        add(b1);
        
        JTextField t2 = new JTextField();
        t2.setPreferredSize(new Dimension(250, 24));
        add(t2);
        JButton b2 = new JButton("print class schedule");
        b2.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    obj.printClassSchedule(t2.getText());
                } catch (Exception p){System.out.println(p);};
            }
        });
        add(b2);
        
        

        
    }   
}
