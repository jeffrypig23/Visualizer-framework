package UI;

/**
 * An interface for all the nodes that need to have their respective positions relative based on the windows size.
 * <p>
 * https://stackoverflow.com/questions/6270132/create-a-custom-event-in-java
 *
 * @author Spud
 */
public interface RelativeNode { // TODO Look into CSS for upadting position and scale

	/**
	 * Sets the relative position of the node in the window based off of the provided width and height.
	 *
	 * @param width  The current window width.
	 * @param height The current window height.
	 */
	public void updatePosition(double width, double height);

}
