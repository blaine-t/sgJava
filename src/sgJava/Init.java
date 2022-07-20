package sgJava;

// Add color support to set the background color of the canvas
import java.awt.Color;

public class Init extends Title {

	private static final long serialVersionUID = -1196351898484999418L;

	public void run() {

		setSize(1280,720); // Set default window to 720p
		setBackground(Color.BLACK); // Sets background to black

		// Initializes default settings
		Settings.defaults();

		// Initializes socket connection to server
		Sockets.connect();

		// Authenticates with server
		Sockets.auth();

		// Asks the server if client can host a server
		Sockets.host();

		pause(100);

		// Asks the server to unready the client
		Sockets.unready();

		// Asks the server to ready the client
		Sockets.ready();

		// Asks the server if client can start a game
		Sockets.start();

		// Asks the server to buy a stock ticker and amount
		Sockets.buy();

		// Asks the server to sell a stock ticker and amount
		Sockets.sell();

		// Asks the server to leave
		Sockets.leave();

		
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

		// Checks for game events
		Sockets.game();

		// Draws the title screen
		drawTitle();
	}
}
