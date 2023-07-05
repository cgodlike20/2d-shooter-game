package java_game;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseTracker extends MouseInputAdapter{
    
    private Handler handler;
    private Player player;

    public MouseTracker(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
    }

    public void mouseMoved(MouseEvent e){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){
                LiveObject tempLiveObject = (LiveObject) tempObject;
                tempLiveObject.setTargetX(e.getX());
                tempLiveObject.setTargetY(e.getY());
            }
        }        
    }

    public void mousePressed(MouseEvent e){
        //HUD.updateScore(-2);
        handler.addObject(new Bullet(player.getX(), player.getY(), ID.BulletPlayer, handler, player));                 
    }

}