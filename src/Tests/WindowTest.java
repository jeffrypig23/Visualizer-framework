import UI.Objects.AlbumArt;
import UI.Objects.Bar;
import UI.Objects.LoadingBar;
import UI.Objects.Monstercat;
import UI.Text.GitHubText;
import UI.Window.Window;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import Utility.Debugger;
import Utility.GenreColors;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

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

	@Test
	void loadingBar() {
		launch("loadingbar_test");
	}

	@Test
	void loadingBarAnimation() {
		launch("loadingbar_animation_test");
	}

	@Test
	void Monstercat() {
		launch("Monstercat_test");
	}

	@Test
	void GitHubText() {
		launch("githubtext_test");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// First, create the window
		final Window window = new Window(primaryStage);
		//window.updateOpacity(0.5d);

		// Add a listener for on shown to then run tests based on parameters
		window.stage.setOnShown((event) -> {

			// Now check for parameters (tests)
			for (String parameter : getParameters().getRaw()) {
				try {
					switch (parameter) {
						case "albumart_test":
							// Show the album art object, and run its various functions
							AlbumArt albumArt = new AlbumArt();
							window.addToWindow(albumArt);

							// Abuse the PauseTransition object for changing colors
							PauseTransition transition = new PauseTransition(Duration.millis(500));
							albumArt.setColor(GenreColors.values()[0]);
							transition.setOnFinished((finished -> {
								try {
									// Parse the current color to find the current index
									int index = Objects.requireNonNull(GenreColors.getGenre(albumArt.getColor())).ordinal();
									if (index < GenreColors.values().length - 1) {
										albumArt.setColor(GenreColors.values()[index + 1]);

										// Check for errors, then play
										transition.play();

									} else {
										System.exit(0);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}));
							transition.play();
							break;
						case "bar_test":  // Show the bar object
							window.addToWindow(new Bar());
							//window.addToWindow(new AlbumArt());
							break;
						case "bars_test":  // Show multiple bars
							window.addToWindow(new AlbumArt());
							ArrayList<Bar> bars = new ArrayList<Bar>();
							for (int i = 0; i < 63; i++) {
								Bar bar = new Bar(i);
								window.addToWindow(bar);
								bars.add(bar);
							}
							bars.get(12).setBarHeight(1);
							break;
						case "loadingbar_test":
							window.addToWindow(new AlbumArt());
							window.addToWindow(new LoadingBar());
							break;
						case "loadingbar_animation_test":
							LoadingBar loadingBar = new LoadingBar();
							window.addToWindow(loadingBar);
							loadingBar.playAnimation();
							break;
						case "Monstercat_test":
							window.addToWindow(new AlbumArt());
							window.addToWindow(new Monstercat());
							break;
						case "githubtext_test":
							window.addToWindow(new GitHubText());
							break;
						default:
							System.out.println("Unrecognized parameter: " + parameter);
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		window.stage.show();
	}
}
