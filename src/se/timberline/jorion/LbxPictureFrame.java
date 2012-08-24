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

	public static LbxPictureFrame createFrom(int width, int height, BinaryBlob frameData) {
		// TODO Auto-generated method stub
		return new LbxPictureFrame(width,height,frameData);
	}
	
	public void draw(PaletteBasedGraphics g) {
		blob.seek(1); // skip first byte in frame. TODO:
											// Why?
		int nextByte = -1;
		int currentX = 0;
		int currentY = 0;
//		int currentFrame = 0;
		System.err.println("Drawing " + width + " x " + height);
		boolean lineModeEnabled = true;
		currentX = -1;
		do {
			currentY = 0;
			currentX++;
//			if (currentX == width) {
//				// next frame?
//				currentFrame++;
//				if (frameOffsets.size() > currentFrame) {
//					blob.seek(frameOffsets.get(currentFrame) + 1);
//				}
//			}
			nextByte = blob.readUInt8();
			if (nextByte == 0x0) {
				lineModeEnabled = false;
			} else {
				lineModeEnabled = true;
			}
			nextByte = blob.readUInt8();
			int ignoreMode = nextByte;
			nextByte = blob.readUInt8();
//			int ignoreColor = nextByte;
			// System.err.println("Ignore palette entry: " + nextByte);
			nextByte = blob.readUInt8();
			while (currentY < height && nextByte != -1) {
				nextByte = blob.readUInt8();
				if (nextByte != -1) {
					 if ((ignoreMode & 0x02) == 0 || (nextByte != 3 || currentY == height -
					 1)) {
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
			}

		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

}
