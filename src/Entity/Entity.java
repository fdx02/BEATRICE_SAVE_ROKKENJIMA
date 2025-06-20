package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed;
    public String animation;
    public String direction;
    public int spriteNum, spriteNumIdle;
    public int spriteCounter;
    public String status;
    public int idleCounter;
    public Rectangle hitbox;
    public boolean collisionOn = false;
}
