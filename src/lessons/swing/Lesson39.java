package lessons.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;

//Monitors user activity with any links that are clicked or hovered over
import javax.swing.event.HyperlinkListener;  

/* TOPIC: JEditor
 * JEditorPane class is used to create a simple text editor window. This class has setContentType() and setText() methods.
 * */

public class Lesson39 extends JFrame implements HyperlinkListener, ActionListener {
	
	private static File projectDir = new File("src/resources/Lesson39");
	private static String defaultURL = "";
	private JPanel toolPanel = new JPanel();
	private JTextField theURLField = new JTextField(25);
	private JEditorPane htmlPage;
	
	public static void main(String[] args)
	{
		try {
			defaultURL = "file:///" + projectDir.getCanonicalPath() + "/initialHTML.html";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Lesson39(defaultURL);
		
	}  // END OF main METHOD
	
	public Lesson39(String defaultURL)
	{
		JFrame frame = new JFrame("Java Browser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		this.defaultURL = defaultURL;
		
		theURLField.addActionListener(this);
		theURLField.setText(defaultURL);
		
		toolPanel.add(theURLField);
		
		frame.add(toolPanel, BorderLayout.NORTH);
		
		try {
			htmlPage = new JEditorPane(defaultURL);
			htmlPage.addHyperlinkListener(this);
			
			htmlPage.setEditable(false);
			
			JScrollPane scroller = new JScrollPane(htmlPage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			frame.add(scroller, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setSize(900, 600);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pageURL = "";
		
		if (e.getSource() == theURLField)
		{
			pageURL = theURLField.getText();
		} else {
			JOptionPane.showMessageDialog(
					Lesson39.this, "Please enter a web-address", 
					"ERROR", JOptionPane.ERROR_MESSAGE);
			
		}

		try {
			htmlPage.setPage(new URL(pageURL));
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(
					Lesson39.this, "Please provide a valid URL", 
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// check the event-type -> could also be hovered etc.
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			try {
				htmlPage.setPage(e.getURL());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// .toExternalForm() -> creates String representation
			theURLField.setText(e.getURL().toExternalForm());
			
		}
		
	}
	
	
	
}  // END OF Lesson39 CLASS
