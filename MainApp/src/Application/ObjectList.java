/**
 * Store all the methods that a linked list can implement
 * 
 * @author DB Team
 * @version 3/2/2018
 */

public class ObjectList extends LinkedList<GeoObject> {

	// the list to store all the objects
	private LinkedList<GeoObject> objectList;
	
	/**
	 * Constructor for the data base class
	 */
	public ObjectList() {
		
		// initiate the object list
		objectList = new LinkedList<GeoObject>();
	}
	
	/**
	 * Insert a new object into the object list
	 * @param object the object to be inserted
	 */
	public void insert (GeoObject object) {
		
		// as long as the object is not null
		if (object != null) {

			// assign a key to the object and add it to the list, key = index
			object.setKey(objectList.size());
			objectList.add(objectList.size(), object);
		}
	}
	
	/*
	 * Remove an object from the object list
	 * @param object the object to be deleted
	 */
	public void remove (GeoObject object) {

		// as long as the object is not null
		if (object != null) {

			// find the object in the list and delete it
			int i = object.getKey();
			objectList.delete(i);
		}
	}
	
	/*
	 * Search for any object within a certain range from the selected point
	 * @param x the x coordinate of the selected point
	 * @param y the y coordinate of the selected point
	 * @return any object that is close enough to the selected point
	 */
	public GeoObject search (float x, float y) {
		
		// loop through the list
		for (int i = 0; i < objectList.size(); i ++) {
			
			// if the point is close enough to an object, return that object
			if (objectList.get(i).isClose(x, y)) {
				return objectList.get(i);
			}
		}	
		
		// no object is close enough to the selected point
		return null;
	}
	
}
