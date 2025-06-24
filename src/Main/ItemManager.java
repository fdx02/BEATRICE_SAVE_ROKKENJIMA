package Main;
import Entity.*;
import Item.*;
import Tile.CollisionTile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ItemManager {
    GamePanel gp;
    Item[] item;
    Item[] itemsActivos;
    int[][] tileMapItems;
    final int scale;
    final int tileSize;
    static final int tileVacia = 9;
    int timerItems;
    public ItemManager(GamePanel gp) {
        this.gp = gp;
        item = new Item[4];
        itemsActivos = new Item[15];
        tileMapItems = new int[gp.maxWorldRow][gp.maxWorldCol];
        scale = gp.scale;
        tileSize = gp.tileSize;
        getTile();
        loadMap("/maps/mapaItems.txt", tileMapItems);
    }

    public void draw(Graphics2D g2){
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                int num = tileMapItems[i][j];
                if (num >= 0 && num != tileVacia) {
                    int worldY = i * tileSize;
                    int worldX = j * tileSize;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int tileWidth = item[num].width * tileSize;
                    int tileHeight = item[num].height * tileSize;


                    if (worldX + tileWidth > gp.player.worldX - gp.player.screenX &&
                        worldX - tileWidth < gp.player.worldX + gp.player.screenX &&
                        worldY + tileHeight > gp.player.worldY - gp.player.screenY &&
                        worldY - tileHeight < gp.player.worldY + gp.player.screenY) {
                            g2.drawImage(item[num].image, screenX, screenY, tileWidth, tileHeight, null);
                    }
                }
            }
        }
    }

    public Item checkItemCollision(Entity entity){
        int tileX = (entity.worldX + entity.hitbox.x + (entity.hitbox.width/2)) / tileSize;
        int tileY = (entity.worldY + entity.hitbox.y + (entity.hitbox.height/2)) / tileSize;
        if (tileMapItems[tileY][tileX] != tileVacia) {
            int itemNum = tileMapItems[tileY][tileX];
            tileMapItems[tileY][tileX] = tileVacia;
            return item[itemNum];
        }
        return null;
    }

    public void loadMap(String filePath, int[][] matriz){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gp.maxWorldRow; i++){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j < gp.maxWorldCol; j++){
                    int num = Integer.parseInt(numbers[j]);
                    matriz[i][j] = num;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(){
        timerItems++;
        //cuando pasan 30 segundos los items vuelven a spawnear
        if (timerItems > 60*30){
            loadMap("/maps/mapaItems.txt", tileMapItems);
            timerItems = 0;
        }
    }
    public void getTile(){
        item[0] = new ITEM_Machete();
        item[1] = new ITEM_Mariposa();
        item[2] = new ITEM_Oro();
        item[3] = new ITEM_Te();
    }

    public void resetItems(){
        loadMap("/maps/mapaItems.txt", tileMapItems);
        timerItems = 0;
    }
}
