package se.timberline.maingui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.timberline.jorion.model.Planet;
import se.timberline.jorion.model.UniverseGenerator;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		List<Planet> planets = new UniverseGenerator().getPlanets();
		JPanel mapPanel = new MapPanel(planets);
		
		JPanel sidePanel = new JPanel();
		sidePanel.add(new JLabel("Sol"));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(createButton("Game"));
		bottomPanel.add(createButton("Design"));
		bottomPanel.add(createButton("Fleet"));
		bottomPanel.add(createButton("Map"));
		bottomPanel.add(createButton("Races"));
		bottomPanel.add(createButton("Planets"));
		bottomPanel.add(createButton("Tech"));
		bottomPanel.add(createButton("Next Turn"));
		
		frame.add(mapPanel,BorderLayout.CENTER);
		frame.add(sidePanel,BorderLayout.EAST);
		frame.add(bottomPanel,BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	private static JButton createButton(String string) {
		JButton jButton = new JButton(string);
		return jButton;
	}
}
