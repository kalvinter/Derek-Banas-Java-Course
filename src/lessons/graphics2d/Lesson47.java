package lessons.graphics2d;

//Base class for all swing-Components. Used for building custom components.
import javax.swing.JComponent;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.geom.*;


public class Lesson47 extends JFrame {

	public static void main(String[] args) {
		new Lesson47();
	}  // END OF main METHOD
	
	public Lesson47() {
		this.setSize(500, 500);
		this.setTitle("Drawing Shapes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new DrawStuff(), BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}  // END OF Lesson47 CONSTRUCTOR
	
	private class DrawStuff extends JComponent {
		public void paint(Graphics g) {
			Graphics2D graph2 = (Graphics2D) g;
			
			// .setRenderingHint(key, value) -> configure Renderer by setting key-value pairs
			// -> KEY_ANTIALIASING -> round of courners and edges
			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			/* Line2D.Float(x_start, x_end, y_start, y_end) */
			Shape drawLine = new Line2D.Float(20, 90, 55, 250);
			
			/* Arc2D.Double(x, y, width, height, starting_angle, angular_extend_in_degrees, closure_type) */
			Shape drawArc2D = new Arc2D.Double(5, 150, 100, 100, 45, 180, Arc2D.OPEN);
			Shape drawArc2D2 = new Arc2D.Double(5, 200, 100, 100, 45, 90, Arc2D.CHORD);
			Shape drawArc2D3 = new Arc2D.Double(5, 250, 100, 100, 45, 90, Arc2D.PIE);
			
			/* BASE METHODS
			 * .setPaint() -> set the color for all following methods
			 * .draw() -> draw the lines / outlines
			 * .fill() -> draw it by filling the area with the color specified in setPaint()
			 * 			  Fill can only fill shapes that have an area. A straight line will just be drawn.
			 * */
			graph2.setPaint(Color.black);
			
			graph2.fill(drawLine);
			graph2.draw(drawArc2D);
			graph2.draw(drawArc2D2);
			graph2.draw(drawArc2D3);
						
			// Ellipse2D.Float(x, y, width, height)
			Shape drawEllipse = new Ellipse2D.Float(10, 10, 100, 100);
			graph2.setPaint(Color.green);
			graph2.draw(drawEllipse);
			
			// Rectangle2D.Float(x, y, width, height)
			Shape drawRectangle = new Rectangle2D.Float(300, 300, 150, 100);
			graph2.setPaint(Color.blue);
			graph2.fill(drawRectangle);
			
			// RoundRectangle2D.Float(x, y, width, height, arcwidth, archeight)
			Shape drawRoundedRec = new RoundRectangle2D.Float(25, 25, 50, 50, 45, 45);
			graph2.setPaint(Color.red);
			graph2.fill(drawRoundedRec);
			
			/* CubicCurve2D.Double( 
			 * x1,
			 * y1, 
			 * ctrlx1,
			 * ctrly1,
			 * ctrlx2,
			 * ctrly2,
			 * x2,
			 * y2)
			 * Basically, you define start and end points for x and y.
			 * Additionally, you define two intermediary points through which the curve should pass through
			 * Java then calculates an appropriate curve that fits these points
			 * */
			CubicCurve2D cubicCurve = new CubicCurve2D.Double(110, 50, 300, 200, 200, 200, 90, 263);
			graph2.setPaint(Color.magenta);
			graph2.draw(cubicCurve);
			
			// QuadCurve2D.Float(x_start, y_start, ctrlx, ctrly, x_end, y_end)
			// ctrl = control point of the quadratic curve segment.
			Shape drawQuadCurve = new QuadCurve2D.Float(300, 100, 400, 200, 150, 300);
			graph2.setPaint(Color.cyan);
			graph2.draw(drawQuadCurve);
			
			graph2.setPaint(Color.gray);
			
			/* setComposite() -> The AlphaComposite class encapsulates various compositing styles, 
			 * which determine how overlapping objects are rendered.
			 * The Composite is used in all drawing methods such as drawImage, drawString, draw, and fill.
			 * 
			 * SRC_OVER -> the shapes are drawn on top of existing shapes
			 * The Float value is Alpha -> in this case this causes shapes to have 60% transparancy
			 * */ 
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40F));

			graph2.fill(new Rectangle2D.Float(10, 10, 150, 100));

			// Rectangle2D.Double(x, y, width, height)
			Shape drawTransRect = new Rectangle2D.Double(300, 300, 75, 50);
			// It gets transparent because we have set the composite before
			
			graph2.setPaint(Color.yellow);
			graph2.fill(drawTransRect);
			
			// Remove the transparancy -> set it back to 1.0F
			graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
			
			/* Make Gradients 
			 * x1, y1,
			 * color1 -> Color at the first specified point
			 * x2, y2
			 * color2 -> Color at the second specified point
			 * boolean cyclic -> default false. If true, color2 is in the middle and color1 is at both endpoints
			 * 
			 * => Depending on how x1/y1 are positioned to x2/y2 -> the gradient will have the same angle
			 * as a straight line drawn between both endpoints
			 * */
			GradientPaint theGradient = new GradientPaint(300, 50, Color.blue, 300, 100, new Color(0x66ffff), true);
			graph2.setPaint(theGradient);
			
			Shape gradientRectangel = new Rectangle2D.Float(300, 50, 100, 100);
			graph2.fill(gradientRectangel);
		}
	}  // END OF DrawStuff CLASS
}
