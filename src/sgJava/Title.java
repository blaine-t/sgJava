package sgJava;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;

public class Title extends Main  {

	private static final long serialVersionUID = 8736023688668374202L;

	public void drawTitle() {

		// Writes the title
		GLabel title1 = new GLabel("STOCK");
		GLabel title2 = new GLabel("GAME");

		GLabel[] titleTitles = {title1,title2};

		titleSetup(titleTitles);

		// Draws the stock line through the screen
		GLine titleLine1 = new GLine(0,0,0,0);
		GLine titleLine2 = new GLine(0,0,0,0);
		GLine titleLine3 = new GLine(0,0,0,0);
		GLine titleLine4 = new GLine(0,0,0,0);
		GLine titleLine5 = new GLine(0,0,0,0);

		GLine[] titleLines = {titleLine1,titleLine2,titleLine3,titleLine4,titleLine5};

		titleLineSetup(titleLines);

		// Draws the buttons
		GLabel playLabel = new GLabel("Play");
		GRoundRect playButton = new GRoundRect(0,0,0,0);

		GLabel optionsLabel = new GLabel("Options");
		GRoundRect optionsButton = new GRoundRect(0,0,0,0);

		GLabel exitLabel = new GLabel("Exit");
		GRoundRect exitButton = new GRoundRect(0,0,0,0);

		GLabel[] titleLabels = {playLabel,optionsLabel,exitLabel};
		GRoundRect[] titleRectangles = {playButton,optionsButton,exitButton};

		titleButtonSetup(titleLabels,titleRectangles);

		// Sets padding and font size of all the title buttons
		final int titleButtonsPadding = 10;
		final int titleButtonsFontSize = 64;

		while (Settings.setting.get("screen") == "title") { // Update the scale objects and check for mouse events

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();
			
			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size

				percentLabel(title1, 96);
				percentLabel(title2, 96);

				percentObjRel(title1, 50, 30, null, false);
				percentObjRel(title2, 50, 10, title1, true);

				percentLine(titleLine1, 20, 59, 44, 35);
				percentLine(titleLine2, 44, 35, 60, 35);
				percentLine(titleLine3, 60, 35, 80, 15);
				percentLine(titleLine4, 80, 15, 78, 16);
				percentLine(titleLine5, 80, 15, 78, 18);

				percentLabel(playLabel, titleButtonsFontSize);
				percentLabel(optionsLabel, titleButtonsFontSize);
				percentLabel(exitLabel, titleButtonsFontSize);

				rectLabel(playLabel, playButton, 50, 60, titleButtonsPadding, null);
				rectLabel(optionsLabel, optionsButton, 50, 200, titleButtonsPadding, playButton);
				rectLabel(exitLabel, exitButton, 50, 200, titleButtonsPadding, optionsButton);
				
				// Update the variables to be correct
				oldWidth = width;
				oldHeight = height;
				forceUpdate = false;

			}
			
			if (mousePress) {
				mousePress = false;
				
				GObject object = getElementAt(mouseX,mouseY); //Grab object at mouse location
				
				if (object != null) {
					
					if (object == playButton) {
						println("play");
						Settings.updateString("screen", "play");
					}
					else if (object == optionsButton) {
						println("options");
						Settings.updateString("screen", "options");
					}
					else if (object == exitButton) {
						println("exit");
						System.exit(0);
					}
				}
			}
			pause(REFRESH);
		}
		removeAll();
		forceUpdate = true;
	}


	/**
	 * Sets the color and font of the title of the title page
	 */
	private void titleSetup(GLabel[] titleTitles) {

		for (int i = 0; i<titleTitles.length; i++) {

			titleTitles[i].setColor(Color.WHITE);
			titleTitles[i].setFont("IMPACT");
			add(titleTitles[i]);
		}
	}


	/**
	 * Sets the color of the lines on the title page
	 */
	private void titleLineSetup(GLine[] titleLines) {

		for (int i = 0; i<titleLines.length; i++) {

			titleLines[i].setColor(Color.GREEN);
			add(titleLines[i]);
		}
	}

	/**
	 * Sets the color and fonts of the buttons on the title page
	 */
	private void titleButtonSetup(GLabel[] titleLabels, GRect[] titleRectangles) {
		for (int i = 0; i<titleLabels.length; i++) {
			titleLabels[i].setColor(Color.WHITE);
			titleLabels[i].setFont("Consolas-bold");
			add(titleLabels[i]);
		}

		for (int i = 0; i<titleRectangles.length; i++) {
			titleRectangles[i].setColor(Color.WHITE);
			add(titleRectangles[i]);
		}
	}
}