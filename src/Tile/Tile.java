package Tile;
import java.awt.image.BufferedImage;
public class Tile {
    public BufferedImage image;
    public boolean collision;
    public int width = 1;
    public int height = 1;
    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }
    public Tile(BufferedImage image, boolean collision, int width, int height) {
        this.image = image;
        this.collision = collision;
        this.width = width;
        this.height = height;
    }

}
