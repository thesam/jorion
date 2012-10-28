package se.timberline.jorion.maingui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
//	private JLabel name;
	private PlanetView planet;

	public InfoPanel() {
		JLabel name = new JLabel("N/A");
		add(name); // TODO Auto-generated constructor stub
	}

	public void setSelectedObject(PlanetView planet) {
		this.planet = planet;
		updateFromSelectedObject();
	}

	public void updateFromSelectedObject() {
		planet.updateInfoPanel(this);
	}
}
