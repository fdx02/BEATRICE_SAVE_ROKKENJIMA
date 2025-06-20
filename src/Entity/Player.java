package Entity;
import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage idleL1, idleL2, idleL3, idleR1, idleR2, idleR3, left1, left2, left3,left4, right1, right2, right3, right4, up1, up2, up3,up4, down1, down2, down3,down4;
    String lastDirection;
    final int scale;
    public final int screenX;
    public final int screenY;
    int movementX;
    int movementY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.scale = gp.scale;
        hitbox = new Rectangle(10 * scale,22 * scale, 12 * scale, 18 * scale);
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (40 / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        this.worldX = 23*gp.tileSize;
        this.worldY = 18*gp.tileSize;
        this.speed = 2*scale;
        status = "idle";
        direction = "idle";
        lastDirection = "right";
    }

    public void update(){
        idleCounter++;
        if (keyH.isMoving()){
            idleCounter = 0;
            status = "moving";
            if (keyH.upPressed){
                direction = "up";
                animation = direction;
                lastDirection = direction;
                movementY = -speed;
            } else if (keyH.downPressed){
                direction = "down";
                animation = direction;
                lastDirection = direction;
                movementY = speed;
            } else if (keyH.leftPressed){
                direction = "left";
                lastDirection = direction;
                animation = direction;
                movementX = -speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                lastDirection = direction;
                animation = direction;
                movementX = speed;
            }
        } else {
            if (idleCounter > 120 || status.equals("idle")){
                status = "idle";
                if (Objects.equals(lastDirection, "left")){
                    animation = "idleLeft";
                } else {
                    animation = "idleRight";
                }
            } else {
                lastDirection = direction;
            }
        }
        // CHEQUEO DE COLISIONES CON EL COLLISIONMAP
        collisionOn = false;
        gp.collisionManager.checkTile(this);
        // SI LA COLISION DA TRUE HAGO EL MOVE CON EL SUPUESTO MOVEMENTX QUE SUME ARRIBA -> BORRAR WORLDX + SPEED DEL IF DE ARRIBA
        if(!collisionOn){
            switch (direction){
                case "up":
                    worldY += movementY;
                    break;
                case "down":
                    worldY += movementY;
                    break;
                case "left":
                    worldX += movementX;
                    break;
                case "right":
                    worldX += movementX;
                    break;
            }
        }

        spriteCounter++;
        if (keyH.isMoving()) {
            if (idleCounter < 120){
                if (spriteCounter > 12) {
                    spriteNum = (spriteNum % 4) + 1;
                    spriteCounter = 0;
                    spriteNumIdle = 3;
                }
            }
        } else {
            spriteNum = 1;
            if (Objects.equals(status, "idle") && spriteCounter > 85) {
                spriteNumIdle = (spriteNumIdle % 3) + 1;
                spriteCounter = 0;
            }
        }

        //reseteo
        movementX = 0;
        movementY = 0;
    }


    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (animation) {
            case "left":
                image = switch(spriteNum){
                    case 1 -> left1;
                    case 2 -> left2;
                    case 3 -> left3;
                    case 4 -> left4;
                    default -> left1;
                };
                break;
            case "right":
                image = switch(spriteNum){
                    case 1 -> right1;
                    case 2 -> right2;
                    case 3 -> right3;
                    case 4 -> right4;
                    default -> right1;
                };
                break;
            case "up":
                image = switch(spriteNum){
                    case 1 -> up1;
                    case 2 -> up2;
                    case 3 -> up3;
                    case 4 -> up4;
                    default -> up1;
                };
                break;
            case "down":
                image = switch(spriteNum){
                    case 1 -> down1;
                    case 2 -> down2;
                    case 3 -> down3;
                    case 4 -> down4;
                    default -> down1;
                };
                break;
            case "idleRight":
                image = switch(spriteNumIdle){
                    case 1 -> idleR1;
                    case 2 -> idleR2;
                    case 3 -> idleR3;
                    default -> idleR1;
                };
                break;
            case "idleLeft":
                image = switch(spriteNumIdle){
                    case 1 -> idleL1;
                    case 2 -> idleL2;
                    case 3 -> idleL3;
                    default -> idleL1;
                };
                break;
        }

        g2.setColor(Color.WHITE);
        g2.drawImage(image,screenX, screenY, 32 * scale, 40*scale, null);

//        g2.setColor(Color.BLUE);
//        g2.fillRect(screenX +hitbox.x, screenY + hitbox.y, hitbox.width , hitbox.height);

        g2.setColor(Color.RED);
        g2.drawString("State: " + status, screenX, screenY - 10);
//        g2.drawString("Anim: " + animation, screenX, screenY - 20);
//        g2.drawString("Anim: " + animation, screenX, screenY - 20);
        g2.drawString("WorldX: " + worldX, screenX, screenY - 20);
        g2.drawString("WorldY: " + worldY, screenX, screenY - 30);
//        g2.drawString("SpriteNum: " + spriteNum, screenX, screenY - 30);
        g2.drawString("Collision: " + collisionOn, screenX, screenY - 40);



    }

    public void getPlayerImage(){
        try{
            idleR1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleRight1.png"));
            idleR2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleRight2.png"));
            idleR3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleRight3.png"));
            idleL1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleLeft1.png"));
            idleL2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleLeft2.png"));
            idleL3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceIdleLeft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkRight3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkRight4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkLeft3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkLeft4.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkUp2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkUp3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkUp4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkDown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkDown3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/BeatriceWalkDown4.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

