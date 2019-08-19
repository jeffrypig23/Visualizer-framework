package UI.Objects;

import UI.RelativeNode;
import javafx.scene.paint.Color;
import name_me.Debugger;
import name_me.GenreColors;

/**
 * Creates a bar (or bars of an index is provided) that can grow in height by a certain percentage.
 *
 * @author Spud
 */
public class Bar extends javafx.scene.shape.Rectangle implements ColoredNode, RelativeNode {

	private double minHeight = 3, maxHeight = 60, percentHeight, indexMod; // TODO Find correct height

	/**
	 * Creates a single bar.
	 */
	public Bar() {
		this(0);
	}

	/**
	 * Creates a bar at the given index. The index is used for spacing that way bars do not overlap with each other.
	 *
	 * @param index The index of the bar.
	 */
	public Bar(int index) {
		this.setStrokeType(javafx.scene.shape.StrokeType.CENTERED);
		this.setWidth(12);
		this.setHeight(this.minHeight);
		this.setY(360);
		this.indexMod = 16.625 * index;
		this.setX(118 + indexMod);
		this.setColor(GenreColors.ELECTRONIC);
		this.percentHeight = 0;
	}

	@Override
	public void setColor(Color color) {
		Debugger.d(this.getClass(), "Changing bar color to " + color.toString());
		this.setFill(color);
	}

	@Override
	public void setColor(GenreColors color) {
		this.setColor(color.getColor());
	}

	/**
	 * Set the height of the bar based on the percentage. 0 percent would be at the minimum height,
	 * where as 100 percent would be at the max height.
	 *
	 * @param percentage The height percentage (from 0 to 1.0).
	 */
	public void setBarHeight(double percentage) {
		double newHeight = this.minHeight + (this.maxHeight * percentage);
		Debugger.d(this.getClass(), "Setting new bar height to: " + newHeight);
		this.setHeight(newHeight);
		this.setY(this.getY() - newHeight + this.minHeight);
		this.percentHeight = percentage;
	}

	@Override
	public void updatePosition(double width, double height) {
		// Get the percentage change
		double percentWidthChange = width / 1280, percentHeightChange = height / 720;
		Debugger.d(this.getClass(), "Percentage width change: " + percentWidthChange);
		Debugger.d(this.getClass(), "Percentage height change: " + percentHeightChange);

		// Adjust the width and indexMod based on the width
		double newWidth = 12 * percentWidthChange, newIndexMod = this.indexMod * percentWidthChange; // FIXME
		Debugger.d(this.getClass(), "Setting new indexMod to: " + newIndexMod);
		Debugger.d(this.getClass(), "Setting new width to: " + newWidth);
		this.setWidth(newWidth);

		// Adjust the min and max heights based on the height
		this.minHeight = 3 * percentHeightChange;
		this.maxHeight = 60 * percentHeightChange; // TODO Find correct height

		// Initial x position for a 1080 display is 118 (plus the index), and y is 360
		double differenceWidth = (118 + newIndexMod) / (1280 / width), differenceHeight = 360 / (720 / height);
		Debugger.d(this.getClass(), "Setting new X position to: " + differenceWidth);
		Debugger.d(this.getClass(), "Setting new Y position to: " + differenceHeight);
		this.setX(differenceWidth);
		this.setY(differenceHeight);

		// Refresh the height
		this.setBarHeight(this.percentHeight);
	}

}
