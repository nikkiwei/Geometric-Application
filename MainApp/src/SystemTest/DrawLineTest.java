package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern; 
import org.sikuli.script.Region;

/**
 * Basic test to draw a line. Currently only set up for a
 * MacBook Air, because the final comparison depends on dimensions
 * based on a screenshot. Needs the Application package to be
 * opening down.
 * 
 * @author Sage Mahannah
 * @version 3/20/2018
 *
 */
public class DrawLineTest {
	
	/** The computer's screen **/
	static Screen s = new Screen();
	/** The computer's operating system **/
	static String OS;

	
	/**
	 * Opens the application, draws a line, and compares it to
	 * a test image.
	 * @param args the argument to be passed in
	 */
	public static void main(String[] args) {
		DrawLineTest lineTest = new DrawLineTest();
		lineTest.getOS();
		lineTest.openApplication();
		lineTest.drawLine();
		lineTest.compareLines();
	}
	
	/**
	 * Determines the user's system operating system
	 * Useful for a shorthand when finding file structures
	 * Also useful because things look different on macs than on pcs
	 * This method was created by Ivy O'Neal-Odom.
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
	 * Created by Sage Mahannah.
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
			Pattern expandButton = new Pattern (OS+"expandButtonHover.PNG");
			s.wait(expandButton.similar((float).95), 3);
			s.click(expandButton.similar((float) 0.9));
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
	 * Created by Sage Mahannah.
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
	 * Helper method to draw a line.
	 * Draws a line from the center of the northwestern corner
	 * to the center of the southeastern corner.
	 * This should be changed to specific cordinates, as these
	 * corners will vary on each device.
	 * Created by Sage Mahannah.
	 */
	private void drawLine() {
		try {
			Region northWest = s.get(Region.NORTH_WEST);
			Region southEast = s.get(Region.SOUTH_EAST);
			s.dragDrop(northWest, southEast); 
		} catch(FindFailed f) {
			System.out.print("Something went wrong in drawing a line.");
			f.printStackTrace();
		} 
	}
	
	/**
	 * Helper method to compare what is currently on the screen to
	 * a test image of a diagonal line. Prints out if there was an
	 * error. Currently an exact test.
	 * Created by Sage Mahannah
	 */
	private void compareLines() {
		Pattern diagonalLine = new Pattern (OS+"diagonalLine.PNG");
		if (s.exists(diagonalLine.exact()) != null) {
			System.out.print("It worked! There are no problems.");
		}
		else {
			System.out.print("Oh no! Something went wrong...");
		}
	}
}
