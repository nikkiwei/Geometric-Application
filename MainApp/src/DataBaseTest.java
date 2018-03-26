package Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Before;

class DataBaseTest {
	// testing an empty list
	private DataBase emptyList;
	
	// testing a non-empty list
	private DataBase list;
	
	// the geometric objects to test the methods on
	private GeomObject point1;
	private GeomObject point2;
	private GeomObject point3;
	private GeomObject point4;
	private GeomObject point5;
	private GeomObject point6;
	private GeomObject point7;
	private GeomObject point8;
	private GeomObject point9;
	//private GeomObject point10;
	private GeomObject line1;
	private GeomObject line2;
	private GeomObject line3;
	private GeomObject line4;
	private GeomObject line5;
	private GeomObject line6;
	private GeomObject polygon1;
	private GeomObject polygon2;
	private GeomObject nil;
	
	/**
	 * Set up all the geometric objects
	 */
	public void setUp() {
		
		// set up all the geometric objects
		point1 = new Point(20, 30);
		point2 = new Point(60, 40);
		point3 = new Point(25, 38);
		point4 = new Point(25, 92);
		point5 = new Point(93, 55);
		point6 = new Point(46, 55);
		point7 = new Point(10, 20);
		point8 = new Point(40, 10);
		point9 = new Point(70, 20);
		//point10 = new Point(46, 55);
		line1 = new Line((Point)point3, (Point)point4);
		line2 = new Line((Point)point5, (Point)point6);
		line3 = new Line((Point)point7, (Point)point8);
		line4 = new Line((Point)point8, (Point)point7);
		line5 = new Line((Point)point8, (Point)point9);
		line6 = new Line((Point)point9, (Point)point8);
		Point[] pointArr1 = {new Point(70, 20), new Point(15, 200), new Point(200, 180)};
		polygon1 = new Polygon(pointArr1);
		Point[] pointArr2 = {new Point(200, 200), new Point(150, 125), new Point(100, 180), new Point(85, 235)};
		polygon2 = new Polygon(pointArr2);
		nil = null;
	}
	
	@Before
	public void initList() {
		// initialize the empty list
		emptyList = new DataBase();
		
		// initialize the non-empty list
		list = new DataBase();
		setUp();
		
		// add these geometric objects to this list
		list.insert(point1);
		list.insert(point3);
		list.insert(point4);
		//list.insert(line1);

		list.insert(polygon1);
		
	}
//	@Test
//	public void initEmptyList() {
//		
//	}
	
//	@Test
//	public void testInsertion() {
//		initList();
//		// insert a null value and test the insertion
//		//emptyList.insert(nil);
//		assertEquals(0, emptyList.size(),"there should not be a new space for the null value in the list");
//
//		// insert point2 and test the insertion
//		emptyList.insert(point2);
//		assertNotNull(emptyList.search(((Point)point2).getX(), ((Point)point2).getY()),"the head node should not be null");
//		assertSame(point2, emptyList.search(((Point)point2).getX(), ((Point)point2).getY()), "the head node should be point2");
//		assertEquals(1, emptyList.size(), "the list now should have size of 1");
//
//		// insert line2 and test the insertion
//		emptyList.insert(line2);
//		
////		assertNotNull(emptyList.search(93, 21), "the second node should not be null");
////		assertSame(line2, emptyList.search(((Line)line2).getStart(),((Line)line2).getEnd()),"the second node should be line2");
////		assertEquals(2, emptyList.size(),"the list now should have size of 2");
//		
////		// insert polygon2 and test the insertion
//		emptyList.insert(polygon2);
////		assertNotNull("the third node should not be null", emptyList.get(2));
////		assertSame("the third node should be polygon2", polygon2, emptyList.get(0));
////		assertEquals("the list now should have size of 3", 3, emptyList.size());
//	}
	
//	@Test
//	public void testPointSearch() {
//		initList();	
//		
//		// the case when x, y are the same as the actual x, y of the point
//		assertSame(point1, list.search(20, 30), "within the detectable range should return point1");
//		
//		list.insert(point2);
//		// the case when x, y are larger than actual x, y of the point, the original point is (60, 40)
//		assertSame( point2, list.search(62, 41), "within the detectable range should return point1");
//		
//		//list.insert(point3);
//		// the case when x,y are smaller than actual x, y of the point, the original point is (25, 38)
//		assertSame( point2, list.search(58, 39),"within the detectable range should return point1");
//	}
	
	@Test
	public void testLineSearch() {
		initList();
		// the case where the line is parallel to y-axis
//		assertSame(line1, list.search(26, 32), "within the detectable range should return line1");
//		
//		// the case when x, y are on the line
//		assertSame(line1, list.search(30, 46),"within the detectable range should return line1");
//		
//		// the case where the line is parallel to x-axis
//		list.insert(point5);
//		list.insert(point6);
//		list.insert(line2);
//		assertSame(line2, list.search(56, 56),"within the detectable range should return line2");
//		
//		assertSame(line2, list.search(87, 60),"within the detectable range should return line2");
		
		// the case where the left point is lower
		list.insert(point7);
		list.insert(point8);
//		// start point on left and end point on right
//		list.insert(line3);
//		assertSame(line3, list.search(20, 16),"within the detectable range should return line3");
		
		// start point on right and end point on left
//		list.insert(line4);
//		assertSame(line4, list.search(20, 16),"within the detectable range should return line2");
		
		// the case where the left point is higher
		list.insert(point9);
		// start point on left and end point on right
		list.insert(line5);
		// point on the line
		assertSame(line5, list.search(60, 50/3),"within the detectable range should return line2");
		// point next to the line
		assertSame(line5, list.search(57, 16),"within the detectable range should return line2");
		
//		list.insert(line6);
//		// point on the line
//		assertSame(line6, list.search(60, 50/3),"within the detectable range should return line2");
//		// point next to the line
//		assertSame(line6, list.search(57, 16),"within the detectable range should return line2");
	}
	
	
//	@Test
//	public void testPointRemove() {
//		initList();
//		// remove point1 and test the removal
//		list.remove(point1);
//		//assertSame(line1, list.get(point1.getKey()), "the location of point1 now should be line1");
//		//assertEquals( , list.size(), "the size of the list should decrease by 1");
//	}
//	
//	@Test
//	public void testLineRemove() {	
//		initList();
//		list.remove(point1);
//		// remove line1 and test the removal
//		list.remove(line1);
//		//assertSame(polygon1, list.get(point1.getKey()), "the location of line1 now should be polygon1");
//		//assertEquals(1, list.size(), "the size of the list should decrease by 1");
//	}
//	
//	@Test
//	public void testPolygonRemove() {
//		initList();
//		list.remove(point1);
//		// remove line1 and test the removal
//		list.remove(line1);
//		// remove polygon1 and test the removal
//		list.remove(polygon1);
//		//assertNull(list.get(point1.getKey()), "the location of line1 now should be null");
//		//assertEquals(0, list.size(), "the size of the list should decrease by 1");
//		//assertTrue(list.isEmpty(), "the list is now empty");
//	}

}
