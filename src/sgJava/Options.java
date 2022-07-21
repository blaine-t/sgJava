package sgJava;

import java.awt.Color;

import acm.io.IODialog;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;

public class Options extends Title {

	private static final long serialVersionUID = 3696910690026843656L;

	public void drawOptions() {
		
		// Initializes dialog box
		IODialog dialog = new IODialog();
		
		// Writes the title
		GLabel optionsTitle = new GLabel("OPTIONS");

		optionsTitle.setColor(Color.WHITE);
		optionsTitle.setFont("IMPACT");
		add(optionsTitle);

		// Draws the bounding box on the screen
		GRoundRect boundingBox = new GRoundRect(0, 0);
		
		boundingBox.setColor(Color.WHITE);
		add(boundingBox);
		
		// Create back button
		GLabel backLabel = new GLabel("Back");
		GRoundRect backButton = new GRoundRect(0,0,0,0);
		
		backLabel.setColor(Color.WHITE);
		backLabel.setFont("Consolas-bold");
		add(backLabel);
		
		backButton.setColor(Color.WHITE);
		add(backButton);
		
		// Draws the input boxes and their labels
		GLabel serverLabel = new GLabel("Server:");
		GRect serverBox = new GRect(0,0,0,0);
		GLabel serverData = new GLabel(Settings.setting.getString("URL"));

		GLabel authLabel = new GLabel("Auth:");
		GRect authBox = new GRect(0,0,0,0);
		GLabel authData = new GLabel(Settings.setting.getString("token"));

		GLabel nameLabel = new GLabel("Name:");
		GRect nameBox = new GRect(0,0,0,0);
		GLabel nameData = new GLabel(Settings.setting.getString("friendly"));

		GLabel[] optionLabels = {serverLabel,authLabel,nameLabel};
		GRect[] optionBoxes = {serverBox,authBox,nameBox};
		GLabel[] optionData = {serverData,authData,nameData};

		optionMenuSetup(optionLabels,optionBoxes,optionData);

		// Sets padding and font size of all the title buttons
		final int optionsLabelsFontSize = 64;
		final int optionsDataFontSize = 48;
		final int optionBackButtonFontSize = 48;
		final int optionBackButtonPadding = 10;

		while (Settings.setting.get("screen") == "options") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size

				// Bounding box dynamic size and location
				percentObjSize(boundingBox, 60, 60, null);
				percentObjRel(boundingBox,50,50,null,false);
				
				// Title dynamic size and location
				percentLabel(optionsTitle, 96);
				percentObjRel(optionsTitle, 50, -10, boundingBox, false);
				
				// Back button dynamic size and location
				percentLabel(backLabel, optionBackButtonFontSize);
				rectLabel(backLabel, backButton, 50, 115, optionBackButtonPadding, boundingBox);
				
				// Entry boxes dynamic size and location
				percentObjSize(serverBox, 60, 15, boundingBox);
				percentObjSize(authBox, 60, 15, boundingBox);
				percentObjSize(nameBox, 60, 15, boundingBox);
				
				percentObjRel(serverBox,32,10,boundingBox,true);
				percentObjRel(authBox,32,40,boundingBox,true);
				percentObjRel(nameBox,32,70,boundingBox,true);
				
				// Labels dynamic font and location
				percentLabel(serverLabel, optionsLabelsFontSize);
				percentLabel(authLabel, optionsLabelsFontSize);
				percentLabel(nameLabel, optionsLabelsFontSize);
				
				percentObjRel(serverLabel,-50,0,serverBox,true);
				percentObjRel(authLabel,-50,0,authBox,true);
				percentObjRel(nameLabel,-50,0,nameBox,true);
				
				// Entry text dynamic font and location
				percentLabel(serverData, optionsDataFontSize);
				percentLabel(authData, optionsDataFontSize);
				percentLabel(nameData, optionsDataFontSize);
				
				percentObjRel(serverData,5,17.5,serverBox,true);
				percentObjRel(authData,5,17.5,authBox,true);
				percentObjRel(nameData,5,17.5,nameBox,true);

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
						println("title");
						Settings.updateString("screen", "title");
					}
					else if (object == serverBox) {
						println("server");
						// Prompt user for URL and store it and show it
						String serverAddress = dialog.readLine("What server address do you want to use?");
						Settings.updateString("URL",serverAddress);
						serverData.setLabel(serverAddress);
					}
					else if (object == authBox) {
						println("auth");
						// Prompt user for auth token and store it and show it
						String authToken = dialog.readLine("What auth token do you want to use?");
						Settings.updateString("token",authToken);
						authData.setLabel(authToken);
					}
					else if (object == nameBox) {
						println("name");
						// Prompt user for auth token and store it and show it
						String name = dialog.readLine("What username do you want to use?");
						Settings.updateString("friendly",name);
						nameData.setLabel(name);
					}
				}
			}
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}

	
	/**
	 * Sets the color and fonts of the labels and input boxes on the options page
	 */
	private void optionMenuSetup(GLabel[] optionLabels, GRect[] optionBoxes, GLabel[] optionData) {
		for (int i = 0; i<optionLabels.length; i++) {
			optionLabels[i].setColor(Color.WHITE);
			optionLabels[i].setFont("Consolas-bold");
			add(optionLabels[i]);
		}

		for (int i = 0; i<optionData.length; i++) {
			optionData[i].setColor(Color.WHITE);
			optionData[i].setFont("Consolas");
			add(optionData[i]);
		}
		
		for (int i = 0; i<optionBoxes.length; i++) {
			optionBoxes[i].setColor(Color.WHITE);
			add(optionBoxes[i]);
		}
	}
}