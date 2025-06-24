package Item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ITEM_Machete extends Item{
    public ITEM_Machete() {
        nombre = "Machete";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/Machete.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
