package UI;

/**
 * An interface for all the nodes that need to have their respective positions relative based on the windows size.
 * <p>
 * https://stackoverflow.com/questions/6270132/create-a-custom-event-in-java
 *
 * @author Spud
 */
public interface RelativeNode { // TODO Look into CSS for updating position and scale

	/**
	 * TODO Documentation
	 *
	 * @param oldWidth
	 * @param newWidth
	 */
	public void updateWidth(double oldWidth, double newWidth);

	/**
	 * TODO Documentation
	 *
	 * @param oldHeight
	 * @param newHeight
	 */
	public void updateHeight(double oldHeight, double newHeight);

}
