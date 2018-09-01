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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Hazards {
    
    private Image exit1, exit2, rock, blueSwitch, blueSwitch2, lockBlue, lockYellow, lockRed,
                yellowSwitch, yellowSwitch2, redSwitch, redSwitch2;
    private Image detonator0, detonator1, tnt, sawH1, sawH2, sawV1, sawV2;
    private ArrayList <NonLethal> NonLethalHazards = new ArrayList<>();
    private ArrayList <Lethal> LethalHazards = new ArrayList<>();
    
    
    
    public Hazards(){
      
        /*
        non-lethal: need locks, switches, rock, and exits
        lethal: detonator, tnt, and saws
        */
        try {
            //NonLethal
            exit1 = ImageIO.read(Player.class.getResource("/Koala_Resources/Exit1.png")); // multiple koalas can exit
            exit2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Exit2.png")); // only one koala can exit
            rock = ImageIO.read(Player.class.getResource("/Koala_Resources/Rock.png"));
            lockBlue = ImageIO.read(Player.class.getResource("/Koala_Resources/Lock_blue.png"));
            lockYellow = ImageIO.read(Player.class.getResource("/Koala_Resources/Lock_yellow.png"));
            lockRed = ImageIO.read(Player.class.getResource("/Koala_Resources/Lock_red.png"));
            blueSwitch = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_blue0.png"));
            blueSwitch2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_blue1.png"));
            yellowSwitch = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_yellow0.png"));
            yellowSwitch2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_yellow1.png"));
            redSwitch = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_red0.png"));
            redSwitch2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Switch_red1.png"));
            
            //Lethal
            detonator0 = ImageIO.read(Player.class.getResource("/Koala_Resources/Detonator0.png"));
            detonator1 = ImageIO.read(Player.class.getResource("/Koala_Resources/Detonator1.png"));
            tnt = ImageIO.read(Player.class.getResource("/Koala_Resources/TNT.png"));
            sawV1 = ImageIO.read(Player.class.getResource("/Koala_Resources/Saw_vertical1.png"));
            sawV2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Saw_vertical2.png"));
            sawH1 = ImageIO.read(Player.class.getResource("/Koala_Resources/Saw_horizontal1.png"));
            sawH2 = ImageIO.read(Player.class.getResource("/Koala_Resources/Saw_horizontal2.png"));
            
            // initial level load
            NonLethalHazards.add(new NonLethal (exit1, 400, 440, 0, 7, 0)); 
          //  NonLethalHazards.add(new NonLethal (exit2, 600, 400, 0, 8, 0));
           // NonLethalHazards.add(new NonLethal (rock,  300, 400, 0, 9, 0));           
           // LethalHazards.add(new Lethal(detonator0, 200, 300, 0, 1));
           // LethalHazards.add(new Lethal(tnt, 200, 350, 0, 2));
           // LethalHazards.add(new Lethal(sawV1, 200, 400, 0, 3));
            //NonLethalHazards.add(new NonLethal (lock, 300 , 300, 0, 1, 1 ));
            //NonLethalHazards.add(new NonLethal (theSwitch, 300, 200, 0 , 2, 1 ));
            
        } catch (Exception e) {
            System.out.println("Hazard Resource loading failed." + e);
        }
    }
    
    
    // locks will always be placed after their color switch
    public void levelChange(int level){
        NonLethalHazards.clear();
        LethalHazards.clear();
         /*
            275 is about the middle of entire walls
            75 is starting corner
            450 height
            75, 75 is upper left corner of map
            685, 75 is upper right corner of map
            75, 450 is bottom left corner of map 
            685, 450 is bottom right corner of map / end of map
        
            */
        switch (level) {
            //REMEMBER TO CHANGE LOCK AND SWTICH IDS
            // 135 for blue, yellow, red locks
            //246 for blue, yellow, red siwtches
            case 0:
                NonLethalHazards.add(new NonLethal(exit1, 400, 440, 0, 7, 0)); 
                break;
            case 1:
                LethalHazards.add(new Lethal(sawH1, 175, 75, 0, 4));
                NonLethalHazards.add(new NonLethal(exit1, 400, 300, 0, 7, 0)); 
                NonLethalHazards.add(new NonLethal(redSwitch, 350, 75, 0, 6, 1));
                NonLethalHazards.add(new NonLethal(lockRed, 125, 275, 0, 5, 1));           
                NonLethalHazards.add(new NonLethal(lockRed, 325, 275, 0, 5, 1));           
                NonLethalHazards.add(new NonLethal(yellowSwitch, 75, 450, 0, 4, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 150, 440, 0, 3, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 250, 75, 0, 3, 1));
                break;
                
            case 2:
                NonLethalHazards.add(new NonLethal(exit2, 685, 75, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 685, 275, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 685, 450, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(rock, 160, 200, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(rock, 300, 200, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(rock, 350, 200, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(redSwitch, 205, 435, 0, 6, 1));
                NonLethalHazards.add(new NonLethal(lockRed, 645, 75, 0, 5, 1));
                NonLethalHazards.add(new NonLethal(lockRed, 685, 120, 0, 5, 1));
                NonLethalHazards.add(new NonLethal(blueSwitch, 280, 80, 0, 2, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 645, 275, 0, 1, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 680, 230, 0, 1, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 685, 315, 0, 1, 1));
                LethalHazards.add(new Lethal(tnt, 200, 350, 0, 2));
                LethalHazards.add(new Lethal(tnt, 300, 250, 0, 2));
                LethalHazards.add(new Lethal(tnt, 400, 450, 0, 2));
                LethalHazards.add(new Lethal(tnt, 500, 375, 0, 2));
                LethalHazards.add(new Lethal(tnt, 600, 150, 0, 2));
                LethalHazards.add(new Lethal(sawV1, 200, 395, 0, 3));
                break;
            case 3:
                NonLethalHazards.add(new NonLethal(exit2, 685, 75, 0, 8, 0));
                LethalHazards.add(new Lethal(sawV1, 110, 400, 0, 3));
                LethalHazards.add(new Lethal(sawV1, 510, 110, 0, 3));
                LethalHazards.add(new Lethal(sawH2, 200, 55, 0, 4));           
                LethalHazards.add(new Lethal(sawH2, 280, 420, 0, 4));
                LethalHazards.add(new Lethal(sawH2, 360, 55, 0, 4));
                LethalHazards.add(new Lethal(sawH2, 440, 420, 0, 4));
                LethalHazards.add(new Lethal(sawH2, 600, 420, 0, 4));
                LethalHazards.add(new Lethal(tnt, 685, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 320, 0, 2));
                LethalHazards.add(new Lethal(tnt, 685, 240, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 160, 0, 2));
                break;
            case 4:
                NonLethalHazards.add(new NonLethal(exit2, 375, 75, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 375, 200, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 375, 325, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 375, 450, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(blueSwitch, 280, 80, 0, 2, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 335, 75, 0, 1, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 415, 75, 0, 1, 1));
                NonLethalHazards.add(new NonLethal(lockBlue, 375, 100, 0, 1, 1));
                NonLethalHazards.add(new NonLethal(yellowSwitch, 500, 80, 0, 4, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 335, 200, 0, 3, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 375, 240, 0, 3, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 415, 200, 0, 3, 1));
                NonLethalHazards.add(new NonLethal(lockYellow, 375, 160, 0, 3, 1));
                
                 NonLethalHazards.add(new NonLethal(rock, 335, 450, 0, 9, 0));
                 NonLethalHazards.add(new NonLethal(rock, 415, 450, 0, 9, 0));
                 NonLethalHazards.add(new NonLethal(rock, 375, 415, 0, 9, 0));

                LethalHazards.add(new Lethal(tnt, 190, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 130, 320, 0, 2));
                LethalHazards.add(new Lethal(tnt, 190, 200, 0, 2));
                LethalHazards.add(new Lethal(tnt, 130, 100, 0, 2));
                
                LethalHazards.add(new Lethal(tnt, 685, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 320, 0, 2));
                LethalHazards.add(new Lethal(tnt, 685, 240, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 160, 0, 2));
                break;
            case 5:
                NonLethalHazards.add(new NonLethal(exit1, 240, 160, 0, 7, 0));
                LethalHazards.add(new Lethal(detonator0, 160, 360, 0, 1));
                LethalHazards.add(new Lethal(tnt, 120, 75, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 80, 0, 2));
                LethalHazards.add(new Lethal(tnt, 485, 160, 0, 2));
                LethalHazards.add(new Lethal(tnt, 240, 200, 0, 2));
                break;
            case 6:
                 NonLethalHazards.add(new NonLethal(exit1, 575, 450, 0, 7, 0));
                 NonLethalHazards.add(new NonLethal(exit1, 575, 75, 0, 7, 0));
                 LethalHazards.add(new Lethal(tnt, 375, 85, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 375, 130, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 375, 165, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 375, 215, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 175, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 245, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 295, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 345, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 385, 0, 2));
                 LethalHazards.add(new Lethal(tnt, 500, 425, 0, 2));
                break;
            case 7:
                NonLethalHazards.add(new NonLethal(exit2, 685, 450, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 640, 450, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 595, 450, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(rock, 175, 75, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(rock, 175, 215, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(yellowSwitch, 685, 75, 0, 4, 0));
                NonLethalHazards.add(new NonLethal(lockYellow, 280, 400, 0, 3, 1));
                NonLethalHazards.add(new NonLethal(blueSwitch, 75, 75, 0, 2, 0));
                NonLethalHazards.add(new NonLethal(lockBlue, 240, 400, 0, 1, 1));
                LethalHazards.add(new Lethal(detonator0, 320, 400, 0, 1));
                LethalHazards.add(new Lethal(tnt, 685, 360, 0, 2));
                LethalHazards.add(new Lethal(tnt, 640, 360, 0, 2));
                LethalHazards.add(new Lethal(tnt, 595, 360, 0, 2));
                LethalHazards.add(new Lethal(tnt, 685, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 640, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 595, 400, 0, 2));
                LethalHazards.add(new Lethal(tnt, 635, 75, 0, 2));
                LethalHazards.add(new Lethal(tnt, 125, 75, 0, 2));
                LethalHazards.add(new Lethal(tnt, 490, 75, 0, 2));
                LethalHazards.add(new Lethal(tnt, 75, 115, 0, 2));
                LethalHazards.add(new Lethal(tnt, 330, 160, 0, 2));
                LethalHazards.add(new Lethal(tnt, 685, 115, 0, 2));
                break;
            case 8:
                NonLethalHazards.add(new NonLethal(exit1, 680, 75, 0, 7, 0));
                NonLethalHazards.add(new NonLethal(blueSwitch, 80, 360, 0, 2, 0));
                NonLethalHazards.add(new NonLethal(lockBlue, 680, 115, 0, 1, 1));
                LethalHazards.add(new Lethal(tnt, 485, 80, 0, 2));
                LethalHazards.add(new Lethal(tnt, 330, 280, 0, 2));
                LethalHazards.add(new Lethal(tnt, 275, 125, 0, 2));
                LethalHazards.add(new Lethal(sawV1, 140, 380, 0, 3));
                LethalHazards.add(new Lethal(sawV1, 180, 380, 0, 3));
                break;
            case 9:
                NonLethalHazards.add(new NonLethal(exit2, 440, 165, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(exit2, 360, 205, 0, 8, 0));
                NonLethalHazards.add(new NonLethal(rock, 250, 440, 0, 9, 0));
                NonLethalHazards.add(new NonLethal(rock, 400, 280, 0, 9, 0));
                
                NonLethalHazards.add(new NonLethal(blueSwitch, 410, 440, 0, 2, 0));
                NonLethalHazards.add(new NonLethal(lockBlue, 440, 260, 0, 1, 1));
                 NonLethalHazards.add(new NonLethal(redSwitch, 75, 150, 0, 6, 1));
               // NonLethalHazards.add(new NonLethal(lockRed, 520, 240, 0, 5, 1));
                NonLethalHazards.add(new NonLethal(lockRed, 490, 90, 0, 5, 1));
                LethalHazards.add(new Lethal(sawH1, 300, 420, 0, 4));
                LethalHazards.add(new Lethal(tnt, 440, 210, 0, 2));
                break;
        }
    }
    
    public void draw(Graphics g, ImageObserver i) {
        for (int j = 0; j < NonLethalHazards.size(); j++) {
            NonLethalHazards.get(j).draw(g, i);

        }
        for (int j = 0; j < LethalHazards.size(); j++) {
            LethalHazards.get(j).draw(g, i);

        }
    }
    
    public Image getSawV1(){
        return sawV1;
    }
    public Image getSawV2(){
        return sawV2;
    }
    public Image getSawH1(){
        return sawH1;
    }
    public Image getSawH2(){
        return sawH2;
    }
    
    public ArrayList<NonLethal> getNonLethal() {
        return NonLethalHazards;
    }
    
    public ArrayList<Lethal> getLethal(){
        return LethalHazards;
    }
    
    public Image blueSwitchActive() {
        return  blueSwitch2;
    }
    public Image yellowSwitchActive() {
        return  yellowSwitch2;
    }
     public Image yellowSwitchInactive() {
        return  yellowSwitch;
    }
    public Image redSwitchActive() {
        return  redSwitch2;
    }
    public Image redSwitchInactive() {
        return  redSwitch;
    }
    public Image redLock() {
        return lockRed;
    }
    public Image yellowLock() {
        return lockYellow;
    }
    public Image detonated() {
        return detonator1;
    }
}
