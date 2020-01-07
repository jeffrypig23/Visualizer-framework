package Audio;

import javafx.scene.image.Image;
import Utility.GenreColors;

import java.util.ArrayList;

/**
 * TODO Documentation
 */
public class Track {

	/**
	 * TODO Documentation
	 */
	public String fileLocation, Title, Artist;

	/**
	 * TODO Documentation
	 */
	public Image art;

	/**
	 * TODO Documentation
	 */
	public GenreColors Genre;

	/**
	 * TODO Documentation
	 *
	 * @param fileLocation
	 * @param Genre
	 */
	public Track(String fileLocation, GenreColors Genre) {

		this.fileLocation = fileLocation;

		//Get the Track's metadata based off the file location
		Metadata metadata = new Metadata(fileLocation);

		// Load the global variables for this class.
		this.Title = metadata.title;
		this.Artist = metadata.artist;
		this.art = metadata.art;
		this.Genre = Genre;

	}

	/**
	 * TODO Documentation
	 *
	 * @return
	 */
	public static Track[] tracksInCommon(Track[] locations, Track[] titles, Track[] artists, Track[] genres) {
		ArrayList<Track> tracksInCommon = new ArrayList<>();

		// Systematically sort though all the tracks in the variables, and add them to the in common list if they are the same
		for (Track track0 : locations) {
			for (Track track1 : titles) {
				for (Track track2 : artists) {
					for (Track track3 : genres) {
						if (track0.equals(track1) && track0.equals(track2) && track0.equals(track3)) {
							tracksInCommon.add(track0);
						}
					}
				}
			}
		}

		return tracksInCommon.toArray(new Track[tracksInCommon.size()]);
	}

}