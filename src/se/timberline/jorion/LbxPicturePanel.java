package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LbxPicturePanel extends JPanel {
	private LbxPicture picture;
	private final LbxPalette palette;
	
	public LbxPicturePanel(LbxPalette palette) {
		this.palette = palette;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (picture != null) {
			picture.draw(new PaletteBasedGraphics(g,palette));
		}
	}

	public void setPicture(LbxPicture picture) {
		this.picture = picture;
	}
}
