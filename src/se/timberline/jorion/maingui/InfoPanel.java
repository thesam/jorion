package se.timberline.jorion.maingui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import se.timberline.jorion.model.Planet;

public class InfoPanel extends JPanel {
	private JLabel name;
	private Planet planet;

	public InfoPanel() {
		name = new JLabel("N/A");
		add(name); // TODO Auto-generated constructor stub
	}

	public void setSelectedObject(Planet planet) {
		this.planet = planet;
		updateFromSelectedObject();
	}

	public void updateFromSelectedObject() {
		name.setText(planet.getName() + "\n" + "Pop: " + planet.getPopulation());
	}
}
