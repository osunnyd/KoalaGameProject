/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

/**
 *
 * @author jrettinghouse
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.Observer;
import java.util.Observable;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Koala extends GameObject implements Observer, ActionListener{
  
    int ANGLE;
    private int animFrame = 0;
    Image [] animation = new Image [3];
    public static boolean[] keys = new boolean [120];
    private double velX , velY ;
    private boolean ranIntoObject;
    private boolean walking;
    private Image img, rock, dead;
   private boolean koalaDied = false;
    private int up, down, left, right;
    private int speed;
    Timer t;
    Graphics2D g;
    
    Koala(Image img, int x, int y, int up, int down, int left, int right, int speed){
        super(img,x,y,speed);
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        walking = false;
        ranIntoObject = false;
        t = new Timer(10, this);
        t.start();
        try {
              dead = ImageIO.read(Player.class.getResource("/Koala_Resources/Koala_dead.png"));
              animation[0] = ImageIO.read(Player.class.getResource("/Koala_Resources/Koala_Stand.png"));
              animation[1] = ImageIO.read(Player.class.getResource("/Koala_Resources/Koala_anim1.png"));
              animation[2] = ImageIO.read(Player.class.getResource("/Koala_Resources/Koala_anim2.png"));
              rock = ImageIO.read(Player.class.getResource("/Koala_Resources/Rock.png"));
        }catch (Exception e) {
            System.out.println("Koala animaton not found.");
        }
    }
    
    public double getdX() {
        return velX;
    }
      public double getdY() {
        return velY;
    }
    
    @Override
    public void draw(Graphics g, ImageObserver i) {
       
        this.g = (Graphics2D) g;
        AffineTransform transform = this.g.getTransform(); // save transformation to reset later
        //rotate at center
        this.g.rotate(Math.toRadians(ANGLE), super.getX() + super.getWidth() / 2, super.getY() + super.getHeight() / 2);
        
        // walking animation
           if (walking) {
        if (animFrame % 40 >= 30) {
            img = animation[0];
           
        }else if (animFrame % 40 >= 20) {
           img = animation[1];
           
        } else if (animFrame % 40 >= 10) {
            img = animation[0];
           
        }
        else if (animFrame % 40 >= 0) {
            img = animation[2];
        }
           }
         if (!walking) {
             img = animation[0];
         }
         if (koalaDied) {
             img = dead;
             ANGLE = 0;
             velX = 0;
             velY = 0;
           
             
         }
         
        this.g.drawImage(img, super.getX(), super.getY(), i); 
        this.g.setTransform(transform); // reset graphic to not rotate everything 
        

        
    }
    
    @Override
    public void update(Observable o, Object o1){
        GameEvents event1 = (GameEvents) o;
        if (event1.type == 1) {
            KeyEvent e = (KeyEvent) event1.event;
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                keys[e.getKeyCode()] = false; // if a arrow key or wasd is released then false
                // stops movement
                velX = 0;
                velY = 0;
                walking = false;
                update();
            }
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keys[e.getKeyCode()] = true; // if  arrow key or wasd is pressed then true
                walking = true;
                update();
            }
        }
    }
    
    public void update() {
        // updating walking animation
         
         // movement; true or false based on key presses 
        if (keys[KeyEvent.VK_UP]|| keys[KeyEvent.VK_W]) {
            //need to multiply by some factor at the end so it doesn't instantly int cast to 0   
            ANGLE = 180;
            velY = -speed;
        }
        if (keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]) {
            ANGLE = 0;
            velY = speed;
        }
        if (keys[KeyEvent.VK_LEFT]|| keys[KeyEvent.VK_A]) {
            ANGLE = 90;
            velX = -speed;

        }
        if (keys[KeyEvent.VK_RIGHT]|| keys[KeyEvent.VK_D]) {
            ANGLE = 270;
            velX = speed;
        }
        if (keys[KeyEvent.VK_ENTER] || keys[KeyEvent.VK_SPACE]) {
            //an action
         
        }
        if (keys[KeyEvent.VK_R]) {
            if (GameWorld.State == GameWorld.STATE.MENU) {
            } else {
                GameWorld.State = GameWorld.STATE.RESTART;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        // keeping track of walking animation
        if (animFrame < 7500){
             animFrame++;
         }else {
             animFrame = 0;
         }
        
        // need x+=velX for smooth movement; constantly updates positon 
        if(ranIntoObject == true){ //for collision of tank vs tank and tank vs wall
            x -= velX;
            y -= velY;
        }else{
            x += velX;
            y += velY;  
        }
        ranIntoObject = false;
    }     
    public void koalaDied(boolean dead) { 
        this.width = 0;
             this.height=0;
        koalaDied = true;
    }
     public void ranIntoObject(boolean changeDirection){
        ranIntoObject = changeDirection;
    } 
     public boolean isKoalaDead() {
         return koalaDied;
     }
}

