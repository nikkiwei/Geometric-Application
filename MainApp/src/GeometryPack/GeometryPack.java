package GeometryPack;

import java.awt.Graphics;
import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GeometryPack {

		



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
