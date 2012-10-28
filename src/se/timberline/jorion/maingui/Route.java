package se.timberline.jorion.maingui;

import java.awt.Component;

import se.timberline.jorion.model.Coordinates;

public class Route {

	private Coordinates from;
	private Coordinates to;

	public Route(Coordinates from, Coordinates to) {
		this.from = from;
		this.to = to;
	}

	public Coordinates getFrom() {
		return from;
	}

	public Coordinates getTo() {
		return to;
	}

}
