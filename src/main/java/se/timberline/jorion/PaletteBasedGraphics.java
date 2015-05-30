package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;

public class PaletteBasedGraphics {
	
	private final Graphics g;
	private final LbxPalette palette;
	private int x0 = 0;
	private int y0 = 0;

	public PaletteBasedGraphics(Graphics g, LbxPalette palette) {
		this.g = g;
		this.palette = palette;
	}
	
	public void drawPixel(int x, int y, int colorIndex) {
		Color color = palette.getColor(colorIndex);
		g.setColor(color);
		g.drawLine(x0+x, y0+y, x0+x, y0+y);
	}

	public void setOffset(int x, int y) {
		this.x0 = x;
		this.y0 = y;
	}

}
