package GeometryPack;

import java.awt.Graphics;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GeometryPack {

	/**
	 * The function takes a point with Cartesian coordinates and transforms them into the
	 *  screen
	 * @param thisHeight - height of the current window
	 * @param currentCoord - the coordinates of a point in cartesian coordinates
	 * @return the screen coordinates of a point
	 * @author Mashka 
	 */
	public Point2D coorScreen(int thisHeight, Point2D currentCoord)
	{
		Point2D newCoord = currentCoord;
		newCoord.setLocation(currentCoord.getX(), thisHeight - currentCoord.getY());
		return newCoord;
	}

	/**
	 * The function takes a point with screen coordinates and transforms them into the
	 *  cartesian
	 * @param thisHeight - height of the current window
	 * @param currentCoord - the coordinates of a point in screen coordinates
	 * @return the cartesian coordinates of a point
	 * @author Mashka 
	 */
	public Point2D coorCartesian(int thisHeight, Point2D currentCoord)
	{
		Point2D newCoord = currentCoord;
		newCoord.setLocation(currentCoord.getX(), thisHeight - currentCoord.getY());
		return newCoord;
	}

	/**
	 * Calculates angle a of a triangle
	 * @param height - height of the triangle
	 * @param hypotenuse - side adjacent to the angle
	 * @return angle in radians 
	 * @author Mashka 
	 */
	public double angleTriangle(double height, double hypotenuse)
	{
		return Math.asin(Math.abs(height/hypotenuse));
	}

	/**
	 * Calculates height of the triangle
	 * @param a coordinates of point a
	 * @param b coordinates of point b
	 * @param c coordinates of point c
	 * @return height of the triangle 
	 * @author Mashka 
	 */
	public double heightTriangle(Point2D a, Point2D b, Point2D c) {

		// calculates length of sides
		double base = lineLength(a,c);
		double sideL = lineLength(a,b);
		double sideR= lineLength(c,b);
		// calculates half perimeter
		double p = (base+sideL+sideR)/2;
		while(p==base||p==sideL||p==sideR)
			p=p+0.0001;

		double height = ((2*Math.sqrt(p*(p-base)*(p-sideR)* (p-sideL)))/base);
		// returns height of the triangle from annex b
		if(b.getY()<=a.getY())
			return height;
		else
			if(b.getY()>a.getY())
				return -height;
		return height;
	}



	/**
	 * Calculates line length from point a to point b
	 * @param a coordinates of point a
	 * @param b coordinates of point b
	 * @return length of line
	 * @author Mashka 
	 */
	public double lineLength( Point2D a, Point2D b)
	{
		//Uses Pythagorean theorem to find the line length
		return Math.sqrt((Math.pow((a.getX()-b.getX()), 2) + Math.pow(a.getY()-b.getY(), 2)));
	}

	/**
	 * Finds the end coordinates of a line
	 * of a given length drawn at a given angle
	 * from a point
	 * @param a the start point for the line
	 * @param d the length of a line 
	 * @param angle - the angle between the line and X axis
	 * @return Point that contains the end coordinates of the line
	 * @author Mashka
	 */
	public Point2D lineAtAngle(Point2D a, double d, double angle)
	{

		double endX;
		double endY;

		//finds the length of the cathesuses
		double base = (Math.cos(angle)*d);
		double height = (Math.sin(angle)*d);

		if( angle>0)
		{
			endX =  (a.getX() - base);
			endY =  (a.getY() + height);
		}
		else
		{
			endX =  (a.getX() - base);
			endY = (a.getY() + height);
		}

		Point2D endCoord = new Point2D.Double();
		endCoord.setLocation(endX,endY);
		return endCoord;
	}

	/**
	 * Calculates coordinates d
	 * @param a coordinates of point a
	 * @param b coordinates of point b
	 * @param c coordinates of point c
	 * @return coordinates of point d
	 * @author Mashka 
	 */
	public Point2D pGram(Point2D a, Point2D b, Point2D c){
		double alpha = angleToX(a,b);
		return lineAtAngle(c,lineLength(a,b), Math.PI+alpha);
	}

	/**
	 * Finds an angle between X axis and a line
	 * @param a Point start point of the line
	 * @param b Point end point of the line
	 * @return the angle between X axis and a line
	 * @author Mashka 
	 */
	public double angleToX(Point2D a, Point2D b)
	{
		double dX = b.getX() - a.getX();
		double dY = b.getY() - a.getY();
		double tan = dY/dX;
		double angle = Math.atan(tan);

		if(dY<0&&dX<0)
			return -angle;
		if(dX<0)
			return -angle;
		if(dX>0&&dY>0)
			return Math.PI - angle;
		return  Math.PI -angle;
	}




	/**
	 * Replicate an angle given a point, line and angle
	 * @param point
	 * @param alpha
	 * @param line_AB
	 * @return line_AC
	 * @author Nguyen Nguyen
	 **/
	private static Point angle_replication ( Point point_B, double alpha, Line2D line_ab )
	{

		// Calculate length of AB 
		double dx = line_ab.getX1() - line_ab.getX2();
		double dy = line_ab.getY1() - line_ab.getY2();
		double length = Math.sqrt(dx*dx + dy*dy);

		// Compute the angle AB makes with the horizontal axis
		double angle_AB = Math.atan(dx/dy);


		// Compute the difference between alpha and the angle AB makes with the horizontal axis (or basically, the angle BC makes with the horizontal axis)
		double angle_BC = alpha - angle_AB;

		// Calculate coordinates of point C
		double xC = point_B.getX() + length*Math.cos(angle_BC);
		double yC = point_B.getY() - length*Math.sin(angle_BC);

		// Construct point C
		Point point_C = new Point((int) xC,(int) yC);

		// Return point C
		return point_C;

	}
	/**
	 * Calculate distance between two points
	 * @param point_1
	 * @param point_2
	 * @return distance
	 * @author Nguyen Nguyen
	 */
	private static double calculate_distance(Point2D point_1, Point2D point_2) {
		return point_1.distance(point_2);
	}

	/**
	 * Return an arraylist of point2D of corresponding to the reflection about the 
	 * rotation point of the list of Point2D inputs
	 * @param list_vertices
	 * @param rotate_point
	 * @return
	 * @author Nguyen Nguyen
	 */
	private static ArrayList<Point2D> rotatePoint( ArrayList<Point2D> list_vertices, Point2D rotate_point){
		ArrayList<Point2D> new_list_vertices = new ArrayList(list_vertices.size());
		for (int i = 0; i < list_vertices.size(); i++) {
			new_list_vertices.add(i, angle_replication(rotate_point, Math.PI, list_vertices.get(i)));
			System.out.println(new_list_vertices.get(i).getX() + "  " + new_list_vertices.get(i).getY());
		}

		return new_list_vertices;
	}

	/**
	 * Second version of angle_replication
	 * Does the same thing, just take in different parameters
	 * @param point_B
	 * @param alpha
	 * @param point_A
	 * @return
	 * @author Nguyen Nguyen
	 */
	private static Point2D angle_replication ( Point2D point_B, double alpha, Point2D point_A ){

		// Calculate length of AB 
		double dx = point_A.getX() - point_B.getX();
		double dy = point_A.getY() - point_B.getY();
		double length = calculate_distance(point_B, point_A);
		System.out.println(dx + "  " + dy);
		// Compute the angle AB makes with the horizontal axis
		double angle_AB = Math.atan(dx/dy);

		// Compute the difference between alpha and the angle AB makes with the horizontal axis (or basically, the angle BC makes with the horizontal axis)
		double angle_BC = alpha - angle_AB;
		System.out.println(angle_BC);
		// Calculate coordinates of point C
		double xC = point_B.getX() + length*Math.sin(angle_BC);
		double yC = point_B.getY() - length*Math.cos(angle_BC);

		// Construct point C
		Point2D point_C = new Point((int) xC,(int) yC);

		// Return point C
		return point_C;

	}
}
