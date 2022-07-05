package game;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon {

    GamePanel gp;
    int id;
    int x;
    int y;
    char direction = 'R';
    boolean attack = false;
    boolean faceLeft = false;

    BufferedImage image;
    //constructor
    public Weapon(int id,int x, int y, GamePanel gp, char direction, boolean attack, boolean faceLeft) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.direction = direction;
        this.faceLeft = faceLeft;
        if (attack){
            this.image = getPlayerImage("sprites/cut-3.png");
            System.out.println("attack");
        } 
        else
        this.image = getPlayerImage("sprites/BloodKatana.png");
    }
    public BufferedImage getPlayerImage(String Path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(Path));
        } catch (Exception e) {
            System.out.println("loi file player anh");
        }
        return null;
    }
    //draw weapon
    public void draw(Graphics2D g) {
        
            switch (direction) {
                case 'R':
                    g.drawImage(image, x+gp.UNIT_SIZE/2, y+20, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
                    break;            
                case 'L':
                    g.drawImage(image, x+gp.UNIT_SIZE/2, y+20, -gp.UNIT_SIZE, gp.UNIT_SIZE, null);
                    break;           
                case 'U':
                    if(!faceLeft){
                        g.rotate(-Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                        g.drawImage(image, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/5, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
                        g.rotate(Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                    }else{
                        g.rotate(-Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                        g.drawImage(image, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE*4/5, gp.UNIT_SIZE, -gp.UNIT_SIZE, null);
                        g.rotate(Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                    }
                    

                    break;            
                case 'D':
                    if (!faceLeft){
                        g.rotate(Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                        g.drawImage(image, x+gp.UNIT_SIZE*3/4, y, gp.UNIT_SIZE, gp.UNIT_SIZE, null);
                        g.rotate(-Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                    }else{
                        g.rotate(Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                        g.drawImage(image, x+gp.UNIT_SIZE*3/4, y+gp.UNIT_SIZE, gp.UNIT_SIZE, -gp.UNIT_SIZE, null);
                        g.rotate(-Math.PI/2, x+gp.UNIT_SIZE/2, y+gp.UNIT_SIZE/2);
                    }
                    
                    break;
            
                default:
                    break;
            } 
        
        
    }
    
}
