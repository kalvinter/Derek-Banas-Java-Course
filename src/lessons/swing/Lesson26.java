package lessons.swing;

import java.awt.Dimension;  // Store different heights, widths
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;  // Work with dates (get todays date etc.)
import java.util.Calendar;  // Work with dates
import javax.swing.SpinnerDateModel;  // Spinner Modal for dates
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;  
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;  

/* TOPIC: Spinner
 * 
 * 
 * */

public class Lesson26 extends JFrame {

	JButton button1;
	JSpinner spinner1, spinner2, spinner3, spinner4;
	String outputString = "";
	
	
	public static void main(String[] args) {
		new Lesson26();
	}
	
	public Lesson26() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("My Sixth Frame");
		
		JPanel thePanel = new JPanel();
		
		// ---- Button ---- ---- ---- ---- ----
		button1 = new JButton("Get Answer");
		
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);
		
		// ---- Spinners ---- ---- ---- ---- ----
		// Without modification -> default spinner allows to increment from 1 to 9
		spinner1 = new JSpinner();
		thePanel.add(spinner1);
		
		// SpinnerNumberModal(initial_nr, min_nr, max_nr, increment_step)
		spinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		thePanel.add(spinner2);
		
		// Spinner with String-Array / List
		String[] weekDays = {"Mon", "Tue", "Wed", "Thurs", "Fri"};
		spinner3 = new JSpinner(new SpinnerListModel(weekDays));
		
		Dimension d = spinner3.getPreferredSize();  // Create dimension object from spinner & content
		d.width = 80;  // manually override width (make a bit larger than content)
		spinner3.setPreferredSize(d);  // apply changed dimensions
		
		thePanel.add(spinner3);
		
		// Spinner with date
		Date todaysDate = new Date();  // Defaults to today's date
		// SpinnerDateModal(initial_date, min_date, max_date, increment_intervall)
		spinner4 = new JSpinner(new SpinnerDateModel(todaysDate, null, null, Calendar.DAY_OF_MONTH));
		
		// Customize date-editor-widget
		// DateEditor(spinner_object, date_format_string)
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner4, "dd/MM/yy");
		spinner4.setEditor(dateEditor);
		
		/* How to apply a ChangeListener to a Spinner:
		 * ListenForSpinner lForSpinner = ListenForSpinner();
		 * spinner4.addChangeListener(lForSpinner);
		 * */
		
		thePanel.add(spinner4);
		
		// ---- Final operations ---- ---- ---- ---- ----
		this.add(thePanel);
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if ( e.getSource() == button1) {
				outputString += "Spinner 1 Value: " + spinner1.getValue() + "\n";
				outputString += "Spinner 2 Value: " + spinner2.getValue() + "\n";
				outputString += "Spinner 3 Value: " + spinner3.getValue() + "\n";
				outputString += "Spinner 4 Value: " + spinner4.getValue() + "\n";
				
				JOptionPane.showMessageDialog(Lesson26.this,  outputString, "Information", JOptionPane.INFORMATION_MESSAGE);
				
				outputString = "";
			}
			
		}
		
	}
	
}
