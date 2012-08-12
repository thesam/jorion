package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class PaletteBasedGraphics {
	
	private final Graphics g;
	private final LbxPalette palette;

	public PaletteBasedGraphics(Graphics g, LbxPalette palette) {
		this.g = g;
		this.palette = palette;
	}
	
	public void drawPixel(int x, int y, int colorIndex) {
		Color color = palette.getColor(colorIndex);
		g.setColor(color);
		g.drawLine(x, y, x, y);
	}

}
