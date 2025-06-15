package Tile;
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
    int[][] tileMap1;

    public TileManager(GamePanel GP) {
        this.gp = GP;
        tiles = new Tile[18];
        tileMap1 = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("/maps/world.txt");
    }
    public void loadMap(String MAP){
        //TODO
        //hay un problema y es que el piso se esta dibujando despues que la pared, entonces parte del piso queda escondido por atras de la pared
        //posible solucion: hacer 2 mapas distintos, y dibujar uno despues que el otro
        try{
            InputStream is = getClass().getResourceAsStream(MAP);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int row = 0; row < gp.maxWorldRow; row++){
                String line = br.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                for (int col = 0; col< gp.maxWorldCol; col++){
                    tileMap1[row][col] = Integer.parseInt(numbers[col]);
                }
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        for (int row = 0; row< gp.maxWorldRow; row++){
            for (int col = 0; col< gp.maxWorldCol; col++){
                int tileNum = tileMap1[row][col];
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX){
                    if (tileNum >= 0 && tileNum < tiles.length && tiles[tileNum] != null){
                        g2.drawImage(tiles[tileNum].image, screenX, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
    public void getTileImage(){
        try{
            //ALFOMBRA -> 0
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Alfombra.png"));
            //PARED LIMPIA -> 1
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Pared.png"));
            //PARED LEFT -> 2
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedLeft.png"));
            //PARED RIGHT -> 3
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedRight.png"));
            //PARED UP -> 4
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedUp.png"));
            //PARED DOWN -> 5
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedDown.png"));
            //PARED ESQ ABAJO IZQ -> 6
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedEsqDownLeft.png"));
            //PARED ESQ ABAJO DER -> 7
            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedEsqDownRight.png"));
            //PARED ESQ ARRIBA DER -> 8
            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedEsqUpRight.png"));
            //PARED ESQ ARRIBA IZQ -> 9
            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ParedEsqUpLeft.png"));
            //VENTANA ESQ ARRIBA IZQ -> 10
            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/VentanaUpLeft.png"));
            //VENTANA ESQ ARRIBA DER -> 11
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/VentanaUpRight.png"));
            //VENTANA ESQ ABAJO IZQ -> 12
            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/VentanaDownLeft.png"));
            //VENTANA ESQ ABAJO DER -> 13
            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/VentanaDownRight.png"));
            //PASTO -> 14
            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Pasto.png"));
            //PISO DE LADRILLOS -> 15
            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/PisoLadrillo.png"));
            //TIERRA -> 16
            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tierra.png"));
            //PISO BALDOSA -> 17
            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Piso.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
