package GeometryPack;

import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import org.junit.Test;

public class GeometryPackTest {


	
	@Test
	public void testAngleTriangle() {
		GeometryPack testG = new GeometryPack();
		double expected = Math.PI/6;
		double actual = testG.angleTriangle(25, 50);
		assertEquals(expected,actual,0.00001);
	}


	@Test
	public void heightTriangleTest()
	{
		GeometryPack testG = new GeometryPack();
		Point2D a = new Point2D.Double(0, 5);
		Point2D b = new Point2D.Double(3, 0);
		Point2D c = new Point2D.Double(6, 5);
		assertEquals(5, testG.heightTriangle(a, b, c), 0.001);
	}


	@Test
	public void lineLengthTest()
	{
		GeometryPack testG = new GeometryPack();
		Point2D a = new Point2D.Double(0, 5);
		Point2D c = new Point2D.Double(6, 5);
		
		assertEquals(6, testG.lineLength(a,c),0.001);
	}
}
