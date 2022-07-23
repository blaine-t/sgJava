package sgJava;

// Add color support to set the background color of the canvas
import java.awt.Color;
import java.awt.event.KeyEvent;
// Add mouse support to allow for user mouse input
import java.awt.event.MouseEvent;


public class Init extends Game {

	private static final long serialVersionUID = -1196351898484999418L;

	public void run() {

		// Add mouse and keyboard support
		addMouseListeners();
		addKeyListeners();

		setSize(1280,720); // Set default window to 720p
		setBackground(Color.BLACK); // Sets background to black

		// Initializes default settings
		Settings.defaults();


		while (true) {
			String screen = (String) Settings.setting.get("screen");
			switch(screen) {
			case "title":
				drawTitle();
				break;

			case "options":
				drawOptions();
				break;

			case "play":
				drawPlay();
				break;

			case "hostLobby":
				drawHostLobby();
				break;

			case "joinLobby":
				drawJoinLobby();
				break;

			case "game":
				drawGame();
				break;
			}
		}
	}

	@Override
	public void mousePressed (MouseEvent m) {
		mouseX = m.getX();
		mouseY = m.getY();
		mousePress = true;
	}

	@Override 
	public void keyPressed (KeyEvent e) {
		keyPress = true;
		
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_0: 
			key = "0";
			break;
		case KeyEvent.VK_1: 
			key = "1";
			break;
		case KeyEvent.VK_2: 
			key = "2";
			break;
		case KeyEvent.VK_3: 
			key = "3";
			break;
		case KeyEvent.VK_4: 
			key = "4";
			break;
		case KeyEvent.VK_5: 
			key = "5";
			break;
		case KeyEvent.VK_6: 
			key = "6";
			break;
		case KeyEvent.VK_7: 
			key = "7";
			break;
		case KeyEvent.VK_8: 
			key = "8";
			break;
		case KeyEvent.VK_9: 
			key = "9";
			break;
		case KeyEvent.VK_B: 
			key = "B";
			break;
		case KeyEvent.VK_S: 
			key = "S";
			break;
		case KeyEvent.VK_P: 
			key = "P";
			break;
		case KeyEvent.VK_O: 
			key = "O";
			break;
		case KeyEvent.VK_A: 
			key = "A";
			break;
		case KeyEvent.VK_N: 
			key = "N";
			break;
		case KeyEvent.VK_H: 
			key = "H";
			break;
		case KeyEvent.VK_J: 
			key = "J";
			break;
		case KeyEvent.VK_C: 
			key = "C";
			break;
		case KeyEvent.VK_R: 
			key = "R";
			break;
		case KeyEvent.VK_L: 
			key = "L";
			break;
		case KeyEvent.VK_D: 
			key = "D";
			break;
		default:
			key= "NULL" ;
			break;
		}
	}
}