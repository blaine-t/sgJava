package sgJava;

// Add JSON support to craft packets
import org.json.JSONObject;

public class Packets extends Settings {
	
	public static JSONObject authPacket() {
		JSONObject auth = new JSONObject();
		auth.put("token", setting.get("token"));
		auth.put("friendly", setting.get("friendly"));
		auth.put("ready", setting.get("ready"));
		auth.put("host",setting.get("host"));
		System.out.println(auth);
		return auth;
	}
	
	public static JSONObject startPacket() {
		JSONObject start = new JSONObject();
		start.put("token", setting.get("token"));
		start.put("capital", setting.get("capital"));
		start.put("length", setting.get("length"));
		start.put("host",setting.get("host"));
		System.out.println(start);
		return start;
	}
	
}
