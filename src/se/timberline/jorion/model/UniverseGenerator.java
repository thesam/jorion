package se.timberline.jorion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.timberline.jorion.maingui.Route;

public class UniverseGenerator {
	private static final int MAX_Y = 20;
	private static final int MAX_X = 20;

	public Universe getUniverse() {
		List<Planet> planets = createPlanets();
		List<Route> routes = createRoutes(planets);
		return new Universe(planets, routes);
	}

	private List<Planet> createPlanets() {
		List<Planet> planets = new ArrayList<Planet>();
		for (int i = 0; i < 10; i++) {
			Planet p = createNewPlanet();
			planets.add(p);
		}
		return planets;
	}

	private Planet createNewPlanet() {
		Planet p = new Planet();
		p.x = Math.abs(new Random().nextInt() % MAX_X);
		p.y = Math.abs(new Random().nextInt() % MAX_Y);
		return p;
	}

	private List<Route> createRoutes(List<Planet> planets) {
		List<Route> routes = new ArrayList<Route>();
		for (Planet p : planets) {
			// addRouteToNearestNeighbour(planets, routes, p);
			addRouteToAllOtherPlanets(planets, routes, p);
		}
		return routes;
	}

	private void addRouteToAllOtherPlanets(List<Planet> planets,
			List<Route> routes, Planet p) {
		for (Planet p2: planets) {
			routes.add(new Route(new Coordinates(p.x, p.y), new Coordinates(p2.x, p2.y)));
		}
	}

	private void addRouteToNearestNeighbour(List<Planet> planets,
			List<Route> routes, Planet p) {
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
}
