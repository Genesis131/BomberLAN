import java.awt.Color;
import java.io.*;
import java.net.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

public class Client
{
	public static Drawable drawLine(int x, int y, boolean vertical){
        RectangleShape Line = new RectangleShape();
        Line.setOrigin(-32*x,-32*y);
        Vector2f size = new Vector2f(0,0);
        Line.setOutlineThickness(1);
       
        if (vertical){
            size = new Vector2f(0,416);
        }
        else {
        	size = new Vector2f(416,0);
        }
        Line.setSize(size);
        return Line;
	}
	
   public static void main(String args[]) throws Exception
   {
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("90.73.167.116");
      byte[] sendData = new byte[1024];
      RenderWindow window = new RenderWindow();
      window.create(new VideoMode(416,416), "BomberLAN", WindowStyle.DEFAULT); // 442*442
      window.setFramerateLimit(60);
      boolean orientation = true;
      for (int i=0 ; i<14 ; ++i){
          window.draw(drawLine(i,0,orientation)); // Coordonnées bizarres 
          window.draw(drawLine(0,i,!orientation)); // Coordonnées bizarres 
      }
      window.display();
      while(window.isOpen()) {
    	  for (int i=0 ; i<14 ; ++i){
              window.draw(drawLine(i,0,orientation)); // Coordonnées bizarres 
              window.draw(drawLine(0,i,!orientation)); // Coordonnées bizarres 
          }
    	  for(Event event : window.pollEvents()) {
              if(event.type == Event.Type.CLOSED) {
                  window.close();
              }
              if (event.type == Event.Type.KEY_PRESSED){
                  KeyEvent keyEvent = event.asKeyEvent();
                  String touche = "" + keyEvent.key;
                  sendData = touche.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5151); 
                  clientSocket.send(sendPacket);
              }
    	  }
    	  window.display();
      }//While windows is open
      
   }
}