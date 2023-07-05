package java_game;

public abstract class LiveObject extends MovingObject{

    int hp;
    protected int targetX, targetY;

    public LiveObject(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        hp = 100;
        targetX = Game.WIDTH/2;
        targetY = 0;
    }
    public void setHP(int hp){
        this.hp = hp;
    }
    public int getHP(){
        return hp;
    }
    
    public void setTargetX(int x){
        this.targetX = x;
    }
    public void setTargetY(int y){
        this.targetY = y;
    }
    public int getTargetX(){
        return targetX;
    }
    public int getTargetY(){
        return targetY;
    }
    boolean walkingAnimation = false;

    public void setWalkAnim(boolean bool){
        walkingAnimation = bool;
    }
    public boolean getWalkAnim(){
        return walkingAnimation;
    }
    
}