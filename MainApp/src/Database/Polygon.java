package Database;

/**
 * A Polygon geometric object
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class Polygon extends GeomObject {
	
	// an array of all the vertices of the polygon
	private Point[] points;

	/**
	 * Constructor for the polygon
	 * NOTICE!!: WHEN ADDING VERTICES TO THE POINTS ARRAY, ADD THEM IN ORDER!!!!
	 * @param point the array of all the vertices of the polygon
	 */
	public Polygon(Point[] points) {
		
		super();
		
		// initialize the point array
		this.points = points;	
	}
	
/*	public boolean isClose(double a, double b) {
		
		// one side of the polygon
		Line segment;
		
		// loop through the points array
		for (int i = 0; i < points.length; i ++) {
			
			// the index of the next point in the array
			int j = i + 1;
			
			// when we get to the last point in the array, the index of the next connecting point is the first one
			if (i == points.length - 1) {
				j = 0;
			}
			
			// each side of the polygon
			segment = new Line(points[i], points[j]);
			
			// check whether the selected point is close enough to each side of the polygon
			if (segment.isClose(a, b)) {
				return true;
			}
		}
		return false;
	}*/

}
