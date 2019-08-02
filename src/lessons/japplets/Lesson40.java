package lessons.japplets;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/* TOPIC: JApplets
 * JApplets are depreciated from Java 9 on!
 * 
 * */

public class Lesson40 extends JApplet {

	File lessonResourceDir = new File("src/resources/Lesson40");
	
	JPanel thePanel;
	JPanel ques1Panel, ques2Panel, ques3Panel, ques4Panel;
	
	JButton getResultBut;
	
	JRadioButton extravertRadio, introvertRadio, sensorRadio, intuitiveRadio,
	feelerRadio, thinkerRadio, judgingRadio, perceivingRadio;
	
	JEditorPane yourReport;
	
	// Applets do not have a main function! They use init!
	public void init()
	{
		this.setSize(700,  900);
		thePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ques1Panel = new JPanel();
		ques2Panel = new JPanel();
		ques3Panel = new JPanel();
		ques4Panel = new JPanel();
		
		Border border1 = BorderFactory.createTitledBorder("Do you prefer to work ...");
		Border border2 = BorderFactory.createTitledBorder("Which is most important ...");
		Border border3 = BorderFactory.createTitledBorder("Do you act on ...");
		Border border4 = BorderFactory.createTitledBorder("Which do you prefer ...");
		
		ques1Panel.setBorder(border1);
		ques2Panel.setBorder(border2);
		ques3Panel.setBorder(border3);
		ques4Panel.setBorder(border4);
		
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		ButtonGroup group3 = new ButtonGroup();
		ButtonGroup group4 = new ButtonGroup();
		
		extravertRadio = new JRadioButton("In groups");
		introvertRadio = new JRadioButton("On your own");
		sensorRadio = new JRadioButton("The specifics");
		intuitiveRadio = new JRadioButton("The big picture");
		feelerRadio = new JRadioButton("What feels right");
		thinkerRadio = new JRadioButton("list of facts");
		judgingRadio = new JRadioButton("to plan");
		perceivingRadio = new JRadioButton("to adapt");
		
		extravertRadio.setSelected(true);
		sensorRadio.setSelected(true);
		feelerRadio.setSelected(true);
		judgingRadio.setSelected(true);
		
		ques1Panel.add(extravertRadio);
		ques1Panel.add(introvertRadio);
		ques2Panel.add(sensorRadio);
		ques2Panel.add(intuitiveRadio);
		ques3Panel.add(feelerRadio);
		ques3Panel.add(thinkerRadio);
		ques4Panel.add(judgingRadio);
		ques4Panel.add(perceivingRadio);
		
		group1.add(extravertRadio);
		group1.add(introvertRadio);
		group2.add(sensorRadio);
		group2.add(intuitiveRadio);
		group3.add(feelerRadio);
		group3.add(thinkerRadio);
		group4.add(judgingRadio);
		group4.add(perceivingRadio);
		
		thePanel.add(ques1Panel);
		thePanel.add(ques2Panel);
		thePanel.add(ques3Panel);
		thePanel.add(ques4Panel);
		
		getResultBut = new JButton("Get Result");
		GetResultsListener getResListener = new GetResultsListener();
		getResultBut.addActionListener(getResListener);
		
		thePanel.add(getResultBut);
		this.add(thePanel);
		
		this.setVisible(true);
		
	}  // END OF init METHOD
	
	private class GetResultsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String pageToOpen = "";
			
			String ResourceDirPath = "";
			
			try {
				ResourceDirPath = lessonResourceDir.getCanonicalPath();
				
			} catch ( IOException ioe) {
				System.out.println("IO ERROR: Could not find directory");
				System.exit(0);
			}
			
			String directoryURL = "file:///" + ResourceDirPath;
			
			String textToDisplay = "<html><h4>" + directoryURL + "/";
			
			if (e.getSource() == getResultBut)
			{
				if (extravertRadio.isSelected()) {
					pageToOpen += "E";
				} else {
					pageToOpen += "I";
				}
				
				if (sensorRadio.isSelected()) {
					pageToOpen += "S";
				} else {
					pageToOpen += "N";
				}
				
				if (feelerRadio.isSelected()) {
					pageToOpen += "F";
				} else {
					pageToOpen += "T";
				}
				
				if (judgingRadio.isSelected()) {
					pageToOpen += "J";
				} else {
					pageToOpen += "P";
				}
				
			}
			
			textToDisplay += pageToOpen + " <\\h4></html>";
			
			yourReport = new JEditorPane("text/html", textToDisplay);
			yourReport.setEditable(false);
			yourReport.setSize(675, 650);
			
			JScrollPane scroller = new JScrollPane(yourReport, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			scroller.setPreferredSize(new Dimension(675, 650));
			
			// add new panel
			thePanel.add(scroller);
			
			// Set result button to invisible
			getResultBut.setVisible(false);
			
			// In order to update the GUI with the new components
			// you have revalidate and repaint the Panel
			thePanel.revalidate();
			thePanel.repaint();
			
		}
		
	}

}
