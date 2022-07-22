package sgJava;

// Add color support to set the background color of the canvas
import java.awt.Color;
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
		
		println("X: " + mouseX + " Y: " + mouseY);
	}
}