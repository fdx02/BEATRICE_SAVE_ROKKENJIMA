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
    public String direction;
    public String lastDirection;
    public BufferedImage idleR1, idleR2, idleR3, idleL1, idleL2, idleL3, right1, right2, right3,right4, left1, left2, left3,left4;
    int spriteNumIdle = 1;

    public Player(GamePanel GP, KeyHandler KEYH) {
        this.gp = GP;
        this.keyH = KEYH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = (gp.screenWidth / 10);
        y = (gp.screenHeight / 6) * 3;
        speed = 3 * gp.scale/2;
        direction = "idleR";
        lastDirection = "right";
        int s1 = gp.screenWidth;
        int s2 = gp.screenHeight;
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
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    //TODO ACTUALIZAR TODO CON LO DEL CHAT (SWTICH CASE MEJOR) (SACAR 900000 IF) (MEJORAR ANIMACION) (ETC)
    public void update(){
        boolean wasMoving = !keyH.isIdle();
        if(keyH.upPressed){
            direction = lastDirection;
            y -= speed;
        }
        if(keyH.downPressed){
            direction = lastDirection;
            y += speed;
        }
        if(keyH.leftPressed){
            direction = "left";
            lastDirection = direction;
            x -= speed;
        }
        if(keyH.rightPressed){
            direction = "right";
            lastDirection = direction;
            x += speed;
        }
        if(!wasMoving) {
            if(lastDirection.equals("left")) {
                direction = "idleL";
            } else {
                direction = "idleR";
            }
        }

        spriteCounter++;
        if (wasMoving){
            if (spriteCounter > 10){
                spriteNum = (spriteNum % 4) + 1;
                spriteCounter = 0;
                spriteNumIdle = 1;
            }
        } else {
            if (spriteCounter > 85){
                spriteNumIdle = (spriteNumIdle % 3) + 1;
                spriteCounter = 0;
            }
        }


    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
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
            case "idleR":
                image = switch(spriteNumIdle){
                    case 1 -> idleR1;
                    case 2 -> idleR2;
                    case 3 -> idleR3;
                    default -> idleR1;
                };
                break;
            case "idleL":
                image = switch(spriteNumIdle){
                    case 1 -> idleL1;
                    case 2 -> idleL2;
                    case 3 -> idleL3;
                    default -> idleL1;
                };
                break;
        }

        g2.drawImage(image, x, y, 32 * gp.scale, 39 * gp.scale, null);
    }
}



//        if (spriteCounter > 10){
//            if (spriteNum == 1){
//                spriteNum = 2;
//            } else if (spriteNum == 2){
//                spriteNum = 3;
//            } else if (spriteNum == 3){
//                spriteNum = 4;
//            } else {
//                spriteNum = 1;
//            }
//            spriteCounter = 0;
//        }
//        spriteCounterIdle++;
//        if (spriteCounterIdle > 65){
//            if (spriteNumIdle == 1){
//                spriteNumIdle = 2;
//            } else if (spriteNumIdle == 2){
//                spriteNumIdle = 3;
//            } else {
//                spriteNumIdle = 1;
//            }
//            spriteCounterIdle = 0;
//        }

//        switch(direction){
//            case "left":
//                if (spriteNum == 1){
//                    image = left1;
//                }
//                if (spriteNum == 2){
//                    image = left2;
//                }
//                if (spriteNum == 3){
//                    image = left3;
//                }
//                if (spriteNum == 4){
//                    image = left4;
//                }
//                break;
//            case "right":
//                if (spriteNum == 1){
//                    image = right1;
//                }
//                if (spriteNum == 2){
//                    image = right2;
//                }
//                if (spriteNum == 3){
//                    image = right3;
//                }
//                if (spriteNum == 4){
//                    image = right4;
//                }
//                break;
//            case "idleR":
//                if (spriteNumIdle == 1){
//                    image = idleR1;
//                }
//                if (spriteNumIdle == 2){
//                    image = idleR2;
//                }
//                if (spriteNumIdle == 3){
//                    image = idleR3;
//                }
//                break;
//            case "idleL":
//                if (spriteNumIdle == 1){
//                    image = idleL1;
//                }
//                if (spriteNumIdle == 2){
//                    image = idleL2;
//                }
//                if (spriteNumIdle == 3){
//                    image = idleL3;
//                }
//                break;
//        }