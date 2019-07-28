package lessons.swing;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.*;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;

public class Lesson30 extends JFrame {

	JLabel nameLabel, streetLabel, stateLabel, dateLabel,
		ageLabel, sexLabel, optionsLabel, aboutLabel;
	JTextField nameText, streetText;
	JComboBox<String> stateList;
	JSpinner dateSpin;
	JSlider ageSlider;
	JRadioButton maleRadio, femaleRadio;
	ButtonGroup sexGroup;
	JCheckBox morningCheck, afterNCheck, eveningCheck;
	JTextArea aboutYou;
	
	public static void main(String[] args) {
		new Lesson30();
	}
	
	public Lesson30() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Frame");
		
		JPanel thePanel = new JPanel();
		thePanel.setLayout(new GridBagLayout());
		
		// ---- X ---- ---- ---- ---- ---- 
		nameLabel = new JLabel("Name: ");
		addComponent(thePanel, nameLabel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		nameText = new JTextField(30);
		addComponent(thePanel, nameText, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		streetLabel = new JLabel("Street: ");
		addComponent(thePanel, streetLabel, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		streetText = new JTextField(30);
		addComponent(thePanel, streetText, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		Box sexBox = Box.createVerticalBox();
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		sexGroup = new ButtonGroup();  // Just needed so that only one Radio can be clicked 
		sexGroup.add(maleRadio);
		sexGroup.add(femaleRadio);
		sexBox.add(maleRadio);
		sexBox.add(femaleRadio);
		sexBox.setBorder(BorderFactory.createTitledBorder("Sex"));
		addComponent(thePanel, sexBox, 3, 0, 2, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		JPanel statePanel = new JPanel();
		statePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		stateLabel = new JLabel("State: ");
		statePanel.add(stateLabel);
		
		String[] states = {"NR", "NW", "SU", "RL"};
		
		stateList = new JComboBox<String>(states);
		
		statePanel.add(stateList);

		dateLabel = new JLabel("Appointment Date");
		
		statePanel.add(dateLabel);
		
		Date todaysDate = new Date();
		dateSpin = new JSpinner(new SpinnerDateModel(todaysDate, null, null, Calendar.DAY_OF_MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpin, "dd.MM.yy");
		
		dateSpin.setEditor(dateEditor);
		statePanel.add(dateSpin);
		
		ageLabel = new JLabel("Age: 50");
		statePanel.add(ageLabel);
		
		ageSlider = new JSlider(1, 99, 50);
		ListenForSlider lForSLider = new ListenForSlider();
		ageSlider.addChangeListener(lForSLider);
		statePanel.add(ageSlider);
		
		addComponent(thePanel, statePanel, 1, 2, 5, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		Box optionBox = Box.createVerticalBox();
		morningCheck = new JCheckBox("Morning");
		afterNCheck = new JCheckBox("Afternoon");
		eveningCheck = new JCheckBox("Evening");
		
		optionBox.add(morningCheck);
		optionBox.add(afterNCheck);
		optionBox.add(eveningCheck);
		
		optionBox.setBorder(BorderFactory.createTitledBorder("Time of Day"));
		addComponent(thePanel, optionBox, 1, 3, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);

		aboutYou = new JTextArea(6, 40);
		aboutYou.setText("Tell us about you");
		aboutYou.setLineWrap(true);
		aboutYou.setWrapStyleWord(true);
		
		JScrollPane scrollbar1 = new JScrollPane(aboutYou, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		addComponent(thePanel, scrollbar1, 2, 3, 3, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		// ---- FINAL OPERATIONS ---- ---- ---- ---- ----
		this.add(thePanel);
		
		/* this.pack() used instead of this.setSize()
		 * Why?
		 * "The pack method sizes the frame so that all its contents are at or above 
		 * their preferred sizes. An alternative to pack is to establish a frame size 
		 * explicitly by calling setSize or setBounds (which also sets the frame 
		 * location). In general, using pack is preferable to calling setSize, since 
		 * pack leaves the frame layout manager in charge of the frame size, and 
		 * layout managers are good at adjusting to platform dependencies and other 
		 * factors that affect component size."
		 * */
		this.pack();
		
		this.setVisible(true);
		
	}
	
	private class ListenForSlider implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if ( e.getSource() == ageSlider ) {
				ageLabel.setText("Age: " + ageSlider.getValue());
			}
			
		}
		
	}
	
	private void addComponent(JPanel thePanel, JComponent comp, int xPos, 
			int yPos, int compWidth, int compoHeight, int place, int stretch) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		
		gridConstraints.gridx = xPos;  // Default x position of each element
		gridConstraints.gridy = yPos;  // Default x position of each element
		gridConstraints.gridwidth = compWidth;  // Default how many columns an element spans
		gridConstraints.gridheight = compoHeight;  // Default how many rows an element spans
		gridConstraints.weightx = 100; 
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);  // 5 pixels of padding around each element
		gridConstraints.anchor = place;  // Where to align components if they do not completely fill a space
		gridConstraints.fill = stretch;  // Define how a component can be stretched to fill free space
		
		thePanel.add(comp, gridConstraints);
	}
}
