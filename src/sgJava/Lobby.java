package sgJava;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.io.IODialog;

public class Lobby extends Play {

	private static final long serialVersionUID = 1887619156444000857L;

	public void drawHostLobby() {
		// Testing variables
		int readyAmount = 1;
		int playerAmount = 2;

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
		GLabel playersLabel = new GLabel("Players: " + Integer.toString(readyAmount) + "/" + Integer.toString(playerAmount));

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

		while (Settings.setting.get("screen") == "hostLobby") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

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
						println("leave");
						Settings.updateString("screen", "play");
					}
					else if (object == startButton) {
						Sockets.start();
						println("start");
						Settings.updateString("screen", "game");

					}
					else if (object == readyButton) {
						println("ready");
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
							println("ready");
							Settings.updateBool("ready", true);
						}
					}
					else if (object == capitalBox) {
						println("capital");
						// Prompt user for money and store it and show it
						int capital = dialog.readInt("How much money should users start with?");
						Settings.updateInt("capital",capital);
						capitalData.setLabel("$" + Integer.toString(capital));
					}
					else if (object == daysBox) {
						println("days");
						// Prompt user for days and store it and show it
						int days = dialog.readInt("How many days should the game last?");
						Settings.updateInt("days",days);
						daysData.setLabel(Integer.toString(days));
					}
				}
			}
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}

	public void drawJoinLobby() {
		Sockets.join();
		pause(10000);
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
}