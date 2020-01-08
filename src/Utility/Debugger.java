package Utility;

import UI.Text.ConsoleText;
import UI.Window.Window;

import java.io.StringWriter;

/**
 * A class that prints debug messages to the console, and logs error to the main window if displayed.
 *
 * @author Spud
 */
public class Debugger {

	/**
	 * Set this boolean to true in order for the debugger to print messages to the console.
	 */
	public static boolean DEBUG = false;

	/**
	 * Logs a debug message to the console with the format: <code>Example.class: Message</code>.
	 *
	 * @param c       The corresponding class.
	 * @param message The message to be logged.
	 */
	public static void d(Class<?> c, String message) {
		if (Debugger.DEBUG) {
			String string = String.format("%s: %s", c.getName(), message);
			System.out.println(string);
			if (Window.console != null) {
				Window.console.addToConsole(new ConsoleText(string, false), false);
			} else {
				System.out.println("Console window not setup!");
			}
		}
	}

	/**
	 * Logs a throwable to the window (if its displayed). If there is an exception that causes the
	 * Throwable to not be displayed, it will print a stacktrace instead.
	 *
	 * @param e The throwable to display.
	 */
	public static void e(Throwable e) {
		try {
			if (Window.console != null) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new java.io.PrintWriter(sw));
				String string = sw.toString();
				Window.console.addToConsole(new ConsoleText(string, true), true);
				System.out.println(string);
			}
		} catch (Exception cannotDisplay) {
			cannotDisplay.printStackTrace();
		}
	}
}
