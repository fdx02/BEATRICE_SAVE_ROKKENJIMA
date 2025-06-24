package Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ITEM_Mariposa extends Item{
    public ITEM_Mariposa() {
        nombre = "Mariposa";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/Mariposa.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}