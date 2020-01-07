package UI.Objects;

import javafx.scene.paint.Color;
import Utility.Debugger;
import Utility.GenreColors;

/**
 * Corresponds to the colored square that is behind the cat.
 *
 * @author Spud
 */
public class AlbumArt extends javafx.scene.shape.Rectangle implements ColoredNode, UI.RelativeNode {

	/**
	 * Creates a grey square with the initial position of 118, 396, and has a size of 126
	 */
	public AlbumArt() {
		this.setX(118);
		this.setY(396);
		this.setWidth(126);
		this.setHeight(126);
		this.setColor(GenreColors.ELECTRONIC);
	}

	@Override
	public void setColor(Color color) {
		Utility.Debugger.d(this.getClass(), "Changing album art color to " + color.toString());
		this.setFill(color);
	}

	@Override
	public void setColor(GenreColors color) {
		this.setColor(color.getColor());
	}

	/**
	 * Gets the color of the album art.
	 *
	 * @return The color (as a Color object) of the album art.
	 */
	public Color getColor() {
		return (Color) this.getFill();
	}

	@Override
	public void updateWidth(double oldWidth, double newWidth) { // FIXME
		// At a normal 720p display size, the width of the square is 126. If that changes,
		// then update the width by the percentage change. The issue is that the window is a rectangle,
		// while this object is a square.

		// Initial x position for a 1080 display is 118.
		double differenceWidth = 118 / (1280 / newWidth);
		Debugger.d(this.getClass(), "Setting new X position to: " + differenceWidth);
		this.setX(differenceWidth);

		// For shrinking / expanding, TODO
		double percentWidthChange = newWidth / 1280, scaleValue = 126 * percentWidthChange;
		Debugger.d(this.getClass(), "Scaling album art to: " + scaleValue);
		this.setWidth(scaleValue);
		this.setHeight(scaleValue);
	}

	@Override
	public void updateHeight(double oldHeight, double newHeight) { // FIXME
		// At a normal 720p display size, height of the square is 126. If that changes,
		// then update the height by the percentage change. The issue is that the window is a rectangle,
		// while this object is a square.

		// Initial y position for a 1080 display is 396.
		double differenceHeight = 396 / (720 / newHeight);
		Debugger.d(this.getClass(), "Setting new Y position to: " + differenceHeight);
		this.setY(differenceHeight);


		// For shrinking / expanding, TODO
		double percentHeightChange = newHeight / 1280, scaleValue = 126 * percentHeightChange;
		Debugger.d(this.getClass(), "Scaling album art to: " + scaleValue);
		this.setWidth(scaleValue);
		this.setHeight(scaleValue);
	}
}
