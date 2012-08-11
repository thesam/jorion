package se.timberline.jorion;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class BinaryFileReaderTest {
	
	private BinaryFileReader reader;

	@Before
	public void setup() throws Exception {
		reader = BinaryFileReader.createFromFile(new File("test/VORTEX.LBX"));
	}
	
	@Test
	public void canReadOneLittleEndianUInt8FromFile() throws Exception {
		assertEquals(0x23,reader.readUInt8());
	}
	
	@Test
	public void canReadOneLittleEndianUInt16FromFile() throws Exception {
		assertEquals(0x0023,reader.readUInt16());
	}
	
	@Test
	public void canReadOneLittleEndianUInt32FromFile() throws Exception {
		assertEquals(0xFEAD0023,reader.readUInt32());
	}
	
	@Test
	public void canSeekToOffset() throws Exception {
		assertEquals(0x23,reader.readUInt8());
		reader.seek(0);
		assertEquals(0x23,reader.readUInt8());
	}
}
