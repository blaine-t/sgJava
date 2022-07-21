package sgJava;

// Add JSON support to store settings
import org.json.JSONObject;

public class Settings {
	
	/**
	 * Setup initial setting values so game doesn't break
	 */
	public static void defaults() {
		
		updateString("URL","ws://localhost:25056");
		updateBool("host",false);
		updateBool("ready",false);
		
		updateString("token","12345-ABCDE-00001");
		updateString("friendly","DEFAULT");
		updateInt("code",123456);
		
		updateInt("capital",15000);
		updateInt("length",5);
		
		updateString("ticker","PAR");
		updateInt("amount",0);
		
		updateString("screen","title");
		
		updateBool("ready",false);
	}
	
	// Creates the JSON object to store the values of settings
	static JSONObject setting = new JSONObject();
	
	// Updates a key value pair where the value is a string
	public static void updateString(String key, String value) {
		setting.put(key, value);
	}
	
	// Updates a key value pair where the value is a boolean
	public static void updateBool(String key, Boolean value) {
		setting.put(key, value);
	}
	
	// Updates a key value pair where the value is an integer
	public static void updateInt(String key, int value) {
		setting.put(key, value);
	}
}