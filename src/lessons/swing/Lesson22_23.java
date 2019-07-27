package lessons.swing;

// This is Lesson22 and Lesson23

import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Format numbers
import java.text.NumberFormat;

// Edit Borders
import javax.swing.border.*;

/* TOPIC: More Elements and Events (JOptionPane etc.)
 * 
 * 
 * */

public class Lesson22_23 extends JFrame{

	JButton button1;
	JLabel label1, label2, label3;
	JTextField textField1, textField2;
	JCheckBox dollarSign, commaSeparator;
	JRadioButton addNums, subtractNums, multNums, divideNums;
	JSlider howManyTimes;
	
	double number1, number2, totalCalc;
	
	public static void main(String[] args) {
		
		new Lesson22_23();
		
	}
	
	public Lesson22_23() {
		this.setSize(400, 400);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setTitle("My Third Frame");
		
		JPanel thePanel = new JPanel();
		
		button1 = new JButton("Calcualte");
		ListenForButton lForButton = new ListenForButton();
		
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);
		
		//  ---- TextFields & Lables  ---- ---- ----
		label1 = new JLabel("Number 1");
		thePanel.add(label1);
		
		textField1 = new JTextField("", 5);
		thePanel.add(textField1);
		
		label2 = new JLabel("Number 2");
		thePanel.add(label2);
		
		textField2 = new JTextField("", 5);
		thePanel.add(textField2);
		
		//  ---- CheckBoxes  ---- ---- ---- ---- ----
		dollarSign = new JCheckBox("Dollars");
		commaSeparator = new JCheckBox("Commas");
		
		thePanel.add(dollarSign);
		thePanel.add(commaSeparator, true);  // true -> checked by default
		
		//  ---- Radio Buttons  ---- ---- ---- ---- ----
		addNums = new JRadioButton("Add");
		subtractNums = new JRadioButton("Subtract");
		multNums = new JRadioButton("Multiply");
		divideNums = new JRadioButton("Divide");
		
		// Group? -> causes that only one can be selected at a time
		// A group cannot be added to the Panel
		// You still have to add each item individually to the Panel
		ButtonGroup operation = new ButtonGroup();
		operation.add(addNums);
		operation.add(subtractNums);
		operation.add(multNums);
		operation.add(divideNums);
		
		JPanel operPanel = new JPanel();
		Border operBorder = BorderFactory.createTitledBorder("Operation");
		
		// Add custom Border object to operPanel
		operPanel.setBorder(operBorder);
		operPanel.add(addNums);
		operPanel.add(subtractNums);
		operPanel.add(multNums);
		operPanel.add(divideNums);
		
		addNums.setSelected(true);  // By default addNums is selected
		
		thePanel.add(operPanel);
		
		// ---- Slider ---- ---- ---- ---- ----
		label3 = new JLabel("Perform How Many Times");
		thePanel.add(label3);
		
		// JSlider(minimum-value, maximum-value, initial-value)
		howManyTimes =  new JSlider(0, 99, 1);
		
		// Puts little ticks on the slider (only visual help)
		howManyTimes.setMinorTickSpacing(1);
		howManyTimes.setMajorTickSpacing(10);
		howManyTimes.setPaintTicks(true);  // has to called to show ticks!
		howManyTimes.setPaintLabels(true);  // has to called to show ticks numbers!
		
		ListenForSlider lForSlider = new ListenForSlider();
		howManyTimes.addChangeListener(lForSlider);
		
		thePanel.add(howManyTimes);
		
		// ---- FINAL Operations ---- ---- ---- ---- ----
		this.add(thePanel);
		this.setVisible(true);
		
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				try {
					number1 = Double.parseDouble(textField1.getText());
					number2 = Double.parseDouble(textField2.getText());
					
				} catch (NumberFormatException ne) {
					
					// JOptionPane.showMessageDialog -> Show error- or other messages in Pop-Ups
					// .showMessageDialog(Frame, Text, Title, Icon)
					JOptionPane.showMessageDialog(
							Lesson22_23.this,  "Please enter a valid number!", "Error", 
							JOptionPane.ERROR_MESSAGE
					);
					// Exits the entire application
					System.exit(0);
				}
				
				if ( addNums.isSelected()) {
					
					totalCalc = addNumbers(number1, number2, howManyTimes.getValue());
				
				} else if ( subtractNums.isSelected()) {
					
					totalCalc = subtractNumbers(number1, number2, howManyTimes.getValue());
				
				} else if ( multNums.isSelected()) {
					
					totalCalc = multiplyNumbers(number1, number2, howManyTimes.getValue());
				
				} else {  // Division is selected
					
					totalCalc = divideNumbers(number1, number2, howManyTimes.getValue());
				
				}
					
				if ( dollarSign.isSelected() ) {
					NumberFormat numFormat = NumberFormat.getCurrencyInstance();  // uses comma and currency symbol
					JOptionPane.showMessageDialog(
							Lesson22_23.this, numFormat.format(totalCalc), "Result", JOptionPane.INFORMATION_MESSAGE
					);
				} else if (commaSeparator.isSelected() ) {
					
					NumberFormat numFormat = NumberFormat.getNumberInstance();  // Uses commas for normal number format
					JOptionPane.showMessageDialog(
							Lesson22_23.this, numFormat.format(totalCalc), "Result", JOptionPane.INFORMATION_MESSAGE
					);
					
				} else {
					JOptionPane.showMessageDialog(
							Lesson22_23.this, totalCalc, "Result", JOptionPane.INFORMATION_MESSAGE
					);
				}
			}
			
		}
		
	}
	
	private class ListenForSlider implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if ( e.getSource() == howManyTimes) {
				label3.setText("Perform How Many Times? " + howManyTimes.getValue());
			}
			
		}
		
		
	}
	
	public static double addNumbers(double number1, double number2, int howManyTimes) {
		
		double total = 0;
		
		int i = 1;
		
		while(i <= howManyTimes) {
			total = total + ( number1 + number2);
			i++;
		}
		return total;
	}
	
	public static double subtractNumbers(double number1, double number2, int howManyTimes) {
		
		double total = 0;
		
		int i = 1;
		
		while(i <= howManyTimes) {
			total = total + ( number1 - number2);
			i++;
		}
		return total;
	}
	
	public static double multiplyNumbers(double number1, double number2, int howManyTimes) {
		
		double total = 0;
		
		int i = 1;
		
		while(i <= howManyTimes) {
			total = total + ( number1 * number2);
			i++;
		}
		return total;
	}
	
	public static double divideNumbers(double number1, double number2, int howManyTimes) {
		
		double total = 0;
		
		int i = 1;
		
		while(i <= howManyTimes) {
			total = total + ( number1 / number2);
			i++;
		}
		return total;
	}
	
}
