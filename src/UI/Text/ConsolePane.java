package UI.Text;

import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 * TODO Documentation
 *
 * @author Spud.
 */
public class ConsolePane extends javafx.scene.control.ScrollPane implements UI.RelativeNode {

	/**
	 * TODO Documentation
	 */
	public VBox layout;

	/**
	 * TODO Documentation
	 */
	public ConsolePane() {
		this.setMinWidth(16);
		this.setMinHeight(9);
		this.setWidth(640);
		this.setHeight(270);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		Background background = new Background(new javafx.scene.layout.BackgroundFill(javafx.scene.paint.Color.BLACK,
				javafx.scene.layout.CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
		this.setBackground(background);
		this.layout = new VBox();
		this.layout.setBackground(background);
		this.setContent(this.layout);
	}

	/**
	 * TODO Documentation
	 *
	 * @param node
	 * @param clickable
	 */
	public void addToConsole(ConsoleText node, boolean clickable) {
		if (clickable) {
			node.setOnMouseEntered((a) -> node.setCursor(Cursor.HAND));
			node.setOnMouseExited((a) -> node.setCursor(Cursor.DEFAULT));
			node.setOnMouseClicked((a) -> {
				java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
						new java.awt.datatransfer.StringSelection(node.getText().split("\n")[0]), null);
				Utility.Debugger.d(ConsolePane.class, "Copied error to clipboard!");
			});
		}

		FadeTransition animation = new FadeTransition(javafx.util.Duration.seconds(30), node);
		animation.setFromValue(1);
		animation.setToValue(0);
		animation.playFromStart();
		animation.setOnFinished((a) -> this.removeFromConsole(node));

		this.layout.getChildren().add(node);
	}

	/**
	 * TODO Documentation
	 *
	 * @param node
	 */
	public void removeFromConsole(ConsoleText node) {
		node.setOnMouseClicked(null);
		node.setOnMouseEntered(null);
		node.setOnMouseExited(null);
		node.setCursor(Cursor.DEFAULT);
		node.setVisible(false);
		this.layout.getChildren().remove(node);
	}

	@Override
	public void updateWidth(double oldWidth, double newWidth) {
		// TODO
	}

	@Override
	public void updateHeight(double oldHeight, double newHeight) {
		// TODO
	}
}
