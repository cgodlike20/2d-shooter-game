package java_game;

public abstract class MovingObject extends GameObject{

    Handler handler;

    public MovingObject(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }
    protected int velX, velY;

    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
    protected abstract void collision();
}