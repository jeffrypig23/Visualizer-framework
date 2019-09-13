import javafx.scene.paint.Color;
import Utility.GenreColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static javafx.scene.paint.Color.rgb;

class GenreColorsTest {

	// Defile the correct colors
	private static final javafx.scene.paint.Color trap = rgb(140, 15, 39), dnb = rgb(242, 25, 4),
			house = rgb(234, 140, 0), electro = rgb(230, 206, 0),
			harddance = rgb(1, 151, 0), glitchhop = rgb(11, 151, 87),
			nudisco = rgb(28, 171, 179), futurebass = rgb(154, 152, 252),
			trance = rgb(0, 126, 231), dubstep = rgb(141, 4, 225),
			drumstep = rgb(243, 33, 136), electronic = rgb(193, 193, 193),
			other = rgb(255, 255, 255);

	@Test
	void general() {
		// Check the number of enums
		assertEquals(GenreColors.values().length, 13);

		// Check the valueOf function
		assertEquals(GenreColors.valueOf("TRAP"), GenreColors.TRAP);
		assertEquals(GenreColors.valueOf("DNB"), GenreColors.DNB);
		assertEquals(GenreColors.valueOf("HOUSE"), GenreColors.HOUSE);
		assertEquals(GenreColors.valueOf("ELECTRO"), GenreColors.ELECTRO);
		assertEquals(GenreColors.valueOf("HARDDANCE"), GenreColors.HARDDANCE);
		assertEquals(GenreColors.valueOf("GLITCHHOP"), GenreColors.GLITCHHOP);
		assertEquals(GenreColors.valueOf("NUDISCO"), GenreColors.NUDISCO);
		assertEquals(GenreColors.valueOf("FUTUREBASS"), GenreColors.FUTUREBASS);
		assertEquals(GenreColors.valueOf("TRANCE"), GenreColors.TRANCE);
		assertEquals(GenreColors.valueOf("DUBSTEP"), GenreColors.DUBSTEP);
		assertEquals(GenreColors.valueOf("DRUMSTEP"), GenreColors.DRUMSTEP);
		assertEquals(GenreColors.valueOf("ELECTRONIC"), GenreColors.ELECTRONIC);
		assertEquals(GenreColors.valueOf("OTHER"), GenreColors.OTHER);
		Assertions.assertThrows(IllegalArgumentException.class, () -> GenreColors.valueOf("FOO"));

		// Check the names
		assertEquals("TRAP", GenreColors.TRAP.name());
		assertEquals("DNB", GenreColors.DNB.name());
		assertEquals("HOUSE", GenreColors.HOUSE.name());
		assertEquals("ELECTRO", GenreColors.ELECTRO.name());
		assertEquals("HARDDANCE", GenreColors.HARDDANCE.name());
		assertEquals("GLITCHHOP", GenreColors.GLITCHHOP.name());
		assertEquals("NUDISCO", GenreColors.NUDISCO.name());
		assertEquals("FUTUREBASS", GenreColors.FUTUREBASS.name());
		assertEquals("TRANCE", GenreColors.TRANCE.name());
		assertEquals("DUBSTEP", GenreColors.DUBSTEP.name());
		assertEquals("DRUMSTEP", GenreColors.DRUMSTEP.name());
		assertEquals("ELECTRONIC", GenreColors.ELECTRONIC.name());
		assertEquals("OTHER", GenreColors.OTHER.name());
	}

	@Test
	void getColor() {
		// Test the colors
		assertEquals(trap, GenreColors.TRAP.getColor());
		assertEquals(dnb, GenreColors.DNB.getColor());
		assertEquals(house, GenreColors.HOUSE.getColor());
		assertEquals(electro, GenreColors.ELECTRO.getColor());
		assertEquals(harddance, GenreColors.HARDDANCE.getColor());
		assertEquals(glitchhop, GenreColors.GLITCHHOP.getColor());
		assertEquals(nudisco, GenreColors.NUDISCO.getColor());
		assertEquals(futurebass, GenreColors.FUTUREBASS.getColor());
		assertEquals(trance, GenreColors.TRANCE.getColor());
		assertEquals(dubstep, GenreColors.DUBSTEP.getColor());
		assertEquals(drumstep, GenreColors.DRUMSTEP.getColor());
		assertEquals(electronic, GenreColors.ELECTRONIC.getColor());
		assertEquals(other, GenreColors.OTHER.getColor());
	}

	@Test
	void getGenre() {
		// Test the color find thing
		assertEquals(GenreColors.TRAP, GenreColors.getGenre(trap));
		assertEquals(GenreColors.DNB, GenreColors.getGenre(dnb));
		assertEquals(GenreColors.HOUSE, GenreColors.getGenre(house));
		assertEquals(GenreColors.ELECTRO, GenreColors.getGenre(electro));
		assertEquals(GenreColors.HARDDANCE, GenreColors.getGenre(harddance));
		assertEquals(GenreColors.GLITCHHOP, GenreColors.getGenre(glitchhop));
		assertEquals(GenreColors.NUDISCO, GenreColors.getGenre(nudisco));
		assertEquals(GenreColors.FUTUREBASS, GenreColors.getGenre(futurebass));
		assertEquals(GenreColors.TRANCE, GenreColors.getGenre(trance));
		assertEquals(GenreColors.DUBSTEP, GenreColors.getGenre(dubstep));
		assertEquals(GenreColors.ELECTRONIC, GenreColors.getGenre(electronic));
		assertEquals(GenreColors.OTHER, GenreColors.getGenre(other));
		Assertions.assertNull(GenreColors.getGenre(Color.rgb(69, 69, 69))); // I had to make it appropriate somehow ;)
	}
}