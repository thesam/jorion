package se.timberline.jorion.maingui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import se.timberline.jorion.model.Planet;

public class InfoPanel extends JPanel {
//	private JLabel name;
	private PlanetLabel planet;

	public InfoPanel() {
		JLabel name = new JLabel("N/A");
		add(name); // TODO Auto-generated constructor stub
	}

	public void setSelectedObject(PlanetLabel planet) {
		this.planet = planet;
		updateFromSelectedObject();
	}

	public void updateFromSelectedObject() {
		planet.updateInfoPanel(this);
	}
}
