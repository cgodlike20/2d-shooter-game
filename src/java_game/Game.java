package java_game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.Color;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -221442445537865691L;

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 16 * 9; 
    public static final String NAME = "My game";

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;

    public Game(){
        handler = new Handler();

        new Window(WIDTH, HEIGHT, NAME, this);
        hud = new HUD();
        Random r = new Random();

        Player player = new Player(WIDTH/2, HEIGHT/2, ID.Player, handler);

        MouseTracker MouseTracker = new MouseTracker(handler, player);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseMotionListener(MouseTracker);
        this.addMouseListener(MouseTracker);

        handler.addObject(player);
        handler.addObject(new Enemy(r.nextInt(WIDTH-200) + 50, r.nextInt(HEIGHT-100) + 50, ID.Enemy, handler));
        handler.addObject(new Enemy(r.nextInt(WIDTH-200) + 50, r.nextInt(HEIGHT-100) + 50, ID.Enemy, handler));
        handler.addObject(new Enemy(r.nextInt(WIDTH-200) + 50, r.nextInt(HEIGHT-100) + 50, ID.Enemy, handler));

    }
    public synchronized void start() {
            thread = new Thread(this);
            thread.start();
            running = true;
    }
    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
}
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
                tick();
                delta--;
            }
            if(running){
                render();
            }    
            frames++;
                                
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: "+ frames);
                System.out.println("GC'd enemies: " + Enemy.nOfDestroyed);
                System.out.println("GC'd bullets: " + Bullet.nOfDestroyed);
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        handler.tick();
        hud.tick();
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }
    /*public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }*/
    public static void main(String[] args){
        new Game();
    }
}