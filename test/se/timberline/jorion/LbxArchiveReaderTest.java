package se.timberline.jorion;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class LbxArchiveReaderTest {
	
	private LbxArchiveReader lbxArchiveReader;
	private LbxArchive archive;

	@Before
	public void setup() throws Exception {
		BinaryBlob reader = BinaryBlob.createFromFile(new File("test/VORTEX.LBX"));
		lbxArchiveReader = new LbxArchiveReader(reader);
		archive = lbxArchiveReader.getArchive();
	}
	
	@Test
	public void canReadCorrectNumberOfEntries() throws Exception {
		assertEquals(0x23,archive.getEntries().size());
	}
	
	@Test
	public void canReadNameOfFirstEntry() throws Exception {
		assertEquals("VORTEX",archive.getEntries().get(0).getName());
	}
	
	@Test
	public void canReadNameOfSecondEntry() throws Exception {
		assertEquals("STARLORD",archive.getEntries().get(1).getName());
	}
	
	@Test
	public void canReadContentFromFirstEntry() throws Exception {
		assertEquals("VORTEX",archive.getEntries().get(0).getContent());
	}
}
