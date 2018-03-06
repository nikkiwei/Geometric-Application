package Application;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a dummy example class generated by professor Louis Conover for the GUI team to use to store the graphics till
 * the database team provides us with the actual class for database.
 * 
 * This class has been completely copied by the demo provided by the professor and it is not written or maintained by the GUI team
 * 
 * @author LOUIS CONOVER
 *
 */
class DB {
	List<Point> db;
	int size;

	DB () {
		db = new ArrayList<Point>(10);
		size = 0;
	}
	
	public void Add (Point p) {
		db.add(p);
		size++;
	}
	
	public Point Get (int i ) {
		return db.get(i);
	}
	
	public int Size () {
		return size;
	}
}
