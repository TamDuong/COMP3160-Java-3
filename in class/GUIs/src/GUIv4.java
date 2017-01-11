
/**
 * Modifies GUIv3 to add a "Stop" button.  The stop button is
 * initially disabled, and becomes clickable only once the
 * thread has been launched.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIv4 extends JFrame
{
    private JTextField tf = new JTextField(10);
    private JButton bGo = new JButton("Go!");
    private JButton bStop = new JButton("Stop");

    // maintains a reference to the currently executing thread
    // (so we can cancel it if necessary)
    private UpdateTextFieldThread currentThread;
    
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Object src = e.getSource(); // determine which button was pressed
            if (src == bGo) {
                (currentThread = new UpdateTextFieldThread()).execute();
                bGo.setEnabled(false);
                bStop.setEnabled(true);
            } else if (src == bStop) {
                currentThread.cancel(true);
                currentThread = null;
                bGo.setEnabled(true);
                bStop.setEnabled(false);
            }
        }
    }

    // Changes from GUIv3:
    // - Added !isCancelled() in the for loop so that the thread will stop the loop if cancelled.
    // - Added the four lines from the ButtonHandler's actions for the stop button after the loop, so the
    //    thread automatically stops once the loop finishes.
    private class UpdateTextFieldThread extends SwingWorker<Void, Integer>
    {
        static final int THREAD_DELAY = 50;

        protected Void doInBackground()
        {
            for (int i = 0; !isCancelled() && i < 50; i++) {
                publish(i);
                try {
                    Thread.sleep(THREAD_DELAY);
                } catch (InterruptedException e) { }
            }
            currentThread.cancel(true);
            currentThread = null;
            bGo.setEnabled(true);
            bStop.setEnabled(false);
            return null;
        }

        protected void process(java.util.List<Integer> list)
        {
            tf.setText("" + list.get(list.size() - 1));
        }
    }
    
    public GUIv4()
    {
        setSize(500, 300);
        setTitle("Dynamic GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ButtonHandler bh = new ButtonHandler();
        bGo.addActionListener(bh);
        bStop.addActionListener(bh);
        bStop.setEnabled(false);
        
        JPanel cp = new JPanel();
        cp.add(tf);
        cp.add(bGo);
        cp.add(bStop);
        setContentPane(cp);
        
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new GUIv4();
    }
}
