package lessons.swing;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* JComboBox
 * 
 * 
 * */

public class Lesson24 extends JFrame{

	JComboBox<String> favoriteShows;
	JButton button1;
	String infoOnComponent = "";
	
	public static void main(String[] args) {
		new Lesson24();
	}
	
	public Lesson24() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("My Fourth Frame");
		
		JPanel thePanel = new JPanel();
		
		// ---- Combobox  ---- ---- ---- ---- ----
		
		String[] shows = {"Breaking Bad", "Star Trek TNG", "Doctor Who"};
		
		// Add list of items to Combobox by handing it to the constructor
		favoriteShows = new JComboBox<String>(shows);
		
		// .addItem() -> Add a new item 
		favoriteShows.addItem("Star Trek Voyager");
		
		// .insertItemAt(index) -> insert item at specific index
		favoriteShows.insertItemAt("Dexter", 1);
		
		// .removeItemAt(index) -> remove item at specific index
		favoriteShows.removeItemAt(3);  // removes Doctor Who
		
		thePanel.add(favoriteShows);
		
		// ---- Button  ---- ---- ---- ---- ----
		button1 = new JButton("Get Answer");
		
		ListenForButtons lForButton = new ListenForButtons();
		
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);		
		
		// ---- FINAL OPERATIONS  ---- ---- ---- ---- ----
		this.add(thePanel);
		this.setVisible(true);
		
	}
	
	private class ListenForButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource() == button1 ) {
				favoriteShows.setEditable(true);
				
				infoOnComponent += "Item at 0: " + favoriteShows.getItemAt(0) + "\n";
				infoOnComponent += "Num of shows: " + favoriteShows.getItemCount() + "\n";
				infoOnComponent += "Selected ID: " + favoriteShows.getSelectedIndex() + "\n";
				infoOnComponent += "Selected Value: " + favoriteShows.getSelectedItem() + "\n";
				infoOnComponent += "Are editable: " + favoriteShows.isEditable() + "\n";
				JOptionPane.showMessageDialog(Lesson24.this,  infoOnComponent, "Information", JOptionPane.INFORMATION_MESSAGE);
				
				infoOnComponent = "";  // Reset infoOnComponent to empty String
				
			}
			
		}
		
	}
	
}
