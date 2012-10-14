package se.timberline.jorion.maingui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import se.timberline.jorion.model.Planet;

public class PlanetLabel extends JLabel {

	private final Planet p;

	public PlanetLabel(ImageIcon img, Planet p) {
		super(img);
		this.p = p;
		// TODO Auto-generated constructor stub
	}

	public Planet getPlanet() {
		return p;
	}

}
