package se.timberline.jorion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPictureTest {
	private static final int LINE_MODE_DISABLED = 0;
	private static final int LINE_MODE_ENABLED = 0x80;
	private LbxPicture picture;
	private PaletteBasedGraphics g;

	@Before
	public void setup() throws Exception {
		List<Integer> content = new ArrayList<Integer>();
		// Width
		content.add(0x2);
		content.add(0x0);
		// Height
		content.add(0x3);
		content.add(0x0);
		
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0xff);
		content.add(0x14);
		content.add(0x00);
		content.add(0xff);
		
		//Column header
		content.add(LINE_MODE_DISABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(0xdd);
		content.add(0xdd);
		content.add(0xdd);
		
		//Column header
		content.add(LINE_MODE_ENABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(0xe2);
		content.add(0xcc);
		
		BinaryBlob blob = new BinaryBlob(content );
		picture = LbxPicture.createFrom(blob);
		
		g = mock(PaletteBasedGraphics.class);
		
	}
	
	@Test
	public void canParseWidthFromHeader() throws Exception {
		assertEquals(2,picture.getWidth());
	}
	
	@Test
	public void canParseHeightFromHeader() throws Exception {
		assertEquals(3,picture.getHeight());
	}
	
	@Test
	public void canDrawFirstColumnWithLineModeDisabled() throws Exception {
		picture.draw(g);
		verify(g).drawPixel(0, 0, 0xdd);
		verify(g).drawPixel(0, 1, 0xdd);
		verify(g).drawPixel(0, 2, 0xdd);
	}
	
	@Test
	public void canDrawSecondColumnWithLineModeEnabled() throws Exception {
		picture.draw(g);
		verify(g).drawPixel(1, 0, 0xcc);
		verify(g).drawPixel(1, 1, 0xcc);
		verify(g).drawPixel(1, 2, 0xcc);
	}
}
