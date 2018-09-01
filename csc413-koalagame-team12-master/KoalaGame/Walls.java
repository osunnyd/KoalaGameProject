/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author jrettinghouse
 */
public class Walls {
     BufferedImage  wallTexture, wallTexture1, wallTexture2, wallTexture3, 
             wallTexture4, wallTexture5, wallTexture6, wallTexture7, wallTexture8, wallTexture9, 
             wallTexture10, wallTexture11, wallTexture12, wallTexture13,
             wallTexture14, wallTexture15, wallTexture16 ;
             
     BufferedReader r;
     private int [][] walls;
     private int mapWidth, mapHeight;
     

    public Walls() {
        System.out.println("Walls created");
        try {
            // load wall stuff
             wallTexture = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall0.png")); // load image
             wallTexture1 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall1.png")); // load image
             wallTexture2 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall2.png")); // load image
             wallTexture3 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall3.png")); // load image
             wallTexture4 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall4.png")); // load image
             wallTexture5 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall5.png")); // load image
             wallTexture6 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall6.png")); // load image
             wallTexture7 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall7.png")); // load image
             wallTexture8 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall8.png")); // load image
             wallTexture9 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall9.png")); // load image
             wallTexture10 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall10.png")); // load image
             wallTexture11 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall11.png")); // load image
             wallTexture12 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall12.png")); // load image
             wallTexture13 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall13.png")); // load image
             wallTexture14 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall14.png")); // load image
             wallTexture15 = ImageIO.read(Walls.class.getResource("/Koala_Resources/Wallpics/Wall15.png")); // load image
             //wallTexture2 = ImageIO.read(Walls.class.getResource("/Tank_Wars_Resourses/Wall2.gif")); // load image
          loadWalls(0);
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("error");
        }
       
    }
    
    public void loadNewLevel(int level){
        this.loadWalls(level);
    }
    public void restartLevel(int level) {
        this.loadWalls(level);
    }
    
    private void loadWalls(int level) {
        try{
                
                String path = "";
                
                switch(level){
                    case 0: path = "walls.txt";
                        break;
                    case 1: path = "wallslevel2.txt";
                        break;
                        
                    case 2: path = "wallslevel3.txt";
                        break;
                        
                    case 3: path = "wallslevel4.txt";
                        break;
                        
                    case 4: path = "wallslevel5.txt";
                        break;
                        
                    case 5: path = "wallslevel6.txt";
                        break;
                    case 6: path = "wallslevel7.txt";
                        break;
                        
                    case 7: path = "wallslevel8.txt";
                        break;
                        
                    case 8: path = "wallslevel9.txt";
                        break; 
                    case 9: path = "wallslevel10.txt";
                        break;
                } 
                r = new BufferedReader(new FileReader(path)); // load txt file what will create walls 
                mapWidth = Integer.parseInt(r.readLine()); // first line read from walls.txt is width
                mapHeight = Integer.parseInt(r.readLine()); // second line read is height
                walls = new int[mapHeight][mapWidth];
                System.out.println(mapHeight);
                System.out.println(mapWidth);
             
                for (int row = 0; row < mapHeight; row++){
                    String line = r.readLine();
                   String[] tokens = line.split("\\s+"); // delimits by whitespace " "
                    for (int col = 0; col < mapWidth; col++){
                        // x,y coordinate for the walls that will be drawn will be represented by 1's adn 2's
                        walls[row][col] = Integer.parseInt(tokens[col]);     
                    }
                 
             }
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("error");
        }
    }
    
    public void draw(Graphics2D g) {
        for (int x = 0; x < mapWidth; x++ ) {
            for (int y = 0; y < mapHeight; y++) {
                int wallHere = walls[x][y]; 
                if (wallHere == 1){
                g.drawImage(wallTexture1, y*wallTexture1.getHeight(), x*wallTexture1.getWidth(), null);    
                }
                if (wallHere == 2){
                g.drawImage(wallTexture2, y*wallTexture2.getHeight(), x*wallTexture2.getWidth(), null);    
                }
                if (wallHere == 3){
                g.drawImage(wallTexture3, y*wallTexture3.getHeight(), x*wallTexture3.getWidth(), null);    
                }
                if (wallHere == 4){
                g.drawImage(wallTexture4, y*wallTexture4.getHeight(), x*wallTexture4.getWidth(), null);    
                }
                if (wallHere == 5){
                g.drawImage(wallTexture5, y*wallTexture5.getHeight(), x*wallTexture5.getWidth(), null);    
                }
                if (wallHere == 6){
                g.drawImage(wallTexture6, y*wallTexture6.getHeight(), x*wallTexture6.getWidth(), null);    
                }
                if (wallHere == 7){
                g.drawImage(wallTexture7, y*wallTexture7.getHeight(), x*wallTexture7.getWidth(), null);    
                }
                if (wallHere == 8){
                g.drawImage(wallTexture8, y*wallTexture8.getHeight(), x*wallTexture8.getWidth(), null);    
                }
                if (wallHere == 9){
                g.drawImage(wallTexture9, y*wallTexture9.getHeight(), x*wallTexture9.getWidth(), null);    
                }
                if (wallHere == 10){
                g.drawImage(wallTexture10, y*wallTexture10.getHeight(), x*wallTexture10.getWidth(), null);    
                }
                if (wallHere == 11){
                g.drawImage(wallTexture11, y*wallTexture11.getHeight(), x*wallTexture11.getWidth(), null);    
                }
                if (wallHere == 12){
                g.drawImage(wallTexture12, y*wallTexture12.getHeight(), x*wallTexture12.getWidth(), null);    
                }
                if (wallHere == 13){
                g.drawImage(wallTexture13, y*wallTexture13.getHeight(), x*wallTexture13.getWidth(), null);    
                }
                if (wallHere == 14){
                g.drawImage(wallTexture14, y*wallTexture14.getHeight(), x*wallTexture14.getWidth(), null);    
                }
                if (wallHere == 15){
                g.drawImage(wallTexture15, y*wallTexture15.getHeight(), x*wallTexture15.getWidth(), null);    
                }
                if (wallHere == 16)
                g.drawImage(wallTexture, y*wallTexture.getHeight(), x*wallTexture.getWidth(), null);    
                }
            }
        } 
    
    public void breakWallAt (int x, int y) {
        walls[x][y] = 0;
    }
    
    public int[][] getWalls(){
        return walls;
    }
  
    public int getPictureWidth(){
        return wallTexture1.getWidth();
    }
    
    public int getPictureHieght(){
        return wallTexture1.getHeight();
    }
    
    public int getMapHeight() {
        return mapHeight;
    }
    public int getMapWidth() {
        return mapWidth;
    }
    
}
