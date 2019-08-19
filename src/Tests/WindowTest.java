import UI.Objects.AlbumArt;
import UI.Objects.Bar;
import UI.Window;
import javafx.application.Application;
import javafx.stage.Stage;
import name_me.Debugger;
import org.junit.jupiter.api.Test;

public class WindowTest extends Application {

	@Test
	void displayWindow() {
		launch();
	}

	@Test
	void AlbumArt() {
		launch("albumart_test");
	}

	@Test
	void Bar() {
		launch("bar_test");
	}

	@Test
	void MultiBar() {
		launch("bars_test");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// First, create the window
		UI.Window window = new Window(primaryStage);
		window.stage.show();
		//window.updateOpacity(0.5d);

		Debugger.DEBUG = false;

		// Now check for arguments (tests)
		for (String parameter : getParameters().getRaw()) {
			if (parameter.equals("albumart_test")) { // Show the album art object
				window.addToWindow(new AlbumArt());
			} else if (parameter.equals("bar_test")) { // Show the bar object
				window.addToWindow(new Bar());
				window.addToWindow(new AlbumArt());
			} else if (parameter.equals("bars_test")) { // Show multiple bars
				window.addToWindow(new AlbumArt());
				for (int i = 0; i < 63; i++) {
					window.addToWindow(new Bar(i));
				}
			} else {
				System.out.println("Unrecognized parameter: " + parameter);
			}
		}
	}
}