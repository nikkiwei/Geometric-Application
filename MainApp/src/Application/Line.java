/**
 * A line geometric object
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class Line extends GeoObject {
	
	// the starting point of the line
	private Point start;
	
	// the ending point of the line
	private Point end;
	
	/**
	 * Constructor for the line
	 * @param x1 x coordinate of the start point
	 * @param y1 y coordinate of the start point
	 * @param x2 x coordinate of the end point
	 * @param y2 y coordinate of the end point
	 */
	public Line(float x1, float y1, float x2, float y2) {

		super();
		
		// initiate the starting and ending points
		start = new Point(x1, y1);
		end = new Point(x2, y2);
	}
	
	@Override
	public boolean isClose(float a, float b) {
		
		// the cotangent value of the angle of the line with x-axis
		double angleCotangent = (Math.abs(start.getX() - end.getX())) // absolute value of x1-x2
				/(Math.abs(start.getY() - end.getY()) //absolute value of y1-y2
						);
		
		// when the line is parallel to x-axis
		if (start.getY() == end.getY()) {
			
			// if the distance between the selected point and the line is within the range
			if (Math.abs(b - start.getY()) <= range) {
				return true;
			}
		}
		
		// when the line is parallel to y-axis
		else if (start.getX() == end.getY()) {
			
			// if the distance between the selected point and the line is within the range
			if (Math.abs(a - start.getX()) <= range) {
				return true;
			}
		}
		
		// when start point is lower than the end point
		else if (start.getY() < end.getY()) {
			
			// the selected point's y coordinate has to be between the y coordinates of start and end points
			if (b <= end.getY() + range && b >= start.getY() - range) {
				
				// when the start point is on the left of the end point
				if (start.getX() < end.getX()) {
					
					// if the distance between the selected point and the line is within the range
					if (Math.abs(start.getX() + angleCotangent*(end.getY() - start.getY()) // this is the x value
							// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
							- a) <= range) {
						return true;
					}
				}

				// when the start point is on the right of the end point
				// if the distance between the selected point and the line is within the range
				else if (Math.abs(end.getX() + angleCotangent*(end.getY() - start.getY()) // this is the x value
						// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
						- a) <= range) {
					return true;
				}
			}
		}
		
		// when the start point is higher than the end point
		// the selected point's y coordinate has to be between the y coordinates of start and end points
		else if (b >= end.getY() + range && b <= start.getY() - range) {
			
			// when the start point is on the left of the end point
			if (start.getX() < end.getX()) {
				
				// if the distance between the selected point and the line is within the range
				if (Math.abs(start.getX() + angleCotangent*(start.getY() - end.getY()) // this is the x value
						// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
						- a) <= range) {
					return true;
				}
			}

			// when the start point is on the right of the end point
			// if the distance between the selected point and the line is within the range
			else if (Math.abs(end.getX() + angleCotangent*(start.getY() - end.getY()) // this is the x value
					// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
					- a) <= range) {
				return true;
			}
			
		}	
		return false;
	}
	
	/**
	 * Get the starting point of the line
	 * @return the start point
	 */
	public Point getStart() {
		return start;
	}
	
	/**
	 * Get the ending point of the line
	 * @return the end point
	 */
	public Point getEnd() {
		return end;
	}
	
	/**
	 * Update the start point
	 * @param a the x coordinate of the new start point
	 * @param b the y coordinate of the new start point
	 */
	public void setStart(float a, float b) {
		start.setX(a);
		start.setY(b);
	}
	
	/**
	 * Update the end point
	 * @param a the x coordinate of the new end point
	 * @param b the y coordinate of the new end point
	 */
	public void setEnd(float a, float b) {
		end.setX(a);
		end.setY(b);
	}

}
