/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Sunny
 */
public class MouseInput implements MouseListener{ 

    @Override
    public void mouseClicked(MouseEvent me) {
       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int mx =me.getX();
        int my =me.getY();
       System.out.println("X : " + mx);
       System.out.println("Y : " + my);
        // start button
     if (mx >=135 && mx <= 235){ // width of buttons = 100, height = 40
           if (my >= 360 && my <=400){
               GameWorld.State = GameWorld.STATE.GAME;
           }
    }
     //quit button 
     if (mx >=520 && mx <= 635){ 
           if (my >= 360 && my <=400){
               System.exit(0);
           }
    }
     
     // restart button
     if (mx >=655 && mx <= 755){ 
           if (my >= 520 && my <=560){
               GameWorld.State = GameWorld.STATE.RESTART;
           }
    }

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    
    }

    @Override
    public void mouseEntered(MouseEvent me) {
      
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }
    
    
}
