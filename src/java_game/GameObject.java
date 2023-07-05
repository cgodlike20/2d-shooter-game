package java_game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected int x, y;
    protected ID id;
    public boolean destroyMe = false;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ID getID(){
        return id;
    }

}