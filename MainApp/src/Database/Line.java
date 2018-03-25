/**
 * A line geometric object
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class Line extends GeomObject {
	
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
		
		// initiate the starting and ending points' keys
		start = s.getKey();
		end = e.getKey();
	}
	
	@Override
	public boolean isClose(double a, double b) {
		
		// the cotangent value of the angle of the line with x-axis
		double angleCotangent = (Math.abs(startP.getX() - endP.getX())) // absolute value of x1-x2
				/(Math.abs(startP.getY() - endP.getY()) //absolute value of y1-y2
						);
		
		// when the line is parallel to x-axis
		if (startP.getY() == endP.getY()) {
			
			// if the distance between the selected point and the line is within the range
			if (Math.abs(b - startP.getY()) <= range) {
				return true;
			}
		}
		
		// when the line is parallel to y-axis
		else if (startP.getX() == endP.getY()) {
			
			// if the distance between the selected point and the line is within the range
			if (Math.abs(a - startP.getX()) <= range) {
				return true;
			}
		}
		
		// when start point is lower than the end point
		else if (startP.getY() < endP.getY()) {
			
			// the selected point's y coordinate has to be between the y coordinates of start and end points
			if (b <= endP.getY() + range && b >= startP.getY() - range) {
				
				// when the start point is on the left of the end point
				if (startP.getX() < endP.getX()) {
					
					// if the distance between the selected point and the line is within the range
					if (Math.abs(startP.getX() + angleCotangent*(endP.getY() - startP.getY()) // this is the x value
							// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
							- a) <= range) {
						return true;
					}
				}

				// when the start point is on the right of the end point
				// if the distance between the selected point and the line is within the range
				else if (Math.abs(endP.getX() + angleCotangent*(endP.getY() - startP.getY()) // this is the x value
						// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
						- a) <= range) {
					return true;
				}
			}
		}
		
		// when the start point is higher than the end point
		// the selected point's y coordinate has to be between the y coordinates of start and end points
		else if (b >= endP.getY() + range && b <= startP.getY() - range) {
			
			// when the start point is on the left of the end point
			if (startP.getX() < endP.getX()) {
				
				// if the distance between the selected point and the line is within the range
				if (Math.abs(startP.getX() + angleCotangent*(startP.getY() - endP.getY()) // this is the x value
						// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
						- a) <= range) {
					return true;
				}
			}

			// when the start point is on the right of the end point
			// if the distance between the selected point and the line is within the range
			else if (Math.abs(endP.getX() + angleCotangent*(startP.getY() - endP.getY()) // this is the x value
					// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
					- a) <= range) {
				return true;
			}
			
		}	
		return false;
	}
	
	/**
	 * Get the key of starting point of the line
	 * @return the start point
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Get the key of ending point of the line
	 * @return the end point
	 */
	public int getEnd() {
		return end;
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
