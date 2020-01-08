import UI.Objects.AlbumArt;
import UI.Objects.Bar;
import UI.Objects.LoadingBar;
import UI.Text.ConsoleText;
import UI.Window.Window;
import Utility.GenreColors;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class AppTest extends Application {

	private Window window;

	private LoadingBar loadingBar = new LoadingBar();

	private AlbumArt albumArt = new AlbumArt();

	private Bar[] bars = new Bar[63];

	@Test
	void test() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {

		// Then create the window
		this.window = new Window(primaryStage);

		// Now add a listener to the stage to run the unit tests once it is shown
		this.window.stage.setOnShown((event) -> {

			// TODO Comment
			this.setOut("Running tests");

			// Run the first unit test
			PauseTransition a = this.changeBackgroundColor();

			// Once the unit test finishes, run the second...
			a.setOnFinished((ree) -> {

				// Run the second unit test
				PauseTransition b = this.changeLoadingBarColor();

				// Once the second unit test finishes, run the third...
				b.setOnFinished((reee) -> {
					// Remove the loading bar from the display
					this.window.hideElement(this.loadingBar, true);

					// Run the third unit test
					PauseTransition c = this.changeAlbumArtColor();

					c.setOnFinished((reeee) -> {
						// Remove the album art from the display
						this.window.hideElement(this.albumArt, true);

						// Initialize the bars
						for (int i = 0; i < 63; i++) {
							this.bars[i] = new Bar(i);
						}

						// You get the idea, run the 4th unit test
						PauseTransition d = this.changeBarColor();

						//d.setOnFinished((reeeee) -> Platform.exit());

						d.play();
					});

					c.play();
				});
				b.play();
			});

			a.play();

		});

		// Now show the window
		this.window.stage.show();
	}


	private PauseTransition changeBackgroundColor() {

		PauseTransition pt = new PauseTransition();

		// First create a queue to hold all the background colors to cycle through
		Queue<Color> colors = new LinkedList<>();

		// Add the colors to the queue
		colors.add(Color.WHITE);
		colors.add(Color.BLACK);

		PauseTransition transition = new PauseTransition(Duration.millis(750));
		transition.setOnFinished((event) -> {
			// Set the background to the color in the queue, unless empty, then break
			if (!colors.isEmpty()) {
				Color color = colors.poll();
				this.setOut("Setting background color to " + color.toString());
				this.window.setStageBackground(color);
				transition.play();
			}
		});
		// Play the "transition"
		transition.play();

		pt.setDuration(Duration.millis(transition.getDuration().toMillis() * colors.size()));
		return pt;
	}

	private PauseTransition changeLoadingBarColor() {

		PauseTransition pt = new PauseTransition();

		// Set the first color the the first in the array
		this.setOut("Setting loading bar color to " + GenreColors.values()[0].name());
		this.loadingBar.setColor(GenreColors.values()[0]);

		// Add the loading bar to the window
		this.window.addToWindow(this.loadingBar);

		PauseTransition transition = new PauseTransition(Duration.millis(750));
		transition.setOnFinished((event) -> {
			// Rotate through the loading bar colors
			// First, get its current index
			int index = Objects.requireNonNull(GenreColors.getGenre((Color) this.loadingBar.getFill())).ordinal();

			// If its not at the max value, continue
			if (index < GenreColors.values().length - 1) {
				this.setOut("Setting loading bar color to " + GenreColors.values()[index + 1].name());
				this.loadingBar.setColor(GenreColors.values()[index + 1]);
				transition.play();
			}
		});
		// Play the "transition"
		transition.play();

		pt.setDuration(Duration.millis(transition.getDuration().toMillis() * GenreColors.values().length));

		return pt;

	}

	private PauseTransition changeAlbumArtColor() {
		PauseTransition pt = new PauseTransition();

		// Set the first color the the first in the array
		this.setOut("Setting Album Art color to " + GenreColors.values()[0].name());
		this.albumArt.setColor(GenreColors.values()[0]);

		// Add the loading bar to the window
		this.window.addToWindow(this.albumArt);

		PauseTransition transition = new PauseTransition(Duration.millis(750));
		transition.setOnFinished((event) -> {
			// Rotate through the loading bar colors
			// First, get its current index
			int index = Objects.requireNonNull(GenreColors.getGenre((Color) this.albumArt.getFill())).ordinal();

			// If its not at the max value, continue
			if (index < GenreColors.values().length - 1) {
				this.setOut("Setting Album Art color to " + GenreColors.values()[index + 1].name());
				this.albumArt.setColor(GenreColors.values()[index + 1]);
				transition.play();
			}
		});
		// Play the "transition"
		transition.play();

		pt.setDuration(Duration.millis(transition.getDuration().toMillis() * GenreColors.values().length));

		return pt;
	}

	private PauseTransition changeBarColor() {

		PauseTransition pt = new PauseTransition();

		// Set the first color to the first in the genreColors array, and add it to the display
		for (Bar bar : this.bars) {
			this.setOut("Setting bar color to " + GenreColors.values()[0].name());
			bar.setColor(GenreColors.values()[0]);
			this.window.addToWindow(bar);
		}

		PauseTransition transition = new PauseTransition(Duration.millis(750));
		transition.setOnFinished((event) -> {
			// Rotate through the loading bar colors
			// First, get its current index
			for (Bar bar : this.bars) {
				int index = Objects.requireNonNull(GenreColors.getGenre((Color) bar.getFill())).ordinal();

				// If its not at the max value, continue
				if (index < GenreColors.values().length - 1) {
					this.setOut("Setting bar color to " + GenreColors.values()[index + 1].name());
					bar.setColor(GenreColors.values()[index + 1]);
					transition.play();
				}
			}
		});
		// Play the "transition"
		transition.play();

		pt.setDuration(Duration.millis(transition.getDuration().toMillis() * GenreColors.values().length));

		return pt;
	}

	private void setOut(String message) {
		Window.console.addToConsole(new ConsoleText(message, false), false);
	}
}
