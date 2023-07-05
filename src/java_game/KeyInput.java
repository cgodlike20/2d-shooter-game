package java_game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
    
    private Handler handler;
    Random r = new Random();
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    private boolean keyWPressed, keySPressed, keyAPressed, keyDPressed = false;
    private void ifNoKeysPressed(LiveObject object){
        if(!keyWPressed && !keySPressed && !keyAPressed && !keyDPressed){
            object.setWalkAnim(false);
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if((key == KeyEvent.VK_CONTROL)){
            handler.addObject(new Enemy(r.nextInt(Game.WIDTH-200) + 50, r.nextInt(Game.HEIGHT-100) + 50, ID.Enemy, handler));
        }
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){

                LiveObject tempLiveObject = (LiveObject) tempObject;

                if((key == KeyEvent.VK_W)){
                    tempLiveObject.setVelY(-5);
                    tempLiveObject.setWalkAnim(true);
                    keyWPressed = true;
                }
                else if((key == KeyEvent.VK_S)){
                    tempLiveObject.setVelY(5);
                    tempLiveObject.setWalkAnim(true);
                    keySPressed = true;
                }
                else if((key == KeyEvent.VK_A)){
                    tempLiveObject.setVelX(-5);
                    tempLiveObject.setWalkAnim(true);
                    keyAPressed = true;
                }
                else if((key == KeyEvent.VK_D)){
                    tempLiveObject.setVelX(5);
                    tempLiveObject.setWalkAnim(true);
                    keyDPressed = true;
                }
            }
            if(key == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){

                LiveObject tempLiveObject = (LiveObject) tempObject;

                if((key == KeyEvent.VK_W)){
                    tempLiveObject.setVelY(0);
                    keyWPressed = false;
                }
                if((key == KeyEvent.VK_S)){
                    tempLiveObject.setVelY(0);
                    keySPressed = false;
                }
                if((key == KeyEvent.VK_A)){
                    tempLiveObject.setVelX(0);
                    keyAPressed = false;
                }
                if((key == KeyEvent.VK_D)){
                    tempLiveObject.setVelX(0);
                    keyDPressed = false;
                }
                ifNoKeysPressed(tempLiveObject);
            }
        }
    }

}