package se.timberline.jorion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPictureTest {
	private static final int NUMBER_OF_FRAMES = 0x2;
	private static final int HEIGHT = 0x3;
	private static final int WIDTH = 0x3;
	private static final int DRAW_LINE = 0xe0;
	private static final int LINE_MODE_DISABLED = 0;
	private static final int LINE_MODE_ENABLED = 0x80;
	private LbxPicture picture;
	private PaletteBasedGraphics g;

	@Before
	public void setup() throws Exception {
		List<Integer> content = new ArrayList<Integer>();
		// Width
		content.add(WIDTH);
		content.add(0x0);

		// Height
		content.add(HEIGHT);
		content.add(0x0);

		content.add(0xff);
		content.add(0xff);

		// Number of frames
		content.add(NUMBER_OF_FRAMES);
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

		// Offset of first frame
		content.add(0x1A);
		content.add(0x00);
		content.add(0x00);
		content.add(0x00);

		// Offset of second frame
		content.add(0x2E);
		content.add(0x00);
		content.add(0x00);
		content.add(0x00);

		// Frame header byte?
		content.add(0xff);

		// Column header
		content.add(LINE_MODE_DISABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(0xdd);
		content.add(0xdd);
		content.add(0xdd);

		// Column header
		content.add(LINE_MODE_ENABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(DRAW_LINE | 2);
		content.add(0xcc);

		// Column header
		content.add(LINE_MODE_ENABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(DRAW_LINE | 0x10);
		content.add(0xcc);

		// Frame header byte?
		content.add(0xff);

		// Column header
		content.add(LINE_MODE_DISABLED);
		content.add(0xFF);
		content.add(0xFF);
		content.add(0xFF);
		// Column content
		content.add(0xaa);
		content.add(0xbb);
		content.add(0xcc);

		BinaryBlob blob = new BinaryBlob(content);
		picture = LbxPicture.createFrom(blob);

		g = mock(PaletteBasedGraphics.class);

	}

	@Test
	public void canParseWidthFromHeader() throws Exception {
		assertEquals(3, picture.getWidth());
	}

	@Test
	public void canParseHeightFromHeader() throws Exception {
		assertEquals(3, picture.getHeight());
	}

	@Test
	public void canParseFrameOffsets() throws Exception {
		assertEquals(2, picture.getFrameOffsets().size());
		assertEquals(new Integer(0x1A), picture.getFrameOffsets().get(0));
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

	@Test
	public void canDrawThirdColumnWithLineLongerThan16Pixels() throws Exception {
		picture.draw(g);
		verify(g).drawPixel(2, 0, 0xcc);
		verify(g).drawPixel(2, 16, 0xcc);
	}

	@Test
	public void canDrawFirstColumnOfSecondFrame() throws Exception {
		picture.draw(g);
		verify(g).drawPixel(3, 0, 0xaa);
		verify(g).drawPixel(3, 1, 0xbb);
		verify(g).drawPixel(3, 2, 0xcc);
	}
}
