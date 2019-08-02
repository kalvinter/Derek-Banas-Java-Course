package lessons.projects.paint;

/* TOPIC: Finish "Java Paint Software" Project
 * */ 

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Lesson49 extends JFrame {

	JButton brushBut, lineBut, rectBut, ellipseBut, strokeBut, fillBut;
	
	// Used to identify which shape is to be drawn next
	int currentAction = 1;
	
	// Holds the currently used stroke and fill-colors
	Color strokeColor = Color.black;
	Color fillColor = Color.black;
	
	DecimalFormat dec = new DecimalFormat("#.##");
	
	JSlider transSlider;
	JLabel transLabel;
	
	Graphics2D graphicSettings;
	
	float transparentVal = 1.0f;
	
	public static void main(String[] args) {
		new Lesson49();
		
	}  // END OF main METHOD
	
	public Lesson49() {
		
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Java Paint 2.0");
		
		this.setLocationRelativeTo(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		// ---- Create Panels
		JPanel buttonPanel = new JPanel();
		Box theBox = Box.createHorizontalBox();
		
		// Create buttons with images on top of them!
		brushBut = makeMeButtons("src/resources/Lesson48/Brush.png", 1);
		lineBut = makeMeButtons("src/resources/Lesson48/Line.png", 2);
		ellipseBut = makeMeButtons("src/resources/Lesson48/Ellipse.png", 3);
		rectBut = makeMeButtons("src/resources/Lesson48/Rectangle.png", 4);
		
		strokeBut = makeMeColorButtons("src/resources/Lesson48/Stroke.png", 5, true);  // strokeBut is currently active -> true
		fillBut = makeMeColorButtons("src/resources/Lesson48/Fill.png", 6, false);
		
		transLabel = new JLabel("    Visibility: 100%    ");
		
		transSlider = new JSlider(1, 99, 99);
		ListenForSlider lForSlider = new ListenForSlider();
		transSlider.addChangeListener(lForSlider);
		
		theBox.add(brushBut);
		theBox.add(lineBut);
		theBox.add(ellipseBut);
		theBox.add(rectBut);
		theBox.add(strokeBut);
		theBox.add(fillBut);
		theBox.add(transLabel);
		theBox.add(transSlider);
		
		buttonPanel.add(theBox);
		
		// ---- FINAL FRAME OPERATIONS
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(new DrawingBoard(), BorderLayout.CENTER);
		this.setVisible(true);
		
	} // END OF Lesson49 CONSTRUCTOR
	
	private class DrawingBoard extends JComponent {
		
		private ArrayList<Shape> shapes = new ArrayList<Shape>();
		private ArrayList<Color> shapeStroke = new ArrayList<Color>();
		private ArrayList<Color> shapeFill = new ArrayList<Color>();
		private ArrayList<Float> shapeTransparancy = new ArrayList<Float>();
		Point drawStart, drawEnd;
		
		public DrawingBoard() {
			this.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					drawStart = new Point(e.getX(), e.getY());
					drawEnd = drawStart;
					repaint();
				}
				
				public void mouseReleased(MouseEvent e) {
					// IF NOT brush is selected
					// Brush is handled in MouseDragged
					if (currentAction != 1) {
						Shape aShape = null;
						
						if (currentAction == 2) {
							aShape = drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
						} else if (currentAction == 3) {
							aShape = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
						} else if (currentAction == 4) {
							aShape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
						}
						
						shapes.add(aShape);
						shapeStroke.add(strokeColor);
						shapeFill.add(fillColor);
						shapeTransparancy.add(transparentVal);
						
						drawStart = null;
						drawEnd = null;
						repaint();
					}
				}

			});  // END OF addMouseListener
			
			this.addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					drawEnd = new Point(e.getX(), e.getY());

					if (currentAction == 1) {
						Shape aShape = null;
						
						// Force it to be the same. 
						// The brush line are actually ellipses but should appear as one thick line
						strokeColor = fillColor;
						aShape = drawBrush(drawEnd.x, drawEnd.y, 5, 5);
						
						shapes.add(aShape);
						shapeStroke.add(strokeColor);
						shapeFill.add(fillColor);
						shapeTransparancy.add(transparentVal);
					}
					
					repaint();
				}
			});
			
		}  // END OF DrawingBoard CONSTRUCTOR
		
		// Override the swings paint-Method
		public void paint(Graphics g) {
			graphicSettings = (Graphics2D) g;
			graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphicSettings.setStroke(new BasicStroke(2));
			
			// Disable transparency
			graphicSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparentVal));
						
			/* Why Iterators?
			 * We have to loop through three Lists at the same time.
			 * The main for-loop iterates through the shapes and 
			 * shoots out the next shape
			 * Inside of the loop the call next() advances both iterators
			 * also to the next item.
			 * */
			Iterator<Color> strokeCounter = shapeStroke.iterator();
			Iterator<Color> fillCounter = shapeFill.iterator();
			Iterator<Float> transpCounter = shapeTransparancy.iterator();
			
			for (Shape s : shapes) {
				graphicSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transpCounter.next()));

				graphicSettings.setPaint(strokeCounter.next());
				graphicSettings.draw(s);
				
				graphicSettings.setPaint(fillCounter.next());
				graphicSettings.fill(s);
			}
			
			if (drawStart != null && drawEnd != null) {
				graphicSettings.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.40f));
				graphicSettings.setPaint(Color.GRAY);
				
				Shape aShape = null;
				
				if (currentAction == 2) {
					aShape = drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
				} else if (currentAction == 3) {
					aShape = drawEllipse(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
				} else if (currentAction == 4) {
					aShape = drawRectangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
				} else if (currentAction == 1) {
					aShape = drawBrush(drawEnd.x, drawEnd.y, 5, 5);
				}
				
				graphicSettings.draw(aShape);
			}
		}
		
		private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2){
			/* Why calculate x, y, width, height separately?
			 * Rectangles are drawn from the upper left x,y position.
			 * From this position width and height are added.
			 * 
			 * If a user draws from right to left -> the x2/y2 values are closer to the
			 * upper-left x/y-starting position!
			 * */ 
			int x = Math.min(x1, x2);  // Look which point is closer to the upper left corner
			int y = Math.min(y1, y2);
			
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			
			return new Rectangle2D.Float(x, y, width, height);
		}  // END OF drawRectangle METHOD
		
		private Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2){
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			
			return new Ellipse2D.Float(x, y, width, height);
		}
		
		private Line2D.Float drawLine(int x1, int y1, int x2, int y2){
			return new Line2D.Float(x1, y1, x2, y2);
		}
		
		private Ellipse2D.Float drawBrush(int x, int y, int brushStrokeWidth, int brushStrokeHeight){
			return new Ellipse2D.Float(x, y, brushStrokeWidth, brushStrokeHeight);
		}
		
	}  // END OF DrawingBoard CLASS
	
	private JButton makeMeButtons(String buttonIconPath, final int actionNum) {
		JButton theBut = new JButton();
		Icon theIcon = new ImageIcon(buttonIconPath);
		theBut.setIcon(theIcon);
		
		theBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAction = actionNum;
			}
		});
		return theBut;
	}  // END OF makeMeButtons METHOD
	
	private JButton makeMeColorButtons(String buttonIconPath, final int actionNum, final boolean isStroke) {
		JButton theBut = new JButton();
		Icon theIcon = new ImageIcon(buttonIconPath);
		theBut.setIcon(theIcon);
		
		theBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isStroke) {
					strokeColor = JColorChooser.showDialog(null,  "Choose Stroke Color", strokeColor);

				} else {
					fillColor = JColorChooser.showDialog(null,  "Choose Fill Color", fillColor);
				}
				
			}
		});
		
		return theBut;
	}  // END OF makeMeColorButtons METHOD
	
	private class ListenForSlider implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			
			if (e.getSource() == transSlider) {
				transparentVal = (float) (transSlider.getValue() / 100.0f);
				System.out.println(transparentVal);
				transLabel.setText("    Transparancy: " + dec.format(transSlider.getValue()) + "%    ");
					
			}
			
		}
	}  // END OF ListenForSlider CLASS
	
}  // END OF Lesson49 CLASS
