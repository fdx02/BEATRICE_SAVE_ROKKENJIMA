package Tile;
import Entity.Entity;
import Main.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    Tile[] items;
    CollisionTile[] collisionTiles;
    int[][] tileMapBG;
    int[][] tileMapFG;
    public int[][] collisionMap;
    final int scale;
    final int tileSize;
    static final int tileVacia = 9;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[9];
        items = new Tile[4];
        collisionTiles = new CollisionTile[9];
        tileMapBG = new int[gp.maxWorldRow][gp.maxWorldCol];
        tileMapFG = new int[gp.maxWorldRow][gp.maxWorldCol];
        collisionMap = new int[gp.maxWorldRow][gp.maxWorldCol];
        scale = gp.scale;
        tileSize = gp.tileSize;
        getTileImage();
        getTileImageItems();
        loadBG();
        loadFG();
        loadCollisions();
    }

    public void loadBG(){
        loadMap("/maps/mapaBG.txt", tileMapBG);
    }
    public void loadFG(){
        loadMap("/maps/mapaFG.txt", tileMapFG);
    }
    public void loadCollisions(){
        loadMap("/maps/collisionMap.txt", collisionMap);
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
    public void drawBG(Graphics2D g2) {
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                int num = tileMapBG[i][j];
                if (num >= 0 && num != tileVacia) {
                    int worldY = i * tileSize;
                    int worldX = j * tileSize;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int tileWidth = tiles[num].width * tileSize;
                    int tileHeight = tiles[num].height * tileSize;


                    if (worldX + tileWidth > gp.player.worldX - gp.player.screenX &&
                        worldX - tileWidth < gp.player.worldX + gp.player.screenX &&
                        worldY + tileHeight > gp.player.worldY - gp.player.screenY &&
                        worldY - tileHeight < gp.player.worldY + gp.player.screenY) {
                            g2.drawImage(tiles[num].image, screenX, screenY, tileWidth, tileHeight, null);
                    }
                }
            }
        }
    }

    public void drawFG(Graphics2D g2) {
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                int num = tileMapFG[i][j];
                if (num >= 0 && num != tileVacia) {
                    int worldY = i * tileSize;
                    int worldX = j * tileSize;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int tileWidth = tiles[num].width * tileSize;
                    int tileHeight = tiles[num].height * tileSize;


                    if (worldX + tileWidth > gp.player.worldX - gp.player.screenX &&
                        worldX - tileWidth < gp.player.worldX + gp.player.screenX &&
                        worldY + tileHeight > gp.player.worldY - gp.player.screenY &&
                        worldY - tileHeight < gp.player.worldY + gp.player.screenY) {
                            g2.drawImage(tiles[num].image, screenX, screenY, tileWidth, tileHeight, null);
                    }
                }
            }
        }
    }

    public String checkTerreno(Entity entity){
        int tileX = (entity.worldX + entity.hitbox.x + (entity.hitbox.width/2)) / tileSize;
        int tileY = (entity.worldY + entity.hitbox.y + (entity.hitbox.height/2)) / tileSize;
        if (tileMapBG[tileY][tileX] != tileVacia) {
            int tileNum = tileMapBG[tileY][tileX];
            return tiles[tileNum].terreno;
        }
        return null;
    }

    public void getTileImage(){
        try{
            // tiles[9] TILE VACIO --!!!!!!!!!!!!!!! -> variable static

            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Pasto.png")), false);
            tiles[0].setTerreno("pasto");

            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/PastoFlor.png")), false);
            tiles[1].setTerreno("pasto");

            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Agua.png")), false);

            tiles[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Arena.png")), false);
            tiles[3].setTerreno("pasto");

            tiles[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Tierra.png")), false);

            tiles[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/PisoLadrillo.png")), false);
            tiles[5].setTerreno("piedra");

            tiles[6] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Piso.png")), false);
            tiles[6].setTerreno("piedra");

            tiles[7] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Bush.png")), true, 4,3);

            tiles[8] = new Tile(ImageIO.read(getClass().getResourceAsStream("/tiles/Arbol.png")), true, 2,3);

//            tiles[1] = new Tile();
//            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/PastoFlor.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getTileImageItems(){
        try{
            // tiles[9] TILE VACIO --!!!!!!!!!!!!!!! -> variable static

            items[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/Machete.png")), false);

            items[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/Mariposa.png")), false);

            items[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/Oro.png")), false);

            items[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/items/Te.png")), false);


        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
