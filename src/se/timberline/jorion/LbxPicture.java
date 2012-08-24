package se.timberline.jorion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LbxPicture {
	private static final int FRAMES_OFFSET = 0x12;
	private final int width;
	private final int height;
//	private final BinaryBlob blob;
//	private final int numberOfFrames;
//	private final List<Integer> frameOffsets;
	private final List<LbxPictureFrame> frames;

//	public LbxPicture(int width, int height, int numberOfFrames,
//			BinaryBlob blob, List<Integer> frameOffsets) {
//		this.width = width;
//		this.height = height;
//		this.numberOfFrames = numberOfFrames;
//		this.blob = blob;
//		this.frameOffsets = frameOffsets;
//	}

	public LbxPicture(int width, int height, List<LbxPictureFrame> frames) {
		this.width = width;
		this.height = height;
		this.frames = frames;
		
	}

	public static LbxPicture createFrom(BinaryBlob blob) throws IOException {
		// parse header
		blob.seek(0);
		int width = blob.readUInt16();
		int height = blob.readUInt16();
		@SuppressWarnings("unused")
		int unknown = blob.readUInt16();
		int numberOfFrames = blob.readUInt16();
		blob.seek(FRAMES_OFFSET);
		List<Integer> frameOffsets = new ArrayList<Integer>();
		// last offset is EOF
		for (int frame = 0; frame < numberOfFrames+1; frame++) {
			int frameOffset = blob.readUInt32();
			frameOffsets.add(frameOffset);
		}
		List<LbxPictureFrame> frames = new ArrayList<LbxPictureFrame>();
		for (int offset = 0; offset < frameOffsets.size()-1; offset++) {
			int frameSize = frameOffsets.get(offset+1)-frameOffsets.get(offset);
			BinaryBlob frameData = blob.subBlob(frameOffsets.get(offset), frameSize);
			frames.add(new LbxPictureFrame(width,height,frameData));
		}
		// TODO: Parse more values, let the blob be just the picture data
		return new LbxPicture(width, height, frames);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void draw(PaletteBasedGraphics g) {
		for (int i = 0; i < frames.size(); i++) {
			g.setOffset(i*width,0);
			frames.get(i).draw(g);
		}
	}

	public List<LbxPictureFrame> getFrames() {
		return frames;
	}
}
