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
        final String pathToFile = null;
        
        
        
        JTextField t1 = new JTextField();
        t1.setPreferredSize(new Dimension(250, 24));
        add(t1);
        JButton b1 = new JButton("print student schedule");
        b1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                    AlphaPoi obj = new AlphaPoi(p);
                    obj.printStudentSchedule(t1.getText());
                } catch (Exception p){System.out.println(p);};
            }
        });
        add(b1);

        
    }   
}
