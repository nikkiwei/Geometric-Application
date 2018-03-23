package Database;

/**
 * A point geometric object.
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class Point extends GeoObject {

	// x coordinate of the point
	private float x;
	
	// y coordinate of the point
	private float y;

	/**
	 * Constructor for the point class
	 * @param a x coordinate of the point
	 * @param b y coordinate of the point
	 */
	public Point(float a, float b) {

		super();
		
		// remember the coordinates of the point
		x = a;
		y = b;
	}
	
	@Override
	public boolean isClose(float a, float b) {
		
		// if x is within a certain range of a, and y is within a certain range of b
		if (x >= a - range && x <= a + range) {
			if (y >= b - range && y <= b + range) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the x coordinate of the point
	 * @return x coordinate of the point
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Get the y coordinate of the point
	 * @return y coordinate of the point
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Update the x coordinate of the point
	 * @param new x coordinate
	 */
	public void setX(float a) {
		x = a;
	}
	
	/**
	 * Update the y coordinate of the point
	 * @param new y coordinate
	 */
	public void setY(float b) {
		y = b;
	}
}
