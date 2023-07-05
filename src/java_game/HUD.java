package java_game;

import java.awt.Graphics;
import java.awt.Color;

public class HUD {
    
    public static int healthBarLength = 174;
    public static int score = 0;

    //mozna wrzucic handler, zeby sciagal z handlera wartosc hp obiektu o id player
    public static void updateHUD(int hp){
        healthBarLength = hp;
    }
    public static void updateScore(int number){
        if(score <= 0 && number < 0){
            score = 0;
        }
        else{
            score += number;
        }
        
    }

    public void tick(){

    }
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 180, 16);
        g.setColor(Color.red);
        g.fillRect(18, 18, healthBarLength, 10);
        g.setColor(Color.white);
        g.drawString("Score: " + score, 14, 45);
    }
}