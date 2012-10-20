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

	public void updateInfoPanel(InfoPanel infoPanel) {
		infoPanel.removeAll();
		infoPanel.add(new JLabel("Name: " + p.getName()));
		infoPanel.add(new JLabel("Pop: " + p.getPopulation()));
		infoPanel.add(new JLabel("Prod: " + "xx" + "(" + p.getUnadjustedProduction() + ")"));
		infoPanel.validate();
		infoPanel.repaint();
	}

}
