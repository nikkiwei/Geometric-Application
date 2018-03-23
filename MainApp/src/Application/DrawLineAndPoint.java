package Application;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;

/**
 * 
 * @author Jaemarie Solyst
 * @contributor Ioanna Deni
 * @contributor Sneha Kanaujia
 * @contributor Kiera McCabe
 * 
 * March 8, KM added DecimalFormat, coordinte getters, length arithmetic 
 * 3.16.18 KM--Added the basic arithmetic methods for creating ovals/ circles nd drawing them. also fixed the existing equations for line geometry; and set up instance variables with equations to find midpoints; unclear on how to actually save those.
 * 
 * This small application can draw a line and a point when the user drags (for a line)
 * and clicks (for a point).
 *
 */
public class DrawLineAndPoint extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	/**
	 * The size of a point when the mouse is clicked
	 */
	private static final int POINT_SIZE = 10;

	/**
	 * The size of points on the ends of lines
	 */
	private static final int ENDPOINT_SIZE = 5;

	// Takes x and y values from press and release methods
	private Point[] lineArray = new Point[2];

	// ints to keep track of X and Y values of where the user has pressed and
	// released clicks
	private int xPress, yPress, xDrag, yDrag, xRelease, yRelease;
	DecimalFormat df = new DecimalFormat("###.###");
	//	Instance variables for drawing circle KM
	double xDifference2, yDifference2, xHalfPt, yHalfPt, lineLength, diameter;

	// boolean to keep track of if the user has already clicked, or dragged
	private boolean released = false;
	private boolean drag = false;
	private boolean pressed=false;
	//this should be tied to a JButton, left true for now so I can check functionality KM
	private boolean circleMode=true;

	//These two instances allow the storing of the database
	//added 3/5/2018 by Ioanna Deni
	private DB database;

	/**
	 * Constructor adds the mouseListner so that the mouseEvents 
	 * will go through.
	 */
	public DrawLineAndPoint(DB database) {		

		//Adds MouseListener and MouseMotionListener to the canvas
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);

		addKeyListener(this);

		//Sets the canvas color to white
		this.setBackground(Color.WHITE);

		//allow the parameter to match the instance object in the class
		//added 3/5/2018 by Ioanna Deni
		this.database = database;
	}

	/**
	 * The paint method draws a line from where the user pressed to where
	 * they released. Draws a 
	 * @param g takes in graphics
	 */
	public void paint(Graphics g) {
		//method modified by demo provided by Louis Conover 
		//added 3/5/2018 by Ioanna Deni
		super.paint(g);

		//Checks if released boolean is true
		//added 3/6/18 by Jaemarie Solyst
		if (released==true) {

			//added by KM, 3.16.18
			if (drag==true&&circleMode==true) {
				drawCircle(g);
				//System.out.println("make a circle");
			} else

				//ADDED BY SNEHA KANAUJIA
				//Checks if drag boolean is true and then draws the line from start point to current mouse location point saved in release x and y variables
				if (drag==true){
					g.drawLine(xPress, yPress, xRelease, yRelease);
				}

			//Added by Jaemarie Solyst
			//for all the point arrays (that will be drawn into shapes) saved in data base
			for (int i=0; i<database.Size(); i++){
				Point[] shapeArray = database.Get(i); 

				// POINT: length of the array is 1
				if (shapeArray.length == 1) {

					Point p =  shapeArray[0];
					g.fillOval(p.x - (int)(POINT_SIZE/2), p.y - (int)(POINT_SIZE/2), POINT_SIZE, POINT_SIZE);
				}

				// LINE: length of the array is 2
				else if (shapeArray.length == 2 ) {

					//Save the two line end points from the array
					Point p1 = shapeArray[0];
					Point p2 = shapeArray[1];

					//Draw the line with two ovals on the end points
					g.fillOval(p1.x - (int)(ENDPOINT_SIZE/2), p1.y - (int)(ENDPOINT_SIZE/2), ENDPOINT_SIZE, ENDPOINT_SIZE);
					g.fillOval(p2.x - (int)(ENDPOINT_SIZE/2), p2.y - (int)(ENDPOINT_SIZE/2), ENDPOINT_SIZE, ENDPOINT_SIZE);
					g.drawLine(p1.x, p1.y, p2.x, p2.y);

				}
			}
		}
	}

	private void drawCircle(Graphics g) {
		g.drawOval(xPress, yPress, (int)(Math.sqrt(Math.abs(xDifference2 + yDifference2))), (int)(Math.sqrt(Math.abs(xDifference2 + yDifference2))));
		System.out.println("The diameter of the circle is " + diameter);
	}


	/** saves X and Y values where the mouse is initially pressed
	 * @param e mouse event passed in to record location of press
	 * written 3/6/2018 by Jaemarie Solyst
	 */
	public void mousePressed(MouseEvent e) {
		// save press X and Y values
		//Add the first point of a line to the array
		lineArray[0] = new Point(e.getX(),e.getY());

		// saves the current mouse position values
		xPress=e.getX();
		yPress=e.getY();
		System.out.println("mouse pressed at (" + xPress + ", " + yPress+ ")");
		drag=true;

	}

	/** saves X and Y values where the mouse is released
	 * @param e mouse event passed in to record location of release
	 * written 3/6/18 by Jaemarie Solyst
	 * supplemented 3/8/18 by Kiera McCabe
	 * Edited by KM 3.16 to correct math
	 */
	public void mouseReleased(MouseEvent e) {
		// save release X and Y values
		xRelease = e.getX();
		yRelease = e.getY();
		System.out.println("mouse released at (" + xRelease + ", " + yRelease	+ ")");
		if (xRelease==xPress && yRelease==yPress ) {
			System.out.println("The length of the line is " + 0);
		} else {
			// length of the line calculated using pythagorean
			xDifference2 = (xRelease - xPress) ^ 2;
			yDifference2 = (yRelease - yPress) ^ 2;

			//KM--Need advice on setting up the array for the midpoints; considering moving where it is
			xHalfPt= xPress+xDifference2/2;
			yHalfPt= yPress+yDifference2/2;

			new Point((int)xHalfPt, (int)yHalfPt);

			lineLength = (Math.sqrt(Math.abs(xDifference2 + yDifference2)));
			System.out.println("The length of the line is " + df.format(lineLength));
		}
		// save release X and Y values
		lineArray[1] = new Point(e.getX(), e.getY());

		//only add to data if the user clicked in the same place (this is not a press and release, it is a click)
		if (!(lineArray[0].equals(lineArray[1]))) {

			// add a clone of the array to avoid null pointer when lineArray is reset
			//Unclear on what is happening here--KM; I believe we need another point array or two for the mid point and selection functions
			database.Add(lineArray.clone());
		}
		//reset the lineArray
		lineArray[0] = null;
		lineArray[1] = null;

		// set boolean to true so that it may draw a line (and not draw anything prior to this)
		released = true;
		drag = false;
		repaint();
	}

	/**
	 * When the mouse is just clicked, add a Point array of length 1 to the database for a single point.
	 * added 3/6/18 Jaemarie Solyst
	 * @param e mouse event passed in to record location of release
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		// Create a point array of length 1 to save the point
		Point[] pointArray = new Point[1];
		pointArray[0] = new Point(e.getX(), e.getY());

		// Add the array to the database and update the canvas
		database.Add(pointArray);
		//repaint(); This line appears to do nothing. Commenting out for now; will remove later?
	}

	/**
	 * imported for MouseListener
	 * 		this is not the correct code; this is just proof? Need to implement a 
	 * key listener in combination with mouse listener
	 * @author mccab
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (pressed==true) {
			setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		} else {
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		// TODO Auto-generated method stub

	}

	/**
	 * imported for MouseListener
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * ADDED BY SNEHA KANAUJIA
	 * imported for MouseMotionListener
	 * Detects when user is dragging from a point
	 * Intended to show the user the line/object they are creating as they draw
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		// saves the current mouse position values while the mouse is still pressed/dragging to the class-wide release X and Y variables
		xDrag = e.getX();
		yDrag = e.getY();
//		xRelease = e.getX();
//		yRelease = e.getY();
		double xDif = (xDrag - xPress) ^ 2;
		double yDif = (yDrag - yPress) ^ 2;
		diameter=(Math.sqrt(Math.abs(xDif + yDif)));
		//set boolean to true so that in paint it may draw a line from the start point to "end" point/current mouse location
		drag = true;
		released = true;
		repaint();

	}

	/**
	 * imported for MouseMotionListener
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	/**
	 * based off of KM's GUITetrisController class. takes input from the user; the input it receives is from the keys
	 * Specifically shift. It should allow a double input of Shift and the mouse key
	 * @author mccab
	 *
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyEvent = e.getKeyCode();
		System.out.println(keyEvent);
		switch (keyEvent) {
		//I want to have a shift to trigger entering the select mode; eventually up or down
		case 16: //16 is the keyCode for the 'shift' keys
			pressed=true;
			//setCursor(new Cursor(Cursor.HAND_CURSOR));
			System.out.println("KEY PRESSED: "
					+ e.getKeyCode() + " : "+ e.getKeyChar());
			break;
		default:
			System.out
			.println("KEY RELEASED: "
					+ e.getKeyCode()
					+ " : "
					+ e.getKeyChar()
					+ "\n Error: Input not recognized.");
			pressed=false;

		}
		System.out.println(pressed);
		//refreshDisplay();		
	}

	private void refreshDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyEvent = e.getKeyCode();
		System.out.println(keyEvent);
		switch (keyEvent) {
		//I want to have a shift to trigger entering the select mode; eventually up or down
		case 16: //16 is the keyCode for the 'shift' keys
			pressed=false;
			//setCursor(new Cursor(Cursor.HAND_CURSOR));
			break;
		default:
			System.out
			.println("KEY RELEASED: "
					+ e.getKeyCode()
					+ " : "
					+ e.getKeyChar()
					+ "\n Error: Input not recognized.");
			pressed=false;
			// TODO Auto-generated method stub
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	} 
}
