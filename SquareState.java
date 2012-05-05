package hssquares;

public enum SquareState {
	NO_STATE("No State"), X("X"), O("O");
	   private String text;

	   private SquareState(String text) {
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
