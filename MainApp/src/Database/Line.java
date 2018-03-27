package Database;

/**
 * A line geometric object
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class Line extends GeomObject {
	private Point startP;
	private Point endP;
	// the key of the starting point of the line
	private int start;
	
	// the key of the ending point of the line
	private int end;
	
	/**
	 * Constructor for the line
	 * @param s the start point
	 * @param e the end point
	 */
	public Line(Point s, Point e) {

		super();
		startP =s;
		endP =e;
		// initiate the starting and ending points' keys
		start = s.getKey();
		end = e.getKey();
		type = 2;
	}
	
	/**
	 * Get the key of starting point of the line
	 * @return the start point
	 */
	public int getStart() {
		return startP.getKey();
		//return start;
	}
	
	/**
	 * Get the key of ending point of the line
	 * @return the end point
	 */
	public int getEnd() {
		return endP.getKey();
		//return end;
	}
	
	/**
	 * Update the start point
	 * @param a the x coordinate of the new start point
	 * @param b the y coordinate of the new start point
	 */
	public void setStart(Point s) {
		start = s.getKey();
	}
	
	/**
	 * Update the end point
	 * @param a the x coordinate of the new end point
	 * @param b the y coordinate of the new end point
	 */
	public void setEnd(Point e) {
		end = e.getKey();
	}
}
