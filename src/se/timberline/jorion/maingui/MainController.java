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
		//processInput
		//updateState
		universe.tickOneYear();
		notifyListeners();
	}
	
	private void notifyListeners() {
		// TODO Auto-generated method stub
		for (StateChangeListener listener : listeners) {
			listener.stateUpdated();
		}
		
		
	}

	public void addNextTurnListener(StateChangeListener listener) {
		this.listeners.add(listener);
	}


}
