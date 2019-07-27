package lessons.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/* JList
 * 
 * */

public class Lesson25 extends JFrame {

	JButton button1;
	String infoOnComponent = "";
	
	// JList by default not editable
	JList<String> favoriteMovies, favoriteColors;
	
	// DefaultListModel can be edited
	DefaultListModel<String> defListModel = new DefaultListModel<String>();
	
	JScrollPane scroller;
	
	public static void main(String[] args) {
		
		new Lesson25();
		
	}
	
	public Lesson25() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Fifth Frame");
		JPanel thePanel = new JPanel();
		
		// ---- Button ---- ---- ---- ---- ---- ----
		button1 = new JButton("Get Answer");
		
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);
		
		// ---- JLists ---- ---- ---- ---- ---- ----
		String[] movies = {"Matrix", "Platton", "The Big Short"};
		favoriteMovies = new JList<String>(movies);
		
		// Set Max Cell items of list
		favoriteMovies.setFixedCellHeight(30);
		
		// Set Max width / characters of list 
		favoriteMovies.setFixedCellWidth(150);
		
		/* Selection Modes:
		 *  SINGLE_SELECTION -> Select only one item
		 * 	SINGLE_INTERVAL_SELECTION -> select as many as you want but 
		 * 								all have to be next to each other
		 * 								e.g. [1, 2, 3, 4] -> select 1, 2 to 3, but you 
		 * 								cannot select 1 and 3 
		 *  MULTIPLE_INTERVAL_SELECTION -> select as many as you want at the same time
		 * */
		favoriteMovies.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		thePanel.add(favoriteMovies);
		
		// ---- DefaultListModel ---- ---- ---- ---- ---- ----
		String[] colors = {"Black", "Blue", "White", "Green", "Orange", "Gray"};
		
		// Has to be added one at a time
		for ( String color : colors ) {
			defListModel.addElement(color);
		}
		
		// .add(index, value) -> Insert item at specific index
		defListModel.add(2,  "Purple");
		
		// Load defListModal into the JList to display it
		favoriteColors = new JList<String>(defListModel);
		
		// Set how many rows should be visible at a time
		favoriteColors.setVisibleRowCount(4);
		
		scroller = new JScrollPane(favoriteColors, 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				);
		
		favoriteColors.setFixedCellHeight(30);
		favoriteColors.setFixedCellWidth(150);
		
		// Add favoriteColors by adding the scroller! 
		// favoriteColors is now part of scroller!
		thePanel.add(scroller);
		
		// ---- Final Operations ---- ---- ---- ---- ---- ----
		this.add(thePanel);
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == button1 ) {
				if ( defListModel.contains("Black")) {
					infoOnComponent += "Black is Here\n";
				}
				if ( !defListModel.isEmpty()) {
					infoOnComponent += "It is not empty\n";
				}
				infoOnComponent += "Elements in the List: " + defListModel.size() +"\n";
				infoOnComponent += "Last Element: " + defListModel.lastElement() +"\n";
				infoOnComponent += "First Element: " + defListModel.firstElement() +"\n";
				infoOnComponent += "Element at index 1: " + defListModel.get(1) +"\n";
				defListModel.remove(0);
				defListModel.removeElement("White");
				
				Object[] arrayOfList = defListModel.toArray();
				
				for ( Object color : arrayOfList ) {
					infoOnComponent += color + "\n";
				}
				
				JOptionPane.showMessageDialog(Lesson25.this, infoOnComponent, "Info", JOptionPane.INFORMATION_MESSAGE);
				
				infoOnComponent = "";

			}
			
		}
		
		
		
	}
	
}
