package sgJava;

import io.socket.emitter.Emitter;
import io.socket.client.Socket;

public class Sockets {

	static Socket socket;

	public static void connect() {
		socket = Main.connectSocket();
		System.out.println("Sending a connection request");
	}
	
	public static void auth() {
		socket.emit("auth", Packets.authPacket());
		System.out.println("Sending an auth request");
	}
	
	public static void host() {
		socket.emit("host", Settings.setting.get("token"));
		System.out.println("Sending a host request");
	}
	
	public static void start() { 
		socket.emit("start", Packets.startPacket());
		System.out.println("Sending a start request");
	}
	
	public static void ready() { 
		socket.emit("ready", Settings.setting.get("token"));
		System.out.println("Sending a ready request");
	}
	
	public static void unready() { 
		socket.emit("unready", Settings.setting.get("token"));
		System.out.println("Sending an unready request");
	}
	
	public static void leave() { 
		socket.emit("leave", Settings.setting.get("token"));
		System.out.println("Sending a leave request");
	}
	
	public static void buy() {
		socket.emit("buy", Packets.buyPacket());
		System.out.println("Sending a buy request");
	}
	
	public static void sell() {
		socket.emit("sell", Packets.sellPacket());
		System.out.println("Sending a sell request");
	}
	
	public static void crash() {
		socket.emit("ping", "crash");
		System.out.println("Sending a crash request");
	}

	
	// Event listeners
	public static void time() {
		socket.on("time", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Time:" + args[0]);
			}

		});
	}

	public static void error() {
		socket.on("error", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	public static void success() {
		socket.on("success", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	public static void code() {
		socket.on("code", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	public static void players() {
		socket.on("players", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
	
	public static void disband() {
		socket.on("disband", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println(args[0]);
			}
		});
	}
}
