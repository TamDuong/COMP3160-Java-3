
/**
 * First attempt at a GUI that dynamically updates an element.
 * We find that the GUI shows only the initial and final states;
 * it does not show any intermediate steps.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIv1 extends JFrame
{
    private JTextField tf = new JTextField(16);			// number is the size of txt field
    private JButton b = new JButton("Go!");
    
    private class ButtonHandler implements ActionListener		// class inside of GUIv1 for button
    {
        public void actionPerformed(ActionEvent e)
        {
            for (int i = 0; i < 50; i++)
                tf.setText("" + i);
        }
    }
    
    public GUIv1()							// GUIv1 constructor
    {
        setSize(400, 400);			// width length of the window 
        setTitle("Dynamic GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        b.addActionListener(new ButtonHandler());
        
        // add button and textfield into a box call JPanel
        JPanel cp = new JPanel();
        cp.add(tf);
        cp.add(b);
        setContentPane(cp);						//add the JPanel to window
        
        setVisible(true);						// make the window visible 
    }
    
    public static void main(String[] args)
    {
        new GUIv1();
    }
}
