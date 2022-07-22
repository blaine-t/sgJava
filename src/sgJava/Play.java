package sgJava;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.io.IODialog;

public class Play extends Options {

	private static final long serialVersionUID = -7807190118393440137L;

	public void drawPlay() {

		// Connect to websocket and authenticate
		Sockets.connect();
		Sockets.auth();

		// Initializes event listeners

		// Checks the in game time
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

		// Initializes the dialog box
		IODialog dialog = new IODialog();

		// Writes the title
		GLabel playTitle = new GLabel("PLAY");

		playTitle.setColor(Color.WHITE);
		playTitle.setFont("IMPACT");
		add(playTitle);

		// Draws the bounding box on the screen
		GRoundRect boundingBox = new GRoundRect(0, 0);

		boundingBox.setColor(Color.WHITE);
		add(boundingBox);

		// Create back button
		GLabel backLabel = new GLabel("Back");
		GRoundRect backButton = new GRoundRect(0,0,0,0);

		GLabel hostLabel = new GLabel("HOST");
		GRoundRect hostButton = new GRoundRect(0,0,0,0);

		GLabel joinLabel = new GLabel("JOIN");
		GRoundRect joinButton = new GRoundRect(0,0,0,0);

		GLabel[] playLabels = {backLabel,hostLabel,joinLabel};
		GRect[] playButtons = {backButton,hostButton,joinButton};

		playButtonsSetup(playLabels,playButtons);

		// Draws the input boxes and their labels
		GLabel codeLabel = new GLabel("Code:");
		GRect codeBox = new GRect(0,0,0,0);
		GLabel codeData = new GLabel(Integer.toString(Settings.setting.getInt("code")));

		GRect nameBox = new GRect(0,0,0,0);
		GLabel nameData = new GLabel(Settings.setting.getString("friendly"));

		// Sets up all of the lables and boxes

		codeLabel.setColor(Color.WHITE);
		codeLabel.setFont("Consolas-bold");
		add(codeLabel);

		GRect[] playBoxes = {codeBox,nameBox};
		GLabel[] playData = {codeData,nameData};

		playMenuSetup(playBoxes,playData);

		// Sets padding and font size of all the title buttons
		final int playLabelsFontSize = 64;
		final int playDataFontSize = 56;
		final int playButtonFontSize = 64;
		final int playButtonPadding = 20;
		final int playBackButtonFontSize = 48;
		final int playBackButtonPadding = 10;

		while (Settings.setting.get("screen") == "play") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size

				// Bounding box dynamic size and location
				percentObjSize(boundingBox, 60, 30, null);
				percentObjRel(boundingBox,50,50,null,false);

				// Title dynamic size and location
				percentLabel(playTitle, 96);
				percentObjRel(playTitle, 50, -20, boundingBox, false);

				// Back button dynamic size and location
				percentLabel(backLabel, playBackButtonFontSize);
				rectLabel(backLabel, backButton, 50, 125, playBackButtonPadding, boundingBox);

				// Entry boxes dynamic size and location
				percentObjSize(codeBox, 20, 25, boundingBox);
				percentObjSize(nameBox, 45, 25, boundingBox);

				percentObjRel(codeBox,75,20,boundingBox,true);
				percentObjRel(nameBox,5,20,boundingBox,true);

				// Labels dynamic font and location
				percentLabel(codeLabel, playLabelsFontSize);

				percentObjRel(codeLabel,-100,0,codeBox,true);

				// Entry text dynamic font and location
				percentLabel(codeData, playDataFontSize);
				percentLabel(nameData, playDataFontSize);

				percentObjRel(codeData,5,10,codeBox,true);
				percentObjRel(nameData,5,10,nameBox,true);

				// Host button drawing

				percentLabel(hostLabel, playButtonFontSize);
				rectLabel(hostLabel, hostButton, 20, 80, playButtonPadding, boundingBox);

				// Join button drawing

				percentLabel(joinLabel, playButtonFontSize);
				rectLabel(joinLabel, joinButton, 80, 80, playButtonPadding, boundingBox);

				// Update the variables to be correct
				oldWidth = width;
				oldHeight = height;
				forceUpdate = false;

			}

			if (mousePress) {
				mousePress = false;

				GObject object = getElementAt(mouseX,mouseY); //Grab object at mouse location

				if (object != null) {

					if (object == backButton) {
						Settings.updateString("screen", "title");
					}
					else if (object == codeBox) {
						// Prompt user for URL and store it and show it
						int code = dialog.readInt("Join code?");
						Settings.updateInt("code",code);
						codeData.setLabel(Integer.toString(code));
					}
					else if (object == nameBox) {
						// Prompt user for friendly and store it and show it
						String name = dialog.readLine("What username do you want to use?");
						Settings.updateString("friendly",name);
						nameData.setLabel(name);
					}
					// TODO: Add better logic to handle errors
					else if (object == hostButton) {
						Sockets.host();
						Settings.updateString("screen", "hostLobby");
					}
					else if (object == joinButton) {
						Sockets.join();
						Settings.updateString("screen", "joinLobby");
					}
				}
			}
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}


	/**
	 * Sets the color and fonts of the input boxes on the play page
	 */
	private void playMenuSetup(GRect[] playBoxes, GLabel[] playData) {

		for (int i = 0; i<playData.length; i++) {
			playData[i].setColor(Color.WHITE);
			playData[i].setFont("Consolas");
			add(playData[i]);
		}

		for (int i = 0; i<playBoxes.length; i++) {
			playBoxes[i].setColor(Color.WHITE);
			add(playBoxes[i]);
		}
	}

	/**
	 * Sets the color and fonts of the buttons on the play page
	 */
	private void playButtonsSetup(GLabel[] playLabels, GRect[] playButtons) {
		for (int i = 0; i<playLabels.length; i++) {
			playLabels[i].setColor(Color.WHITE);
			playLabels[i].setFont("Consolas-bold");
			add(playLabels[i]);
		}

		for (int i = 0; i<playButtons.length; i++) {
			playButtons[i].setColor(Color.WHITE);
			add(playButtons[i]);
		}
	}
}