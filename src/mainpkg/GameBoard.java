package mainpkg;


import java.util.Vector;

import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;;


/**
 * Trucs importants : 
 * L'origine de n'importe quoi est en haut � gauche. 
 * L'origine de la map est en haut � gauche, les coordonn�es descendantes et vers la droite sont n�gatives. Ici, le coin bas droite de la map est environ (-442,-442)
 * Les x et y restent les m�mes que conventionnellement (x abscisses y ordonn�es)
 */
public class GameBoard {
	
	
    public static Drawable player(int x, int y,Color color){//0 � 12             
        CircleShape Circle = new CircleShape();
        Circle.setRadius(16);//Radius = rayon
        Circle.setOrigin(-32*x,-32*y);
        Circle.setFillColor(color);
        return Circle;
    }
    
    public static Drawable drawLine(int x, int y, boolean vertical){
        RectangleShape Line = new RectangleShape();
        Line.setOrigin(-32*x,-32*y);
        Vector2f size = new Vector2f(0,0);
        Line.setOutlineThickness(1);
        Line.setOutlineColor(Color.WHITE);
        Line.setFillColor(Color.WHITE);
        if (vertical){
            size = new Vector2f(0,416);
        }
        else {
        	size = new Vector2f(416,0);
        }
        Line.setSize(size);
        return Line;
    }
    
    public static Shape bomb(int x, int y){
    	CircleShape Circle = new CircleShape();
        Circle.setRadius(16);//Radius = rayon
        Circle.setOrigin(-32*x,-32*y);
        Circle.setFillColor(Color.MAGENTA);
        /*if (drawBomb%2 == 0){
        	Circle.setOutlineColor(Color.WHITE);
        	Circle.setOutlineThickness(2);
        }*/
        return Circle;
    }
    
    /*
    public int msTime(Time time){
    	String mstr;
    	int ms;
    	mstr = time.toString();
    	mstr = 
    	ms = mstr.getValue().parseInt;
    	
    	return ms;
    }*/
    
    public static Drawable unbreakable(int x, int y){
        RectangleShape Block = new RectangleShape();
        Block.setOrigin(-32*x,-32*y);
        Vector2f size = new Vector2f(32,32);
        Block.setFillColor(Color.WHITE);
        Block.setSize(size);
        return Block;
    }
    
    public static Drawable breakable(int x, int y){
        RectangleShape Block = new RectangleShape();
        Block.setOrigin(-32*x,-32*y);
        Vector2f size = new Vector2f(32,32);
        Block.setFillColor(Color.MAGENTA);
        Block.setSize(size);
        return Block;
    }
    
    public static Drawable empty(int x, int y){
        RectangleShape Block = new RectangleShape();
        Block.setOrigin(-32*x,-32*y);
        Vector2f size = new Vector2f(32,32);
        Block.setFillColor(Color.BLACK);
        Block.setSize(size);
        return Block;
    }
    
    
    public static int[] coordFromAction(String action, int x, int y){
        int newCoord[]={x,y};
        		//Coords.Coords(x,y);
        if(action.equals("D")){
            if (x+1<=12){
            	newCoord[0] = x+1;
            } else {
            	newCoord[0] = 12;
            }
        } // if(action.equals("D"))
        
        else if(action.equals("Q")){
        	if (x-1>=0){
        		newCoord[0] = x-1;
            } else {
            	newCoord[0] = 0;
            }
        } //  if(action.equals("Q"))
        
         else if(action.equals("S")){
        	if (y+1<=12){
                newCoord[1] = y+1;
            } else {
                newCoord[1] = 12;
            }
        } //  if(action.equals("S"))
        
         else if(action.equals("Z")){
        	if (y-1>=0){
                newCoord[1] = y-1;
            } else {
            	newCoord[1] = 0;
            }
        } //  if(action.equals("Z"))
         else {
             System.out.println("mauvaise touche " + action);
         } // else
        return newCoord;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Create the window
        RenderWindow window = new RenderWindow();
        Clock globalTime = new Clock();
        
        //WindowStyle style = new WindowStyle();
        window.create(new VideoMode(416,416), "BomberLAN", WindowStyle.DEFAULT); // 442*442

        //Limit the framerate
        window.setFramerateLimit(60);
        
        Vector <Shape> storeBomb = new Vector();
        Vector <Integer> storeTimer = new Vector();
        int rx=0;
        int ry=0;
        int gx = 12;
        int gy = 0;
        int cx = 0;
        int cy = 12;
        int yx = 12;
        int yy = 12;
        
        boolean orientation = true;

        for (int i=0 ; i<14 ; ++i){
            window.draw(drawLine(i,0,orientation)); // Coordonn�es bizarres 
            window.draw(drawLine(0,i,!orientation)); // Coordonn�es bizarres 
        }
        
        window.draw(player(rx,ry,Color.RED));
        window.draw(player(gx,gy,Color.GREEN));
        window.draw(player(cx,cy,Color.CYAN));
        window.draw(player(yx,yy,Color.YELLOW));
        //window.draw(unbreakable(5,5));
        //window.draw(empty(3,5));
        //window.draw(breakable(4,5));
        window.display();
        
        int drawBomb =0;
        int savedx = 0; 
        int savedy = 0;
        //Time bombTime;
        
        while(window.isOpen()) {//Main loop
            
            window.clear(Color.BLACK);
            for (int i=0 ; i<14 ; ++i){
                window.draw(drawLine(i,0,orientation)); // Coordonn�es bizarres 
                window.draw(drawLine(0,i,!orientation)); // Coordonn�es bizarres 
            }
            
            //Handle events
            for(Event event : window.pollEvents()) {
                if(event.type == Event.Type.CLOSED) {
                    //The user pressed the close button
                    window.close();
                }
                if (event.type == Event.Type.KEY_PRESSED){
                	
                    KeyEvent keyEvent = event.asKeyEvent();
                    String touche = "" + keyEvent.key;
                    if (touche.equals("C")){ 
                    	//bombTime =  ;
                    	//bombTime += 3;
                    	drawBomb = 3;
                    	savedx = rx;
                    	savedy = ry;
                    	storeTimer.add(3*60);
                    	storeBomb.add(bomb(rx,ry));
                    	
                    } else {
                    	int[] coord = coordFromAction(touche,rx,ry);
                    	rx = coord[0];
                        ry = coord[1];
                    }
                   
                    System.out.println(touche+" "+rx+" "+ry);
                   
                    
                     
                } // if (event.type == Event.Type.KEY_PRESSED)
            }// for(Event event : window.pollEvents()) 
            
            for (int i=0 ; i<storeBomb.size() ; ++i) {
        		int j = storeTimer.get(i);
        		storeTimer.set(i,j-1);
        		if (storeTimer.get(i)<=0) {
        			storeTimer.remove(i);
        			storeBomb.remove(i);
        		} else {
        			window.draw(storeBomb.elementAt(i));
        		}
            }
            
            window.draw(player(rx,ry,Color.RED));
            window.draw(player(gx,gy,Color.GREEN));
            window.draw(player(cx,cy,Color.CYAN));
            window.draw(player(yx,yy,Color.YELLOW));
            window.display();
        } // while(window.isOpen()) MAIN LOOP
    } // main
}