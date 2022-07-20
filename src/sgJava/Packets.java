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
	
	
	public static JSONObject buyPacket() {
		JSONObject buy = new JSONObject();
		buy.put("token", setting.get("token"));
		buy.put("ticker", setting.get("ticker"));
		buy.put("amount", setting.get("amount"));
		System.out.println(buy);
		return buy;
	}
	
	
	public static JSONObject sellPacket() {
		JSONObject sell = new JSONObject();
		sell.put("token", setting.get("token"));
		sell.put("ticker", setting.get("ticker"));
		sell.put("amount", setting.get("amount"));
		System.out.println(sell);
		return sell;
	}	
}