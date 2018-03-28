package Application;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.Stack; 
import javax.swing.JPanel;

/**
 * 
 * @author Jaemarie Solyst
 * @contributor Ioanna Deni
 * @contributor Sneha Kanaujia
 * @contributor Kiera McCabe
 * 
 * March 8, KM added DecimalFormat, coordinate getters, length arithmetic 
 * 
 * This small application can draw a line and a point when the user drags (for a line)
 * and clicks (for a point).
 *
 */
public class DrawLineAndPoint extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * The size of a point when the mouse is clicked
	 */
	private static final int POINT_SIZE = 10;

	/**
	 * The size of points on the ends of lines
	 */
	private static final int ENDPOINT_SIZE = 5;
	
	/**
	 * Number of modes.
	 */
	private static final int MODE_NUM = 2;
	
	/**
	 * Line mode has index 1
	 */
	private static final int LINE_MODE = 0;
	
	/**
	 * Circle mode has index 1
	 */
	private static final int CIRCLE_MODE = 1;

	/**
	 * Point array that takes x and y values from press and release methods
	 */
	private Point[] lineArray = new Point[2];

	/** 
	 * The int variables meant to keep track of X and Y values of where the user has pressed and released clicks
	 */
	private int xPress, yPress, xRelease, yRelease;
	DecimalFormat df = new DecimalFormat("###.###");

	//Booleans to keep track of if the user has already clicked, dragged, or entered draw line mode
	private boolean released = false;
	private boolean drag = false;
	
	//Modes Array holds booleans to determine which mode it is in (see constant mode values on top)
	private boolean[] modeArray = new boolean[MODE_NUM];

	//These two instances allow the storing of the database
	//added 3/5/2018 by Ioanna Deni
	private DB database;

	//Stack for undo and redo
	private Stack<DB> dbStack = new Stack<DB>();

	/**
	 * Constructor adds the mouseListner so that the mouseEvents 
	 * will go through.
	 */
	public DrawLineAndPoint(DB database) {		

		//Adds MouseListener and MouseMotionListener to the canvas
		addMouseListener(this);
		addMouseMotionListener(this);

		//Sets the JPanel background color to white
		this.setBackground(Color.WHITE);

		//Allows the parameter to match the instance object in the class
		//added 3/5/2018 by Ioanna Deni
		this.database = database;
	}

	/**
	 * Switches the mode based on a String passed in.
	 * Written by Jaemarie Solyst
	 * @param mode mode to switch
	 */
	public void switchMode(String mode) {
		int modeIndex = -1;
		
		//Set the array index based on the mode
		if (mode.equals("line") || mode.equals("Line")) {
			modeIndex = LINE_MODE;
		}
		else if (mode.equals("circle") || mode.equals("Circle")) {
			modeIndex = CIRCLE_MODE;
		}
		//Otherwise, if the passed in String is not any mode, stop the method
		else if (modeIndex == -1) {
			return;
		}
		
		//Go through the modeArray and change all to false except the modeIndex
		for (int i = 0; i < modeArray.length; i++) {
			//If the place in the array is not the modeIndex, set it to false
			if (i != modeIndex) {
				modeArray[i] =  false;
			}
			//Otherwise, if it is at the modeIndex
			else if (i == modeIndex) {
				
				//Switch it (if false to true, if true to false)
				if(modeArray[modeIndex]) {
					modeArray[modeIndex] = false;
				}
				else if(!modeArray[modeIndex]) {
					modeArray[modeIndex] = true;
					System.out.println("In drawCircleMode");
				}
			}
		}
	}

	/**
	 * Thus far, this paint method draws a line from where the user pressed to where
	 * they released when in line mode and draws points that are created when users
	 * click on the JPanel.
	 * @param g takes in graphics
	 */
	public void paint(Graphics g) {
		//method modified by demo provided by Louis Conover 
		//added 3/5/2018 by Ioanna Deni
		super.paint(g);

		//Checks if released boolean is true
		//added 3/6/18 by Jaemarie Solyst
		if (released==true) {

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

					//Saves the point from the array
					Point p =  shapeArray[0];
					//Draws ovals at the point's location
					g.fillOval(p.x - (int)(POINT_SIZE/2), p.y - (int)(POINT_SIZE/2), POINT_SIZE, POINT_SIZE);
				}

				// LINE: length of the array is 2
				else if (shapeArray.length == 2 ) {

					//Saves the two line end points from the array
					Point p1 = shapeArray[0];
					Point p2 = shapeArray[1];

					//Draws the line with two ovals on the end points
					g.fillOval(p1.x - (int)(ENDPOINT_SIZE/2), p1.y - (int)(ENDPOINT_SIZE/2), ENDPOINT_SIZE, ENDPOINT_SIZE);
					g.fillOval(p2.x - (int)(ENDPOINT_SIZE/2), p2.y - (int)(ENDPOINT_SIZE/2), ENDPOINT_SIZE, ENDPOINT_SIZE);
					g.drawLine(p1.x, p1.y, p2.x, p2.y);
				}
			}
		}
	}

	/** 
	 * Method that senses when a user has pressed the mouse and saves the X and Y values where the mouse is initially pressed
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

	/** 
	 * Method that senses when the mouse is released and saves the X and Y values where the mouse is released
	 * @param e mouse event passed in to record location of release
	 * written 3/6/18 by Jaemarie Solyst
	 * supplemented 3/8/18 by Kiera McCabe
	 * supplemented 3/14/18 by Sneha Kanaujia
	 */
	public void mouseReleased(MouseEvent e) {
		// Saves the X and Y values of the point where the user released the mouse
		xRelease = e.getX();
		yRelease = e.getY();
		System.out.println("mouse released at (" + xRelease + ", " + yRelease	+ ")");

		// Calculates the length of the line calculated using pythagorean theorem
		double xDifference2 = (xRelease - xPress) ^ 2;
		double yDifference2 = (yRelease - yPress) ^ 2;
		double lineLength = (Math.sqrt(Math.abs(xDifference2 + yDifference2)));
		System.out.println("The length of the line is " + df.format(lineLength));

		// Saves the X and Y values of the point where the user released the mouse into the line array
		lineArray[1] = new Point(e.getX(), e.getY());

		// Only adds the array of points to the database if the user didn't clicked in the same place (this is not a press and release, it is a click)
		if (!(lineArray[0].equals(lineArray[1])) && modeArray[LINE_MODE]) {
			
			// Adds a clone of the array to the database to avoid null pointer exception when lineArray is reset
			database.Add(lineArray.clone());
		}
		// Reset the lineArray
		lineArray[0] = null;
		lineArray[1] = null;

		// Sets released boolean to true so that it may draw a line (and not draw anything prior to this)
		released = true;
		// Sets drag boolean to false so that the end point is registered as a valid end point and the line can be drawn and added to the database.
		drag = false;
		repaint();
	}

	/**
	 * Method that senses when the mouse is clicked and adds a Point array of length 1 to the database for a single point.
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
		repaint();
	}

	/**
	 * Written 3/13/18 by Jaemarie Solyst
	 * This will be called by buttons.
	 * Deletes the last thing made in the array.
	 * 
	 */
	public void deleteLastShape() {
		//Delete last item in array
		if (database.size != 0) {

		//Create a copy of the current DB
		DB copyDB = new DB(database.getShapeList(), database.Size());
		
		//Store the current state of the DB in the history stack
		dbStack.push(copyDB);
		
		//Delete the last shape
		database.delete(database.Size()-1);
		
		//refresh the view
		repaint();
		}
	}
	
	/**
	 * Written 3/13/18 by Jaemarie Solyst
	 * This will be called by buttons.
	 * Readds the last shape made in the array.
	 */
	public void addLastShape() {
		//Add the last shape onto the database
		if (dbStack.size() != 0) {
			
		//Make the current database the last one on the history stack
		database = dbStack.pop();
		
		//Refresh the view
		repaint();
		}
	}

	/**
	 * Written 3/13/18 by Jaemarie Solyst
	 * This method will be called by buttons.
	 * Clears all by reseting the db (database) and thus the JPanel.
	 */
	public void clearAll() {
		//Creates a new DB
		database = new DB();
		repaint();
	}

	/**
	 * ADDED BY SNEHA KANAUJIA
	 * imported for MouseMotionListener
	 * Detects when user is dragging from a point
	 * Intended to show the user the line/object they are creating as they draw
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		if(modeArray[LINE_MODE])
		{
			// Saves the current mouse position values while the mouse is still pressed/dragging to the class-wide release X and Y variables
			xRelease = e.getX();
			yRelease = e.getY();

			// Sets booleans to true so that in paint it may draw a line from the start point to "end" point/current mouse location
			drag = true;
			released = true;
			repaint();
		}
	}

	/**
	 * imported for MouseListener
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
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
	 * imported for MouseMotionListener
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * ADDED BY IOANNA DENI 3/21/2018
	 * Meant to repaint the JPanel
	 */
	public void canvasRepaint(){
		repaint();
	}

}