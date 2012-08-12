package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class PaletteBasedGraphics {
	
	private final Graphics g;

	public PaletteBasedGraphics(Graphics g) {
		this.g = g;
	}
	
	public void drawPixel(int x, int y, int colorIndex) {
		Color color = getColorFromPalette(colorIndex);
		g.setColor(color);
		g.drawLine(x, y, x, y);
	}
	
	
	//TODO: Move to palette class
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
