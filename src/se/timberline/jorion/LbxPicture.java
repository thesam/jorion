package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class LbxPicture {
	private final int width;
	private final int height;
	private final BinaryBlob blob;

	public LbxPicture(int width, int height, BinaryBlob blob) {
		this.width = width;
		this.height = height;
		this.blob = blob;
	}

	public static LbxPicture createFrom(BinaryBlob blob) throws IOException {
		int width = blob.readUInt16();
		int height = blob.readUInt16();
		return new LbxPicture(width, height, blob);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(Graphics g) {
		blob.seek(0x1F);
		int width2 = getWidth();
		int height2 = getHeight();
		int nextByte = -1;
		int currentX = 0;
		int currentY = 0;
		System.err.println("Drawing " + width2 + " x " + height2);
		do {
			g.setColor(Color.BLACK);
			nextByte = blob.readUInt8();
			if (nextByte == 0x80) {
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
			}
			if (nextByte != -1) {
				int pixelCounter = 1;
				if ((nextByte & 0xF0) == 0xE0) {
					pixelCounter += nextByte & 0x0F;
					nextByte = blob.readUInt8();
				}
				while (pixelCounter > 0) {
					if (nextByte == 0x28) {
						g.setColor(new Color(36,56,101));
					}
					if (nextByte == 0x30) {
						g.setColor(new Color(36, 60, 125));
					}
					if (nextByte == 0x20) {
						g.setColor(new Color(32, 48, 81));
					}
					System.err.println("Draw to " + currentX + "-" +  currentY);
					g.drawLine(currentX, currentY, currentX, currentY);
					currentY++;
					pixelCounter--;
				}
			}
			if (currentY >= getHeight()) {
				currentY = 0;
				currentX++;
			}
		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

}
