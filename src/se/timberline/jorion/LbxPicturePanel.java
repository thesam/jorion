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
			picture.draw(g);
		}
	}

	public void setPicture(LbxPicture picture) {
		this.picture = picture;
	}
}
