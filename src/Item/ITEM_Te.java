package Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ITEM_Te extends Item{
    public ITEM_Te() {
        nombre = "Te";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/Te.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}