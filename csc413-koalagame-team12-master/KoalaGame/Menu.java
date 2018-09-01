/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Sunny
 */
public class Menu extends JPanel{
    BufferedImage title, startButton, quitButton,  background;
  
    public Menu() {
        
        try {
            title = ImageIO.read(Menu.class.getResource("/Koala_Resources/Title.png"));
            startButton = ImageIO.read(Menu.class.getResource("/Koala_Resources/Button_start.png"));
            quitButton = ImageIO.read(Menu.class.getResource("/Koala_Resources/Button_quit.png"));
            background = ImageIO.read(Menu.class.getResource("/Koala_Resources/Background.bmp"));
        } catch (Exception e) {
            System.out.println("Error loading Menu buttons. " + e);
        }

    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                g.drawImage(background, i * background.getWidth(), j * background.getHeight(), null);
            }
        }

        g.drawImage(title, 120, 50, this);
        g.drawImage(startButton, 135, 366, this);
        g.drawImage(quitButton, 520, 366, this);

    }
}
