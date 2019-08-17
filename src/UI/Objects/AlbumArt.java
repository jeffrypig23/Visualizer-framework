package UI.Objects;

import javafx.scene.paint.Color;
import name_me.Debugger;
import name_me.GenreColors;

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
		name_me.Debugger.d(this.getClass(), "Changing album art color to " + color.toString());
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
	public void updatePosition(double width, double height) {
		// At a normal 720p display size, the width and height of the square is 126. If that changes,
		// then update it by the percentage change. The issue is that the window is a rectangle,
		// while this object is a square.

		// Initial x position for a 1080 display is 118, and y is 396
		double differenceWidth = 118 / (1280 / width), differenceHeight = 396 / (720 / height);
		Debugger.d(this.getClass(), "Setting new X position to: " + differenceWidth);
		Debugger.d(this.getClass(), "Setting new Y position to: " + differenceHeight);
		this.setX(differenceWidth);
		this.setY(differenceHeight);

		// For shrinking / expanding, choose the smallest percentage change value
		double percentWidthChange = width / 1280, percentHeightChange = height / 720,
				scaleValue = 126 * Math.min(percentWidthChange, percentHeightChange);
		Debugger.d(this.getClass(), "Scaling album art to: " + scaleValue);
		this.setWidth(scaleValue);
		this.setHeight(scaleValue);
	}
}
