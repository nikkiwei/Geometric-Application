package Database;

/**
 * A paintable point geometric object.
 * 
 * @author DB Team
 * @version 4/1/2018
 */

public class PaintablePoint extends PaintableObject {

	// x coordinate of the point
	private double x;
	
	// y coordinate of the point
	private double y;

	/**
	 * Constructor for the point class
	 * @param point the geometric point
	 */
	public PaintablePoint(Point point) {

		super(point);
		
		// remember the coordinates of the point
		x = point.getX();
		y = point.getY();
	}
	
	/**
	 * Get x coordinate
	 * @return x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get y coordinate
	 * @return y coordinate
	 */
	public double getY() {
		return y;
	}
}
