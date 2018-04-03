package Database;

/**
 * A paintable line geometric object
 * 
 * @author DB Team
 * @version 3/22/2018
 */

public class PaintableLine extends PaintableObject {

	// coordinates of starting and ending points
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	/**
	 * Constructor for paintable line
	 * @param a x coordinate of starting point
	 * @param b y coordinate of starting point
	 * @param x x coordinate of ending point
	 * @param y y coordinate of ending point
	 */
	public PaintableLine(Point start, Point end) {
		
		super(start);
		
		// initiate the four coordinates
		x1 = start.getX();
		y1 = start.getY();
		x2 = end.getX();
		y2 = end.getY();
		
		type = 2;
	}
	
	/**
	 * Get the x coordinate of the start point
	 * @return the x coordinate of the start point
	 */
	public double getStartX() {
		return x1;
	}
	
	/**
	 * Get the y coordinate of the start point
	 * @return the y coordinate of the start point
	 */
	public double getStartY() {
		return y1;
	}
	
	/**
	 * Get the x coordinate of the end point
	 * @return the x coordinate of the end point
	 */
	public double getEndX() {
		return x2;
	}
	
	/**
	 * Get the y coordinate of the end point
	 * @return the y coordinate of the end point
	 */
	public double getEndY() {
		return y2;
	}
}
