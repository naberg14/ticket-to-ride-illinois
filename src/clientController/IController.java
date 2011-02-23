package clientController;

public interface IController {

	/**
	 * Handles a callback from the server on requesting the current version.
	 * @param version The current version of the application.
	 */
	public void onReceiveVersion(String version);
}
