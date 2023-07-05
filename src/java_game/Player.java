package java_game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends LiveObject {

    public int counterToChange = 0;
    long lastTime = System.nanoTime();
    long oneSec = 1000000000;
    boolean changeFlag = false;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        velX = 0;
        velY = 0;
    }

    public void tick() {
        if (!(x <= 0 && velX < 0) && !(x >= Game.WIDTH - 39 && velX > 0)) {
            x += velX;
        }
        if (!(y <= 0 && velY < 0) && !(y >= Game.HEIGHT - 61 && velY > 0)) {
            y += velY;
        }
        collision();
        HUD.updateHUD((int )Math.round(hp * 1.74));       
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }

    protected void collision() {
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Enemy){
                if(getBounds().intersects(tempObject.getBounds())){
                }
            }
            if(tempObject.getID() == ID.BulletEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.destroyMe = true;
                    if(hp > 5){
                        hp = hp - 5;
                    }
                    else{
                        hp = 0;
                        destroyMe = true;
                    }
                }
            }
        }

    }
}