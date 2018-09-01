/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author jrettinghouse
 */
public class NonLethal extends GameObject{
    Image img;
    private int Id;
    private int lockNum = 0;
    /*
    ID cheat sheet for distinguishing exits and harmful items
    lets koala know what to do, die or disappear without reset
    
    1: locks
    2: switches
    3: rocks
    4: exits1
    5: exits2
    
    */

    public NonLethal(Image img, int x, int y, int speed, int id, int functionNumber) {
        super(img, x, y, speed);
        this.img = img;
        this.y = y;
        this.x = x;
        this.speed = 0;
        Id = id;
      
        switch (id){
            // case 1-2 are blue switch and locks
            case 1:
                lockNum = functionNumber;
                break;
            case 2:
                lockNum = functionNumber;
                break;
            // case 3-4 are yellow switch and locks
            case 3:
                 lockNum = functionNumber;
                break;
            case 4:
                lockNum = functionNumber;
                break;
            // case 5-6 are red switch and locks
            case 5:
                lockNum = functionNumber;
                break;
            case 6:
                lockNum = functionNumber;
                break;               
            case 7: // exit1 where all koalas can leave
                lockNum = functionNumber;
                break;
            case 8: // exit 2 where only one koala can leave
                lockNum = functionNumber;
                break;
            case 9: // rock
                lockNum = functionNumber;
                speed = 1;
                x += speed;
                y+= speed;
                break;
                
        }
    }
    
    public int getId(){
        return Id;
    }
    
    public Image getImage() {
        return this.img;
    }
    public void setImage(Image i) {
         this.img = i;
    }
    
    @Override
      public void draw(Graphics g, ImageObserver i) {
      g.drawImage(img, x, y, i);
    } 
      
    public int getLockNumber(){
        return lockNum;
    }  
    
}
