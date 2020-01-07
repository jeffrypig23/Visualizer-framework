package Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import Utility.Debugger;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO Documentation FIXME
 */
public class AudioPlayer {

	/**
	 * TODO Documentation
	 */
	public MediaPlayer mediaPlayer;

	/**
	 * TODO Documentation
	 */
	public Queue<Media> queue = new LinkedList<>();


	/**
	 * TODO Documentation
	 */
	public MediaPlayer.Status status = MediaPlayer.Status.STOPPED;

	/**
	 * TODO Documentation
	 */
	public Track currentTrack = null;

	/**
	 * TODO Documentation
	 */
	public void changeVolume(double volume) {
		// TODO
	}

	/**
	 * TODO Documentation
	 */
	public void loadTrack() {

		// Create a file picker dialog for loading the file into the queue
		FileChooser pickFile = new FileChooser();

		// Create an extension regex for only audio files
		String[] supportedExtensions = new String[]{"*.mp3", "*.m4a", "*.mp4", "*.m4v", "*.wav", "*.aif", ".aiff", "*.flv", "*.m3u8"};
		FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Music", supportedExtensions);
		pickFile.getExtensionFilters().addAll(fileFilter);

		// Get the file chosen
		File file = pickFile.showOpenDialog(null);
		Debugger.d(this.getClass(), "File path: " + file.getPath());

		// Create a new media object given the file path
		Media media = new Media(file.toPath().toUri().toString());
		media.setOnError(() -> Debugger.d(this.getClass(), "Error with media: " + media.getError().toString()));
		Debugger.d(this.getClass(), "Queueing from URI: " + media.getSource());

		// Add the media to the queue
		this.queue.add(media);

		// Play the track
		this.play();
	}

	/**
	 * TODO Documentation
	 */
	public void play() {

		// Make sure the media player isn't null before the initial checks
		if (this.mediaPlayer != null) {

			// Check if the player is stopped or paused, in order to play
			switch (this.status) {
				case PAUSED:
					// Continue to play
					Debugger.d(this.getClass(), "Resuming playback media");
					this.mediaPlayer.play();
					break;
				case PLAYING:
					// Do nothing, just have the next track remain in the queue until playback is completed/skipped
					Debugger.d(this.getClass(), "Media is still playing...");
					return;
				default:
					// Dispose of the old player
					Debugger.d(this.getClass(), "Disposing of old media player");
					this.mediaPlayer.dispose();
					break;
			}
		}

		// Create a new player with the media in the queue (might be null)
		Media media = this.queue.poll();

		if (media != null) {
			Debugger.d(this.getClass(), "Loading media at URI: " + media.getSource());
			this.mediaPlayer = new MediaPlayer(media);
		} else {
			// Since there aren't anymore tracks, return early
			Debugger.d(this.getClass(), "No more tracks in queue");
			return;
		}

		// Just go through the play method again once done
		this.mediaPlayer.setOnEndOfMedia(() -> {
			Debugger.d(this.getClass(), "End of media!");
			this.status = MediaPlayer.Status.STOPPED;
			this.play();
		});

		// If there are any errors, report it
		this.mediaPlayer.setOnError(() -> Debugger.d(this.getClass(), "Error with media player: " + this.mediaPlayer.getError().toString()));

		// Play the track
		this.mediaPlayer.play();

		// Update the private status to PLAYING
		this.status = MediaPlayer.Status.PLAYING;
	}
}
