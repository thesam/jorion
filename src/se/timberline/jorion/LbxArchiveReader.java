package se.timberline.jorion;

import java.io.IOException;
import java.util.LinkedList;

public class LbxArchiveReader {

	private static final int NAME_SIZE = 0x20;
	private static final int NAMES_OFFSET = 0x200;
	private final BinaryFileReader reader;

	public LbxArchiveReader(BinaryFileReader reader) {
		this.reader = reader;
	}

	public LbxArchive getArchive() throws IOException {
		LinkedList<LbxEntry> entries = new LinkedList<LbxEntry>();
		
		int numberOfEntries = reader.readUInt16();
		
		reader.seek(0x08);
		
		
		for (int entry = 0; entry < numberOfEntries; entry++) {
			reader.seek(NAMES_OFFSET+entry*NAME_SIZE);
			String name = reader.readString();
			entries.add(new LbxEntry(name));
		}
		
		
		
		return new LbxArchive(entries);
	}

}
