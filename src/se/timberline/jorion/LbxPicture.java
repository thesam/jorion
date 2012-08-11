package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

			nextByte = blob.readUInt8();
			if (nextByte != -1) {
				int pixelCounter = 1;
				if (nextByte >= 0xE0 && nextByte <= 0xEF) { // TODO: Find out upper limit, can it be related to the 4 skipped bytes?
					pixelCounter += nextByte & 0x0F;
					nextByte = blob.readUInt8();
				}
				while (pixelCounter > 0) {
					Color color = getColorFromPalette(nextByte);
					g.setColor(color);
//					System.err.println("Draw to " + currentX + "-" + currentY);
					g.drawLine(currentX, currentY, currentX, currentY);
					currentY++;
					pixelCounter--;
				}
			}
			if (currentY >= getHeight()) {
				currentY = 0;
				currentX++;
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
				nextByte = blob.readUInt8();
			}
		} while (nextByte != -1);

		// g.drawRect(0, 0, width2, height2);
	}

	private Color getColorFromPalette(int nextByte) {
		Color color = null;
		Map<Integer,Color> palette = new HashMap<Integer,Color>();
		
		palette.put(0x20,new Color(32, 48, 81));
		palette.put(0x28,new Color(36, 56, 101));
		palette.put(0x30,new Color(36, 60, 125));
		palette.put(0xBF,new Color(97, 73, 0));
		palette.put(0xC0,new Color(150, 113, 0));
		
		if (palette.containsKey(nextByte)) {
			color = palette.get(nextByte);
		} else {
			color = Color.BLACK;
		}
		return color;
	}

}
