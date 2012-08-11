package se.timberline.jorion;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LbxPicturePanel extends JPanel {
	private LbxPicture picture;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (picture != null) {
			g.setColor(Color.GREEN);
			int width2 = picture.getWidth();
			int height2 = picture.getHeight();
			System.err.println("Drawing " + width2 + " x " + height2);
			g.drawRect(0, 0, width2, height2);
		}
	}

	public void setPicture(LbxPicture picture) {
		this.picture = picture;
		
	}
}
