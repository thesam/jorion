package se.timberline.jorion.maingui;

import java.util.ArrayList;
import java.util.List;

import se.timberline.jorion.controller.StateChangeListener;
import se.timberline.jorion.model.Universe;

public class MainController {

	private List<StateChangeListener> listeners = new ArrayList<StateChangeListener>();
	private final Universe universe;

	public MainController(Universe universe) {
		this.universe = universe;
	}

	public void nextTurn() {
		System.err.println("Next turn!");
		
		//updateParametersFromPlayerActions
		
		
		//updateState
		universe.tickOneYear();
		
		notifyListeners();
	}
	
	private void notifyListeners() {
		for (StateChangeListener listener : listeners) {
			listener.modelUpdated();
		}
	}

	public void addNextTurnListener(StateChangeListener listener) {
		this.listeners.add(listener);
	}


}
