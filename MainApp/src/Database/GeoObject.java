/**
 * The GeoObject abstract class represents a geometric object that could be a point, a line, or a polygon
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public abstract class GeomObject {
	
	// unique identifier of each object
	protected int key;
	
	// differentiate between points, lines, and polygons
	protected int type;
	
	/**
	 * Constructor for the geometric object
	 */
	public GeomObject() {
	}
	
	/**
	 * Get the key value
	 * @return the value of key
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * Update the key value
	 * @param k the number passed in
	 */
	public void setKey(int k) {
		key = k;
	}
	
	/**
	 * Get the type of the object
	 * @return object type, 1 for points, 2 for lines, 3 for polygons
	 */
	public int getType() {
		return type;
	}
}
