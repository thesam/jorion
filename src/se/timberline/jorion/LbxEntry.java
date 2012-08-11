package se.timberline.jorion;

import java.util.List;

public class LbxEntry {

	private final String name;
	private final BinaryBlob content;
	private final int offset;

	public LbxEntry(String name, BinaryBlob content, int offset) {
		this.name = name;
		this.content = content;
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public BinaryBlob getContent() {
		return content.duplicate();
	}
	
	@Override
	public String toString() {
		return getName() + "(" + String.format("%x",getOffset()) + ")";
	}

	private int getOffset() {
		return offset;
	}

}
