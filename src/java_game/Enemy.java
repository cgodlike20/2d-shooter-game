package java_game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

public class Enemy extends LiveObject {

    Random r = new Random();
    public static int nOfDestroyed = 0;

    long lastTime = System.nanoTime();
    long oneSec = 1000000000;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        velX = r.nextInt(5);
        velY = r.nextInt(5);
    }

    private void getPlayerPos(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player){
                targetX = tempObject.getX() + r.nextInt(50) + 20;
                targetY = tempObject.getY() + r.nextInt(50) + 20;
            }
        }
    }
    private void tryToShoot(){
        getPlayerPos();
        long now = System.nanoTime(); 
        if((now - lastTime > oneSec) && (r.nextInt(20) == 5)){
            handler.addObject(new Bullet(this.getX(), this.getY(), ID.BulletEnemy, handler, this));
            lastTime = System.nanoTime();
        }
    }

    public void tick() {
        if(x < 1 || x > Game.WIDTH - 39){
            velX = (-1) * velX;
        }
        if(y < 1 || y > Game.HEIGHT - 61){
            velY = (-1) * velY;
        }
        x += velX;
        y += velY;

        tryToShoot();

        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    protected void collision() {        
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.BulletPlayer){
                if(getBounds().intersects(tempObject.getBounds())){
                    tempObject.destroyMe = true;
                    if(hp > 20){
                        hp = hp - 20;
                    }
                    else{
                        HUD.updateScore(50);
                        destroyMe = true;
                    }
                }
            }
        }
    }
    public void finalize(){
        nOfDestroyed++;
    }
    
}