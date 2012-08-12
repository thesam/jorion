package se.timberline.jorion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LbxPictureTest {
	private LbxPicture picture;

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
		
		//Content
		content.add(0xdd);
		content.add(0xdd);
		content.add(0xdd);
		
		BinaryBlob blob = new BinaryBlob(content );
		picture = LbxPicture.createFrom(blob);
		
	}
	
	@Test
	public void canParseWidthFromHeader() throws Exception {
		assertEquals(2,picture.getWidth());
	}
	
	@Test
	public void canParseHeightFromHeader() throws Exception {
		assertEquals(3,picture.getHeight());
	}
	
//	@Test
//	public void canDrawOneColumnWithLineModeDisabled() throws Exception {
//	}
}
