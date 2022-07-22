package sgJava;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import hgl.Positioning;

public class Main extends Positioning {

	private static final long serialVersionUID = -8795248987746065207L;

	public static final byte REFRESH = 1000/60;

	// Declare required variables
	static int oldWidth = 0;
	static int oldHeight = 0;
	static int width = 0;
	static int height = 0;
	static boolean forceUpdate = true;
	
	static int mouseX = 0;
	static int mouseY = 0;
	static boolean mousePress = false;
	
	static boolean gameRunning = false;
	
	static Object players;
	
	static GLabel player1 = new GLabel("", 0,0);
	static GOval status1 = new GOval(0,0,0,0);
	static GLabel player2 = new GLabel("", 0,0);
	static GOval status2 = new GOval(0,0,0,0);
	static GLabel player3 = new GLabel("", 0,0);
	static GOval status3 = new GOval(0,0,0,0);
	static GLabel player4 = new GLabel("", 0,0);
	static GOval status4 = new GOval(0,0,0,0);
	static GLabel player5 = new GLabel("", 0,0);
	static GOval status5 = new GOval(0,0,0,0);
	static GLabel player6 = new GLabel("", 0,0);
	static GOval status6 = new GOval(0,0,0,0);
	static GLabel player7 = new GLabel("", 0,0);
	static GOval status7 = new GOval(0,0,0,0);
	static GLabel player8 = new GLabel("", 0,0);
	static GOval status8 = new GOval(0,0,0,0);
	
}