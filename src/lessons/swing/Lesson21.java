package lessons.swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

/* TOPIC: JFrame Event-Handling
 * 
 * */

import java.awt.event.*;;

public class Lesson21 extends JFrame {

	JButton button1;
	JTextField textField1;
	JTextArea textArea1;
	int buttonClicked = 0;
	
	public static void main(String[] args) {
		
		new Lesson21();
		
	}
	
	public Lesson21() {

		this.setSize(400, 400);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Lesson 21: My Second Frame");
		
		JPanel thePanel = new JPanel();
		
		// ---- Button ---- ---- ---- ---- ---- 
		button1 = new JButton("Click Here");
		
		ListenForButton lForButton = new ListenForButton();
		
		button1.addActionListener(lForButton);
		
		thePanel.add(button1);
		
		// ---- TextArea ---- ---- ---- ---- ---- 
		textArea1 = new JTextArea(15, 20);
		
		// Important: Otherwise \n have to be manually inserted and a long text
		// increases the width of the TextArea!
		textArea1.setLineWrap(true);
		
		textArea1.setWrapStyleWord(true);  // wrap lines: break them at white spaces
		
		textArea1.setText("Tracking Events: \n");
		
		JScrollPane scrollbar1 = new JScrollPane(textArea1);
		
		thePanel.add(scrollbar1);
		//thePanel.add(textArea1);  -> Not needed anymore. Scrollbar adds the textarea
		
		// ---- TextField ---- ---- ---- ---- ---- 
		textField1 = new JTextField("", 15);
		
		ListenForKeys lForKeys = new ListenForKeys();
		
		textField1.addKeyListener(lForKeys);
		
		thePanel.add(textField1);
		
		// ---- Mouse Events  ---- ---- ---- ---- ---- 
		ListenForMouse lForMouse = new ListenForMouse();
		thePanel.addMouseListener(lForMouse);
		
		// ---- Final Settings / Adding to Frame ---- 
		this.add(thePanel);
		
		ListenForWindow lForWindow = new ListenForWindow();
		this.addWindowListener(lForWindow);
		
		this.setVisible(true);
		
	}
	
	// ---- Implement Listeners ---- ---- ---- ---- ---- ---- ----
	
	// Implement ActionListeners for Action-Events (e.g. Button-click)
	private class ListenForButton implements ActionListener{
		
		// actionPerformed -> needs to be implemented -> from ActionListener
		@Override 
		public void actionPerformed(ActionEvent e) {
			
			// If the event-source is the button1 defined above
			if (e.getSource() == button1) {
				buttonClicked++;
				
				textArea1.append("Buton clicked " + buttonClicked + " times\n");
				
				// You can print all kind of information about the button and the event
				System.out.println(e.getSource().toString());
			}
			
		}
	}
	
	// Implement KeyListener to listen for key-presses
	private class ListenForKeys implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			textArea1.append("Key Hit: " + e.getKeyChar() + "\n");
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	}
	
	// Implement WindowListener for events regarding the entire Frame/Window
	private class ListenForWindow implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			textArea1.append("Window is active\n");
			
		}

		// if this.dispose() is called
		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		// if window is closed from the Menu (e.g. X button)
		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		// If window is not active anymore 
		// (e.g. user clicked to a different window / programme)
		@Override
		public void windowDeactivated(WindowEvent e) {
			textArea1.append("Window is not active\n");
			
		}

		// Window went from minimized to normal state
		@Override
		public void windowDeiconified(WindowEvent e) {
			textArea1.append("Window was maximized\n");
			
		}

		// Window went normal to minimized state
		@Override
		public void windowIconified(WindowEvent e) {
			textArea1.append("Window was minimized\n");
			
		}

		// Window was opened for the first time / orginally created
		@Override
		public void windowOpened(WindowEvent e) {
			textArea1.append("Window was opened\n");
			
		}
		
	}
	
	private class ListenForMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			textArea1.append("Mouse Panel pos: " + e.getX() + " " + e.getY() + "\n");
			textArea1.append("Mouse Screen pos: " + e.getXOnScreen() + " " + e.getYOnScreen() + "\n");
			
			// Distinguish left, right click etc.
			textArea1.append("Mouse Button: " + e.getButton() + "\n");
			
			// Means click counts during that event 
			// Usage: distinguish single click & double click!
			textArea1.append("Mouse Clicks: " + e.getClickCount() + "\n");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
