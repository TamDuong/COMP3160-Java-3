import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


public class SortingVisualizer extends JFrame{
	private Bar[] theBars = new Bar[100];
	private JButton Bsort = new JButton("Bubble sort"),		Ssort = new JButton("Selection sort"),
					Isort = new JButton("Insertion sort"),	Shsort = new JButton("Shell sort"),
					Msort = new JButton("Merge sort"),		Hsort = new JButton("Heapsort"),
					Qsort = new JButton("Quicksort"),		Mosort = new JButton("Monkeysort"),
					shuffle = new JButton("Shuffle"),		Pause = new JButton("Pause"),
					Continue = new JButton("Continue"),		Stop = new JButton("Stop");
	private JPanel ButtonContainer1 = new JPanel(),
				   ButtonContainer2 = new JPanel(),
				   ButtonContainer =  new JPanel();			// buttonContainer = allButtonContainer from 1 and 2
	private JTextField statusfield = new JTextField("");	//textfield ONLY show the current sorting algorithm
						// so when pause or continue is pressed we still know what sorting algorithm is selected
	// maintains a reference to the currently executing thread
    // (so we can cancel it if necessary)
	private UpdateScreenThread currentThread;
	private JPanel BarContainer = new JPanel();
	
	
	// UpdateScreenThread class
	private class UpdateScreenThread extends SwingWorker<Void, Integer>
	{
		boolean freeze;							// to stop the screen 
		static final int THREAD_DELAY = 30;		// speed of the bars moving
		
		@Override
		protected Void doInBackground(){
		switch(statusfield.getText()){
			case "Bubble sort":	for (int j = 0; j < theBars.length; j++)
							    for (int i = 0; i < theBars.length - 1; i++) {
									if (theBars[i].length > theBars[i + 1].length) {
									    int temp = theBars[i].length;
									    theBars[i].length = theBars[i + 1].length;
									    theBars[i + 1].length = temp;
									}
									callPublish();
							    }
								break;
								
			case "Selection sort":	for (int j = 0; j < theBars.length; j++) {
								    // find the minimum element starting from index j
								    int min = theBars[j].length, minIndex = j;
								    for (int i = j + 1; i < theBars.length; i++) {
									if (theBars[i].length < min) {
									    min = theBars[i].length;
									    minIndex = i;
									}
									callPublish();
								    }
								    // swap the minimum element with index j
								    int temp = theBars[j].length;
								    theBars[j].length = theBars[minIndex].length;
								    theBars[minIndex].length = temp;	
									}
									break;
									
			case "Insertion sort":	for (int i = 1; i < theBars.length; i++) {
									    int thingToInsert = theBars[i].length;
									    // find out where to insert this item
									    int j = i - 1;
									    while (j >= 0 && thingToInsert < theBars[j].length) {
										theBars[j+1].length = theBars[j].length;
										j--;
										callPublish();
									    } 
									    theBars[j+1].length = thingToInsert;
									}
									break;
									
			case "Shell sort":	int gapSize = theBars.length / 2;
								while (gapSize > 0) {
								    for (int k = 0; k < gapSize; k++) {
									for (int i = gapSize + k; i < theBars.length; i += gapSize) {
									    int toInsert = theBars[i].length;
					
									    int j = i - gapSize;
									    while (j >= 0 && toInsert < theBars[j].length) {
										theBars[j + gapSize].length = theBars[j].length;
										j -= gapSize;
										callPublish();
									    }
					
									    theBars[j + gapSize].length = toInsert;
									}
								    }
								    gapSize = (gapSize == 2) ? 1 : (int) (gapSize / 2.2);
								}
								break;
									
			case "Merge sort":	mergeSort(theBars, 0, theBars.length - 1, "");
								break;
							    
			case "Heapsort":	for (int i = 1; i < theBars.length; i++) {
								    int cIndex = i, pIndex = (cIndex - 1) / 2;
					
								    while (cIndex > 0 && theBars[cIndex].length > theBars[pIndex].length) {
									int temp = theBars[cIndex].length;
									theBars[cIndex].length = theBars[pIndex].length;
									theBars[pIndex].length = temp;
									cIndex = pIndex;
									pIndex = (cIndex - 1) / 2;
									callPublish();
								 }
								}
								for (int i = 0; i < theBars.length; i++) {
					
								    // swap root with last element in the array
								    int topHeapElement = theBars[0].length;
								    theBars[0].length = theBars[theBars.length - 1 - i].length;
								    theBars[theBars.length - 1 - i].length = topHeapElement;
					
								    // work our way back down the heap, swapping as necessary
								    int pIndex = 0;
								    while (true) {
									// check for left child
									int lIndex = 2 * pIndex + 1;
									if (lIndex >= theBars.length - 1 - i) // no left child - heapify is done
										break;
					
									int cIndex = lIndex; // index of the greater child (initially we
											     // assume it's the left)
									// check for right child, and see if it's greater than the left
									int rIndex = lIndex + 1;
									if (rIndex < theBars.length - 1 - i && theBars[rIndex].length > theBars[lIndex].length)
									    cIndex = rIndex;
									// check if parent is less than greater child, swap if so
									if (theBars[pIndex].length < theBars[cIndex].length) {
									    int temp = theBars[cIndex].length;
									    theBars[cIndex].length = theBars[pIndex].length;
									    theBars[pIndex].length = temp;
									    pIndex = cIndex;
									} else
									    break; // parent is not < greater child - heapify is done!
									callPublish();
								    }
								}
								callPublish();
								break;
								
			case "Quicksort":	quicksort(theBars, 0, theBars.length - 1);
								callPublish();
								break;
								
			case "Monkeysort":	// for the array to be sorted then index at j have to always be < than index at i
								for (int i = 1; i < theBars.length; i++) {
									int j = i-1;
									if (theBars[j].length > theBars[i].length){
										//small shuffle
										for (int k = 0; k < theBars.length; k++) {
											int randomIndex = (int) (theBars.length*Math.random());
											int temp = theBars[k].length;
											theBars[k].length = theBars[randomIndex].length;
											theBars[randomIndex].length = temp;
										}
										callPublish();
										i=0;
									}
								}
								break;
		}
		ButtonContainer.remove(ButtonContainer2);
		ButtonContainer.add(ButtonContainer1);
		ButtonContainer.revalidate();
		ButtonContainer.repaint();
		currentThread.cancel(true);
		currentThread = null;
		return null;
		}
		
		protected void process(java.util.List<Integer> list) {
			BarContainer.repaint();
		}
		
		private void callPublish(){
			publish();
		    try {
                Thread.sleep(THREAD_DELAY);
                while(freeze)
                	Thread.sleep(1);
            } catch (InterruptedException e) { }
		}
		
		private void quicksort(Bar[] a, int start, int end)
	    {
			if (start < end) { // base case is when start is no longer < end (i.e.,
					   // nothing to sort)
			    int j = quicksortPartition(a, start, end);
			    quicksort(a, start, j - 1);
			    quicksort(a, j + 1, end);
			}
	    }
		
		private int quicksortPartition(Bar[] a, int start, int end)
	    {
			// pick a pivot
			int pivot = a[start].length;
	
			int lower = start, upper = end;
			while (lower < upper) {
			    // look for the first element (from the left) that's greater than
			    // the pivot
			    while (lower < end) {
				if (a[lower].length > pivot)
				    break;
				callPublish();
				lower++;
			    }
	
			    // look for the first element (from the right) that's less than or
			    // equal to the pivot
			    while (upper > start) {
				if (a[upper].length <= pivot)
				    break;
				callPublish();
				upper--;
			    }
	
			    // swap lower/upper indices if lower < upper
			    if (lower < upper) {
				int temp = a[lower].length;
				a[lower].length = a[upper].length;
				a[upper].length = temp;
			    }
			}
	
			// swap the pivot (at index start) with index upper
			int temp = a[start].length;
			a[start].length = a[upper].length;
			a[upper].length = temp;
	
			return upper;
	    }
		
		private void mergeSort(Bar[] a, int start, int end, String s)
	    {
		// base case is when start/end are the same -- in this case there's only
		// one element, so no action is needed
		if (end - start > 0) {
		    // find the middle index
		    int mid = (start + end) / 2;
	
		    // sort each half
		    mergeSort(a, start, mid, s + " ");
		    mergeSort(a, mid + 1, end, s + " ");
	
		    // temp array to hold the merged elements
		    int[] temp = new int[end - start + 1];
	
		    // i tracks the position in the left half, j tracks position in
		    // right half, k tracks position in merged array
		    int i = start, j = mid + 1, k = 0;
		    while (i <= mid && j <= end) {
			if (a[i].length < a[j].length)
			    temp[k++] = a[i++].length;
			else
			    temp[k++] = a[j++].length;
		    }
	
		    // copy remaining elements from left half
		    while (i <= mid)
			temp[k++] = a[i++].length;
		    // copy remaining elements from right half
		    while (j <= end)
			temp[k++] = a[j++].length;
			
		    // the merged elements are in a temp array right now, so copy them
		    // back into original array
		    for (i = 0; i < temp.length; i++) {
			a[start + i].length = temp[i];
			callPublish();
		    }
		    callPublish();
		}
	    }
	}
	
	// ButtonHandler class - react to button pressed
		private class ButtonHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				Object src = e.getSource();
				if (src == shuffle){
					shuffle();
				} else if (src == Pause){
					currentThread.freeze = true;
					Continue.setEnabled(true);
					Pause.setEnabled(false);
				} else if (src == Continue){
					currentThread.freeze = false;
					Continue.setEnabled(false);
					Pause.setEnabled(true);
				} else if (src == Stop){
					statusfield.setText("");
					ButtonContainer.remove(ButtonContainer2);
					ButtonContainer.add(ButtonContainer1);
					ButtonContainer.revalidate();
					ButtonContainer.repaint();
					currentThread.freeze = true;
					currentThread.cancel(true);
					currentThread = null;
					Pause.setEnabled(true);
					// make all Bars look like the orginal when it new
					for (int i = 0; i < theBars.length; i ++){
						theBars[i].length = i*3;
						theBars[i].repaint();
					}
					BarContainer.repaint();
					shuffle();
				} else {
					statusfield.setText( ((JButton)src).getText() );
					ButtonContainer.remove(ButtonContainer1);
					ButtonContainer.add(ButtonContainer2);
					ButtonContainer.revalidate();
					ButtonContainer.repaint();
					Continue.setEnabled(false);
					(currentThread = new UpdateScreenThread()).execute();
				}
			}
		}
		
		// Bar class - represent 1 single bar
		private class Bar extends JPanel{
			private int length;				//the length of the bar vertically
			// Bar constructor
			public Bar(int length){
				this.length = length;
			}
			
			// the paintComponent method is called whenever this component is rendered on the screen
			public void paintComponent(Graphics g)
			{
			    g.setColor(Color.CYAN);
			    g.fillRect(0, getSize().height - length ,  6, length);	// 0, getSize().height is the bottom left corner of the component
			    				// 6, length indicates the width and height to fill for a bar
			    g.setColor(Color.BLACK);
			    g.drawRect(0, getSize().height - length, 6, length);
			}
		}
	
	
	
	// Constructor
	public SortingVisualizer()
	{	
		// add array of theBars to container
		for (int i = 0; i < theBars.length; i++)
			theBars[i] = new Bar(i*3);		// multiply by 3 just my custom to make the length of each bar look clearer
		BarContainer.setLayout(new GridLayout(1, theBars.length));
		for (int i = 0; i < theBars.length; i++){
			BarContainer.add(theBars[i]);
		}
		
		// add Button objects to a container
		ButtonHandler bh = new ButtonHandler();
		Bsort.addActionListener(bh);
		Ssort.addActionListener(bh);
		Isort.addActionListener(bh);
		Shsort.addActionListener(bh);
		Msort.addActionListener(bh);
		Hsort.addActionListener(bh);
		Qsort.addActionListener(bh);
		Mosort.addActionListener(bh);
		shuffle.addActionListener(bh);
		Pause.addActionListener(bh);
		Continue.addActionListener(bh);
		Stop.addActionListener(bh);
		// ButtonContainer1 include all sort button and shuffle
		ButtonContainer1.setLayout(new GridLayout(3, 3));
		ButtonContainer1.add(Bsort);
		ButtonContainer1.add(Ssort);
		ButtonContainer1.add(Isort);
		ButtonContainer1.add(Shsort);
		ButtonContainer1.add(Msort);
		ButtonContainer1.add(Hsort);
		ButtonContainer1.add(Qsort);
		ButtonContainer1.add(Mosort);
		ButtonContainer1.add(shuffle);
		// ButtonContainer2 include "Stop", "Pause" and "Continue"
		ButtonContainer2.setLayout(new GridLayout(3, 1));
		ButtonContainer2.add(Pause);
		ButtonContainer2.add(Continue);
		ButtonContainer2.add(Stop);
		
		ButtonContainer.setLayout(new GridLayout(1, 1));
		ButtonContainer.add(ButtonContainer1);		// first display all button in ButtonContainer1
							// when UpdateScreen is call then display all button in ButtonContainer2
		
		
		// add all Containers and statusfield to masterPanel
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		masterPanel.add(statusfield, BorderLayout.NORTH);
		masterPanel.add(BarContainer, BorderLayout.CENTER);
		masterPanel.add(ButtonContainer, BorderLayout.SOUTH);
		
		
		setTitle("Sorting Visualizer");
		setSize(605, 435);
		setContentPane(masterPanel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// method to shuffle all bars
	public void shuffle(){
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < theBars.length; j++) {
				int randomIndex = (int) (theBars.length*Math.random());
				// swap randomIndex and index at j
				int temp = theBars[j].length;
				theBars[j].length = theBars[randomIndex].length;
				theBars[randomIndex].length = temp;
			}
		BarContainer.repaint();
	}
		

	public static void main(String[] args){
		new SortingVisualizer();
	}
}
