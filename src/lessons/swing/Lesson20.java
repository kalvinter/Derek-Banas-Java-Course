package lessons.swing;

/* GUI Introduction
 * 
 * Basic Terms:
 * 		Window -> Called Frame in Java
 * 		Panel  -> Contains all components 
 * 
 * 1) Class has to extend JFrame
 * 
 * */

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class Lesson20 extends JFrame {

	public static void main(String[] args) {
		new Lesson20();
		
	}
	
	public Lesson20() {

		this.setSize(400, 400);
		
		// Toolkit, several utils for GUI development
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		// Get screen size to size everything relative to the size of the screen
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width / 2) - (this.getWidth());
		int yPos = (dim.height / 2) - (this.getHeight());

		this.setLocation(xPos, yPos);
		
		this.setResizable(false);  // Is true by default
		
		// By default, program does not stop when X-button is clicked
		// This enables closing the program by closing the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("My first Frame");
		
		// All components should be in Panels (not directly in the window)
		JPanel thePanel = new JPanel();
		
		JLabel label1 = new JLabel("Tell me something");
		
		label1.setText("New Text");  // change text
		
		label1.setToolTipText("Does not do anythin");  // enable tooltip
		
		JButton button1 = new JButton("Send");
		
		// JTextField(InitialText, Width)
		JTextField textField1 = new JTextField("Type here", 15);
		textField1.setColumns(10);
		
		// JTextArea(width, height)
		JTextArea textArea1 = new JTextArea(15, 20);
		textArea1.setText("Just a whole bunch of text that is very, "
				+ "very important and very long.");
		
		// Important: Otherwise \n have to be manually inserted and a long text
		// increases the width of the TextArea!
		textArea1.setLineWrap(true);
		
		textArea1.setWrapStyleWord(true);  // wrap lines: break them at white spaces
		
		int numOfLines = textArea1.getLineCount();
		textArea1.append(" \nNumber of Lines: " + numOfLines);
		
		JScrollPane scrollbar1 = new JScrollPane(
				textArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		// Components are added to the panel by simply .add()
		thePanel.add(label1);
		thePanel.add(button1);
		thePanel.add(textField1);
		thePanel.add(textArea1);
		thePanel.add(scrollbar1);
		
		// The Panel is also added to the window by simply .add()
		this.add(thePanel);
		
		this.setVisible(true);
		
		// Set Focus on a specific element
		textField1.requestFocus();
		
	}
	
}
