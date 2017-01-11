
/**
 * In this revision, we attempt to slow down the GUI updates by
 * calling Thread.sleep() after each iteration.  Unfortunately,
 * this interferes with Swing's event dispatch thread, which is
 * responsible for drawing things to the screen.  So we still end
 * up seeing only the last number (although it takes much longer
 * this time).
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIv2 extends JFrame
{
    private JTextField tf = new JTextField(10);
    private JButton b = new JButton("Go!");
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            final int THREAD_DELAY = 50;
            for (int i = 0; i < 50; i++) {
                tf.setText("" + i);
                try {
                    Thread.sleep(THREAD_DELAY);
                } catch (InterruptedException ex) { }
            }
        }
    }
    
    public GUIv2()
    {
        setSize(500, 300);
        setTitle("Dynamic GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        b.addActionListener(new ButtonHandler());
        
        JPanel cp = new JPanel();
        cp.add(tf);
        cp.add(b);
        setContentPane(cp);
        
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new GUIv2();
    }
}
