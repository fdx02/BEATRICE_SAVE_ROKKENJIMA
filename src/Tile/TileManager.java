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
        tiles = new Tile[10];
        tileMap1 = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();
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
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        //TODO
        //hay un problema y es que el piso se esta dibujando despues que la pared, entonces parte del piso queda escondido por atras de la pared
        //posible solucion: hacer 2 mapas distintos, y dibujar uno despues que el otro
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int row = 0; row < gp.maxScreenRow; row++){
                String line = br.readLine();
                if (line == null) break;
                String[] numbers = line.split(" ");
                for (int col = 0; col< gp.maxScreenCol; col++){
                    tileMap1[row][col] = Integer.parseInt(numbers[col]);
                }
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        for (int row = 0; row< gp.maxScreenRow; row++){
            for (int col = 0; col< gp.maxScreenCol; col++){
                int tileNum = tileMap1[row][col];
                if (tileNum >= 0 && tileNum < tiles.length && tiles[tileNum] != null){
                    g2.drawImage(tiles[tileMap1[row][col]].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
