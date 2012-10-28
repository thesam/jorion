package se.timberline.jorion.model;

public class Coordinates {

	private final int x;
	private final int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public long distanceTo(Coordinates coordinates) {
		int xdist = Math.abs(coordinates.getX() - x);
		int ydist = Math.abs(coordinates.getY() - y);
		return Math.round(Math.sqrt(xdist*xdist+ydist*ydist));
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

}
