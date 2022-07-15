import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import acm.graphics.*;
import acm.program.*;

public class title extends GraphicsProgram {
    private static final long serialVersionUID = 281758316991282313L;
        
    public static void main(String[] args) throws URISyntaxException {
    Socket socket = IO.socket("http://localhost:8080");
    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

    	@Override
        public void call(Object... args) {
        System.out.println("Connected to server!");
         }
});
            socket.on("time", new Emitter.Listener() {
                
                @Override
                public void call(Object... args) {
                    System.out.println("Time: " + args[0]);
                }
                
            });
            socket.connect();
        }
    
    private void percentObject(GObject object1, double posX, double posY, double width, double height, boolean text, boolean pixel) {
        //This function allows to set position with *percents* instead of coordinates.
        double posYFixed;
        double posXFixed;
        if (!pixel) {
            posXFixed = (getWidth()*(posX/100))-(object1.getWidth()*0.5);
            if (text) {
                posYFixed = (getHeight()*(posY/100))+(object1.getHeight()*0.5);
            } else {
                posYFixed = (getHeight()*(posY/100))-(object1.getHeight()*0.5);
            }
        } else {
            posXFixed = posX+object1.getX();
            if (text) {
                posYFixed = posY-object1.getY();
            } else {
                posYFixed = posY+object1.getY();
            }
        }
        
        if (!text) {
            ((GRect) object1).setSize(width,height);
        }
        object1.setLocation(posXFixed, posYFixed);
    }
    private void percentObjectRel(GObject object1, GObject objectref, double posX, double posY, double width, double height, boolean text, boolean pixel) {
        double posYFixed;
        double posXFixed;
        if (!pixel) {
            posXFixed = (objectref.getWidth()*(posX/100))-(object1.getWidth()*0.5)+objectref.getX();
            if (text) {
                posYFixed = (objectref.getHeight()*(posY/100))+(object1.getHeight()*0.5)+objectref.getY();
            } else {
                posYFixed = (objectref.getHeight()*(posY/100))-(object1.getHeight()*0.5)+objectref.getY();
            }
        } else {
            posXFixed = posX+objectref.getX();
            if (text) {
                posYFixed = posY-objectref.getY();
            } else {
                posYFixed = posY+objectref.getY();
            }
        }
        
        if (!text) {
            ((GRect) object1).setSize(width,height);
        }
        object1.setLocation(posXFixed, posYFixed);
    }
}