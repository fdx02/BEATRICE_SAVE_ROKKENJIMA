package Main;
import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 32;//32x32 tile
    public final int scale = 5;
    public final int tileSize = originalTileSize * scale; //64x64 tile //lo que se ve en la pantalla

    public final int maxScreenCol = 10;
    public final int maxScreenRow = 6;
    public final int screenWidth = 320 * scale;
    public final int screenHeight = 180 * scale;
    //CAMBIANDO las settins puedo modificar la resolucion del juego al gusto
    //basicamente es dividir la pantalla en tiles

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        //setDoubleBuffered hace algo para que tenga mejor performance el juego al dibujar cosas
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
//        g2.setColor(Color.darkGray);
//       for(int i = 0 ; i<maxScreenCol ; i++){
//            g2.drawLine(i*tileSize, 0, i*tileSize, screenHeight);
//       }
//       for(int i = 0 ; i<maxScreenRow ; i++){
//            g2.drawLine(0, i*tileSize, screenWidth, i*tileSize);
//       }

       g2.dispose();
    }
}
