package GameComponents;

import AbtractClasses.Event;
import Interfaces.Observable;

public class BallDirectionChange extends Event {

	public BallDirectionChange(Observable source) {
		super(source);
	}

}
