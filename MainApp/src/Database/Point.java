package Database;

/**
 * A point geometric object.
 * 
 * @author DB Team
 * @version 4/1/2018
 */

public class Point extends GeomObject {

	// x coordinate of the point
	private double x;
	
	// y coordinate of the point
	private double y;

	/**
	 * Constructor for the point class
	 * @param a x coordinate of the point
	 * @param b y coordinate of the point
	 */
	public Point(double a, double b) {

		super();
	
		// remember the coordinates of the point
		x = a;
		y = b;
		type = 1;
	}
	
	/**
	 * Get the x coordinate of the point
	 * @return x coordinate of the point
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get the y coordinate of the point
	 * @return y coordinate of the point
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Update the x coordinate of the point
	 * @param a new x coordinate
	 */
	public void setX(double a) {
		x = a;
	}
	
	/**
	 * Update the y coordinate of the point
	 * @param b new y coordinate
	 */
	public void setY(double b) {
		y = b;
	}
}
