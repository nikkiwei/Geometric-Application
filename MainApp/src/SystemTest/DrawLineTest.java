package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern; 
import org.sikuli.script.Region;

/**
 * Basic test to draw a line. Currently only set up for a
 * MacBook Air, because the final comparison depends on dimensions
 * based on a screenshot.
 * 
 * @author Sage Mahannah
 * @version 3/20/2018
 *
 */
public class DrawLineTest {
	
	static Screen s = new Screen();
	static String OS;

	public static void main(String[] args) {
		getOS();
		openApplication();
		drawLine();
		compareLines();
	}
	
	/**
	 * Determines the user's system operating system
	 * Useful for a shorthand when finding file structures
	 * Also useful because things look different on macs than on pcs
	 */
	public static void getOS() {
		if(System.getProperty("os.name").contains("Windows")) {
			OS = "SystemTestImages/windows/";
		}else {
			OS = "SystemTestImages/mac/";
		}
	}
	
	public static void openApplication() {
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
			s.click(expandButton.similar((float) 0.9));
			//waiting for it to expand
			s.wait(OS +"startWindowExpanded.PNG", 5);
		} catch(FindFailed f) {
			f.printStackTrace();
		}
	}
	
	public static void proceedThroughError() {
		try {
			if (s.exists(OS + "proceedButton.PNG", 5) != null) {
				s.click(OS + "proceedButton.PNG");
			}
		} catch(FindFailed f) {
			f.printStackTrace();
		} 
	}
	
	public static void drawLine() {
		try {
			Region northWest = s.get(Region.NORTH_WEST);
			Region southEast = s.get(Region.SOUTH_EAST);
			s.hover(northWest);
			s.drag(northWest);
			s.hover(southEast);
			s.dropAt(southEast);
		} catch(FindFailed f) {
			f.printStackTrace();
		} 
	}
	
	public static void compareLines() {
		Pattern diagonalLine = new Pattern (OS+"diagonalLine.PNG");
		if (s.exists(diagonalLine.similar((float) 0.5)) != null) {
			System.out.print("It worked! There are no problems.");
		}
		else {
			System.out.print("Oh no! Something went wrong...");
		}
	}
	
	
}
