package Application;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author Jaemarie Solyst
 * @contributor Ioanna Deni
 * 
 * This small application can draw a line and a point when the user drags (for a line)
 * and clicks (for a point).
 *
 */
public class DrawLineAndPoint extends Canvas implements MouseListener {
	/* ints to keep track of X and Y values of where the user has pressed and
	released clicks */
	private int pressX;
	private int pressY;
	private int releaseX;
	private int releaseY;
	
	// boolean to keep track of if the user has already clicked
	private boolean released = false;
	
	//These two instances allow the storing of the database
	//added 3/5/2018 by Ioanna Deni
	private DB database;

	/**
	 * Constructor adds the mouseListner so that the mouseEvents 
	 * will go through.
	 */
	public DrawLineAndPoint(DB database) {		
		addMouseListener(this);
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
	
		if (released==true) {
			System.out.println("in paint");
			
			for (int i=0; i<database.Size(); i=i+2){
				//for all the points saved in data base
				
				//for every two points get the first
				Point p1 = database.Get(i);
				
				//draw a little circle - method introduced by professor Louis Conover
				g.fillOval(p1.x - 2, p1.y - 2, 5, 5);
				
				//get the second point 
				Point p2 = database.Get(i+1);
				
				//draw another little circle and a line connecting the two - methods introduced by professor Louis Conover
				g.fillOval(p2.x - 2, p2.y - 2, 5, 5);
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
				
				//if we were to draw every point (instead of every two points)
				//then all lines would end up being connected!! We might need to use that functionality for shapes later
			}
		}
	}
	
	/** saves X and Y values where the mouse is initially pressed
	 * @param e mouse event passed in to record location of press
	 */
	public void mousePressed(MouseEvent e) {
		// save press X and Y values
		System.out.println("in press");
		pressX = e.getX();
		pressY = e.getY();
		
		//Add the new point with x,y coordinates to the database and increase the counter
		//added 3/5/2018 by Ioanna Deni
		database.Add(new Point(pressX,pressY));
	}

	/** saves X and Y values where the mouse is released
	 * @param e mouse event passed in to record location of release
	 */
	public void mouseReleased(MouseEvent e) {
		// save release X and Y values
		System.out.println("in release");
		releaseX = e.getX();
		releaseY = e.getY();
		
		//Add the new point with x,y coordinates to the database and increase the counter
		//added 3/5/2018 by Ioanna Deni
		database.Add(new Point(releaseX,releaseY));
		

		// set boolean to true so that it may draw a line (and not draw anything prior to this)
		released = true;
		repaint();
	}
	
	/**
	 * imported for MouseListener
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
