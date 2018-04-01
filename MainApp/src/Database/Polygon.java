package Database;

/**
 * A Polygon geometric object
 * 
 * @author DB Team
 * @version 4/1/2018
 */

public class Polygon extends GeomObject {

	// a linked list for keys for each point
	private LinkedList<Integer> keys;
	
	// the number of vertices this polygon has
	private int numVertices;

	/**
	 * Constructor for the polygon
	 * NOTICE!!: WHEN ADDING VERTICES TO THE POINTS ARRAY, ADD THEM IN ORDER!!!!
	 * @param points the linked list of all the vertices of the polygon
	 */
	public Polygon(LinkedList<Point> points) {

		super();

		// initialize the key list
		keys = new LinkedList<Integer>();

		// Set the keys of the points in the polygon
		setPointsKeys(points);	
		
		// initialize the number of vertices
		numVertices = keys.size();

		// Key value for polygon
		type = 3;
	}	

	/**
	 * Get the keys of the points in the polygon.
	 * @param points the linked list of all the vertices of the polygon
	 */
	private void setPointsKeys(LinkedList<Point> points) {

		// Add keys to the keys linked list in order of the points
		for(int i = 0; i < points.size(); i++) {
			keys.add(keys.size(), points.get(i).getKey());
		}
	}

	/**
	 * A method that returns the keys of the points
	 * @return a linked list containing the keys
	 */
	public LinkedList<Integer> getPointsKeys() {
		return keys;
	}
	
	/**
	 * Get the number of vertices the polygon has
	 * @return the number of vertices this polygon has
	 */
	public int getNumVertices() {
		return numVertices;
	}

	/** 
	 * Given three colinear points p, q, r, the function checks if point q lies on line segment pr
	 * @param p starting point of the edge
	 * @param q the point being checked
	 * @param r ending point of the edge
	 * @return true if q lies on the segment of pr
	 */
	private boolean onSegment(Point p, Point q, Point r) {

		// if point q is on line pr, return true
		if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())  // the x coordinate of q has to be between x coordinates of p and r
				&& q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())  // the y coordinate of q has to be between y coordinates of p and r
				) {
			return true;
		}
		return false;	
	}

	/**
	 * To find orientation of ordered triplet (p, q, r).
	 * The function returns following values
	 * 0 --> p, q and r are colinear
	 * 1 --> Clockwise
	 * 2 --> Counterclockwise
	 * @param p one of the points of the edge
	 * @param q the point being checked
	 * @param r one of the points of the edge
	 * @return the type of the orientation
	 */
	private int orientation(Point p, Point q, Point r) {

		// line a = q.getY() - p.getY(), line b = r.getY() - q.getY(), line c = r.getX() - q.getX(), line d = q.getX() - p.getX()
		// the tangent value of line pq is a/d, and tangent value of line qr is b/c
		// if a/d = b/c, which is ac = bd, which is ac - bd = 0, then p, q, r is colinear
		// if a/d > b/c, which is ac > bd, which is ac - bd > 0, then p, q, r is clockwise
		// if a/d < b/c, which is ac < bd, which is ac - bd < 0, then p, q, r is counterclockwise

		// the value of ac - bd
		int val = (int) (Math.abs(q.getY() - p.getY()) * Math.abs(r.getX() - q.getX()) - Math.abs(q.getX() - p.getX()) * Math.abs(r.getY() - q.getY()));

		// the case where ac - bd = 0, so a/d = b/c
		if (val == 0) {

			// p, q, r is colinear
			return 0;
		}

		// the case where ac - bd > 0, so a/d > b/c
		else if (val > 0) {

			// p, q, r is clockwise
			return 1;
		}

		// the case where ac - bd < 0, so a/d < b/c
		else {

			// p, q, r is counterclockwise
			return 2;
		}
	}

	/**
	 * The function that returns true if line segment 'p1q1' and 'p2q2' intersect.
	 * @param p1 one of the points of the p1q1
	 * @param q1 one of the points of the p1q1
	 * @param p2 one of the points of the p2q2
	 * @param q2 one of the points of the p2q2
	 * @return true if p1q1 and p2q2 intersect
	 */
	private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {	

		// Find the four orientations for different combinations of three points
		int o1 = orientation(p1, p2, q1);
		int o2 = orientation(p1, q2, q1);
		int o3 = orientation(p2, p1, q2);
		int o4 = orientation(p2, q1, q2);

		// General case: no three vertices are colinear
		if (o1 != o2 && o3 != o4) {
			return true;
		}

		// Special Cases: at least three vertices are colinear
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1)) {
			return true;
		}

		// p1, q1 and q2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1)) {
			return true;
		}

		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2)) {
			return true;
		}

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2)) {
			return true;
		}

		// Doesn't fall in any of the above cases
		return false;
	}

	/**
	 * Check if one point is inside the polygon.
	 * @param polygonPoints the list of points of the polygon to be checked
	 * @param n the number of vertices of the polygon
	 * @param p the point being checked
	 * @return true if the point p lies inside the polygon with n vertices
	 */
	public boolean isInside(LinkedList<Point> polygonPoints, int n, Point p) {

		// a value that is large enough
		int infinite = 10000;

		// There must be at least 3 vertices in a polygon
		if (n < 3) {
			return false;
		}

		// Create a point for line segment from p to infinite
		Point extreme = new Point(infinite, p.getY());

		// Count intersections of the above line with sides of polygon
		int count = 0;

		// loop through all the points of the polygon
		for (int i = 0; i < polygonPoints.size(); i ++) {

			// index of the next point
			int next = i + 1;

			// if we reach the last point in the list
			if (i == polygonPoints.size() - 1) {
				next = 0;
			}

			// Check if the line segment from 'p' to 'extreme' intersects with the line segment from 'polygon[i]' to 'polygon[next]'
			if (doIntersect(polygonPoints.get(i), polygonPoints.get(next), p, extreme)) {

				// If the point 'p' is colinear with line segment 'i-next', then check if it lies on segment.
				if (orientation(polygonPoints.get(i), p, polygonPoints.get(next)) == 0) {
					
					// If it lies on the segment, return true, otherwise false
					return onSegment(polygonPoints.get(i), p, polygonPoints.get(next));
				}

				count++;
			}
		}

		// Return true if count is odd, false otherwise
		if (count % 2 == 1) {
			return true;
		}
		return false;
	}
}
