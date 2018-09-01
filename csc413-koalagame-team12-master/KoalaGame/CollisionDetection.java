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
import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionDetection {

    private GameEvents gameEvent1, gameEvent2;
    Sound effects = new Sound();
    private int xOffset, yOffset, heightOffset, widthOffset;
    //Walls wall = new Walls();
    private Rectangle wallHitbox, koalaHitbox, nonLethalHitbox;
    private boolean onRedSwitch, onYellowSwitch;
    NonLethal redLockClone;
    private int count = 0;
    int redLockPosition;
    private ArrayList<Rectangle> koalaHitboxes = new ArrayList<>();

    public CollisionDetection(GameEvents gameEventA) {

    }
    // can use koala to get hitbox of rectangle instead

    public void koalaVsWall(Player player1, Walls wall) {
        //testing
        if (!player1.getKoalas().isEmpty()) {
             koalaHitboxes.clear();
            // to get height and width of koalas
            //trying to make multiple koala hitboxes
            for (int i = 0; i < player1.getKoalas().size(); i++) {

                xOffset = (int) (player1.getKoalas().get(i).getX() + player1.getKoalas().get(i).getdX());
                yOffset = (int) (player1.getKoalas().get(i).getY() + player1.getKoalas().get(i).getdY());
                heightOffset = 30; //10 + player1.getKoalas().getWidth()/2;
                widthOffset = 30; // 10 + player1.getKoalas().getHeight()/2;
                Rectangle temp = new Rectangle(xOffset, yOffset, heightOffset, widthOffset);
                koalaHitboxes.add(temp);
            }
        }

        int[][] wallcomparison = wall.getWalls();
        int widthWalls = wall.getMapWidth();
        int lengthWalls = wall.getMapHeight();

        //sometimes corners cause koalas to clip and go through walls
        for (int y = 0; y < widthWalls; y++) {
            for (int x = 0; x < lengthWalls; x++) {

                // make hitbox for every wall
                if (wallcomparison[y][x] >= 1) {
                    wallHitbox = new Rectangle(x * wall.getPictureWidth(), y * wall.getPictureHieght(),
                            10 + wall.getPictureHieght() / 2, 10 + wall.getPictureWidth() / 2);
                    for (int i = 0; i < player1.getKoalas().size(); i++) {
                        koalaHitboxes.get(i);

                        if (wallHitbox.intersects(koalaHitboxes.get(i))) {
                            //  System.out.println("HitBoxes: " + koalaHitboxes.get(i));
                            player1.getKoalas().get(i).ranIntoObject(true);
                        }
                    }
                }
            }
        }
        //  koalaHitboxes.clear();
    }

    public void koalaVsLethal(Player player1, Hazards h) {

        if (!player1.getKoalas().isEmpty()) {
            koalaHitboxes.clear();
            for (int i = 0; i < player1.getKoalas().size(); i++) {

                xOffset = (int) (player1.getKoalas().get(i).getX() + player1.getKoalas().get(i).getdX());
                yOffset = (int) (player1.getKoalas().get(i).getY() + player1.getKoalas().get(i).getdY());
                heightOffset = 30;
                widthOffset = 30;
                Rectangle temp = new Rectangle(xOffset, yOffset, heightOffset, widthOffset);
                koalaHitboxes.add(temp);
            }
            for (int i = 0; i < h.getNonLethal().size(); i++) {
                if (h.getNonLethal().get(i).getId() == 9) {
                    Rectangle rockHitbox =  new Rectangle (h.getNonLethal().get(i).getX(), h.getNonLethal().get(i).getY(), 30, 30);
                }
            }

            for (int i = 0; i < h.getLethal().size(); i++) {
                Rectangle deathbox = new Rectangle(h.getLethal().get(i).getX(), h.getLethal().get(i).getY(), h.getLethal().get(i).getWidth()-10, h.getLethal().get(i).getHeight()-10);
                   for (int j = 0; j < h.getNonLethal().size(); j++) {
                       // check rock vs lethal collisions and then koala vs lethal collisions
                    if (h.getNonLethal().get(j).getId() == 9) {
                Rectangle rockHitbox = new Rectangle(h.getNonLethal().get(j).getX(), h.getNonLethal().get(j).getY(), 30, 30);

                        if (deathbox.intersects(rockHitbox)) {

                            switch (h.getLethal().get(i).getId()) {
                                case 2:
                                    System.out.println("TNT Death");
                                    effects.explodeSound();
                                    rockHitbox.setSize(0, 0);
                                     deathbox.setSize(0, 0);
                                    
                                    h.getNonLethal().remove(j);
                                    h.getLethal().remove(i);
                                    break;
                                case 3:
                                    System.out.println("Rock hit saw");
                                    h.getNonLethal().get(j).setX(deathbox.x);
                                    h.getNonLethal().get(j).setY(deathbox.y);
                                    deathbox.setSize(0, 0);
                                    break;
                                case 4:
                                    h.getNonLethal().get(j).setX(deathbox.x + 5);
                                    h.getNonLethal().get(j).setY(deathbox.y);
                                    deathbox.setSize(0, 0);
                                    break;
                            }
                        }
                    }
                }
                for (int j = 0; j < koalaHitboxes.size(); j++) {

                    if (deathbox.intersects(koalaHitboxes.get(j))) {
                        
                        switch(h.getLethal().get(i).getId()){
                            case 1:
                                for(int k = 0; k < h.getLethal().size(); k++){
                                    h.getLethal().get(i).setImg(h.detonated());
                                    if(h.getLethal().get(k).getId() == 2){ // need to change when detonator pic when pressed
                                        //explosion at location
                                        //explosion collision?
                                        h.getLethal().remove(k);
                                         effects.explodeSound();
                                        if(k == i){
                                            i--;
                                        } 
                                        k--;
                                    }
                                    
                                }
                                break;
                            case 2:
                                System.out.println("TNT Death");
                                player1.getKoalas().get(j).koalaDied(true);        
                                player1.incrementDied();
                                koalaHitboxes.remove(j);
                                effects.explodeSound();
                                //player1.getKoalas().remove(j);              
                                h.getLethal().remove(i);
                                
                                break;
                            case 3:
                                 player1.getKoalas().get(j).koalaDied(true); 
                                koalaHitboxes.remove(j);
                                System.out.println("Saw Death");
                                player1.incrementDied();  
                                effects.sawedSound();
                                break;
                                case 4:
                                player1.getKoalas().get(j).koalaDied(true); 
                                koalaHitboxes.get(j).setSize(0, 0);
                                koalaHitboxes.remove(j);
                                System.out.println("Saw Death");
                               
                                player1.incrementDied();
                                effects.sawedSound();
                                break;
                        }
                    }
                }
             
            }
      
        }
    }
    
    
    

    public void koalaVsNonLethal(Player player1, Hazards h) {
        

        if (player1.getKoalas().size() != 0) {
             int w = h.getNonLethal().get(0).getWidth() / 2;
        int hh = h.getNonLethal().get(0).getHeight() / 2;
            koalaHitboxes.clear();
            for (int i = 0; i < player1.getKoalas().size(); i++) {

                xOffset = (int) (player1.getKoalas().get(i).getX() + player1.getKoalas().get(i).getdX());
                yOffset = (int) (player1.getKoalas().get(i).getY() + player1.getKoalas().get(i).getdY());
                heightOffset = 30;
                widthOffset = 30;
                Rectangle temp = new Rectangle(xOffset, yOffset, heightOffset, widthOffset);
                koalaHitboxes.add(temp);
            }
        }

        for (int i = 0; i < h.getNonLethal().size(); i++) {
            if (h.getNonLethal().get(i) == null) {
                continue; // skips past locks that are already opened
            }
            if (h.getNonLethal().get(i).getId() == 1 || h.getNonLethal().get(i).getId() == 3 || h.getNonLethal().get(i).getId() == 5
                    || h.getNonLethal().get(i).getId() == 9) {
                nonLethalHitbox = new Rectangle(h.getNonLethal().get(i).getX(), h.getNonLethal().get(i).getY(), 30, 30);
            } else {
                nonLethalHitbox = new Rectangle(h.getNonLethal().get(i).getX() + 20, h.getNonLethal().get(i).getY() + 20, 1, 1);
            }
            // locks are odd ID and switches are even ID
            for (int j = 0; j < koalaHitboxes.size(); j++) {
                // blue lock 
                if (h.getNonLethal().get(i).getId() == 1) {
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        player1.getKoalas().get(j).ranIntoObject(true);

                    }
                }
                //blue switch
                if (h.getNonLethal().get(i).getId() == 2) {
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                       
                       
                        // colored lock is right after the colored switch; extra check to not check for objects that may be out of bounds                      
                        if (h.getNonLethal().size() > i + 1 && h.getNonLethal().get(i + 1).getId() == 1) {
                            h.getNonLethal().get(i).setImage(h.blueSwitchActive());
                            h.getNonLethal().remove(i + 1);
                            effects.unlockedSound();
                        }
                    }
                }
                //yellow lock stays open for about roughly 5 second and then closes
                if (h.getNonLethal().get(i).getId() == 3) {
                     
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        if (onYellowSwitch) {
                            nonLethalHitbox.setSize(0, 0);
                           
                        } else {
                            if (count % 1000 == 0) {
                                player1.getKoalas().get(j).ranIntoObject(true);
                                nonLethalHitbox.setSize(30, 30);                            
                                count = 0;
                            }
                        }
                    }
                }
                // yellow switch 
                if (h.getNonLethal().get(i).getId() == 4) {
                    int temp = i;
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        onYellowSwitch = true;                    
                        h.getNonLethal().get(i).setImage(h.yellowSwitchActive());
                         // save i to reset after done looping through yellow locks
                        while (i < h.getNonLethal().size()) {    
                            if (h.getNonLethal().get(i).getId() == 3) {
                                h.getNonLethal().get(i).setImage(null);
                               
                            }
                            i++;
                        }
                        i = temp;
                        
                        break;
                      
                    } /*else {
                        if (count % 10000000 == 0) {
                            while (i < h.getNonLethal().size()) {
                                if (h.getNonLethal().get(i).getId() == 3) {
                                    // onYellowSwitch = false;
                                    h.getNonLethal().get(i).setImage(h.yellowLock());
                                    // 
                                }
                                i++;
                            }
                            i = temp;
                        }
                    }*/
                }
                // red lock
                if (h.getNonLethal().get(i).getId() == 5) {
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        if (onRedSwitch) {
                            nonLethalHitbox.setSize(0, 0); // must stay on redSwitch to clear lock                         
                        } else {
                            player1.getKoalas().get(j).ranIntoObject(true);
                            nonLethalHitbox.setSize(30, 30);
                        }
                    }
                }
                int temp = i;
                // unlocks red lock if standing on red lock
                if (h.getNonLethal().get(i).getId() == 6) {
                    // save i to reset after done looping through red locks
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        h.getNonLethal().get(i).setImage(h.redSwitchActive());
                        onRedSwitch = true;
                        while (i < h.getNonLethal().size()) {
                            if (h.getNonLethal().get(i).getId() == 5) {
                                h.getNonLethal().get(i).setImage(null);
                            }
                         //  effects.unlockedSound(); // dont know how play sound once after intersecting
                            i++;
                        }
                        
                        break;
                    } else {
                        h.getNonLethal().get(i).setImage(h.redSwitchInactive());
                        while (i < h.getNonLethal().size()) {
                            if (h.getNonLethal().get(i).getId() == 5) {
                                h.getNonLethal().get(i).setImage(h.redLock());
                            }
                            i++;
                        }
                        onRedSwitch = false;
                    }
                }

                    i = temp;
                if (h.getNonLethal().get(i).getId() == 7) {
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        System.out.println("exit");
                        player1.incrementSaved();
                        koalaHitboxes.remove(j);
                        effects.savedSound();
                        player1.theKoalas.remove(j);
                   }
                }
                if (h.getNonLethal().get(i).getId() == 8) {
                    if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                        System.out.println("exit");
                        h.getNonLethal().remove(i);
                        player1.incrementSaved();
                        koalaHitboxes.remove(j);
                        effects.savedSound();
                        player1.theKoalas.remove(j);
                    }
                } else {
                    if (h.getNonLethal().get(i).getId() == 9) {
                        if (nonLethalHitbox.intersects(koalaHitboxes.get(j))) {
                            player1.getKoalas().get(j).ranIntoObject(true);
                            effects.rockSound();
                            h.getNonLethal().get(i).x += player1.getKoalas().get(j).getdX();
                            h.getNonLethal().get(i).y += player1.getKoalas().get(j).getdY();
                        }

                    }
                }
            }

        }
        
    }


                
            
        
    

    // should mostly work if not purposefully trying to break the game
    public void koalaVsKoala(Player player1) {
        //testing

        if (player1.getKoalas().size() != 0) {
             koalaHitboxes.clear();
            for (int i = 0; i < player1.getKoalas().size(); i++) {

                xOffset = (int) (player1.getKoalas().get(i).getX() + player1.getKoalas().get(i).getdX());
                yOffset = (int) (player1.getKoalas().get(i).getY() + player1.getKoalas().get(i).getdY());
                heightOffset = 30;
                widthOffset = 30;
                Rectangle temp = new Rectangle(xOffset, yOffset, heightOffset, widthOffset);
                koalaHitboxes.add(temp);
            }

        }
        for (int i = 0; i < player1.getKoalas().size() - 1; ++i) {
            if (player1.getKoalas().get(i).isKoalaDead()) {
                            koalaHitboxes.get(i).setSize(0, 0);
                            player1.getKoalas().get(i).ranIntoObject(false);
                        }
             if (koalaHitboxes.size() == 1 || !player1.getKoalas().isEmpty()) {
                player1.getKoalas().get(i).ranIntoObject(false);        
                    }
             if(koalaHitboxes.get(i).intersects(koalaHitboxes.get(i + 1))) {
                        player1.getKoalas().get(i).ranIntoObject(true);
            } 
            }     
    }
    
    public boolean redUnlocked() {
        return onRedSwitch;
    }
}

