package UI.Text;

import javafx.scene.paint.Color;

/**
 * TODO Documentation
 *
 * @author Spud.
 */
public class ConsoleText extends javafx.scene.text.Text {

	/**
	 * TODO Documentation
	 *
	 * @param string
	 * @param error
	 */
	public ConsoleText(String string, boolean error) {
		this.setText(string);
		this.setFont(new javafx.scene.text.Font("Andale Mono", 12));
		if (error) {
			this.setFill(Color.RED);
		} else {
			this.setFill(Color.WHITE);
		}
	}
}
