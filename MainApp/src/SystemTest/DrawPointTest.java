package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern; 
//import org.sikuli.script.Region;
import org.sikuli.script.Location;

/**
 * Basic test to draw a point in DrawWindow class. Based on Sage's DrawLineTest.
 * 
 * @author Eleanor Harris
 * @version 3/24/2018
 *
 */

public class DrawPointTest {
	
	/** The computer's screen **/
	static Screen s = new Screen();
	/** The computer's operating system **/
	static String OS;
	
	/**
	 * Opens the app, draws a point, and compares to image.
	 * @param args: n/a
	 */
	public static void main(String[] args) {
		
		DrawPointTest pointTest = new DrawPointTest();
		pointTest.getOS();
		pointTest.openApplication();
		pointTest.drawPoint();
		pointTest.comparePoints();
		
	}
	
	/**
	 * Determines the user's system operating system
	 * Useful for a shorthand when finding file structures
	 * Also useful because things look different on macs than on pcs
	 * @author Ivy O'Neal-Odom.
	 */
	private void getOS() {
		if(System.getProperty("os.name").contains("Windows")) {
			OS = "SystemTestImages/windows/";
		}else {
			OS = "SystemTestImages/mac/";
		}
	}
	
	/**
	 * Helper method to open the application. Expands the window to fill the screen.
	 * Currently, customized to work for a Macbook Air. Unsure of use
	 * on other computers.
	 * @author Sage Mahannah.
	 */
	private void openApplication() {
		try {
			//Opening the DrawWindow class
			s.doubleClick(OS + "drawWindowClass.PNG");
			s.click(OS + "compileButton.PNG");
			proceedThroughError();
			//waiting for window to open
			s.wait(OS + "startWindow.PNG", 10);
			//Expanding the window
			s.hover(OS + "expandButton.PNG");
			if(OS.contains("mac")) {
				Pattern expandButton = new Pattern (OS+"expandButtonHover.PNG");
				s.wait(expandButton.similar((float).95), 3);
				s.click(expandButton.similar((float) 0.9));
			}else {
				s.click(OS + "expandButton.PNG");
			}
			//waiting for it to expand
			s.wait(OS +"startWindowExpanded.PNG", 5);
		} catch(FindFailed f) {
			System.out.print("Something wrong happened with openning the application");
			f.printStackTrace();
		}
	}
	
	/**
	 * Helper method to get through any error messages
	 * Eclipse might have. 
	 * @author Sage Mahannah.
	 */
	private void proceedThroughError() {
		try {
			if (s.exists(OS + "proceedButton.PNG", 5) != null) {
				s.click(OS + "proceedButton.PNG");
			}
		} catch(FindFailed f) {
			System.out.print("Something went wrong in checking if an error message showed up.");
			f.printStackTrace();
		} 
	}
	
	/**
	 * Draws a point at 100,200 and then moves the mouse a little.
	 * @author Eleanor Harris.
	 */
	private void drawPoint() {
		try {
			s.click(new Location(100,200));
			s.hover(new Location(200, 400));
		}
		catch(FindFailed f) {
			System.out.println("Something went wrong drawing the point.");
			f.printStackTrace();
		}
	}
	
	/**
	 * Checks whether the drawn point matches the example point.PNG image.
	 * @author Eleanor Harris
	 */
	private void comparePoints() {
		Pattern point = new Pattern (OS + "point.PNG");
		if(s.exists(point) != null) {
			System.out.println("It worked!");
		}
		else
			System.out.println("The test failed.");
	}

}
