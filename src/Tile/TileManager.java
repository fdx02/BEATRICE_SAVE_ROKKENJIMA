package Tile;
import Main.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    public TileManager(GamePanel GP) {
        this.gp = GP;
        tiles = new Tile[10];
        getTileImage();
    }
    public void getTileImage(){
        try{
            //TODO TERMINAR TODO ESTO CON EL VIDEO DEL CHINO DESPUES DE BDD Y SUBIR A GIT
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
    public void draw(Graphics2D g2){
        for (int i = 0; i< gp.maxScreenCol; i++){
            for (int j = 0; j< gp.maxScreenRow; j++){
                g2.drawImage(tiles[0].image, i * gp.tileSize, j * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        }
    }
//    public void draw(Graphics2D g2){
//        int col= 0;
//        int row = 0;
//        int x =0;
//        int y = 0;
//        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
//            g2.drawImage(tiles[0].image, x,y,gp.tileSize,gp.tileSize, null);
//            col++;
//            x += gp.tileSize;
//            if (col == gp.maxScreenCol){
//                col = 0;
//                x= 0;
//                row++;
//                y += gp.tileSize;
//            }
//        }
//    }
}
