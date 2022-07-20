package sgJava;

import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

import java.net.URI;

import io.socket.client.IO;
import io.socket.client.Socket;

public class Sockets {

	static Socket socket;
	
	/**
	 * Initialize a connection to the server
	 */
	public static void connect() {
		URI serverUri = URI.create((String) Settings.setting.get("URL"));

		IO.Options options = IO.Options.builder()
				.setTransports(new String[] { WebSocket.NAME })
				.setRememberUpgrade(true)
				.build();

		socket = IO.socket(serverUri,options);
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Connected to server!");
			}
		});
		socket.connect();
		System.out.println("Sending a connection request");
	}
	
	
	/**
	 * Sends an auth packet to the server to authenticate the user
	 */
	public static void auth() {
		socket.emit("auth", Packets.authPacket());
		System.out.println("Sending an auth request");
	}
	
	
	/**
	 * Sends a host packet to the server to access the server if the user can host a lobby
	 */
	public static void host() {
		socket.emit("host", Settings.setting.get("token"));
		System.out.println("Sending a host request");
	}
	
	
	/**
	 * Sends a start packet to the server to ask if the user can start the lobby
	 */
	public static void start() { 
		socket.emit("start", Packets.startPacket());
		System.out.println("Sending a start request");
	}
	
	
	/**
	 * Sends a ready packet to the server to ready the user in game
	 */
	public static void ready() { 
		socket.emit("ready", Settings.setting.get("token"));
		System.out.println("Sending a ready request");
	}
	
	
	/**
	 * Sends an unready packet to the server to unready the user in game
	 */
	public static void unready() { 
		socket.emit("unready", Settings.setting.get("token"));
		System.out.println("Sending an unready request");
	}
	
	/**
	 * Sends a leave packet to the server to authenticate the user
	 */
	public static void leave() { 
		socket.emit("leave", Settings.setting.get("token"));
		System.out.println("Sending a leave request");
	}
	
	
	/**
	 * Sends a buy packet to the server to buy a stock if the user is allowed
	 */
	public static void buy() {
		socket.emit("buy", Packets.buyPacket());
		System.out.println("Sending a buy request");
	}
	
	
	/**
	 * Sends a sell packet to the server to sell a stock if the user is allowed
	 */
	public static void sell() {
		socket.emit("sell", Packets.sellPacket());
		System.out.println("Sending a sell request");
	}
	
	
	/**
	 * Sends a magic packet to the server that crashes it
	 */
	public static void crash() {
		socket.emit("ping", "crash");
		System.out.println("Sending a crash request");
	}

	
	// Event listeners
	
	
	/**
	 * Listens for errors from the server
	 */
	public static void error() {
		socket.on("error", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for successes from the server
	 */
	public static void success() {
		socket.on("success", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for host code emits from the server
	 */
	public static void code() {
		socket.on("code", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for player list from the server
	 */
	public static void players() {
		socket.on("players", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for disband statuses from the server
	 */
	public static void disband() {
		socket.on("disband", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for game events from the server
	 */
	public static void game() {
		socket.on("game", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for date of game from the server
	 */
	public static void date() {
		socket.on("date", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	
	/**
	 * Listens for time of game from the server
	 */
	public static void time() {
		socket.on("time", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
}
