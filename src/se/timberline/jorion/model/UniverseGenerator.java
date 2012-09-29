package se.timberline.jorion.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniverseGenerator {
	public List<Planet> getPlanets() {
		List<Planet> planets = new ArrayList<Planet>();
		for (int i = 0; i < 100; i++) {
			Planet p = new Planet();
			p.x = new Random().nextInt() % 20;
			p.y = new Random().nextInt() % 20;
			planets.add(p);
		}
		return planets;
	}
}
