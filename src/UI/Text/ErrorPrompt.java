package UI.Text;

import name_me.Debugger;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;

/**
 * Creates a prompt to house error text. This is shown in red, and when clicked will copy the error the the clipboard.
 * <p>
 * It also plays a fade animation that lasts 1 minute, so the prompt will dissipate given time.
 *
 * @author Spud.
 */
public class ErrorPrompt extends javafx.scene.text.Text {

	/**
	 * Create the fade animation for the error prompt. This should take 1 minute to complete.
	 */
	private final FadeTransition animation = new FadeTransition(javafx.util.Duration.minutes(1d), this);

	/**
	 * Constructor for the error prompt. This also sets what happens for when the animation for the prompt has completed.
	 */
	public ErrorPrompt() {

		// Setup the label
		this.setFont(new javafx.scene.text.Font("Andale Mono", 15));
		this.setFill(javafx.scene.paint.Color.RED);
		this.setX(5);
		this.setY(15);
		this.setOpacity(0d);

		// Change the cursor back to normal when finished
		this.animation.setOnFinished(event -> {
			this.setCursor(Cursor.DEFAULT);
			this.setOnMouseClicked(null);
		});

	}

	/**
	 * Plays the fade animation of the text. This takes a full minute to finish, and while playing the user can click
	 * on the error to copy it to their clipboard.
	 */
	private void playAnimation() {
		this.animation.setFromValue(1);
		this.animation.setToValue(0);
		this.animation.playFromStart();

		// Set the cursor to the hand cursor when the mouse hovers over it
		this.setCursor(Cursor.HAND);

		// Copy the top error message to the users clipboard when they click on the text
		this.setOnMouseClicked(event -> {
			java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(this.getText().split("\n")[0]), null);
			Debugger.d(ErrorPrompt.class, "Copied error to clipboard!");
			this.setText("Copied error to clipboard!\n" + this.getText());
		});
	}

	/**
	 * Logs the error the the main window. After displayed, the fade animation will play.
	 *
	 * @param stacktrace The stacktrace of the error (in string form)
	 */
	public void logError(String stacktrace) {
		Debugger.d(this.getClass(), "Logging error: \n" + stacktrace);
		this.setText(stacktrace + "\n");
		this.playAnimation();
	}

}
