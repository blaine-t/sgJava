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
		
		// Asks the server to unready the client
		Sockets.unready();
		
		// Asks the server to ready the client
		Sockets.ready();
		
		// Asks the server to leave
		Sockets.leave();
		
		// Asks the server to buy a stock ticker and amount
		Sockets.buy();
		
		// Asks the server to sell a stock ticker and amount
		Sockets.sell();
		
		//Crashes the server
		//Sockets.crash();
		
		// Initializes event listeners
		
		// Checks the UNIX time stamp
		Sockets.time();
		
		//Checks for any error codes sent
		Sockets.error();
		
		// Checks for any success codes sent
		Sockets.success();
		
		// Checks for codes given to host
		Sockets.code();
		
		// Checks for player list when user joins or leaves or readys
		Sockets.players();
		
		// Checks if the host has left the lobby and the lobby has been disbanded 
		Sockets.disband();
		
	}
}