package Database;

import java.awt.Color;

/**
 * The PaintableObject abstract class represents a paintable geometric object that could be a point, a line, or a polygon
 * 
 * @author DB Team
 * @version 4/1/2018
 */

public abstract class PaintableObject {

	// the color of the paintable object
	protected Color color;
	
	// the selection state of the paintable object
	protected boolean isSelected;
	
	/**
	 * Constructor for a paintable object
	 * @param object the corresponding geometric object
	 */
	public PaintableObject(GeomObject object) {
		
		// initialize the color and selection state
		color = object.getColor();
		isSelected = object.isSelected();
	}
	
	/**
	 * Get the color of the object
	 * @return object color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Determine if the object is selected or not
	 * @return true if the object is selected
	 */
	public boolean isSelected() {
		return isSelected;
	}
}
