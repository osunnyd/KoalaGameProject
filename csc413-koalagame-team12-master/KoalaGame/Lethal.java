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
public class Lethal extends GameObject {
    Image img;
    private boolean death;
    private int Id;
    
    /*
    ID cheat sheet for distinguishing exits and harmful items
    lets koala know what to do, die or disappear without reset
    
    1:detonator
    2:tnt
    3:saws    
    */
    
    
    public Lethal(Image img, int x, int y, int speed, int id) {
        super(img, x, y, speed);
        this.img = img;
        this.y = y;
        this.x = x;
        this.speed = 0;
        Id = id;
        if(id > 0 /*or anything*/ ){
            death = true;
        } else {
            death = false;
        }
        
        
    }
    
    
    public int getId(){
        return Id;
    }
    
    public boolean getDeadly(){
        return death;
    }   
    
    public void setImg(Image i) {
        this.img = i;
    }
    @Override
      public void draw(Graphics g, ImageObserver i) {
      g.drawImage(img, x, y, i);
    } 
    
}
