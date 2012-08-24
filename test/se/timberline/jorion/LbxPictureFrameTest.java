package se.timberline.jorion;

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

		frame = new LbxPictureFrame(1, 3, new BinaryBlob(content));
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

	@Test
	public void canIgnoreColor3With0F30Header() throws Exception {
		// Column header
		content.add(0x0);
		content.add(0xF);
		content.add(0x3);
		content.add(0x0);
		// Column content
		content.add(0x3);
		content.add(0x1);
		content.add(0x1);
		content.add(0x1);

		frame.draw(g);

		verify(g).drawPixel(0, 0, 0x1);
		verify(g).drawPixel(0, 1, 0x1);
		verify(g).drawPixel(0, 2, 0x1);
	}

}
