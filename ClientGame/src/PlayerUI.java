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

    boolean attack = false;
    boolean move = false;
    char direction = 'R';
    boolean faceLeft = false;

    int health = 6;

    int step = 1;

    //constructor
    public PlayerUI(int id,int x, int y, GamePanel gp, int step, boolean faceLeft, boolean move, int health) {
        this.id = id;
        this.step = step;
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.faceLeft = faceLeft;
        this.move = move;
        this.health = health;
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
        g.fillRect(x-18,y-25,22*6+4,20);
        if (this.id != gp.playerCurrent.id){
            g.setColor(Color.RED);
        }else
        g.setColor(Color.green);
        for (int i = 0; i < health; i++) {
            g.fillRect(x - 15 +(i*22), y - 22, 20, 14);
        }
        
        g.setColor(Color.BLACK);

        g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        g.drawString("Player: " + this.id, x+10, y-40);
        if (!faceLeft)
        g.drawImage(image, x, y, gp.UNIT_SIZE, gp.UNIT_SIZE,null);
        else 
        g.drawImage(image, x+gp.UNIT_SIZE, y, -gp.UNIT_SIZE, gp.UNIT_SIZE,null);
    }

}
