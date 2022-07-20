package sgJava;

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
	
}