package se.timberline.maingui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import se.timberline.jorion.model.Planet;

public class MapPanel extends JPanel {

	private final List<Planet> planets;
	private int topLeftX;
	private int topLeftY;

	public MapPanel(List<Planet> planets) {
		this.planets = planets;
		this.topLeftX = 0;
		this.topLeftY = 0;
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		for (Planet p : planets) {
			ImageIcon img = new ImageIcon("resource\\yellow_star.png");
			g.drawImage(img.getImage(),p.x*50,p.y*50,null);
		}
	}

}
