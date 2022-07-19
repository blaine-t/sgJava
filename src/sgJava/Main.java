package sgJava;

import java.net.URI;

import acm.graphics.GLabel;
import hgl.*;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

public class Main extends Positioning {
	private static final long serialVersionUID = -8795248987746065207L;
	
	public static Socket connectSocket() {
		URI serverUri = URI.create((String) Settings.setting.get("URL"));

		IO.Options options = IO.Options.builder()
				.setTransports(new String[] { WebSocket.NAME })
				.setRememberUpgrade(true)
				.build();

		Socket socket = IO.socket(serverUri,options);
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Connected to server!");
			}
		});
		socket.connect();
		return(socket);
	}
	
	public static void textDefaults(GLabel label) {
		
	}

}
