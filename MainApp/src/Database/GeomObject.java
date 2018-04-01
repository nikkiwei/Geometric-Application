package Database;

import java.awt.Color;

/**
 * The GeoObject abstract class represents a geometric object that could be a point, a line, or a polygon
 * 
 * @author DB Team
 * @version 4/1/2018
 */

public abstract class GeomObject {
	
	// unique identifier of each object
	protected int key;
	
	// differentiate between points, lines, and polygons
	protected int type;
	
	// the selection state of the object
	protected boolean isSelected;
	
	// the color of the object
	protected Color color;
	
	/**
	 * Constructor for the geometric object
	 */
	public GeomObject() {
		isSelected = false;
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
	
	/**
	 * Get the color of the object
	 * @return object color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Change object's color
	 * @param newColor set color to the new color
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	/**
	 * Determine if the object is selected or not
	 * @return true if the object is selected
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * Select an object
	 */
	public void select() {
		isSelected = true;
	}
}
