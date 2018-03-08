package Application;
/**
 * The GeoObject abstract class represents a geometric object that could be a point, a line, or a polygon
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public abstract class GeoObject {
	
	// unique identifier of each object
	protected int key;
	
	// the detectable range of each object
	protected float range;
	
	/**
	 * Constructor for the geometric object
	 */
	public GeoObject() {
		range = 5;
	}
	
	/**
	 * Check whether the object is within a certain range of a selected point
	 * @param a, x coordinate passed in
	 * @param b, y coordinate passed in
	 * @return true if the object is within the range
	 */
	public abstract boolean isClose(float x, float y);
	
	/**
	 * Get the key value
	 * @return the value of key
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * Update the key value
	 * @param n the number passed in
	 */
	public void setKey(int n) {
		key = n;
	}
}
