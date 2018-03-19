package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class DrawLineTest {
	
	static Screen s = new Screen();
	static String OS;

	public static void main(String[] args) {
		getOS();
		openApplication();

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
			s.doubleClick(OS + "drawWindowClass.PNG");
			s.click(OS + "compileButton.PNG");
			s.wait(OS + "proceedButton.PNG");
			s.click(OS + "proceedButton.PNG");
		} catch(FindFailed f) {
			f.printStackTrace();
		}
	}
}
