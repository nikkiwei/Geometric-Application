/*package Database;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObjectListTest {
	
	// testing an empty list
	private ObjectList emptyList;
	
	// testing a non-empty list
	private ObjectList list;
	
	// the geometric objects to test the methods on
	private GeomObject point1;
	private GeomObject point2;
	private GeomObject line1;
	private GeomObject line2;
	private GeomObject polygon1;
	private GeomObject polygon2;
	private GeomObject nil;
	
	*//**
	 * Set up all the geometric objects
	 *//*
	public void setUp() {
		
		// set up all the geometric objects
		point1 = new Point(20, 30);
		point2 = new Point(60, 40);
		line1 = new Line(10, 10, 90, 90);
		line2 = new Line(70, 35, 140, 80);
		Point[] pointArr1 = {new Point(70, 20), new Point(15, 200), new Point(200, 180)};
		polygon1 = new Polygon(pointArr1);
		Point[] pointArr2 = {new Point(200, 200), new Point(150, 125), new Point(100, 180), new Point(85, 235)};
		polygon2 = new Polygon(pointArr2);
		nil = null;
	}
	
	@Before
	public void initEmptyList() {
		
		// initialize the empty list
		emptyList = new ObjectList();
		setUp();
	}
	
	@Before
	public void initList() {
		
		// initialize the non-empty list
		list = new ObjectList();
		setUp();
		
		// add these geometric objects to this list
		list.insert(point1);
		list.insert(line1);
		list.insert(polygon1);
	}

	@Test
	public void testInsertion() {
		
		// insert a null value and test the insertion
		emptyList.insert(nil);
		assertEquals("there should not be a new space for the null value in the list", 0, emptyList.size());

		// insert point2 and test the insertion
		emptyList.insert(point2);
		assertNotNull("the head node should not be null", emptyList.get(0));
		assertSame("the head node should be point2", point2, emptyList.get(0));
		assertEquals("the list now should have size of 1", 1, emptyList.size());

		// insert line2 and test the insertion
		emptyList.insert(line2);
		assertNotNull("the second node should not be null", emptyList.get(1));
		assertSame("the second node should be line2", line2, emptyList.get(0));
		assertEquals("the list now should have size of 2", 2, emptyList.size());
		
		// insert polygon2 and test the insertion
		emptyList.insert(polygon2);
		assertNotNull("the third node should not be null", emptyList.get(2));
		assertSame("the third node should be polygon2", polygon2, emptyList.get(0));
		assertEquals("the list now should have size of 3", 3, emptyList.size());
	}
	
	@Test
	public void testPointSearch() {
		
		// the case when x,y are smaller than actual x, y of the point
		assertSame("within the detectable range should return point1", point1, list.search(18, 25));
		
		// the case when x, y are the same as the actual x, y of the point
		assertSame("within the detectable range should return point1", point1, list.search(20, 30));
		
		// the case when x, y are larger than actual x, y of the point
		assertSame("within the detectable range should return point1", point1, list.search(25, 32));
	}
	
	@Test
	public void testLineSearch() {
		
		// the case when x, y are above the line
		assertSame("within the detectable range should return line1", line1, list.search(47, 48));
		
		// the case when x, y are on the line
		assertSame("within the detectable range should return line1", line1, list.search(20, 20));
		
		// the case when x, y are below the line
		assertSame("within the detectable range should return line1", line1, list.search(76, 75));
	}
	
	@Test
	public void testPolygonSearch() {
		
		// the case when x, y are inside the polygon
		assertSame("within the detectable range should return polygon1", polygon1, list.search(70, 22));
		
		// the case when x, y are on one side of the polygon
		assertSame("within the detectable range should return polygon1", polygon1, list.search(200, 180));
		
		// the case when x, y are outside the polygon
		assertSame("within the detectable range should return polygon1", polygon1, list.search(14, 199));
	}
	
	@Test
	public void testNullSearch() {
		
		// outside the range (outside of polygon)
		assertNull("outside the detectable range should return null", list.search(200, 100));
		
		// outside the range (inside of polygon)
		assertNull("outside the detectable range should return null", list.search(70, 100));
	}
	
	@Test
	public void testPointRemove() {
		
		// remove point1 and test the removal
		list.remove(point1);
		assertSame("the location of point1 now should be line1", line1, list.get(point1.getKey()));
		assertEquals("the size of the list should decrease by 1", 2, list.size());
	}
	
	@Test
	public void testLineRemove() {
		
		// remove line1 and test the removal
		list.remove(line1);
		assertSame("the location of line1 now should be polygon1", polygon1, list.get(point1.getKey()));
		assertEquals("the size of the list should decrease by 1", 1, list.size());
	}
	
	@Test
	public void testNullRemove() {
		
		// remove a null object and test the removal
		list.remove(nil);
		assertSame("the location of line1 now should be polygon1", polygon1, list.get(point1.getKey()));
		assertEquals("the size of the list should decrease by 1", 1, list.size());
		assertFalse("the list is not empty", list.isEmpty());
	}
	
	@Test
	public void testPolygonRemove() {
		
		// remove polygon1 and test the removal
		list.remove(polygon1);
		assertNull("the location of line1 now should be null", list.get(point1.getKey()));
		assertEquals("the size of the list should decrease by 1", 0, list.size());
		assertTrue("the list is now empty", list.isEmpty());
	}

}
*/