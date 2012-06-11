/** 	
*		Enum - State 
*		An enum to store a state blank, x, o, x_on, o_on. Used for squares, current player, winner, etc... X_ON and O_ON are used for animation
*		@author Garrett Hawes
*/

public enum State {
	NO_STATE("_"), X("X"), O("O"), X_ON("X_ON"), O_ON("O_ON");
	
	private String text;
	
	private State(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public String getText() {
		return text;
	}
}
