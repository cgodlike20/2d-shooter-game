package java_game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.lang.Math;

public class Bullet extends MovingObject {

    private float fX;
    private float fY;
    private float fVelX;
    private float fVelY;

    public static int nOfDestroyed = 0;

    public Bullet(int x, int y, ID id, Handler handler, LiveObject object) {
        super(x, y, id, handler);

        this.fX = x;
        this.fY = y;

        int dir = 1; 
        float tempVelX = object.getTargetX() - x;
        float tempVelY = object.getTargetY() - y;
        if(tempVelX < 0){
            dir = -1;
        }
        tempVelY = tempVelY / tempVelX;
        float ratio = 100/(tempVelY * tempVelY + 1);
        tempVelX = (float) Math.sqrt(ratio);
        tempVelY = tempVelY * tempVelX;
        fVelX = tempVelX * dir;
        fVelY = tempVelY * dir;  

    }

    public void tick() {
        fX += fVelX;
        fY += fVelY;
        x = Math.round(fX);
        y = Math.round(fY);
        collision();
        if(x < -5 || x > Game.WIDTH || y < -5 || y > Game.HEIGHT){
            destroyMe = true;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);    
        g.fillRect(x, y, 6, 6);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 6, 6);
    }

    protected void collision() {

    }

    public void finalize(){
        nOfDestroyed++;
    }
    
}