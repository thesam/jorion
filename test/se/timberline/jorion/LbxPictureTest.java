package se.timberline.jorion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPictureTest {
	private static final int NUMBER_OF_FRAMES = 0x2;
	private static final int HEIGHT = 0x3;
	private static final int WIDTH = 0x3;
	private LbxPicture picture;

	@Before
	public void setup() throws Exception {
		List<Integer> content = new ArrayList<Integer>();
		// Width
		content.add(WIDTH);
		content.add(0x0);

		// Height
		content.add(HEIGHT);
		content.add(0x0);

		// Unknown
		content.add(0xff);
		content.add(0xff);
//
		// Number of frames
		content.add(NUMBER_OF_FRAMES);
		content.add(0x0);

		// Unknown
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
//
		// Offset of first frame
		content.add(0x1A);
		content.add(0x00);
		content.add(0x00);
		content.add(0x00);
//
		// Offset of second frame
		content.add(0x1B);
		content.add(0x00);
		content.add(0x00);
		content.add(0x00);
		
		// Offset of EOF
		content.add(0x1C);
		content.add(0x00);
		content.add(0x00);
		content.add(0x00);

//		// Frame 1 content
		content.add(0x11);
		
//		// Frame 2 content
		content.add(0x22);
		
		BinaryBlob blob = new BinaryBlob(content);
		picture = LbxPicture.createFrom(blob);

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
	public void canExtractFrameContent() throws Exception {
		assertEquals(2, picture.getFrames().size());
		//TODO: Check equals for blobs
	}

}
