package mainpkg;


import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsfml.internal.SFMLNative;


/**
 * Trucs importants : 
 * L'origine de n'importe quoi est en haut à gauche. 
 * L'origine de la map est en haut à gauche, les coordonnées descendantes et vers la droite sont négatives. Ici, le coin bas droite de la map est environ (-442,-442)
 * Les x et y restent les mêmes que conventionnellement (x abscisses y ordonnées)
 */
public class GameBoard {

            
    static int dir = 0;
    static int[][] tabBD = new int[13][13];
    //static Color GRIS = new Color(128, 128 ,128);
    
    public static Drawable player(int x, int y,Color color){//0 à 12 
        
        CircleShape Circle = new CircleShape();
        Circle.setRadius(16);//Radius = rayon
        Circle.setOrigin(-32*x,-32*y);
        Circle.setFillColor(color);
        return Circle;
    }
    
    
    public static Drawable unbreakable(int x, int y){
    Texture unbreakable_texture = new Texture();
        try {
            unbreakable_texture.loadFromFile(Paths.get("sprites/unbreakable.png"));
            Vector2i size = unbreakable_texture.getSize();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite unbreakable = new Sprite(unbreakable_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        unbreakable.setOrigin(x*-31,y*-31);
        unbreakable.setPosition(x, y);
        return unbreakable;
    }
    
    public static Drawable breakable(int x, int y){
        Texture breakable_texture = new Texture();
        try {
            breakable_texture.loadFromFile(Paths.get("sprites/breakable.png"));
            Vector2i size = breakable_texture.getSize();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite breakable = new Sprite(breakable_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        breakable.setOrigin(x*-31,y*-31);
        breakable.setPosition(x, y);
        return breakable;
    }
    
    
   public static Drawable bomb(int x, int y){
        Texture bomb_texture = new Texture();
        try {
            bomb_texture.loadFromFile(Paths.get("sprites/bomb.png"));
            Vector2i size = bomb_texture.getSize();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite bomb = new Sprite(bomb_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        bomb.setOrigin(x*-31,y*-31);
        bomb.setPosition(x, y);
        return bomb;
           
   }
        
    
    public static Drawable empty(int x, int y){
        Texture empty_texture = new Texture();
        try {
            empty_texture.loadFromFile(Paths.get("sprites/empty.png"));
            Vector2i size = empty_texture.getSize();
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite empty = new Sprite(empty_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        empty.setOrigin(x*-31,y*-31);
        empty.setPosition(x, y);
        return empty;
    }
    
    public static Drawable perso_down(int x, int y){
        Texture perso_down_texture = new Texture();
        try {
            perso_down_texture.loadFromFile(Paths.get("sprites/perso_down.png"));
            Vector2i size = perso_down_texture.getSize();
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite perso_down = new Sprite(perso_down_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        perso_down.setOrigin(x*-31,y*-31);
        perso_down.setPosition(x, y);
        return perso_down;
    }
        
    public static Drawable perso_right(int x, int y){
        Texture perso_right_texture = new Texture();
        try {
            perso_right_texture.loadFromFile(Paths.get("sprites/perso_right.png"));
            Vector2i size = perso_right_texture.getSize();
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite perso_right = new Sprite(perso_right_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        perso_right.setOrigin(x*-31,y*-31);
        perso_right.setPosition(x, y);
        return perso_right;
    }
        
    public static Drawable perso_up(int x, int y){
        Texture perso_up_texture = new Texture();
        try {
            perso_up_texture.loadFromFile(Paths.get("sprites/perso_up.png"));
            Vector2i size = perso_up_texture.getSize();
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite perso_up = new Sprite(perso_up_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        perso_up.setOrigin(x*-31,y*-31);
        perso_up.setPosition(x, y);
        return perso_up;
    }
    public static Drawable perso_left(int x, int y){
        Texture perso_left_texture = new Texture();
        try {
            perso_left_texture.loadFromFile(Paths.get("sprites/perso_left.png"));
            Vector2i size = perso_left_texture.getSize();
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        Sprite perso_left = new Sprite(perso_left_texture);
        //perso.setOrigin(Vector2f.div(new Vector2f(perso_texture.getSize()), 2));
        perso_left.setOrigin(x*-31,y*-31);
        perso_left.setPosition(x, y);
        return perso_left;
    }
        
        
       /* 
        private static native boolean nativeIsKeyPressed(int key);
        
        public static boolean isKeyPressed(KeyEvent key) {
                return nativeIsKeyPressed(key.ordinal() - 1);
        }*/
    static int[] coordFromAction(String action, int x, int y){
        int[] newCoord={x,y};
        tabBD[x][y] = 0;
        if(action.equals("D")){
            if (x+1<=12){
                ++x;
                dir = 1; // Equiv Droite
            }
            
            if(checkUnbreak(x, y)|| checkBreak(x, y)){ // Check de la possibilité de déplacement 
                --x;
            }

            newCoord[0]=x;
            newCoord[1]=y;
            
        } // if(action.equals("D"))
        
        else if(action.equals("Q")){
            if (x-1>=0){
                x-=1;
                dir = 2;
            }
            if(checkUnbreak(x, y) || checkBreak(x, y)){
                    ++x;
            }

            newCoord[0]=x;
            newCoord[1]=y;
        } //  if(action.equals("Q"))
        
         else if(action.equals("S")){
            if (y+1<=12){
                    ++y;
                    dir = 3;
            } 
            if(checkUnbreak(x, y) || checkBreak(x, y)){
                    --y;
            } 

            newCoord[0]=x;
            newCoord[1]=y;
        } //  if(action.equals("S"))
        
         else if(action.equals("Z")){
            if (y-1>=0){
                --y;
                dir = 4;
            } 
                
            if(checkUnbreak(x, y) || checkBreak(x, y)){
                --y;
            } 

            newCoord[0]=x;
            newCoord[1]=y;
        } //  if(action.equals("Z"))
        
         else{
             System.out.println("mauvaise touche " + action);

             newCoord[0]=x;
             newCoord[1]=y;
         } // else
        return newCoord;
    }
    
    
    
    public static boolean checkUnbreak(int x, int y){
        if(tabBD[x][y] == 1){
            return true;
        }else {
            return false;        
        }
    } // fin checkUnbreak
    
    public static boolean checkBreak(int x, int y){
        if(tabBD[x][y] == 2){
            return true;
        }else {
            return false;        
        }
    } // fin checkBreak
    public static boolean explodeR(Integer[] xy ){
    	int x = xy[0]; 
    	int y = xy[1];
        try{        
            if(tabBD[x+1][y] == 2 || tabBD[x+1][y] == 7 || tabBD[x+1][y] == 8 || tabBD[x+1][y] == 9){
                tabBD[x+1][y] = 0;
                return true;
            }
            else{
            	return false;
            }
        }catch(Exception e){                        
            return false;
        }
    }
    
    public static boolean explodeL(Integer[] xy ){
    	int x = xy[0]; 
    	int y = xy[1];
        try{
		    if(tabBD[x-1][y] == 2 || tabBD[x-1][y] == 7 || tabBD[x+1][y] == 8 || tabBD[x+1][y] == 9){
	            tabBD[x-1][y] = 0;
	            return true;
		    }
		    else{
		    	return false;
		    }
        }catch(Exception e){                        
            return false;
        }
    }
    public static boolean explodeU(Integer[] xy ){
    	int x = xy[0]; 
    	int y = xy[1];
        try{
	        if(tabBD[x][y+1] == 2|| tabBD[x][y+1] == 7 || tabBD[x+1][y] == 8 || tabBD[x+1][y] == 9){
                tabBD[x][y+1] = 0;
                return true;
	        }
		    else{
		    	return false;
		    }
        }catch(Exception e){                        
        	return false;
        }
    }
    public static boolean explodeD(Integer[] xy ){
    	int x = xy[0]; 
    	int y = xy[1];
        try{
	        if(tabBD[x][y-1] == 2 || tabBD[x][y-1] == 7 || tabBD[x+1][y] == 8 || tabBD[x+1][y] == 9){
               tabBD[x][y-1] = 0;
               return true;
            }
	        else{
	        	return false;
	        }
        }catch(Exception e){                        
            return false;
        }
    }
    
    public static boolean checkDestruction(int x, int y){
        int k=0;
        for (int l=0; x<=12; x++){
            //tabBD[x][y] = 1;
            if(l == 12 && k != 12){
                x = 0;
                k++;
            }
            
            else if(k == 0 || k == 12){
                tabBD[l][k] = 0;
            }
            
            else if(l == 0 || l == 12){
                tabBD[l][k] = 0;
            }
            
            else if(k == 1 || k == 3 || k == 5 || k == 7 || k == 9 || k == 11){
                if(l == 1 || l == 3 || l == 5 || l == 7 || l == 9 || l == 11){
                    tabBD[l][k] = 1;
                }
                else{
                    tabBD[l][k] = 2;
                }
            }
            else if(k == 2 || k == 4 || k == 6 || k == 8 || k == 10){
                tabBD[l][k] = 2;
            }
            else{
                tabBD[l][k] = 0;
            }
        }
            //System.out.println(tab[0][0]);
            //System.out.println(bd[0] + bd[1]);
        if(tabBD[x][y] == 1){
            return true;
        }else {
            return false;
        }
    }
        
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Create the window
        RenderWindow window = new RenderWindow();
        
        //WindowStyle style = new WindowStyle();
        window.create(new VideoMode(416,416), "BomberLAN", WindowStyle.DEFAULT); // 442*442

        //On cale la Framerate
        window.setFramerateLimit(60);
        
        int drawBomb =0;
        int savedx = 0; 
        int savedy = 0;
        
        Vector <Drawable> storeBomb = new Vector();
        Vector <Integer> storeTimer = new Vector();
        Vector <Integer[]> storeCoord = new Vector();

        int gx = 12;
        int gy = 0;
        int cx = 0;
        int cy = 12;
        int yx = 12;
        int yy = 12;
        int rx=0;
        int ry=0;
        int x=0;
        int y=0;
        boolean orientation;
        orientation=true;
        int j=0;
        int g=1;
        //int[][] tabBD = new int[13][13];
          
        for (x=0; x<=12; x++){
            //tabBD[x][y] = 1;
            if(x == 12 && y != 12){
                x = 0;
                y++;
            }
            
            else if(y == 0 && x == 1 || y == 12){
                tabBD[x][y] = 0;
            }
            
            else if(x == 0 && y == 1 || x == 12){
                tabBD[x][y] = 0;
            }
            
            else if(y == 1 || y == 3 || y == 5 || y == 7 || y == 9 || y == 11){
                if(x == 1 || x == 3 || x == 5 || x == 7 || x == 9 || x == 11){
                    tabBD[x][y] = 1;
                 window.draw(unbreakable(x,y));
                }
                else{
                    tabBD[x][y] = 2;
                 window.draw(breakable(x,y));
                }
            }
            else if(y == 2 || y == 4 || y == 6 || y == 8 || y == 10){
                tabBD[x][y] = 2;
                window.draw(breakable(x,y));
            }
            else{
            	tabBD[x][y] = 0;
            }
        }
        tabBD[12][1] = 2;
        tabBD[12][2] = 2;
        tabBD[0][0] = 6;  //RED
        tabBD[0][12] = 7;  //CYAN
        tabBD[12][0] = 8;  //GREEN
        tabBD[12][12] = 9;  //YELLOW
        

        window.draw(player( rx, ry,Color.RED));
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
            
            y=0;
            for (x=0; x<=12; x++){
                //tabBD[x][y] = 1;
                
                
                if(tabBD[x][y] == 0){
                    window.draw(empty(x,y));
                }
                
                else if(tabBD[x][y] == 1){
                     window.draw(unbreakable(x,y));
                }
                
                else if(tabBD[x][y] == 2){
                     window.draw(breakable(x,y));
                }
                if(x == 12 && y != 12){
                    x = -1;
                    y++;
                }
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
                    int[] coord = new int[2];
                    
                    
                    if (touche.equals("C")){ 
                        drawBomb = 3;
                        savedx = rx;
                        savedy = ry;
                        storeTimer.add(1*60);
                        storeBomb.add(bomb(rx,ry));
                        Integer[] daCoord = new Integer[2];
                        daCoord[0] = rx; daCoord[1] = ry;
                        storeCoord.add(daCoord);
                            
                    } else {
                        coord = coordFromAction(touche,rx,ry);
                        tabBD[coord[0]][coord[1]] = 6;
                        rx=coord[0];
                        ry=coord[1];
                    }
                    
                    //String coord = coordFromAction(touche,x,y, bd1);
                    //x= Integer.parseInt(""+coord.charAt(0)+coord.charAt(1));
                    //y= Integer.parseInt(""+coord.charAt(3)+coord.charAt(4));
                    System.out.println(touche+" "+ coord[0] + coord[1]);

                    
                } // if (event.type == Event.Type.KEY_PRESSED)
            }  // for(Event event : window.pollEvents()) 
            
            for (int i=0 ; i<storeBomb.size() ; ++i) {
                int k = storeTimer.get(i);
                storeTimer.set(i,k-1);
                if (storeTimer.get(i)<=0) {
                    storeTimer.remove(i);
                    storeBomb.remove(i);
                    explodeR(storeCoord.get(i));
                    explodeL(storeCoord.get(i));
                    explodeU(storeCoord.get(i));
                    explodeD(storeCoord.get(i));
                    storeCoord.remove(i);
                } else {
                    window.draw(storeBomb.elementAt(i));
                }
            }
            y=0;
            
            
                    
            for (x=0; x<=12; ++x){
                    //tabBD[x][y] = 1;
                if(tabBD[x][y] == 6){
            
                        
                    //window.draw(player( x, y,Color.RED));
                    window.draw(perso_down(x, y));
                    if(dir == 1){
                        window.draw(perso_down(x, y));
                    }
            
                }
                if(tabBD[x][y] == 7){
                    window.draw(player( x, y,Color.CYAN));
            
                }
                if(tabBD[x][y] == 8){
                    window.draw(player( x, y,Color.GREEN));
                }
                if(tabBD[x][y] == 9){
                    window.draw(player( x, y,Color.YELLOW));
                }
                if(x == 12 && y != 12){
                    x = -1;
                    y++;
	            }
	        }
            
            window.display();
        } // while(window.isOpen())
    } // main
} // FIN
