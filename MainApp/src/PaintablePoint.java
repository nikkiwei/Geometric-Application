package Database;

/**
 * A paintable point geometric object.
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class PaintablePoint extends PaintableObject {

	// x coordinate of the point
	private double x;
	
	// y coordinate of the point
	private double y;

	/**
	 * Constructor for the point class
	 * @param a x coordinate of the point
	 * @param b y coordinate of the point
	 */
	public PaintablePoint(double a, double b) {

		super();
		
		// remember the coordinates of the point
		x = a;
		y = b;
	}
}
