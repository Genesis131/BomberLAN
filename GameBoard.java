




import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;;


/**
 * Trucs importants : 
 * L'origine de n'importe quoi est en haut à gauche. 
 * L'origine de la map est en haut à gauche, les coordonnées descendantes et vers la droite sont négatives. Ici, le coin bas droite de la map est environ (-442,-442)
 * Les x et y restent les mêmes que conventionnellement (x abscisses y ordonnées)
 */
public class GameBoard {
    
    public static Drawable player(int x, int y,Color color){//0 à 12             
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
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Create the window
        RenderWindow window = new RenderWindow();
        
        //WindowStyle style = new WindowStyle();
        window.create(new VideoMode(416,416), "BomberLAN", WindowStyle.DEFAULT); // 442*442

        //Limit the framerate
        window.setFramerateLimit(60);

        int gx = 12;
        int gy = 0;
        int cx = 0;
        int cy = 12;
        int yx = 12;
        int yy = 12;
        int x=0;
        int y=0;
        boolean orientation;
        orientation=true;
        int j=0;
        for (int i=0 ; i<14 ; ++i){
            window.draw(drawLine(i,j,orientation)); // Coordonnées bizarres 
            window.draw(drawLine(j,i,!orientation)); // Coordonnées bizarres 
        }
        window.draw(player( x, y,Color.RED));
        window.draw(player(gx, gy,Color.GREEN));
        window.draw(player( cx,cy,Color.CYAN));
        window.draw(player(yx,yy,Color.YELLOW));
        //window.draw(unbreakable(5,5));
        //window.draw(empty(3,5));
        //window.draw(breakable(4,5));
        window.display();

        //Main loop
        
        while(window.isOpen()) {
            window.clear(Color.BLACK);
        	for (int i=0 ; i<14 ; ++i){
                window.draw(drawLine(i,j,orientation)); // Coordonnées bizarres 
                window.draw(drawLine(j,i,!orientation)); // Coordonnées bizarres 
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
                    String coord = coordFromAction(touche,x,y);
                    x= Integer.parseInt(""+coord.charAt(0)+coord.charAt(1));
                    y= Integer.parseInt(""+coord.charAt(3)+coord.charAt(4));
                    System.out.println(touche+" "+x+" "+y);
                    window.draw(player( x,y,Color.RED));
                    window.draw(player(gx, gy,Color.GREEN));
                    window.draw(player(cx,cy,Color.CYAN));
                    window.draw(player(yx,yy,Color.YELLOW));
                    window.display();
                } // if (event.type == Event.Type.KEY_PRESSED)
            }  // for(Event event : window.pollEvents()) 
        }	// while(window.isOpen())
    } // main
    static String coordFromAction(String action, int x, int y){
        String newCoord=""+x+","+y;
        if(action.equals("D")){
            if (x+1<=12){
            	x+=1;
            } else {
            	x=12;
            }
            String sx = String.valueOf(x +100).substring(1);
            String sy = String.valueOf(y +100).substring(1);
            newCoord = ""+sx+","+sy;
        } // if(action.equals("D"))
        
        else if(action.equals("Q")){
        	if (x-1>=0){
            	x-=1;
            } else {
            	x=0;
            }    
            String sx = String.valueOf(x +100).substring(1);
            String sy = String.valueOf(y +100).substring(1);
            newCoord = ""+sx+","+sy;
        } //  if(action.equals("Q"))
        
         else if(action.equals("S")){
        	if (y+1<=12){
        		y+=1;
            } else {
            	y=12;
            }    
            String sx = String.valueOf(x +100).substring(1);
            String sy = String.valueOf(y +100).substring(1);
            newCoord = ""+sx+","+sy;
        } //  if(action.equals("S"))
        
         else if(action.equals("Z")){
        	if (y-1>=0){
            	y-=1;
            } else {
            	y=0;
            } 
            String sx = String.valueOf(x +100).substring(1);
            String sy = String.valueOf(y +100).substring(1);
            newCoord = ""+sx+","+sy;
        } //  if(action.equals("Z"))
         else{
             System.out.println("mauvaise touche " + action);
             String sx = String.valueOf(x +100).substring(1);
             String sy = String.valueOf(y +100).substring(1);
             newCoord = ""+sx+","+sy;
         } // else
        return newCoord;
    }
}