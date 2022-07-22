package sgJava;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.io.IODialog;

public class Game extends Lobby {

	// Fix stupid eclipse error
	private static final long serialVersionUID = 3472877151586754461L;

	// How far the left UI elements of the screen should take up of the canvas
	private static final double LEFT_PERCENT_WIDTH = 20;
	private static final double LEFT_PERCENT_X1 = 0;
	private static final double LEFT_PERCENT_X2 = LEFT_PERCENT_X1 + LEFT_PERCENT_WIDTH;

	// How far the right UI elements of the screen should take up of the canvas
	private static final double RIGHT_PERCENT_WIDTH = 20;
	private static final double RIGHT_PERCENT_X1 = 80;
	//private static final double RIGHT_PERCENT_X2 = RIGHT_PERCENT_X1 + RIGHT_PERCENT_WIDTH;

	// How far the stocks on the left UI panel should go down and the location of it
	private static final double LEFT_STOCKS_HEIGHT = 70;
	private static final double LEFT_STOCKS_Y1 = 0;
	//private static final double LEFT_STOCKS_Y2 = LEFT_STOCKS_Y1 + LEFT_STOCKS_HEIGHT;

	// How far the money on the left UI panel should go down and the location of it
	private static final double LEFT_BALANCE_HEIGHT = 20;
	private static final double LEFT_BALANCE_Y1 = 70;
	//private static final double LEFT_BALANCE_Y2 = LEFT_BALANCE_Y1 + LEFT_BALANCE_HEIGHT;

	// How far the clock on the right UI panel should go down and the location of it
	private static final double RIGHT_CLOCK_HEIGHT = 10;
	private static final double RIGHT_CLOCK_Y1 = 0;
	//private static final double RIGHT_CLOCK_Y2 = RIGHT_CLOCK_Y1 + RIGHT_CLOCK_HEIGHT;

	// How far the stock history on the right UI panel should go down and the location of it
	private static final double RIGHT_HISTORY_HEIGHT = 80;
	private static final double RIGHT_HISTORY_Y1 = 10;
	//private static final double RIGHT_HISTORY_Y2 = RIGHT_HISTORY_Y1 + RIGHT_HISTORY_HEIGHT;

	// How far the bar on the bottom should go down and the location of it
	private static final double BOTTOM_BAR_HEIGHT = 10;
	private static final double BOTTOM_BAR_Y1 = 90;
	//private static final double BOTTOM_BAR_Y2 = BOTTOM_BAR_Y1 + BOTTOM_BAR_HEIGHT;

	// How far the bar on the bottom should go across and the location of it
	private static final double BOTTOM_BAR_WIDTH = 100;
	private static final double BOTTOM_BAR_X1 = 0;
	//private static final double BOTTOM_BAR_X2 = BOTTOM_BAR_Y1 + BOTTOM_BAR_HEIGHT;

	// How far the graph should go down and the location of it
	private static final double CENTER_GRAPH_HEIGHT = 100-BOTTOM_BAR_HEIGHT;
	private static final double CENTER_GRAPH_Y1 = 0;
	//private static final double CENTER_GRAPH_Y2 = CENTER_GRAPH_Y1 + CENTER_GRAPH_HEIGHT;

	// How wide the graph should be and the location of it
	private static final double CENTER_GRAPH_WIDTH = 100-LEFT_PERCENT_WIDTH-RIGHT_PERCENT_WIDTH;
	private static final double CENTER_GRAPH_X1 = LEFT_PERCENT_X2;
	//private static final double CENTER_GRAPH_X2 = CENTER_GRAPH_X1 + CENTER_GRAPH_WIDTH;

	// Amount of stocks available to the user
	private static final int STOCKS = 10;

	// Amount of graph boxes and lines to draw
	private static final int GRAPH_LINES = 10;

	// Amount of history to log to GUI
	private static final int HISTORY_LENGTH = 20;

	// Amount of pixel padding on the buy and sell button
	private static final int PADDING = 10;

	// How wide and long the dotted lines are and the location of it
	private static final double CENTER_DOTLINE_WIDTH = CENTER_GRAPH_WIDTH/GRAPH_LINES/4;
	private static final double CENTER_DOTLINE_HEIGHT = 0.5;

	// Size of buy and sell button font at 1080p
	private static final int BUTTON_FONT_SIZE = 50;

	// Size of small UI fonts at 1080p
	private static final int SMALL_FONT_SIZE = 30;

	// Size of small UI fonts at 1080p
	private static final int MEDIUM_FONT_SIZE = 40;

	// Size of small UI fonts at 1080p
	private static final int LARGE_FONT_SIZE = 60;
	
	// Multiplier in which to multiply the selection box so it is highlighting the right stock
	private static int SELECTION_MULTIPLIER = 0;
	
	// Boolean that forces the graph to redraw even without resolution change
	private static boolean forceGraph = false;

	public void drawGame() {

		// START OF VARIABLE DECLARATION

		// LEFT SIDE OF SCREEN

		// Declare the stocks to hold the stocks on the left side of the screen
		GRect[] stocks = new GRect[STOCKS];

		// Declare the bounding box for stocks
		GRect stocksBox = new GRect(0,0,0,0);

		// Declare the labels to display the stocks on the left side of the screen
		GLabel[] tickers = new GLabel[STOCKS];

		// Declare the labels to display the stocks percent change on the left side of the screen
		GLabel[] percents = new GLabel[STOCKS];

		// Declare the balances labels
		GLabel[] balances = new GLabel[4];

		// Declare the bounding box for balances
		GRect balancesBox = new GRect(0,0,0,0);

		// CENTER OF SCREEN

		// Declare the bars in the back of the graph for the alternating colors
		GRect[] bars = new GRect[GRAPH_LINES];

		// Declare the line for the graph
		GPolygon[] lines = new GPolygon[GRAPH_LINES];

		// Declare the dotted line for the starting price of the day
		GRect[] dotLines = new GRect[GRAPH_LINES];

		// Declare the bounding box for graph
		GRect graphBox = new GRect(0,0,0,0);

		// RIGHT SIDE OF SCREEN

		// Declares the right digital clock label
		GLabel clock = new GLabel("09:00",0,0);

		// Declares the clock bounding box
		GRect clockBox = new GRect(0,0,0,0);

		// Declares the purchase history labels
		GLabel[] history = new GLabel[HISTORY_LENGTH];

		// Declares the history bounding box
		GRect historyBox = new GRect(0,0,0,0);

		// BOTTOM OF SCREEN

		// Declares the buy button
		GRect buyButton = new GRect(0,0,0,0);
		GLabel buyLabel = new GLabel("BUY",0,0);

		// Declares the sell button
		GRect sellButton = new GRect(0,0,0,0);
		GLabel sellLabel = new GLabel("SELL",0,0);

		// Declares bottom bounding box
		GRect bottomBox = new GRect(0,0,0,0);

		// Declares bottom label
		GLabel bottomLabel = new GLabel("Shares: 0 ($0) Average: $0.00",0,0);
		
		// Declares the selecting box for stocks
		GRect selectionBox = new GRect(0,0,0,0);

		// Custom color variables
		Color lightBar = new Color(70,70,70);
		Color darkBar = new Color(45,45,45);
		Color dashColor = new Color(217,217,217);
		Color selectColor = new Color(60,60,60);

		// END OF VARIABLE DECLARATION

		// TEMP FILES
		String[] tickerList = {"PAR","FOT","SFT","IOG","MTA","TLA","NVD","NFX","PYP","ETY"};
		String[] balanceList = {"Cash: $","Net Worth: $","Daily Profit: $","Profit: $"};

		// START OF INITIAL CONFIGURATION

		// Initializes dialog box
		IODialog dialog = new IODialog();

		// CENTER OF SCREEN

		// Setup bars for the graph with alternating colors
		for (int i = 0; i < bars.length; i++) {
			GRect bar = new GRect(0,0,0,0);
			bars[i] = bar;
			setupBox(bar);
			bar.setFilled(true);
			if (i % 2 == 0) {
				bar.setColor(lightBar);
			}
			else {
				bar.setColor(darkBar);
			}
		}

		// Setup lines for the graph
		for (int i = 0; i < lines.length; i++) {
			//TODO: MAKE LINES
		}

		// Setup dot lines for the graph
		for (int i = 0; i < dotLines.length; i++) {
			GRect dotLine = new GRect(0,0,0,0);
			dotLines[i] = dotLine;
			dotLine.setColor(dashColor);
			dotLine.setFilled(true);
			add(dotLine);
		}

		setupBox(graphBox);

		// LEFT SIDE OF SCREEN

		// Setup selection box
		selectionBox.setColor(selectColor);
		selectionBox.setFilled(true);
		add(selectionBox);
		
		// Setup stocks boxes
		for (int i = 0; i < stocks.length; i++) {
			GRect stock = new GRect(0,0,0,0);
			stocks[i] = stock;
			setupBox(stock);
		}

		setupBox(stocksBox);

		for (int i = 0; i < tickers.length; i++) {
			GLabel ticker = new GLabel(i + ". " + tickerList[i],0,0);
			tickers[i] = ticker;
			setupLabel(ticker);
		}

		for (int i = 0; i < percents.length; i++) {
			GLabel percent = new GLabel("",0,0);
			percents[i] = percent;
			setupLabel(percent);
		}

		// Setup balance strings
		for (int i = 0; i < balances.length; i++) {
			GLabel balance = new GLabel(balanceList[i],0,0);
			balances[i] = balance;
			setupLabel(balance);
		}

		setupBox(balancesBox);

		// RIGHT SIDE OF SCREEN

		setupLabel(clock);
		setupBox(clockBox);

		// Setup history lines
		for (int i = 0; i < history.length; i++) {
			GLabel entry = new GLabel(Integer.toString(i),0,0);
			history[i] = entry;
			setupLabel(entry);
		}

		setupBox(historyBox);

		// BOTTOM OF SCREEN

		setupBox(bottomBox);
		setupLabel(bottomLabel);
		
		setupButton(buyLabel,buyButton);
		setupButton(sellLabel,sellButton);

		// END OF INITIAL CONFIGURATION

		// START OF UPDATE LOOP

		while(Settings.setting.get("screen") == "game") {

			// Update width and height of the canvas
			width = getWidth();
			height = getHeight();

			if (width != oldWidth || height != oldHeight || forceUpdate) { // Only run updates when canvas changes size
				
				// Disable force update
				forceUpdate = false;
				
				// Update the graph
				forceGraph = true;
				
				// LEFT SIDE OF SCREEN

				// Sets location and size of stocks
				percentObjSize(stocksBox, LEFT_PERCENT_WIDTH, LEFT_STOCKS_HEIGHT, null);
				percentObjRel(stocksBox, LEFT_PERCENT_X1, LEFT_STOCKS_Y1, null, true);

				for (int i = 0; i < stocks.length; i++) {
					percentObjSize(stocks[i], LEFT_PERCENT_WIDTH, LEFT_STOCKS_HEIGHT/stocks.length, null);
					percentObjRel(stocks[i], LEFT_PERCENT_X1,LEFT_STOCKS_Y1+(LEFT_STOCKS_HEIGHT*i/stocks.length), null, true);
				}

				for (int i = 0; i < tickers.length; i++) {
					percentLabel(tickers[i], LARGE_FONT_SIZE);
					percentObjRel(tickers[i], LEFT_PERCENT_X1,LEFT_STOCKS_Y1+(LEFT_STOCKS_HEIGHT*i/tickers.length), null, true);
				}

				// Sets location and size of balances
				percentObjSize(balancesBox, LEFT_PERCENT_WIDTH, LEFT_BALANCE_HEIGHT, null);
				percentObjRel(balancesBox, LEFT_PERCENT_X1, LEFT_BALANCE_Y1, null, true);

				for (int i = 0; i < balances.length; i++) {
					percentLabel(balances[i], MEDIUM_FONT_SIZE);
					percentObjRel(balances[i], LEFT_PERCENT_X1,LEFT_BALANCE_Y1+(LEFT_BALANCE_HEIGHT*i/balances.length), null, true);
				}

				// RIGHT OF SCREEN

				// Sets location and size of clock
				percentObjSize(clockBox, RIGHT_PERCENT_WIDTH, RIGHT_CLOCK_HEIGHT, null);
				percentObjRel(clockBox, RIGHT_PERCENT_X1, RIGHT_CLOCK_Y1, null, true);

				percentLabel(clock, LARGE_FONT_SIZE);
				percentObjRel(clock, 50, 50, clockBox, false);
				clock.setLabel(time);

				// Sets the location and size of history
				percentObjSize(historyBox, RIGHT_PERCENT_WIDTH, RIGHT_HISTORY_HEIGHT, null);
				percentObjRel(historyBox, RIGHT_PERCENT_X1, RIGHT_HISTORY_Y1, null, true);

				for (int i = 0; i < history.length; i++) {
					percentLabel(history[i], SMALL_FONT_SIZE);
					percentObjRel(history[i], RIGHT_PERCENT_X1,RIGHT_HISTORY_Y1+(RIGHT_HISTORY_HEIGHT*i/history.length), null, true);
				}

				// CENTER OF SCREEN

				for (int i = 0; i < bars.length; i++) {
					percentObjSize(bars[i], CENTER_GRAPH_WIDTH/bars.length,CENTER_GRAPH_HEIGHT,null);
					percentObjRel(bars[i], CENTER_GRAPH_X1+(CENTER_GRAPH_WIDTH*i/bars.length),CENTER_GRAPH_Y1,null,true);
				}

				for (int i = 0; i < dotLines.length; i++) {
					percentObjSize(dotLines[i], CENTER_DOTLINE_WIDTH,CENTER_DOTLINE_HEIGHT,null);
					percentObjRel(dotLines[i], 50, 50, bars[i],false);
				}

				// BOTTOM OF SCREEN

				// Sets up location and size of bottom box
				percentObjSize(bottomBox, BOTTOM_BAR_WIDTH, BOTTOM_BAR_HEIGHT, null);
				percentObjRel(bottomBox, BOTTOM_BAR_X1, BOTTOM_BAR_Y1, null, true);

				// Sets up location and size of buy button
				percentLabel(buyLabel, BUTTON_FONT_SIZE);
				rectLabel(buyLabel, buyButton, 5, 50, PADDING, bottomBox);

				// Sets up location and size of sell button
				percentLabel(sellLabel, BUTTON_FONT_SIZE);
				rectLabel(sellLabel, sellButton, 200, 50, PADDING, buyButton);

				// Sets up location and size of bottom label
				percentLabel(bottomLabel, LARGE_FONT_SIZE);
				percentObjRel(bottomLabel, 125, 0, sellButton, true);
			}
			
			// If resolution changes or stock changes redraw the graph
			if (forceGraph) {
				
				// Disable graph update
				forceGraph = false;
				
				//TODO: REDRAW THE GRAPH
				
				// Selector
				percentObjSize(selectionBox, LEFT_PERCENT_WIDTH, LEFT_STOCKS_HEIGHT/stocks.length, null);
				percentObjRel(selectionBox, LEFT_PERCENT_X1,LEFT_STOCKS_Y1+(LEFT_STOCKS_HEIGHT*SELECTION_MULTIPLIER/stocks.length), null, true);
				
				
				// Bottom label
				bottomLabel.setLabel(""); //TODO: MAKE LABEL ACTUALLY HAVE DATA
				
				// Graph
				//TODO: GRAPH DRAWING
			}
			// START MOUSE EVENTS

			if (mousePress) {
				mousePress = false;

				GObject object = getElementAt(mouseX,mouseY); //Grab object at mouse location

				if (object != null) {

					if (object == buyButton) {
						Settings.updateInt("amount",dialog.readInt("BUY how many shares?"));
						Sockets.buy();
					}
					else if (object == sellButton) {
						Settings.updateInt("amount",dialog.readInt("SELL how many shares?"));
						Sockets.sell();
					}
				}
			}
			if (keyPress) {
				keyPress = false;
				
				switch(key) {
				case "0":
					setStock(tickers[0],0);
					break;
				case "1":
					setStock(tickers[1],1);
					break;
				case "2":
					setStock(tickers[2],2);
					break;
				case "3":
					setStock(tickers[3],3);
					break;
				case "4":
					setStock(tickers[4],4);
					break;
				case "5":
					setStock(tickers[5],5);
					break;
				case "6":
					setStock(tickers[6],6);
					break;
				case "7":
					setStock(tickers[7],7);
					break;
				case "8":
					setStock(tickers[8],8);
					break;
				case "9":
					setStock(tickers[9],9);
					break;
				case "B":
					mousePress = true;
					println(buyButton.getX());
					mouseX = (int) buyButton.getX()+1;
					mouseY = (int) buyButton.getY()+1;
					break;
				case "S":
					mousePress = true;
					mouseX = (int) sellButton.getX()+1;
					mouseY = (int) buyButton.getY()+1;
					break;
				}
			}
			
			oldWidth = width;
			oldHeight = height;
			
			pause(REFRESH);
		}

		// END OF UPDATE LOOP

	}


	private void setupButton (GLabel label, GRect box) {
		setupLabel(label);
		setupBox(box);
	}


	private void setupLabel (GLabel label) {
		label.setColor(Color.WHITE);
		label.setFont("Consolas");
		add(label);
	}


	private void setupBox (GRect box) {
		box.setColor(Color.WHITE);
		add(box);
	}
	
	
	private void setStock(GLabel ticker, int i) {
		Settings.updateString("ticker", ticker.getLabel());
		SELECTION_MULTIPLIER = i;
		forceGraph = true;
	}
}