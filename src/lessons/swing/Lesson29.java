package lessons.swing;

import java.awt.Font;

/* TOPIC: GridBag, Grid Layouts
 * 
 * */ 

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.GridLayout;

import java.awt.Insets;  // for padding

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Lesson29 extends JFrame {

	JButton button1, button2, button3, button4, button5, button6, button7, 
	button8, button9, buttonPlus, button0, buttonMinus, clearAll;
	
	JTextField textResult;
	
	public static void main(String[] args) {
		new Lesson29();
	}
	
	public Lesson29() {
		/* Use this to set the UIManager to local Machine Look-And-Feel!
		 * Otherwise it will look really ugly
		 * */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(UIManager.getSystemLookAndFeelClassName());
		this.setSize(400,  400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Calculator");
		
		// ---- GRID LAYOUT ---- ---- ---- ---- ----
		/* new GridLayout(nr_of_rows, nr_of_columns, )
		 * nr_of_rows     =  if 0, use as many as you need
		 * nr_of_columns  = 
		 * horizontal_gap = gap between components in pixel 
		 * vertical_gap   = gap between components in pixel
		 * 
		 * It fills each column in each row with one element
		 * 
		 * */ 
		JPanel thePanel = new JPanel();
		thePanel.setLayout(new GridLayout(0, 3, 2, 2));
		
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		buttonPlus = new JButton("+");
		button0 = new JButton("0");
		buttonMinus = new JButton("-");
		
		thePanel.add(button1);
		thePanel.add(button2);
		thePanel.add(button3);
		thePanel.add(button4);
		thePanel.add(button5);
		thePanel.add(button6);
		thePanel.add(button7);
		thePanel.add(button8);
		thePanel.add(button9);
		thePanel.add(buttonPlus);
		thePanel.add(button0);
		thePanel.add(buttonMinus);
		
		// ---- GRID BAG LAYOUT ---- ---- ---- ---- ----
		/* new GridLayout()
		 * -> constructor stays empty.
		 * Layout is created by defining GridBagConstraints
		 * */ 
		JPanel gridBagPanel = new JPanel();
		gridBagPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;  // Default x position of each element
		gridConstraints.gridy = 1;  // Default x position of each element
		gridConstraints.gridwidth = 1;  // Default how many columns an element spans
		gridConstraints.gridheight = 1;  // Default how many rows an element spans
		
		/* weight -> give LayoutManager a hint how to adjust component 
		 * widths so that everything fits into place
		 * if default value = 0 -> its fixed */
		gridConstraints.weightx = 50;
		gridConstraints.weighty = 100;
		
		gridConstraints.insets = new Insets(5, 5, 5, 5);  // 5 pixels of padding around each element
		
		// Where to align components if they do not completely fill a space
		gridConstraints.anchor = GridBagConstraints.CENTER;
		
		// Define how a component can be stretched to fill free space
		gridConstraints.fill = GridBagConstraints.BOTH;
		
		textResult = new JTextField("0", 20);
		// Font(font-name, type, font-seize)
		Font font = new Font("Helvetice", Font.BOLD, 18);
		textResult.setFont(font);
		
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		buttonPlus = new JButton("+");
		button0 = new JButton("0");
		buttonMinus = new JButton("-");
		clearAll = new JButton("C");
		
		gridBagPanel.add(clearAll, gridConstraints);
		
		gridConstraints.gridwidth = 20;  // Change to full widht
		gridConstraints.gridx = 5;  // Move field to the right
		
		gridBagPanel.add(textResult, gridConstraints);
		
		gridConstraints.gridwidth = 1;  // Change back
		gridConstraints.gridx = 1;  // Set to Left
		gridConstraints.gridy = 2;  // Set to first row
		gridBagPanel.add(button1, gridConstraints);
		
		gridConstraints.gridx = 5;  // Set to Center
		gridBagPanel.add(button2, gridConstraints);
		
		gridConstraints.gridx = 9;  // Set to Right
		gridBagPanel.add(button3, gridConstraints);

		gridConstraints.gridx = 1;  // Set to Left
		gridConstraints.gridy = 3;  // Set to Second row
		gridBagPanel.add(button4, gridConstraints);
		
		gridConstraints.gridx = 5;  // Set to Center
		gridBagPanel.add(button5, gridConstraints);
		
		gridConstraints.gridx = 9;  // Set to Right
		gridBagPanel.add(button6, gridConstraints);
		
		gridConstraints.gridx = 1;  // Set to Left
		gridConstraints.gridy = 4;  // Set to Second row
		gridBagPanel.add(button7, gridConstraints);
		
		gridConstraints.gridx = 5;  // Set to Center
		gridBagPanel.add(button8, gridConstraints);
		
		gridConstraints.gridx = 9;  // Set to Right
		gridBagPanel.add(button9, gridConstraints);
		
		gridConstraints.gridx = 1;  // Set to Left
		gridConstraints.gridy = 5;  // Set to Second row
		gridBagPanel.add(buttonPlus, gridConstraints);
		
		gridConstraints.gridx = 5;  // Set to Center
		gridBagPanel.add(button0, gridConstraints);

		gridConstraints.gridx = 9;  // Set to Right
		gridBagPanel.add(buttonMinus, gridConstraints);
		
		// ---- FINAL OPERATIONS ---- ---- ---- ---- ----
		//this.add(thePanel);
		this.add(gridBagPanel);
		this.setVisible(true);
		
	}
	
}
