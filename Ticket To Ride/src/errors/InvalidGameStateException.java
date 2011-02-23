package errors;

public class InvalidGameStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Command not valid for current gamestate";
	}
}
