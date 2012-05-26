public enum State {
	NO_STATE("_"), X("X"), O("O");
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
