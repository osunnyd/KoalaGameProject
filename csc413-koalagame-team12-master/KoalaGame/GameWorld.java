/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

/*
 *
 * @author jrettinghouse
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class GameWorld extends JApplet implements Runnable{
    private Thread thread;
    private int animFrame = 0;
    Image Koalaimg;
    boolean win;
    Graphics2D g2D, g2D2;
    Player player1;
    Hazards hazards = new Hazards();
    GameEvents gameEvents1;
    Image congrats;
    private BufferedImage bimg;
    Sound bgMusic = new Sound (); // background music for now; maybe need other sounds as well 
    Background background = new Background();
    Walls wall;
    CollisionDetection collisionCheck;
    private boolean levelLoaded = true;
    private boolean restartLevel = false;
    private int level = 0;
    KeyControl key1;
    private static boolean[] restart = new boolean [120];
    Menu menu = new Menu();

   // Pickups pickups;
   // int typeofPickup;
    
    private final static int width = 800, height = 600; //for outer dimentions for bullet
    
    
    protected static enum STATE {
        MENU,
        GAME,
        RESTART
    };
    
    protected static STATE State = STATE.MENU;
    @Override
    public void init(){
        setFocusable(true);
        try{
            
              this.addMouseListener(new MouseInput());
            //fill
            bgMusic.playMusic();
            wall = new Walls();
            player1 = new Player(1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
            gameEvents1 = new GameEvents();
            // to get all koalas moving
            for (int i = 0; i < player1.getKoalas().size(); i++) {
                gameEvents1.addObserver(player1.theKoalas.get(i));
            }

            key1 = new KeyControl(gameEvents1);
          
            addKeyListener(key1);
            collisionCheck = new CollisionDetection(gameEvents1);
           
            congrats = ImageIO.read(Player.class.getResource("/Koala_Resources/Congratulation.png"));
        } catch (Exception e) {
            System.out.print(e.getStackTrace() + "No Resources were found. (init)");
        }
    }
    
    public void reinit() {
        gameEvents1.deleteObservers();
        System.out.println("changes values");
        removeKeyListener(key1);
        player1.addMoreKoalas(level);
        hazards.levelChange(level);
        for (int i = 0; i < player1.getKoalas().size(); i++) {
            gameEvents1.addObserver(player1.theKoalas.get(i));
        }
        addKeyListener(key1);
        collisionCheck = new CollisionDetection(gameEvents1);
    }
    
    public void drawDemo() {
        if (State == STATE.MENU) {
            menu.paint(g2D);
        } else {
            if (!levelLoaded) {
                level++;
                switch (level) {
                    // case 1 is actually level 2
                    case 1:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 2:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 3:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 4:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 5:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 6:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 7:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 8:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    case 9:
                        wall.loadNewLevel(level);
                        reinit();
                        break;
                    default:
                        break;

                }

                levelLoaded = true;
            }
            if (State == STATE.RESTART) {
                levelLoaded = true;
                player1.getKoalas().clear();
                wall.restartLevel(level);
                reinit();
                State = STATE.GAME;
            }

            background.paint(g2D);
            wall.draw(g2D);

            for (int i = 0; i < hazards.getNonLethal().size(); i++) {
                if (hazards.getNonLethal().get(i) != null) {
                    hazards.getNonLethal().get(i).draw(g2D, this);
                }
            }
            // drawing multiple koalas
            for (int i = 0; i < player1.getKoalas().size(); i++) {
                player1.getKoalas().get(i).draw(g2D, this);
            }

            //draw all hazards
            for (int i = 0; i < hazards.getLethal().size(); i++) {
                hazards.getLethal().get(i).draw(g2D, this);
                if (hazards.getLethal().get(i).getId() == 3) {
                    if (animFrame % 40 > 20) {
                        hazards.getLethal().get(i).setImg(hazards.getSawV2());
                    } else {
                        hazards.getLethal().get(i).setImg(hazards.getSawV1());
                    }
                }

                if (hazards.getLethal().get(i).getId() == 4) {
                    if (animFrame % 40 > 20) {
                        hazards.getLethal().get(i).setImg(hazards.getSawH2());
                    } else {
                        hazards.getLethal().get(i).setImg(hazards.getSawH1());
                    }
                }

            }
            // remove movement from dead koalas
            for (int i = 0; i < player1.getKoalas().size(); i++) {
                if (player1.getKoalas().get(i).isKoalaDead() == true) {
                    gameEvents1.deleteObserver(player1.theKoalas.get(i));
                }
            }

            // for saw animations
            if (animFrame < 7500) {
                animFrame++;
            } else {
                animFrame = 0;
            }

            //collisions
            checkCollisions();
           
            if (player1.goToNextLevel() == true) {
                levelLoaded = false;
                System.out.println("level next");
            }

            
            player1.draw(g2D, this);
            repaint();
        }
         if (player1.savedAll() && level == 10) {
                g2D.drawImage(congrats, 175, 150, this);
                levelLoaded = true;
            }
    }
   
    public void checkCollisions () {
        collisionCheck.koalaVsKoala(player1);
        collisionCheck.koalaVsNonLethal(player1, hazards);       
        collisionCheck.koalaVsLethal(player1, hazards);
        collisionCheck.koalaVsWall(player1, wall);
      
    }
    
    
    @Override
    public void start() {
        System.out.println("Start() here");
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);       
        thread.start();
        
    }
    
    @Override
    public void run () {
        Thread theThread = Thread.currentThread();
        while(thread == theThread){
            repaint();
            try{
                thread.sleep(25);
            } catch (InterruptedException e){
                break;
            }
        }
    }
    

    
    @Override
    public void paint(Graphics g) {
        Dimension windowSize = getSize();
        if (bimg == null) {
            bimg = (BufferedImage) createImage(width, height);
        }
        g2D = bimg.createGraphics();
        //draw everything for graphics
        drawDemo();
        //presentation of the JFrame
        g.drawImage(bimg, 0, 0, this);
    }


    
    public static void main(String args[]) {
        final GameWorld demo = new GameWorld();
        demo.init();
        JFrame frame = new JFrame("Koala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter(){});
        frame.add(demo);
        frame.setSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setVisible(true);
        demo.start();
    }
}
