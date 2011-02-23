package errors;

public class UsernameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username = null;
	public UsernameException(String username) {
		this.username = username;
	}
	public String toString() {
		
		return "UsernameException - " + username + " cannot be added to the game.";
	}
}
