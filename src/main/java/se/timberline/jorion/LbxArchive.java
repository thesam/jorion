package se.timberline.jorion;

import java.util.List;

public class LbxArchive {

	private final List<LbxEntry> entries;

	public LbxArchive(List<LbxEntry> entries) {
		this.entries = entries;
	}

	public List<LbxEntry> getEntries() {
		return entries;
	}

}
