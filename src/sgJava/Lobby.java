package sgJava;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.io.IODialog;

public class Lobby extends Play {

	private static final long serialVersionUID = 1887619156444000857L;

	public void drawHostLobby() {

		// Initializes dialog box
		IODialog dialog = new IODialog();

		// Writes the title
		GLabel lobbyTitle = new GLabel("LOBBY");

		lobbyTitle.setColor(Color.WHITE);
		lobbyTitle.setFont("IMPACT");
		add(lobbyTitle);

		// Draws the bounding box on the screen
		GRoundRect boundingBox = new GRoundRect(0, 0);

		boundingBox.setColor(Color.WHITE);
		add(boundingBox);

		GLabel codeLabel = new GLabel("CODE: " + Integer.toString(Settings.setting.getInt("code")));
		GLabel playersLabel = new GLabel("Players: ");

		// Create back button
		GLabel leaveLabel = new GLabel("LEAVE");
		GRect leaveButton = new GRect(0,0,0,0);

		GLabel startLabel = new GLabel("START");
		GRect startButton = new GRect(0,0,0,0);

		GLabel readyLabel = new GLabel("READY");
		GRect readyButton = new GRect(0,0,0,0);

		GLabel[] lobbyButtonLabels = {leaveLabel,startLabel,readyLabel,codeLabel,playersLabel};
		GRect[] lobbyButtons = {leaveButton,startButton,readyButton};

		lobbyButtonSetup(lobbyButtonLabels,lobbyButtons);

		// Draws the input boxes and their labels
		GLabel capitalLabel = new GLabel("Capital:");
		GRect capitalBox = new GRect(0,0,0,0);
		GLabel capitalData = new GLabel("$" + Integer.toString(Settings.setting.getInt("capital")));

		GLabel daysLabel = new GLabel("Days:");
		GRect daysBox = new GRect(0,0,0,0);
		GLabel daysData = new GLabel(Integer.toString(Settings.setting.getInt("length")));

		GLabel[] lobbyLabels = {capitalLabel,daysLabel};
		GRect[] lobbyBoxes = {capitalBox,daysBox};
		GLabel[] lobbyData = {capitalData,daysData};

		lobbyMenuSetup(lobbyLabels,lobbyBoxes,lobbyData);

		// Sets padding and font size of all the title buttons
		final int lobbyLabelsFontSize = 64;
		final int lobbyDataFontSize = 48;
		final int lobbyBackButtonFontSize = 48;
		final int lobbyButtonFontSize = 64;
		final int lobbyBackButtonPadding = 10;
		final int lobbyButtonPadding = 10;
		final int lobbyPlayerFontSize = 64;
		final double lobbyPlayerReadyRadius = 30;

		// Initialize lists for player list
		List<String> playerList = new ArrayList<>();
		List<Boolean> readyList = new ArrayList<>();

		// Initializes box for players
		GRect playerBox = new GRect(0,0,0,0);
		playerBox.setColor(Color.WHITE);
		add(playerBox);

		add(player1);
		add(player2);
		add(player3);
		add(player4);
		add(player5);
		add(player6);
		add(player7);
		add(player8);

		add(status1);
		add(status2);
		add(status3);
		add(status4);
		add(status5);
		add(status6);
		add(status7);
		add(status8);

		int asyncRefresh = 0;


		while (Settings.setting.get("screen") == "hostLobby") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

			codeLabel.setLabel("CODE: " + Integer.toString(Settings.setting.getInt("code")));
			
			// A stupidly inefficient way of handling player list but it works

			if (players != null && asyncRefresh >= 0) {	
				String playerString = (String) players;
				String[] playerReadyList = playerString.split(",");
				playerList.clear();
				readyList.clear();
				int readyAmount = 0;
				for (int i = 0; i < playerReadyList.length; i++) {
					if (i % 2 == 0) {
						playerList.add(playerReadyList[i]);
					}
					else {
						boolean state = Boolean.parseBoolean(playerReadyList[i]);
						readyList.add(state);
						if (state) {
							readyAmount += 1;
						}
					}
				}
				playersLabel.setLabel("Players: " + readyAmount + "/" + readyList.size()); 
				// Due to ACMs infinite wisdom I have to use a junky if statement so I did my best to hide it into a method
				playerListSetup(playerList,readyList,playerBox,lobbyPlayerFontSize,lobbyPlayerReadyRadius);	
				asyncRefresh = 0;
			}

			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size

				// Bounding box dynamic size and location
				percentObjSize(boundingBox, 70, 70, null);
				percentObjRel(boundingBox,50,50,null,false);

				// Title dynamic size and location
				percentLabel(lobbyTitle, 96);
				percentObjRel(lobbyTitle, 50, -10, boundingBox, false);

				// Back button dynamic size and location
				percentLabel(leaveLabel, lobbyBackButtonFontSize);
				rectLabel(leaveLabel, leaveButton, 50, 115, lobbyBackButtonPadding, boundingBox);

				// Entry boxes dynamic size and location
				percentObjSize(capitalBox, 30, 10, boundingBox);
				percentObjSize(daysBox, 10, 10, boundingBox);

				percentObjRel(capitalBox,65,30,boundingBox,true);
				percentObjRel(daysBox,85,45,boundingBox,true);

				// Labels dynamic font and location
				percentLabel(capitalLabel, lobbyLabelsFontSize);
				percentLabel(daysLabel, lobbyLabelsFontSize);

				percentObjRel(capitalLabel,-75,0,capitalBox,true);
				percentObjRel(daysLabel,-150,0,daysBox,true);

				// Entry text dynamic font and location
				percentLabel(capitalData, lobbyDataFontSize);
				percentLabel(daysData, lobbyDataFontSize);

				percentObjRel(capitalData,5,17.5,capitalBox,true);
				percentObjRel(daysData,5,17.5,daysBox,true);

				// Draw the other strings
				percentLabel(playersLabel, lobbyLabelsFontSize);
				percentLabel(codeLabel, lobbyLabelsFontSize);

				percentObjRel(playersLabel,2,0,boundingBox,true);
				percentObjRel(codeLabel,67,0,boundingBox,true);

				// Back button dynamic size and location
				percentLabel(startLabel, lobbyButtonFontSize);
				rectLabel(startLabel, startButton, 50, 80, lobbyButtonPadding, boundingBox);

				// Back button dynamic size and location
				percentLabel(readyLabel, lobbyButtonFontSize);
				rectLabel(readyLabel, readyButton, 70, 80, lobbyButtonPadding, boundingBox);

				// Back button dynamic size and location
				percentLabel(leaveLabel, lobbyButtonFontSize);
				rectLabel(leaveLabel, leaveButton, 90, 80, lobbyButtonPadding, boundingBox);

				// Player box dynamic size and location
				percentObjSize(playerBox, 40, 85, boundingBox);

				percentObjRel(playerBox,2,10,boundingBox,true);

				// Update the variables to be correct
				oldWidth = width;
				oldHeight = height;
				forceUpdate = false;

			}

			if (mousePress) {
				mousePress = false;

				GObject object = getElementAt(mouseX,mouseY); //Grab object at mouse location

				if (object != null) {

					if (object == leaveButton) {
						Sockets.leave();
						Settings.updateString("screen", "play");
					}
					else if (object == startButton) {
						Sockets.start();
					}
					else if (object == readyButton) {

						if (Settings.setting.getBoolean("ready")) {
							Sockets.unready();
							readyLabel.setLabel("READY");
							forceUpdate = true;
							Settings.updateBool("ready", false);
						}
						else {
							Sockets.ready();
							readyLabel.setLabel("CANCEL");
							forceUpdate = true;
							Settings.updateBool("ready", true);
						}
					}
					else if (object == capitalBox) {
						// Prompt user for money and store it and show it
						int capital = dialog.readInt("How much money should users start with?");
						Settings.updateInt("capital",capital);
						capitalData.setLabel("$" + Integer.toString(capital));
					}
					else if (object == daysBox) {
						// Prompt user for days and store it and show it
						int days = dialog.readInt("How many days should the game last?");
						Settings.updateInt("days",days);
						daysData.setLabel(Integer.toString(days));
					}
				}
			}
			asyncRefresh++;
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}

	public void drawJoinLobby() {

		// Writes the title
		GLabel lobbyTitle = new GLabel("LOBBY");

		lobbyTitle.setColor(Color.WHITE);
		lobbyTitle.setFont("IMPACT");
		add(lobbyTitle);

		// Draws the bounding box on the screen
		GRoundRect boundingBox = new GRoundRect(0, 0);

		boundingBox.setColor(Color.WHITE);
		add(boundingBox);

		GLabel codeLabel = new GLabel("CODE: " + Integer.toString(Settings.setting.getInt("code")));
		GLabel playersLabel = new GLabel("Players: ");

		// Create back button
		GLabel leaveLabel = new GLabel("LEAVE");
		GRect leaveButton = new GRect(0,0,0,0);

		GLabel readyLabel = new GLabel("READY");
		GRect readyButton = new GRect(0,0,0,0);

		GLabel[] lobbyButtonLabels = {leaveLabel,readyLabel,codeLabel,playersLabel};
		GRect[] lobbyButtons = {leaveButton,readyButton};

		lobbyButtonSetup(lobbyButtonLabels,lobbyButtons);

		// Sets padding and font size of all the title buttons
		final int lobbyLabelsFontSize = 64;
		final int lobbyBackButtonFontSize = 48;
		final int lobbyButtonFontSize = 64;
		final int lobbyBackButtonPadding = 10;
		final int lobbyButtonPadding = 10;
		final int lobbyPlayerFontSize = 64;
		final double lobbyPlayerReadyRadius = 30;

		// Initialize lists for player list
		List<String> playerList = new ArrayList<>();
		List<Boolean> readyList = new ArrayList<>();

		// Initializes box for players
		GRect playerBox = new GRect(0,0,0,0);
		playerBox.setColor(Color.WHITE);
		add(playerBox);

		add(player1);
		add(player2);
		add(player3);
		add(player4);
		add(player5);
		add(player6);
		add(player7);
		add(player8);

		add(status1);
		add(status2);
		add(status3);
		add(status4);
		add(status5);
		add(status6);
		add(status7);
		add(status8);

		int asyncRefresh = 0;


		while (Settings.setting.get("screen") == "joinLobby") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();
			
			codeLabel.setLabel("CODE: " + Integer.toString(Settings.setting.getInt("code")));
			
			// A stupidly inefficient way of handling player list but it works

			if (players != null && asyncRefresh >= 0) {	
				String playerString = (String) players;
				String[] playerReadyList = playerString.split(",");
				playerList.clear();
				readyList.clear();
				int readyAmount = 0;
				for (int i = 0; i < playerReadyList.length; i++) {
					if (i % 2 == 0) {
						playerList.add(playerReadyList[i]);
					}
					else {
						boolean state = Boolean.parseBoolean(playerReadyList[i]);
						readyList.add(state);
						if (state) {
							readyAmount += 1;
						}
					}
				}
				playersLabel.setLabel("Players: " + readyAmount + "/" + readyList.size()); 
				// Due to ACMs infinite wisdom I have to use a junky if statement so I did my best to hide it into a method
				playerListSetup(playerList,readyList,playerBox,lobbyPlayerFontSize,lobbyPlayerReadyRadius);	
				asyncRefresh = 0;
			}

			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size

				// Bounding box dynamic size and location
				percentObjSize(boundingBox, 70, 70, null);
				percentObjRel(boundingBox,50,50,null,false);

				// Title dynamic size and location
				percentLabel(lobbyTitle, 96);
				percentObjRel(lobbyTitle, 50, -10, boundingBox, false);

				// Back button dynamic size and location
				percentLabel(leaveLabel, lobbyBackButtonFontSize);
				rectLabel(leaveLabel, leaveButton, 50, 115, lobbyBackButtonPadding, boundingBox);

				// Draw the other strings
				percentLabel(playersLabel, lobbyLabelsFontSize);
				percentLabel(codeLabel, lobbyLabelsFontSize);

				percentObjRel(playersLabel,2,0,boundingBox,true);
				percentObjRel(codeLabel,67,0,boundingBox,true);

				// Back button dynamic size and location
				percentLabel(readyLabel, lobbyButtonFontSize);
				rectLabel(readyLabel, readyButton, 70, 80, lobbyButtonPadding, boundingBox);

				// Back button dynamic size and location
				percentLabel(leaveLabel, lobbyButtonFontSize);
				rectLabel(leaveLabel, leaveButton, 90, 80, lobbyButtonPadding, boundingBox);

				// Player box dynamic size and location
				percentObjSize(playerBox, 40, 85, boundingBox);

				percentObjRel(playerBox,2,10,boundingBox,true);

				// Update the variables to be correct
				oldWidth = width;
				oldHeight = height;
				forceUpdate = false;

			}

			if (mousePress) {
				mousePress = false;

				GObject object = getElementAt(mouseX,mouseY); //Grab object at mouse location

				if (object != null) {

					if (object == leaveButton) {
						Sockets.leave();
						Settings.updateString("screen", "play");
					}
					else if (object == readyButton) {
						if (Settings.setting.getBoolean("ready")) {
							Sockets.unready();
							readyLabel.setLabel("READY");
							forceUpdate = true;
							Settings.updateBool("ready", false);
						}
						else {
							Sockets.ready();
							readyLabel.setLabel("CANCEL");
							forceUpdate = true;
							Settings.updateBool("ready", true);
						}
					}
				}
			}
			asyncRefresh++;
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}

	/**
	 * Sets the color and fonts of the labels and input boxes on the options page
	 */
	private void lobbyMenuSetup(GLabel[] lobbyLabels, GRect[] lobbyBoxes, GLabel[] lobbyData) {
		for (int i = 0; i<lobbyLabels.length; i++) {
			lobbyLabels[i].setColor(Color.WHITE);
			lobbyLabels[i].setFont("Consolas-bold");
			add(lobbyLabels[i]);
		}

		for (int i = 0; i<lobbyData.length; i++) {
			lobbyData[i].setColor(Color.WHITE);
			lobbyData[i].setFont("Consolas");
			add(lobbyData[i]);
		}

		for (int i = 0; i<lobbyBoxes.length; i++) {
			lobbyBoxes[i].setColor(Color.WHITE);
			add(lobbyBoxes[i]);
		}
	}

	/**
	 * Sets the color and fonts of the labels and buttons on the lobby page
	 */
	private void lobbyButtonSetup(GLabel[] lobbyButtonLabels, GRect[] lobbyButtons) {

		for (int i = 0; i<lobbyButtonLabels.length; i++) {
			lobbyButtonLabels[i].setColor(Color.WHITE);
			lobbyButtonLabels[i].setFont("Consolas-bold");
			add(lobbyButtonLabels[i]);
		}

		for (int i = 0; i<lobbyButtons.length; i++) {
			lobbyButtons[i].setColor(Color.WHITE);
			add(lobbyButtons[i]);
		}
	}

	private void playerListSetup(List<String> playerList, List<Boolean> readyList, GRect playerBox, int fontSize, double radius) {
		if (playerList.size() >= 1) {
			player1.setLabel(playerList.get(0));
			player1.setColor(Color.WHITE);
			player1.setFont("Consolas");

			status1.setFilled(true);
			if (readyList.get(0) == true) {
				status1.setColor(Color.GREEN);
			}
			else {
				status1.setColor(Color.RED);
			}

			percentLabel(player1, fontSize);
			percentObjRel(player1,2,0,playerBox,true);

			percentObjSize(status1,radius/16,radius/9,null);
			percentObjRel(status1,90,2,playerBox,true);
		}
		if (playerList.size() >= 2) {
			player2.setLabel(playerList.get(1));
			player2.setColor(Color.WHITE);
			player2.setFont("Consolas");

			status2.setFilled(true);
			if (readyList.get(1) == true) {
				status2.setColor(Color.GREEN);
			}
			else {
				status2.setColor(Color.RED);
			}

			percentLabel(player2, fontSize);
			percentObjRel(player2,2,10,playerBox,true);

			percentObjSize(status2,radius/16,radius/9,null);
			percentObjRel(status2,90,12,playerBox,true);
		}
		if (playerList.size() >= 3) {
			player3.setLabel(playerList.get(2));
			player3.setColor(Color.WHITE);
			player3.setFont("Consolas");

			status3.setFilled(true);
			if (readyList.get(2) == true) {
				status3.setColor(Color.GREEN);
			}
			else {
				status3.setColor(Color.RED);
			}

			percentLabel(player3, fontSize);
			percentObjRel(player3,2,20,playerBox,true);

			percentObjSize(status3,radius/16,radius/9,null);
			percentObjRel(status3,90,22,playerBox,true);
		}
		if (playerList.size() >= 4) {
			player4.setLabel(playerList.get(3));
			player4.setColor(Color.WHITE);
			player4.setFont("Consolas");

			status4.setFilled(true);
			if (readyList.get(3) == true) {
				status4.setColor(Color.GREEN);
			}
			else {
				status4.setColor(Color.RED);
			}

			percentLabel(player4, fontSize);
			percentObjRel(player4,2,30,playerBox,true);

			percentObjSize(status4,radius/16,radius/9,null);
			percentObjRel(status4,90,32,playerBox,true);
		}
		if (playerList.size() >= 5) {
			player5.setLabel(playerList.get(4));
			player5.setColor(Color.WHITE);
			player5.setFont("Consolas");

			status5.setFilled(true);
			if (readyList.get(4) == true) {
				status5.setColor(Color.GREEN);
			}
			else {
				status5.setColor(Color.RED);
			}

			percentLabel(player5, fontSize);
			percentObjRel(player5,2,40,playerBox,true);

			percentObjSize(status5,radius/16,radius/9,null);
			percentObjRel(status5,90,42,playerBox,true);
		}
		if (playerList.size() >= 6) {
			player6.setLabel(playerList.get(5));
			player6.setColor(Color.WHITE);
			player6.setFont("Consolas");

			status6.setFilled(true);
			if (readyList.get(5) == true) {
				status6.setColor(Color.GREEN);
			}
			else {
				status6.setColor(Color.RED);
			}

			percentLabel(player6, fontSize);
			percentObjRel(player6,2,50,playerBox,true);

			percentObjSize(status6,radius/16,radius/9,null);
			percentObjRel(status6,90,52,playerBox,true);
		}
		if (playerList.size() >= 7) {
			player7.setLabel(playerList.get(6));
			player7.setColor(Color.WHITE);
			player7.setFont("Consolas");

			status7.setFilled(true);
			if (readyList.get(6) == true) {
				status7.setColor(Color.GREEN);
			}
			else {
				status7.setColor(Color.RED);
			}

			percentLabel(player7, fontSize);
			percentObjRel(player7,2,60,playerBox,true);

			percentObjSize(status7,radius/16,radius/9,null);
			percentObjRel(status7,90,62,playerBox,true);
		}
		if (playerList.size() >= 8) {
			player8.setLabel(playerList.get(7));
			player8.setColor(Color.WHITE);
			player8.setFont("Consolas");

			status8.setFilled(true);
			if (readyList.get(7) == true) {
				status8.setColor(Color.GREEN);
			}
			else {
				status8.setColor(Color.RED);
			}

			percentLabel(player8, fontSize);
			percentObjRel(player8,2,70,playerBox,true);

			percentObjSize(status8,radius/16,radius/9,null);
			percentObjRel(status8,90,72,playerBox,true);
		}
	}
}