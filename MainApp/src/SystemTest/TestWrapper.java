package SystemTest;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 * Proof of concept that the Sikuli code can be in the same project as the Geometry App
 * @author Ivy O'Neal-Odom
 *
 */
public class TestWrapper {
	static Screen s = new Screen();
	static String OS;
	
	public static void main(String[] args) {
		getOS();
		selectApp();
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
			OS = "SystemTestImages/mac";
		}
	}
	
	/**
	 * Selects the icon of the Geometry App from the taskbar
	 */
	public static void selectApp() {
		try {
			s.click(OS + "appToolbar.PNG");
		}catch(FindFailed f) {
			f.printStackTrace();
		}
	}
}
