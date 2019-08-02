package lessons.projects.paint;

/* TOPIC: Start "Java Paint Software" Project
 * */

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Lesson48 extends JFrame {

	JButton brushBut, lineBut, rectBut, ellipseBut, strokeBut, fillBut;
	
	// Used to identify which shape is to be drawn next
	int currentAction = 1;
	
	// Holds the currently used stroke and fill-colors
	Color strokeColor = Color.black;
	Color fillColor = Color.black;
	
	public static void main(String[] args) {
		new Lesson48();
	}  // END OF main METHOD
	
	public Lesson48() {
		
		this.setTitle("My Java Paint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(600, 500);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// ---- 
		JPanel buttonPanel = new JPanel();
		Box theBox = Box.createHorizontalBox();
		
		// Create buttons with images on top of them!
		brushBut = makeMeButtons("src/resources/Lesson48/Brush.png", 1);
		lineBut = makeMeButtons("src/resources/Lesson48/Line.png", 2);
		ellipseBut = makeMeButtons("src/resources/Lesson48/Ellipse.png", 3);
		rectBut = makeMeButtons("src/resources/Lesson48/Rectangle.png", 4);
		
		strokeBut = makeMeColorButtons("src/resources/Lesson48/Stroke.png", 5, true);
		fillBut = makeMeColorButtons("src/resources/Lesson48/Fill.png", 6, false);
		
		theBox.add(brushBut);
		theBox.add(lineBut);
		theBox.add(ellipseBut);
		theBox.add(rectBut);
		theBox.add(strokeBut);
		theBox.add(fillBut);
		
		buttonPanel.add(theBox);
		
		// ---- FINAL FRAME OPERATIONS ----
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(new DrawingBoard(), BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}  // END OF Lesson48 CONSTRUCTOR
	
	private JButton makeMeButtons(String iconFile, final int actionNum) {
		JButton theBut = new JButton();
		
		// Create an ImageIcon-Object. It opens and parses the image file
		Icon butIcon = new ImageIcon(iconFile);
		
		// Set the Icon as Button Icon
		theBut.setIcon(butIcon);
		
		theBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = actionNum;
				
			}
		});
		
		return theBut;
	}  // END OF makeMeButtons METHOD
	
	private JButton makeMeColorButtons(String iconFile, final int actionNum, final boolean isStroke) {
		JButton theBut = new JButton();
		
		// Create an ImageIcon-Object. It opens and parses the image file
		Icon butIcon = new ImageIcon(iconFile);
		
		// Set the Icon as Button Icon
		theBut.setIcon(butIcon);
		
		theBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isStroke) {
					// Shows a dialog-box to set the Color
					strokeColor = JColorChooser.showDialog(null, "Pick a Stroke",  Color.BLACK);
					
				} else {
					fillColor = JColorChooser.showDialog(null, "Pick a Stroke",  Color.BLACK);
				}
				currentAction = actionNum;
				
			}
		});
		
		return theBut;
	} // END OF makeMeColorButtons METHOD
	
	private class DrawingBoard extends JComponent {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		ArrayList<Color> shapeFill = new ArrayList<Color>();
		ArrayList<Color> shapeStroke = new ArrayList<Color>();
		Point drawStart, drawEnd;
		
		public DrawingBoard() {
			
			this.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					drawStart = new Point(e.getX(), e.getY());
					drawEnd = drawStart;
					System.out.println("Pressed");
					repaint();
				}
				
				public void mouseReleased(MouseEvent e) {
					Shape aShape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
					shapes.add(aShape);
					shapeFill.add(fillColor);
					shapeStroke.add(strokeColor);
					System.out.println("Released");
					drawStart = null;
					drawEnd = null;
					repaint();
				}
			});  // END OF MouseListener
			
			this.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					System.out.println("Dragged");
					drawEnd = new Point(e.getX(), e.getY());
					repaint();
					
				}
			});  // END OF addMouseMotionListener
			
		}
		
		// Override the swings paint-Method
		public void paint(Graphics g) {
			Graphics2D graphSettings = (Graphics2D) g;
			
			// Enable ANTIALIASING
			graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Define line width for a stroke on the screen
			graphSettings.setStroke(new BasicStroke(2));
			
			// Disable transparency
			graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));			
			
			Iterator<Color> strokeCounter = shapeStroke.iterator();
			Iterator<Color> fillCounter = shapeFill.iterator();
			
			// Iterate through all shapes and draw / fill them based on the color
			// contained in shapeStroke and shapeFill
			for (Shape s : shapes) {
				graphSettings.setPaint(strokeCounter.next());
				graphSettings.draw(s);
				graphSettings.setPaint(fillCounter.next());
				graphSettings.fill(s);
			}
			
			if (drawStart != null && drawEnd != null) {
				graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
				graphSettings.setPaint(Color.gray);
				Shape aShape = drawRectangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
			}
		}
		
		private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			
			return new Rectangle2D.Float(x, y, width, height);
		}
		
	}  // END OF DrawingBoard CLASS
	 
}  // END OF Lesson48 CLASS
