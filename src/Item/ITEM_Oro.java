package Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ITEM_Oro extends Item{
    public ITEM_Oro() {
        nombre = "Oro";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/Oro.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}