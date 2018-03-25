package Database;

/**
 * A paintable line geometric object
 * 
 * @author DB Team
 * @version 3/22/2018
 */

public class PaintableLine extends PaintableObject {

	//
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	//
	
	/**
	 * Constructor for paintable line
	 * @param a x coordinate of starting point
	 * @param b y coordinate of starting point
	 * @param x x coordinate of ending point
	 * @param y y coordinate of ending point
	 */
	public PaintableLine(double a, double b, double x, double y) {
		
		super();
		
		// initiate the four coordinates
		x1 = a;
		y1 = b;
		x2 = x;
		y2 = y;
	}
}
