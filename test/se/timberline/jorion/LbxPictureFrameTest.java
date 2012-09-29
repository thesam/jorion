package se.timberline.jorion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPictureFrameTest {

	private static final int DRAW_LINE = 0xe0;
	private static final int LINE_MODE_DISABLED = 0;
	private static final int LINE_MODE_ENABLED = 0x80;

	private PaletteBasedGraphics g;
	private List<Integer> content;
	private LbxPictureFrame frame;

	@Before
	public void setup() throws Exception {
		g = mock(PaletteBasedGraphics.class);
		content = new ArrayList<Integer>();

		frame = new LbxPictureFrame(1, 11, new BinaryBlob(content));
		// Frame header byte?
		content.add(0xff);
	}

	@Test
	public void canDrawOneColumnWithLineModeDisabled() throws Exception {
		// Column header
		content.add(LINE_MODE_DISABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(0xdd);
		content.add(0xdd);
		content.add(0xdd);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0xdd);
		verify(g).drawPixel(0, 1, 0xdd);
		verify(g).drawPixel(0, 2, 0xdd);
	}

	@Test
	public void canDrawOneColumnWithLineModeEnabled() throws Exception {
		// Column header
		content.add(LINE_MODE_ENABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(DRAW_LINE | 0x10);
		content.add(0xcc);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0xcc);
		verify(g).drawPixel(0, 1, 0xcc);
		verify(g).drawPixel(0, 2, 0xcc);
	}

	//Based on STARMAP.LBX - MAP_BUTT
	@Test
	public void canIgnoreColor3InCorrectPlaceWith0F30Header() throws Exception {
		// Column header
		content.add(0x0);
		content.add(0xF);
		content.add(0x3);
		content.add(0x0);
		// Column content
		content.add(0x4);
		content.add(0xFB);
		content.add(0x6);
		content.add(0x3);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x3);
		content.add(0x1);
		content.add(0x6);
		content.add(0xFA);
		content.add(0x3);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0x4);
		verify(g).drawPixel(0, 1, 0xFB);
		verify(g).drawPixel(0, 2, 0x6);
		verify(g).drawPixel(0, 3, 0x1);
		verify(g).drawPixel(0, 4, 0x1);
		verify(g).drawPixel(0, 5, 0x1);
		verify(g).drawPixel(0, 6, 0x1);
		verify(g).drawPixel(0, 7, 0x1);
		verify(g).drawPixel(0, 8, 0x6);
		verify(g).drawPixel(0, 9, 0xFA);
		verify(g).drawPixel(0, 10, 0x3);
	}
	
	//Based on STARMAP.LBX - MAP_BUTT
	@Test
	public void canIgnoreColor3InCorrectPlaceWith0E70Header() throws Exception {
		// Column header
		content.add(0x0);
		content.add(0xE);
		content.add(0x7);
		content.add(0x0);
		// Column content
		content.add(0x4);
		content.add(0xFB);
		content.add(0x6);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x3);
		content.add(0x1);
		content.add(0x6);
		content.add(0xFA);
		content.add(0x3);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0x4);
		verify(g).drawPixel(0, 1, 0xFB);
		verify(g).drawPixel(0, 2, 0x6);
		verify(g).drawPixel(0, 3, 0x1);
		verify(g).drawPixel(0, 4, 0x1);
		verify(g).drawPixel(0, 5, 0x1);
		verify(g).drawPixel(0, 6, 0x1);
		verify(g).drawPixel(0, 7, 0x1);
		verify(g).drawPixel(0, 8, 0x6);
		verify(g).drawPixel(0, 9, 0xFA);
		verify(g).drawPixel(0, 10, 0x3);
	}
	
	//Based on STARMAP.LBX - MAP_BUTT
	@Test
	public void canIgnoreColor6InCorrectPlaceWith0E40Header() throws Exception {
		// Column header
		content.add(0x0);
		content.add(0xE);
		content.add(0x4);
		content.add(0x0);
		// Column content
		content.add(0x4);
		content.add(0xFB);
		content.add(0x6);
		content.add(0x1);
		content.add(0x6);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x6);
		content.add(0xFA);
		content.add(0x3);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0x4);
		verify(g).drawPixel(0, 1, 0xFB);
		verify(g).drawPixel(0, 2, 0x6);
		verify(g).drawPixel(0, 3, 0x1);
		verify(g).drawPixel(0, 4, 0x1);
		verify(g).drawPixel(0, 5, 0x1);
		verify(g).drawPixel(0, 6, 0x1);
		verify(g).drawPixel(0, 7, 0x1);
		verify(g).drawPixel(0, 8, 0x6);
		verify(g).drawPixel(0, 9, 0xFA);
		verify(g).drawPixel(0, 10, 0x3);
	}
	
	//Based on STARMAP.LBX - MAP_BUTT
	@Test
	public void canIgnoreColorsInCorrectPlaceWith01030Header() throws Exception {
		// Column header
		content.add(0x0);
		content.add(0x10);
		content.add(0x3);
		content.add(0x0);
		// Column content
		content.add(0x4);
		content.add(0xFB);
		content.add(0x6);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);
		content.add(0x3);
		content.add(0x1);
		content.add(0x6);
		content.add(0xFA);
		content.add(0x3);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0x4);
		verify(g).drawPixel(0, 1, 0xFB);
		verify(g).drawPixel(0, 2, 0x6);
		verify(g).drawPixel(0, 3, 0x1);
		verify(g).drawPixel(0, 4, 0x1);
		verify(g).drawPixel(0, 5, 0x1);
		verify(g).drawPixel(0, 6, 0x1);
		verify(g).drawPixel(0, 7, 0x1);
		verify(g).drawPixel(0, 8, 0x6);
		verify(g).drawPixel(0, 9, 0xFA);
		verify(g).drawPixel(0, 10, 0x3);		
	}

}
