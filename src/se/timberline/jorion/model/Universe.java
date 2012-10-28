package se.timberline.jorion.model;

import java.util.List;

import se.timberline.jorion.maingui.Route;

public class Universe {

	private final List<Planet> planets;
	private List<Route> routes;

	public Universe(List<Planet> planets, List<Route> routes) {
		this.planets = planets;
		this.routes = routes;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void tickOneYear() {
		for (Planet planet : planets) {
			planet.tickOneYear();
		}
		
	}

	public List<Route> getRoutes() {
		return routes;
	}


}
