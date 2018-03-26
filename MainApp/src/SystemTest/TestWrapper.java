package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 * Runs each test class
 * The test classes must be added to the "test()" function
 * @author Ivy O'Neal-Odom
 *
 */
public class TestWrapper {
	static Screen s = new Screen();
	static String OS;
	
	/**
	 * Calls the various tests
	 * @param args N/A
	 */
	public static void main(String[] args) {
		getOS(); //First we need to get the OS
		
		//Test Start
		DrawLineTest.main(args);
		try {
			s.click(OS + "close.png");
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Test End
		
		//Test Start
		DrawPointTest.main(args);
		try {
			s.click(OS + "close.png");
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Test End
	}
	
	/**
	 * Determines the user's system operating system
	 * Useful for a shorthand when finding file structures
	 * Also useful because things look different on macs than on pcs
	 */
	private static void getOS() {
		if(System.getProperty("os.name").contains("Windows")) {
			OS = "SystemTestImages/windows/";
		}else {
			OS = "SystemTestImages/mac/";
		}
	}
}
