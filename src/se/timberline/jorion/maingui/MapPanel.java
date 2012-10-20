package se.timberline.jorion.maingui;

import java.awt.Color;

import javax.swing.JPanel;

import se.timberline.jorion.model.Universe;

public class MapPanel extends JPanel {

	private final Universe planets;
	private int topLeftX;
	private int topLeftY;

	public MapPanel(Universe planets2) {
		this.planets = planets2;
		this.topLeftX = 0;
		this.topLeftY = 0;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
	}

}
