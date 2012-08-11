package se.timberline.jorion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LbxArchiveReader {

	private static final int NAME_SIZE = 0x20;
	private static final int NAMES_OFFSET = 0x200;
	private final BinaryBlob reader;

	public LbxArchiveReader(BinaryBlob reader) {
		this.reader = reader;
	}

	public LbxArchive getArchive() throws IOException {
		LinkedList<LbxEntry> entries = new LinkedList<LbxEntry>();
		
		int numberOfEntries = reader.readUInt16();
		
		reader.seek(0x08);
		
		List<Integer> offsets = new LinkedList<Integer>();
		for (int entry = 0; entry < numberOfEntries + 1; entry++) { // one last offset for EOF
			int offset = reader.readUInt32();
			offsets.add(offset);
		}
		
		List<String> names = new LinkedList<String>();
		for (int entry = 0; entry < numberOfEntries; entry++) {
			// read names
			reader.seek(NAMES_OFFSET+entry*NAME_SIZE);
			String name = reader.readString(0x20);
			names.add(name);
		}
		
		for (int entry = 0; entry < numberOfEntries; entry++) {
			reader.seek(offsets.get(entry));
			Integer offset = offsets.get(entry);
			int size = offsets.get(entry+1) - offset;
			String name = names.get(entry);
			
			entries.add(new LbxEntry(name,reader.subBlob(offset,size),offset));
		}
		
		
		
		return new LbxArchive(entries);
	}

}
