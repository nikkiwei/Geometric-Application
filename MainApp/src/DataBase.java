package Database;

/**
 * Store all the methods that a linked list can implement
 * 
 * @author DB Team
 * @version 3/22/2018
 */

public class DataBase {

	// unique identifier of each object
	private int currentKey;
	
	// the detectable range of each object
	private double range;
	
	// the list to store all the objects
	private LinkedList<GeomObject> objectList;
	
	// the list to store all paintable objects
	private LinkedList<PaintableObject> paintList;
	
	/**
	 * Constructor for the data base class
	 */
	public DataBase() {
		
		// initiate the key and range
		currentKey = 1;
		range = 5;
		
		// initiate the object list
		objectList = new LinkedList<GeomObject>();
		paintList = new LinkedList<PaintableObject>();
	}
	
	/**
	 * Insert a new object into the object list
	 * @param object the object to be inserted
	 */
	public void insert (GeomObject object) {
		
		// as long as the object is not null
		if (object != null) {

			// assign a key to the object and add it to the list, key = index
			object.setKey(currentKey);
			objectList.add(objectList.size(), object);
			currentKey++;
		}
	}
	
	/**
	 * Remove an object from the object list
	 * @param object the object to be deleted
	 */
	public void remove (GeomObject object) {

		// as long as the object is not null
		if (object != null) {

			// find the object in the list and delete it
			objectList.delete(getObjectIdx(object.getKey()));
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private int getObjectIdx(int key) {
		
		int idx = 0;
		
		// loop through the object list
		for (int i =0; i < objectList.size(); i++) {
			// find the object by its key
			if (objectList.get(i).getKey() == key) {
				return idx = i;
			}
		}
		return idx;
	}
	
	/**
	 * Search for any object within a certain range from the selected point
	 * @param x the x coordinate of the selected point
	 * @param y the y coordinate of the selected point
	 * @return any object that is close enough to the selected point
	 */
	public GeomObject search (double x, double y) {
		double left=0;
		double right=0;
		double up=0;
		double down=0;

		//
		for (int i = 0; i < objectList.size(); i++) {
			System.out.println("Searching");
			//System.out.println(objectList.get(i).getType());
			// if the object is a point
			if (objectList.get(i).getType() == 1) {
				System.out.println("Point");
				Point point = (Point) objectList.get(i);

				// if x is within a certain range of a, and y is within a certain range of b
				if (x >= point.getX() - range && x <= point.getX() + range) {
					if (y >= point.getY() - range && y <= point.getY() + range) {
						return point;
					}
				}
			}

			// if the object is a line
			else if (objectList.get(i).getType() == 2) {
				System.out.println("It's a line!");
				Line line = (Line) objectList.get(i);
				Point start = (Point) (objectList.get(getObjectIdx(line.getStart())));
				Point end = (Point) (objectList.get(getObjectIdx(line.getEnd())));

				// the cotangent value of the angle of the line with x-axis
				double angleCotangent = (Math.abs(start.getX() - end.getX())) // absolute value of x1-x2
						/(Math.abs(start.getY() - end.getY()) //absolute value of y1-y2
								);
				if(start.getX()<=end.getX()) {
					left = start.getX();
					right = end.getX();
				}
				else if(start.getX()>end.getX()) {
					left= end.getX();
					right=start.getX();
				}
				if(start.getY()<=end.getY()) {
					up= start.getY();
					down =end.getY();
				}
				else if(start.getY()>end.getY()) {
					up= end.getY();
					down =start.getY();
				}
				// when the line is parallel to x-axis
				if (up==down) {
					
					if(x>=left&&x<=right) {
						if(y>=up&&y<=down) {
							return line;
						}
						return null;
					}
					return null;
				}

				// when the line is parallel to y-axis
				else if (left==right) {
					if (y>=up&&y<=down) {
						if(x>=left&&x<=right) {
							return line;
						}
						return null;
					}
					return null;
				}

				// when start point is lower than the end point
				else if (start.getY() < end.getY()) {

					// the selected point's y coordinate has to be between the y coordinates of start and end points
					if (y <= end.getY() + range && y >= start.getY() - range) {

						// when the start point is on the left of the end point
						if (start.getX() < end.getX()) {

							// if the distance between the selected point and the line is within the range
							if (Math.abs(start.getX() + angleCotangent*(end.getY() - start.getY()) // this is the x value
									// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
									- x) <= range) {
								return line;
							}
						}

						// when the start point is on the right of the end point
						// if the distance between the selected point and the line is within the range
						else if (Math.abs(end.getX() + angleCotangent*(end.getY() - start.getY()) // this is the x value
								// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
								- x) <= range) {
							return line;
						}
					}
				}

				// when the start point is higher than the end point
				// the selected point's y coordinate has to be between the y coordinates of start and end points
				else if (y >= end.getY() + range && y <= start.getY() - range) {

					// when the start point is on the left of the end point
					if (start.getX() < end.getX()) {

						// if the distance between the selected point and the line is within the range
						if (Math.abs(start.getX() + angleCotangent*(start.getY() - end.getY()) // this is the x value
								// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
								- x) <= range) {
							return line;
						}
					}

					// when the start point is on the right of the end point
					// if the distance between the selected point and the line is within the range
					else if (Math.abs(end.getX() + angleCotangent*(start.getY() - end.getY()) // this is the x value
							// of the intersection point with the line if we draw a line parallel to the x-axis at the selected point
							- x) <= range) {
						return line;
					}

				}
			}
			
			// if the object is a polygon
			else if (objectList.get(i).getType() == 3) {
				
				// one side of the polygon
				Line segment;
				
/*				// loop through the points array
				for (int g = 0; g < points.length; g ++) {
					
					// the index of the next point in the array
					int j = g + 1;
					
					// when we get to the last point in the array, the index of the next connecting point is the first one
					if (g == points.length - 1) {
						j = 0;
					}
					
					// each side of the polygon
					segment = new Line(points[g], points[j]);
					
					// check whether the selected point is close enough to each side of the polygon
					if (segment.isClose(a, b)) {
						return true;
					}
				}*/
			}
		}
		
		//
		
		return null;
	}
	
	/**
	 * 
	 * @return a list of paintable objects
	 */
	public LinkedList<PaintableObject> paintList() {
		
		// loop through the object list
		for (int i = 0; i < objectList.size(); i++) {
			
			// if the object is a point
			if (objectList.get(i).getType() == 1) {
				Point point = (Point) objectList.get(i);
				PaintableObject pPoint = new PaintablePoint(point.getX(), point.getY());
				paintList.add(paintList.size(), pPoint);
			}
			
			// if the object is a line
			else if (objectList.get(i).getType() == 2) {
				Line line = (Line) objectList.get(i);
				Point start = (Point) objectList.get(getObjectIdx(line.getStart()));
				Point end = (Point) objectList.get(getObjectIdx(line.getEnd()));
				PaintableObject pLine = new PaintableLine(start.getX(), start.getY(), end.getX(), end.getY());
				paintList.add(paintList.size(), pLine);
			}
			
			// if the object is a polygon
			else if (objectList.get(i).getType() == 3) {
				PaintableObject polygon = new PaintablePolygon();
				paintList.add(paintList.size(), polygon);
			}
		}
		return paintList;
	}
	
	public int size() {
		return objectList.size();
	}
	public static void main(String[] args) {
		DataBase list = new DataBase();
		//System.out.println(list.size());
		GeomObject point5 = new Point(93, 55);
		GeomObject point6 = new Point(93, 21);
		GeomObject line2 = new Line((Point)point5, (Point)point6);
		list.insert(line2);
		list.search(93, 93);
	}
	
	public boolean isEmpty() {
		return objectList.isEmpty();
	}
	
//	public setCoordinate() {
//		
//	}
}
