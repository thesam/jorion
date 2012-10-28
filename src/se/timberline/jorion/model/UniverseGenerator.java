package se.timberline.jorion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.timberline.jorion.maingui.Route;

public class UniverseGenerator {
	public Universe getUniverse() {
		List<Planet> planets = new ArrayList<Planet>();
		List<Route> routes = new ArrayList<Route>();
		for (int i = 0; i < 100; i++) {
			Planet p = new Planet();
			p.x = new Random().nextInt() % 20;
			p.y = new Random().nextInt() % 20;
			planets.add(p);
		}

		for (Planet p : planets) {
			// find nearest neighbour
			Planet closestNeighbour = null;
			long minDistance = Integer.MAX_VALUE;
			// add route
			for (Planet p2 : planets) {
				if (p == p2) {
					continue;
				}
				long dist = p.getCoordinates().distanceTo(p2.getCoordinates());
				if (dist < minDistance) {
					minDistance = dist;
					closestNeighbour = p2;
				}
			}
			if (closestNeighbour != null) {
				Coordinates from = p.getCoordinates();
				Coordinates to = closestNeighbour.getCoordinates();
				Route route = new Route(from, to);
				routes.add(route);
			}

		}
		return new Universe(planets,routes);
	}
}
