package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LbxPicture {
	private static final int FRAMES_OFFSET = 0x12;
	private final int width;
	private final int height;
	private final BinaryBlob blob;
	private final int numberOfFrames;
	private final List<Integer> frameOffsets;

	public LbxPicture(int width, int height, int numberOfFrames,
			BinaryBlob blob, List<Integer> frameOffsets) {
		this.width = width;
		this.height = height;
		this.numberOfFrames = numberOfFrames;
		this.blob = blob;
		this.frameOffsets = frameOffsets;
	}

	public static LbxPicture createFrom(BinaryBlob blob) throws IOException {
		// parse header
		blob.seek(0);
		int width = blob.readUInt16();
		int height = blob.readUInt16();
		int unknown = blob.readUInt16();
		int numberOfFrames = blob.readUInt16();
		blob.seek(FRAMES_OFFSET);
		List<Integer> frameOffsets = new ArrayList<Integer>();
		for (int frame = 0; frame < numberOfFrames; frame++) {
			int frameOffset = blob.readUInt32();
			frameOffsets.add(frameOffset);
		}
		// TODO: Parse more values, let the blob be just the picture data
		return new LbxPicture(width, height, numberOfFrames, blob, frameOffsets);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(PaletteBasedGraphics g) {
		blob.seek(frameOffsets.get(0) + 1); // skip first byte in frame. TODO:
											// Why?
		int nextByte = -1;
		int currentX = 0;
		int currentY = 0;
		int currentFrame = 0;
		System.err.println("Drawing " + width + " x " + height);
		boolean lineModeEnabled = true;
		currentX = -1;
		do {
			currentY = 0;
			currentX++;
			if (currentX == getWidth()) {
				// next frame?
				currentFrame++;
				if (frameOffsets.size() > currentFrame) {
					blob.seek(frameOffsets.get(currentFrame) + 1);
				}
			}
			nextByte = blob.readUInt8();
			if (nextByte == 0x0) {
				lineModeEnabled = false;
			} else {
				lineModeEnabled = true;
			}
			nextByte = blob.readUInt8();
			nextByte = blob.readUInt8();
			nextByte = blob.readUInt8();
			while (currentY < getHeight() && nextByte != -1) {
				nextByte = blob.readUInt8();
				if (nextByte != -1) {
					int pixelCounter = 1;
					if (lineModeEnabled) {
						if (nextByte >= 0xE0) {
							pixelCounter += nextByte - 0xE0;
							nextByte = blob.readUInt8();
						}
					}
					while (pixelCounter > 0) {
						g.drawPixel(currentX, currentY, nextByte);
						currentY++;
						pixelCounter--;
					}
				}
			}

		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

	public List<Integer> getFrameOffsets() {
		return frameOffsets;
	}

}
