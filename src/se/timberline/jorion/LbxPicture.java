package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LbxPicture {
	private static final int LAST_OFFSET_OF_HEADER_OFFSET = 0x12;
	private final int width;
	private final int height;
	private final BinaryBlob blob;
	private final int numberOfFrames;

	public LbxPicture(int width, int height, int numberOfFrames, BinaryBlob blob) {
		this.width = width;
		this.height = height;
		this.numberOfFrames = numberOfFrames;
		this.blob = blob;
	}

	public static LbxPicture createFrom(BinaryBlob blob) throws IOException {
		// parse header
		blob.seek(0);
		int width = blob.readUInt16();
		int height = blob.readUInt16();
		int unknown = blob.readUInt16();
		int numberOfFrames = blob.readUInt16();
		blob.seek(LAST_OFFSET_OF_HEADER_OFFSET);
		int lastOffsetOfHeader = blob.readUInt16();
		//TODO: Parse more values, let the blob be just the picture data
		return new LbxPicture(width, height, numberOfFrames, blob.subBlob(lastOffsetOfHeader+1));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(Graphics g) {
		// blob.seek(0x1F);
//		blob.seek(0x1B);
		int nextByte = -1;
		int currentX = 0;
		int currentY = 0;
		System.err.println("Drawing " + width + " x " + height);
		boolean lineModeEnabled = true;
		currentX = -1;
		do {
			currentY = 0;
			currentX++;
			nextByte = blob.readUInt8();
			if (nextByte == 0x00) {
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
						if ((nextByte >= 0xE0 && nextByte <= 0xEF)) {
							pixelCounter += nextByte & 0x0F;
							nextByte = blob.readUInt8();
						} else if (nextByte >= 0xF0 && nextByte <= 0xFF) {
							pixelCounter += 16 + (nextByte & 0x0F);
							nextByte = blob.readUInt8();
						}
					}
					while (pixelCounter > 0) {
						Color color = getColorFromPalette(nextByte);
						g.setColor(color);
						// System.err.println("Draw to " + currentX + "-" +
						// currentY);
						g.drawLine(currentX, currentY, currentX, currentY);
						currentY++;
						pixelCounter--;
					}
				}
			}

		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

	private Color getColorFromPalette(int nextByte) {
		Color color = null;
		Map<Integer, Color> palette = new HashMap<Integer, Color>();

		palette.put(0x20, new Color(32, 48, 81));
		palette.put(0x28, new Color(36, 56, 101));
		palette.put(0x30, new Color(36, 60, 125));
		palette.put(0x33, new Color(203, 93, 28));
		palette.put(0x3D, new Color(138, 60, 16));
		palette.put(0x3E, new Color(125, 56, 16));
		palette.put(0x44, new Color(85, 36, 8));
		palette.put(0xBF, new Color(97, 73, 0));
		palette.put(0xC0, new Color(150, 113, 0));
		palette.put(0xC1, new Color(203, 150, 0));
		palette.put(0xED, new Color(170, 121, 77));
		palette.put(0x38, new Color(174, 81, 24));
		palette.put(0x35, new Color(190, 89, 24));
		palette.put(0x32, new Color(211, 97, 28));
		palette.put(0x31, new Color(209, 101, 28));
		
		

		if (palette.containsKey(nextByte)) {
			color = palette.get(nextByte);
		} else {
			color = Color.BLACK;
		}
		return color;
	}

}
