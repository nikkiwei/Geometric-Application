package Application;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class DrawPoint extends JPanel implements MouseListener {
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
