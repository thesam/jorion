package se.timberline.jorion.maingui;

import se.timberline.jorion.model.Universe;
import se.timberline.jorion.model.UniverseGenerator;

public class Main {
	public static void main(String[] args) {
		Universe universe = new UniverseGenerator().getUniverse();
		MainController controller = new MainController(universe);
		MainView mainView = new MainView(universe,controller);
		controller.addNextTurnListener(mainView);
		
	}

}
