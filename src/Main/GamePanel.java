package Main;
import Entity.*;
import Item.*;
import Tile.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 32;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = 512 * scale;
    public final int screenHeight = 384 * scale;

    // FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public TileManager tileManager = new TileManager(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public ItemManager itemManager = new ItemManager(this);
    public SoundManager BGM = new SoundManager();
    public SoundManager SE = new SoundManager();
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);
    //res -> 512 x 384

    // WORLD SETTINGS
    public final int maxWorldCol = 16 * scale;
    public final int maxWorldRow = 12 * scale;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        playMusic(0);
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }

        }
    }
    public void update(){
        player.update();
        itemManager.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;



        tileManager.drawBG(g2);
        itemManager.draw(g2);
        tileManager.drawFG(g2);
        //collisionManager.drawCollisions(g2);
        player.draw(g2);
        ui.draw(g2);

        g2.setColor(Color.darkGray);
//        for (int i = 0; i < maxScreenRow; i++){
//            g2.drawLine(0, i * tileSize, screenWidth, i * tileSize);
//        }
//        for (int i = 0; i < maxScreenCol; i++){
//            g2.drawLine(i * tileSize,0 , i * tileSize, screenHeight);
//        }
        g2.dispose();
    }

    public void playMusic(int i){
        BGM.setFile(i);
        BGM.play();
        BGM.loop();
    }
    public void stopMusic(){
        BGM.stop();
    }
    public void playSoundEffect(int i){
        SE.setFile(i);
        SE.play();
    }
}
