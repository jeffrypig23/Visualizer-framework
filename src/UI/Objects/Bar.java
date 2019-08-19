package UI.Objects;

import name_me.Debugger;
import name_me.GenreColors;
import javafx.scene.paint.Color;

/**
 * Creates a bar (or bars of an index is provided) that can grow in height by a certain percentage.
 *
 * @author Spud
 */
public class Bar extends javafx.scene.shape.Rectangle implements ColoredNode {

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
		this.setHeight(3);
		this.setY(360);
		this.setX(118 + (16.625 * index));
		this.setColor(GenreColors.ELECTRONIC);
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

}
