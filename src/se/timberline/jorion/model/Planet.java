package se.timberline.jorion.model;

public class Planet {
	public int x = 0;
	public int y = 0;
	private String name;
	private int population;
	private int factories;
	
	public Planet() {
		this.name = "" + Math.random();
		this.population = 1;
		this.factories = 1;
	}
	
	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}
	
	public void tickOneYear() {
		population++;
	}
	
	public double getUnadjustedProduction() {
		return 0.53*population+factories;
	}

	public Coordinates getCoordinates() {
		return new Coordinates(x,y);
	}

}
