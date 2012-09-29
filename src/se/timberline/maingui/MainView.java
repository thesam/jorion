package se.timberline.maingui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import se.timberline.jorion.model.Planet;
import se.timberline.jorion.model.Universe;

public class MainView {
	private final Universe planets;
	private final MainController controller;
	private PlanetLabel selectedPlanet;
	
	public MainView(Universe planets, final MainController controller) {

		this.planets = planets;
		this.controller = controller;
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		JPanel mapPanel = new MapPanel(planets);
		
		for (Planet p : planets.getPlanets()) {
			ImageIcon img = new ImageIcon("resource\\yellow_star.png");
			final PlanetLabel imgLabel = new PlanetLabel(img,p);
			imgLabel.setBounds(p.x*50, p.y*50, img.getIconWidth(), img.getIconHeight());
			imgLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(java.awt.event.MouseEvent arg0) {
					System.err.println("Click on planet");
					updatePlanetSelection(imgLabel);
				}

				@Override
				public void mouseEntered(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			mapPanel.add(imgLabel);
		}
		
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
	
	private void updatePlanetSelection(PlanetLabel imgLabel) {
		if (selectedPlanet != null) {
			selectedPlanet.setBorder(null);
		}
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		selectedPlanet = imgLabel;
	}
	
	
}
