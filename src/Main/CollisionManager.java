package Main;

import Entity.*;
import Tile.*;

import java.awt.*;

public class CollisionManager {
    GamePanel gp;
    final int [][] collisionMap;
    public CollisionManager(GamePanel gp){
        this.gp = gp;
        collisionMap = gp.tileManager.collisionMap;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entityLeftWorldX + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entityTopWorldY + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;


        int tileNum1, tileNum2;
        CollisionTile tile1, tile2;
        switch (entity.direction){
            case "up":
                if ( /* OUT OF BOUNDS ARRIBA*/entityTopWorldY - entity.speed <= 0){
                    entity.collisionOn = true;
                    return;
                }
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.collisionMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.collisionMap[entityTopRow][entityRightCol];
                 if (tileNum1 == 1 || tileNum2 == 1){
                     entity.collisionOn = true;
                 }
                break;
            case "down":
                if (/* OUT OF BOUNDS ABAJO*/entityBottomWorldY + entity.speed >= gp.maxWorldRow * gp.tileSize){
                    entity.collisionOn = true;
                    return;
                }
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.collisionMap[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.collisionMap[entityBottomRow][entityRightCol];
                if (tileNum1 == 1 || tileNum2 == 1 ){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                if ( /* OUT OF BOUNDS IZQUIERDA*/ entityLeftWorldX - entity.speed <= 0){
                    entity.collisionOn = true;
                    return;
                }
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.collisionMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.collisionMap[entityBottomRow][entityLeftCol];
                if (tileNum1 == 1 || tileNum2 == 1 ){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                if (/* OUT OF BOUNDS DERECHA*/entityRightWorldX + entity.speed >= gp.maxWorldCol * gp.tileSize){
                    entity.collisionOn = true;
                    return;
                }
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.collisionMap[entityBottomRow][entityRightCol];
                tileNum2 = gp.tileManager.collisionMap[entityBottomRow][entityRightCol];
                if (tileNum1 == 1 || tileNum2 == 1 ){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public void drawCollisions(Graphics2D g2){
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                int num = collisionMap[i][j];
                if (num == 1) {
                    int worldY = i * gp.tileSize;
                    int worldX = j * gp.tileSize;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;



                    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                        g2.setColor(Color.blue);
                        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
                    }
                }
            }
        }
    }
}
