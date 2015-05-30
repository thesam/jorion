package se.timberline.jorion;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPaletteTest {
	
	private LbxPalette palette;

	@Before
	public void setup() throws Exception {
		List<Integer> content = new ArrayList<Integer>();
		// Two colors, 0,0,0 and 3f,3f,3f , stored in 4½ bytes
		content.add(0x00);
		content.add(0x00);
		content.add(0x3F);
		content.add(0xFF);
		content.add(0xF0);
		BinaryBlob blob = new BinaryBlob(content);
		palette = LbxPalette.createFrom(blob);
	}
	
	@Test
	public void canReadFirstColor() throws Exception {
		Color color = palette.getColor(0);
		assertEquals(0,color.getRed());
		assertEquals(0,color.getGreen());
		assertEquals(0,color.getBlue());
	}
	
	@Test
	public void canReadSecondColor() throws Exception {
		Color color = palette.getColor(1);
		assertEquals(255,color.getRed());
		assertEquals(255,color.getGreen());
		assertEquals(255,color.getBlue());
	}
}
