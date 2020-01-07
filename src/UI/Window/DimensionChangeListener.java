package UI.Window;

import UI.RelativeNode;
import Utility.Debugger;
import javafx.beans.property.ReadOnlyProperty;

/**
 * TODO Documentation
 *
 * @author Spud.
 */
public class DimensionChangeListener implements javafx.beans.value.ChangeListener<Number> {

	/**
	 * TODO Documentation
	 */
	private RelativeNode[] relativeNodes;

	/**
	 * TODO Documentation
	 *
	 * @param relativeNodes
	 */
	public DimensionChangeListener(RelativeNode[] relativeNodes) {
		this.relativeNodes = relativeNodes;
	}

	/**
	 * TODO Documentation
	 *
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	@Override
	public void changed(javafx.beans.value.ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

		// TODO Comments
		double o = oldValue.doubleValue(), n = newValue.doubleValue();

		Debugger.d(this.getClass(), "Old value: " + o);
		Debugger.d(this.getClass(), "New value: " + n);

		// Get the ReadOnlyProperty that is the ObservableValue
		ReadOnlyProperty property = (ReadOnlyProperty) observable;

		switch (property.getName().toLowerCase()) {
			case "height":
				Debugger.d(this.getClass(), "Adjusting height");
				for (RelativeNode node : this.relativeNodes) {
					node.updateHeight(o, n);
				}
				break;
			case "width":
				Debugger.d(this.getClass(), "Adjusting width");
				for (RelativeNode node : this.relativeNodes) {
					node.updateWidth(o, n);
				}
				break;
			default:
				// Error!
				try {
					throw new DimensionChangeException();
				} catch (DimensionChangeException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	/**
	 * TODO Documentation
	 *
	 * @author Spud.
	 */
	private static class DimensionChangeException extends Exception {

		/**
		 * TODO Documentation
		 */
		DimensionChangeException() {
			super("Unable to determine use of readonly property!");
		}
	}
}
