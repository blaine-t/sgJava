package sgJava;

import java.awt.Color;

import acm.graphics.*;

public class Title extends Main  {
	private static final long serialVersionUID = 8736023688668374202L;

	public void run() {
		setSize(1280,720);
		setBackground(Color.BLACK);
		
		// Writes the title
		GLabel title1 = new GLabel("STOCK",0,0);
		GLabel title2 = new GLabel("GAME",0,0);
		title1.setColor(Color.white);
		title2.setColor(Color.white);
		add(title1);
		add(title2);
		
		GLine titleLine1 = new GLine(0,0,0,0);
		GLine titleLine2 = new GLine(0,0,0,0);
		GLine titleLine3 = new GLine(0,0,0,0);
		GLine titleLine4 = new GLine(0,0,0,0);
		GLine titleLine5 = new GLine(0,0,0,0);
		
		GLine[] titleLines = {titleLine1,titleLine2,titleLine3,titleLine4,titleLine5};
		
		for (int i = 0; i<titleLines.length; i++) {
			titleLines[i].setColor(Color.GREEN);
			add(titleLines[i]);
		}
		
		GPolygon poly = thickLine(0, 0, 30, 30, 6);
		poly.setColor(Color.green);
		poly.setFilled(true);
		add(poly);
		
		titleLine1.setColor(Color.GREEN);
		titleLine2.setColor(Color.GREEN);
		titleLine3.setColor(Color.GREEN);
		add(titleLine1);
		add(titleLine2);
		add(titleLine3);
		
		Settings.init();
		Sockets.connect();
		Sockets.auth();
		Sockets.host();
		pause(1000);
		Sockets.ready();
		Sockets.start();
		Sockets.game();
		Sockets.date();
		Sockets.time();
		
		while (true) {
			percentObjRel(title1, 50, 30, null, false);
			percentObjRel(title2, 50, 20, title1, true);
			percentLabel(title1, 96);
			percentLabel(title2, 96);
			percentLine(titleLine1, 20, 59, 44, 35);
			percentLine(titleLine2, 44, 35, 60, 35);
			percentLine(titleLine3, 60, 35, 80, 15);
			
			pause(16);
		}
	}
	
	
	/* Socket testing
	 * 
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
*/
}