package se.timberline.jorion.model;

import java.util.List;

public class Universe {

	private final List<Planet> planets;

	public Universe(List<Planet> planets) {
		this.planets = planets;
	}

	public List<Planet> getPlanets() {
		return planets;
	}


}
