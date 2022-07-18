package sgJava;

public class Title extends Main  {
	private static final long serialVersionUID = 8736023688668374202L;

	public void run() {
		// Initializes default settings
		Settings.init();
		
		// Initializes socket connection to server
		Sockets.connect();
		
		// Authenticates with server
		Sockets.auth();
		
		// Asks the server if client can host a server
		Sockets.host();
		
		// Asks the server if client can start a game
		Sockets.start();
		
		// Asks the server to ready the client
		Sockets.ready();
		
		// Initializes event listeners
		
		// Checks the UNIX time stamp
		Sockets.time();
		
		//Checks for any error codes sent
		Sockets.error();
		
		// Checks for any success codes sent
		Sockets.success();
	}
}