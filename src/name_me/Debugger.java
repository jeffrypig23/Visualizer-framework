package name_me;

import UI.Window;

import java.io.StringWriter;

public class Debugger {

	/**
	 * TODO Documentation
	 */
	public static boolean DEBUG = false;

	/**
	 * TODO Documentation
	 *
	 * @param c
	 * @param message
	 */
	public static void d(Class<?> c, String message) {
		if (DEBUG) {
			System.out.println(String.format("%s: %s", c.getName(), message));
		}
	}

	/**
	 * TODO Documentation
	 *
	 * @param e
	 */
	public static void e(Throwable e) {
		try {
			if (Window.errorPrompt != null) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new java.io.PrintWriter(sw));
				Window.errorPrompt.logError(sw.toString());
			}
		} catch (Exception cannotDisplay) {
			cannotDisplay.printStackTrace();
		}
	}
}
