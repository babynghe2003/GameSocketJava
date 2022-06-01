import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerUI {
    int id;
    int x;
    int y;
    BufferedImage image;
    GamePanel gp;

    boolean move = false;
    char direction = 'R';
    boolean faceLeft = false;

    int step = 1;

    //constructor
    public PlayerUI(int id,int x, int y, GamePanel gp, int step, boolean faceLeft, boolean move) {
        this.id = id;
        this.step = step;
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.faceLeft = faceLeft;
        this.move = move;
        if (id ==0){
            if (move)
            this.image = getPlayerImage("sprites/AssasinMoveRight-"+step+".png");
            else
            this.image = getPlayerImage("sprites/AssasinRight-"+step+".png");
        }else{
            if (move)
            this.image = getPlayerImage("sprites/BabyDragonRun-"+step+".png");
            else
            this.image = getPlayerImage("sprites/BabyDragon-"+step+".png");
        }
        
    }

    // get buffered image by path
    public BufferedImage getPlayerImage(String Path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(Path));
        } catch (Exception e) {
            System.out.println("loi file player anh");
        }
        return null;
    }
    //draw graphics2d
    public void draw(Graphics2D g) {
        // camera folowing player translate
    
        //draw string id 

        g.setColor(Color.BLACK);
        
        g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        g.drawString("Player: " + this.id, x+10, y-3);
        if (!faceLeft)
        g.drawImage(image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE,null);
        else 
        g.drawImage(image, x+gp.UNIT_SIZE, y, -gp.UNIT_SIZE, gp.UNIT_SIZE,null);
    }

}
