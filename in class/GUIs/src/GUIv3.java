
/**
 * Here we make use of javax.swing.SwingWorker, which allows
 * us to create a new thread (in addition to the event dispatch
 * thread) to handle the calculations in our program.
 * 
 * The SwingWorker allows us to *publish* data at certain intervals,
 * which is then *processed* to determine how to use it
 * to update the GUI.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIv3 extends JFrame
{
    private JTextField tf = new JTextField(10);
    private JButton b = new JButton("Go!");
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // create and launch a new thread
            (new UpdateTextFieldThread()).execute();
        }
    }

    // This class extends SwingWorker and specifies what actions
    //  should take place in our new thread.  The code in the
    //  doInBackground method specifies what should happen when
    //  the thread is executed.  The code in the process method
    //  specifies what should happen each time publish is called.
    // 1st generic type parameter - what is returned from doInBackground()
    // 2nd generic type parameter - the type of data to publish whenever publish is called
    private class UpdateTextFieldThread extends SwingWorker<Void, Integer>
    {
        static final int THREAD_DELAY = 100;

        protected Void doInBackground()
        {
            for (int i = 0; i < 50; i++) {
                publish(i);
                try {
                    Thread.sleep(THREAD_DELAY);
                } catch (InterruptedException e) { }
            }
            return null;
        }

        // The parameter here is a list of all published data
        //  since the last call to process.  We are interested
        //  in displaying only the latest one on the GUI.
        protected void process(java.util.List<Integer> list)
        {
            tf.setText("" + list.get(list.size() - 1));
        }
    }
    
    public GUIv3()
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
        new GUIv3();
    }
}
