package UI.Text;

/**
 * Create the GitHub text that resides at the bottom right corner of the screen.
 * When clicked, it will open a browser window to a provided site, or the Visualizer-framework repo if no URL is provided.
 *
 * @author Spud
 */
public class GitHubText extends javafx.scene.text.Text implements UI.RelativeNode {

	/**
	 * Generates a small bit of text that when clicked will open a browser to the Visualizer-framework git repo.
	 * <p>
	 * To make it open a specific URL, pass the URL as a string as an argument.
	 */
	public GitHubText() {
		this("https://github.com/jeffrypig23/Visualizer-framework/");
	}

	/**
	 * Generates a small bit of text that when clicked will open a browser to the provided URL.
	 *
	 * @param URLString The URL to be opened.
	 */
	public GitHubText(String URLString) {

		// Set the text and font
		this.setText("Source code");
		this.setFont(javafx.scene.text.Font.font(15));
		this.setUnderline(true);
		this.setFill(javafx.scene.paint.Color.WHITE);

		// Set it so when the cursor hovers over the text, it changes to the hand cursor
		this.setCursor(javafx.scene.Cursor.HAND);

		// Add the opening event
		this.setOnMouseClicked(event -> {
			try {
				java.awt.Desktop.getDesktop().browse(new java.net.URI(URLString));
			} catch (java.io.IOException | java.net.URISyntaxException e) {
				e.printStackTrace();
			}
		});
		this.setX(1280 - (10 + this.getLayoutBounds().getWidth()));
		this.setY(720 - (this.getLayoutBounds().getHeight()));
	}

	@Override
	public void updatePosition(double width, double height) {
		double x = width - (10 + this.getLayoutBounds().getWidth()), y = height - (20 + this.getLayoutBounds().getHeight());
		name_me.Debugger.d(this.getClass(), String.format("Updating position to: %f, %f", x, y));
		this.setX(x);
		this.setY(y);
	}

}
