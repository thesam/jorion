package se.timberline.maingui;

import se.timberline.jorion.model.Universe;
import se.timberline.jorion.model.UniverseGenerator;

public class Main {
	public static void main(String[] args) {
		Universe planets = new UniverseGenerator().getUniverse();
		MainController controller = new MainController();
		new MainView(planets,controller);
	}

}
