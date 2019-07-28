package lessons.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;  // Puts each components next to each other. (Floating)

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* Layout Manager
 * 
 * */

public class Lesson28 extends JFrame {

	JButton button1, button2, button3, button4, button5, button6, button7,
	button8, button9, button10, button11;
	
	public static void main(String[] args) {
		new Lesson28();
	}
	
	public Lesson28() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("My Seventh Frame");
				
		// ---- Flow Layout ---- ---- ---- ---- ---- 
		JPanel thePanel = new JPanel();
		/* Set Layout with FlowLayout 
		 * FlowLayout(alignment, horizontal_padding, vertical_padding)
		 * padding in pixel -> between components and each other as well as border 
		 * */
		thePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 20));
		button1 = new JButton("Button1");
		button2 = new JButton("Button2");

		thePanel.add(button1);
		thePanel.add(button2);
		
		/* ---- Border Layout ---- ---- ---- ---- ----
		 * Border Layout -> Divides Panel in five areas (NORTH, EAST, CENTER, WEST, SOUTH)
		 * https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
		 * ---------------------------------------
		 * |			 	NORTH				 |
		 * ---------------------------------------
		 * | EAST	|		CENTER		| WEST	 |
		 * ---------------------------------------
		 * |				SOUTH				 |
		 * ---------------------------------------
		 * 
		 * */
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		button3 = new JButton("Button3");
		button4 = new JButton("Button4");
		button5 = new JButton("Button5");
		button6 = new JButton("Button6");
		button7 = new JButton("Button7");
		

		// If an element is assigned to an area it will fill the area
		borderPanel.add(button3, BorderLayout.NORTH);
		borderPanel.add(button4, BorderLayout.CENTER);
		borderPanel.add(button5, BorderLayout.WEST);
		
		/* Each element that is assigned to an area, overrides everything that is in there
		 * Only the last added button will be shown!
		borderPanel.add(button6, BorderLayout.SOUTH);
		borderPanel.add(button7, BorderLayout.SOUTH);
		
		 Correct way: Add  the buttons to a panel and add the panel to the area!
		*/
		
		JPanel northPanel = new JPanel();
		northPanel.add(button6);
		northPanel.add(button7);
		
		borderPanel.add(northPanel, BorderLayout.SOUTH);
		
		// ---- Box Layout ---- ---- ---- ---- ----		
		Box theBox = Box.createHorizontalBox();
		
		button8 = new JButton("Button8");
		button9 = new JButton("Button9");
		button10 = new JButton("Button10");
		button11 = new JButton("Button11");
		
		theBox.add(button8);
		// HorizontalStrut -> space between components in pixel
		theBox.add(Box.createHorizontalStrut(49));
		theBox.add(button9);
		theBox.add(button10);
		// HorizontalGlue -> puts as much space between two components as possible
		// without kicking any component out of the Frame
		theBox.add(Box.createHorizontalGlue());
		theBox.add(button11);		
		
		// ---- FINAL OPERATIONS ---- ---- ---- ---- ----
		/* Only one main Panel can be added to the Frame.
		 * Each Sub-Panel has to be added in the Main Panel that is added to the JFrame
		 * To test the individual Panels defined above - comment out all other panels */ 
		// this.add(thePanel);
		// this.add(borderPanel);
		this.add(theBox);
		
		this.setVisible(true);
	}
	
}
