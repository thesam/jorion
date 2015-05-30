package se.timberline.jorion;

public class LbxPictureFrame {

	private final int width;
	private final int height;
	private final BinaryBlob blob;

	public LbxPictureFrame(int width, int height, BinaryBlob frameData) {
		this.width = width;
		this.height = height;
		this.blob = frameData;
	}

	// public static LbxPictureFrame createFrom(int width, int height,
	// BinaryBlob frameData) {
	// // TODO Auto-generated method stub
	// return new LbxPictureFrame(width,height,frameData);
	// }

	public void draw(PaletteBasedGraphics g) {
		blob.seek(1); // skip first byte in frame. TODO:
						// Why?
		int nextByte = -1;
		int currentX = 0;
//		System.err.println("Drawing " + width + " x " + height);
		boolean lineModeEnabled = true;
		currentX = -1;
		do {
			int currentY = 0;
			int columnOffset = 0;
			currentY = 0;
			currentX++;
			nextByte = blob.readUInt8();
			if (nextByte == 0x0) {
				lineModeEnabled = false;
			} else {
				lineModeEnabled = true;
			}
			nextByte = blob.readUInt8();
			int ignoreMode = nextByte;
			nextByte = blob.readUInt8();
			int ignoreOffset = nextByte;
			// System.err.println("Ignore palette entry: " + nextByte);
			nextByte = blob.readUInt8();
			while (currentY < height && nextByte != -1) {
				nextByte = blob.readUInt8();
				if (nextByte != -1) {
					if (!shouldBeSkipped(nextByte, currentY, ignoreMode,
							ignoreOffset,columnOffset)) {
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
				columnOffset++;
			}

		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

	private boolean shouldBeSkipped(int nextByte, int currentY, int ignoreMode,
			int ignoreOffset, int columnOffset) {
		if (ignoreIsSet(ignoreMode)) {
			if (!isLastPixel(currentY)) {
				if (ignoreModeIsE(ignoreMode)) {
					if (matchesIgnoreOffset(ignoreOffset, columnOffset)) {
						return true;
					}
				} else {
					if (isIgnoredColor(nextByte)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}

	private boolean ignoreModeIsE(int ignoreMode) {
		return ignoreMode == 0xE;
	}

	private boolean matchesIgnoreOffset(int ignoreOffset,
			int currentOffset) {
		System.err.println("Match ignore offset: " + ignoreOffset + " - " + currentOffset);
		if (ignoreOffset == currentOffset) {
			System.err.println("Skipping pixel!");
			return true;
		}
		return false;
	}

	private boolean isLastPixel(int currentY) {
		return currentY == height - 1;
	}

	private boolean isIgnoredColor(int nextByte) {
		return nextByte == 3;
	}

	private boolean ignoreIsSet(int ignoreMode) {
		return ignoreMode == 0xE || ignoreMode == 0xF;
	}

}
