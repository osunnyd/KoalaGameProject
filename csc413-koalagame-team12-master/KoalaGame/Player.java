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
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;

import java.util.ArrayList;

public class Player {

    private int life;
    private int up, down, left, right;
    private int savedKoalas, totalOrigKoalas;
    private Image koalaImg;
    private boolean allKoalasRescued;

    private boolean KoalasDead;
    private int KoalasDeadNum;
    ArrayList<Koala> theKoalas = new ArrayList<>();

    //maybe an arraylist of koala objects?
    public Player(int life, int up, int down, int left, int right) {
        this.life = life;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        savedKoalas = 0;
        allKoalasRescued = false;

        try {
            koalaImg = ImageIO.read(Player.class.getResource("/Koala_Resources/Koala_Stand.png"));
            // initial koalas at level 0
            /*
            375 is about the middle of entire walls
            75 is starting corner
            450 height
            75, 75 is upper left corner of map
            685, 75 is upper right corner of map
            75, 450 is bottom left corner of map 
            685, 450 is bottom right corner of map / end of map
             */
            theKoalas.add(new Koala(koalaImg, 75, 75, up, down, left, right, 1));

        } catch (Exception e) {
            System.out.println("Could not find resources. (Player)");
        }

        totalOrigKoalas = theKoalas.size();
    }

    public void addMoreKoalas(int level) {
        switch (level) {
            case 0:
                theKoalas.add(new Koala(koalaImg, 75, 75, up, down, left, right, 1));
                savedKoalas = 0;
                KoalasDeadNum = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 1:
                theKoalas.add(new Koala(koalaImg, 75, 250, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 685, 250, up, down, left, right, 1));
                // theKoalas.add(new Koala(koalaImg, 500, 275, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;

            case 2:
                theKoalas.add(new Koala(koalaImg, 75, 450, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 75, 350, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 75, 250, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 3:
                theKoalas.add(new Koala(koalaImg, 75, 75, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 4:
                theKoalas.add(new Koala(koalaImg, 75, 75, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 685, 75, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 685, 450, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 75, 450, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 5:
                theKoalas.add(new Koala(koalaImg, 75, 250, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 75, 350, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 160, 440, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 6:
                theKoalas.add(new Koala(koalaImg, 325, 150, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 325, 200, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 325, 250, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 325, 350, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 325, 400, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 325, 450, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 7:
                theKoalas.add(new Koala(koalaImg, 175, 250, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 125, 290, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 75, 330, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 8:
                theKoalas.add(new Koala(koalaImg, 685, 300, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 685, 380, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 480, 200, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
            case 9:
                theKoalas.add(new Koala(koalaImg, 125, 440, up, down, left, right, 1));
                theKoalas.add(new Koala(koalaImg, 520, 360, up, down, left, right, 1));
                KoalasDeadNum = 0;
                savedKoalas = 0;
                allKoalasRescued = false;
                totalOrigKoalas = theKoalas.size();
                break;
                
        }

    }
    
    public void draw(Graphics g, ImageObserver obs) {
        Font stringFont = new Font("TimesRoman", Font.PLAIN, 50);
        g.setFont(stringFont);
        String saved = "" + getSavedCount();
        g.drawString(saved, 180, 560);
    }  
    
    public ArrayList<Koala> getKoalas(){
        return theKoalas;
    }
    
    public void saved(){
        savedKoalas++;
    }
    public int getSavedCount() {
        return savedKoalas;
    }
    public boolean savedAll() {
       if ( savedKoalas == totalOrigKoalas){
           allKoalasRescued = true;
           return allKoalasRescued;
       }
        return allKoalasRescued;
    }

    public void incrementSaved(){
        savedKoalas++;
    }
    
    public void incrementDied(){
        KoalasDeadNum++;
    }
    
    public boolean goToNextLevel() {
        return (savedKoalas-KoalasDeadNum) == totalOrigKoalas;
    }
}
