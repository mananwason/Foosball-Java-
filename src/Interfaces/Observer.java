package Interfaces;

import AbtractClasses.Event;

public interface Observer {
	public void startObserving(Observable observable);

	public void handleEvent(Event event);
}
