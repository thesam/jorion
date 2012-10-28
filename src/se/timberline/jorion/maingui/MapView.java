package se.timberline.jorion.maingui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import se.timberline.jorion.model.Universe;

public class MapView extends JPanel {

	private final Universe universe;
	private int topLeftX;
	private int topLeftY;

	public MapView(Universe universe) {
		this.universe = universe;
		this.topLeftX = 0;
		this.topLeftY = 0;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		paintBorder(g);
		paintChildren(g);
		paintRoutes(g);
		
	}

	private void paintRoutes(Graphics g) {
		List<Route> routes = universe.getRoutes();
		for (Route route : routes) {
			g.setColor(Color.GREEN);
			g.drawLine(route.getFrom().getX()*50, route.getFrom().getY()*50, route.getTo().getX()*50, route.getTo().getY()*50);
		}
	}

}
