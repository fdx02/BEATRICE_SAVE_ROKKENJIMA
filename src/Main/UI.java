package Main;

import Item.ITEM_Machete;
import Item.ITEM_Mariposa;
import Item.ITEM_Oro;
import Item.ITEM_Te;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_35;
    BufferedImage oroImage, mariposaImage, teImage, macheteImage;
    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_35 = new Font("Arial", Font.PLAIN, 35);
        ITEM_Machete machete = new ITEM_Machete();
        ITEM_Mariposa mariposa = new ITEM_Mariposa();
        ITEM_Oro oro = new ITEM_Oro();
        ITEM_Te te = new ITEM_Te();
        this.mariposaImage = mariposa.image;
        this.oroImage = oro.image;
        this.macheteImage = machete.image;
        this.teImage = te.image;
    }
    public void draw(Graphics g2){
        g2.setFont(arial_35);
        g2.setColor(Color.WHITE);
        g2.drawImage(oroImage, 40, 50,gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("X " + gp.player.oro, 100,90);

        g2.drawImage(mariposaImage, 40, 100,gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("X " + gp.player.mariposa, 100,140);

        g2.drawImage(teImage, 40, 150,gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("X " + gp.player.te, 100,190);

        g2.drawImage(macheteImage, 40, 200,gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("X " + gp.player.machete, 100,240);


    }
}
