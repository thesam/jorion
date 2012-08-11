package se.timberline.jorion;

import java.io.IOException;

public class LbxPicture {
	private final int width;
	private final int height;

	public LbxPicture(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static LbxPicture createFrom(BinaryBlob blob) throws IOException {
		int width = blob.readUInt16();
		int height = blob.readUInt16();
		return new LbxPicture(width,height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
