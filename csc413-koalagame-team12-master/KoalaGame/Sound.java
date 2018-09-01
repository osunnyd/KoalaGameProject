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
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class Sound {
    private Clip bgMusic, saved, unlocked, died, exploded, rock; // to loop the background music
    private AudioInputStream stream; // used to convert into a Clip to play
    //default 
   public Sound() {
     
    }
   
   public void playMusic(){
         try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Music.wav"));
            bgMusic = AudioSystem.getClip();
            bgMusic.open(stream); 
            bgMusic.loop(Clip.LOOP_CONTINUOUSLY); // loops if music is finished
       } catch (Exception e){
           System.out.println(e);
       }  
   }
   
   public void savedSound() {
       try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Saved.wav"));
            saved = AudioSystem.getClip();
            saved.open(stream); 
            saved.start(); 
       } catch (Exception e){
           System.out.println(e);
       }  
   }
   public void unlockedSound() {
       try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Lock.wav"));
            unlocked = AudioSystem.getClip();
            unlocked.open(stream); 
            unlocked.start(); 
       } catch (Exception e){
           System.out.println(e);
       }  
   }
   public void sawedSound() {
       try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Saw.wav"));
            died = AudioSystem.getClip();
            died.open(stream); 
            died.start(); 
       } catch (Exception e){
           System.out.println(e);
       }  
   }
   public void explodeSound() {
       try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Explosion.wav"));
            exploded = AudioSystem.getClip();
            exploded.open(stream); 
            exploded.start(); 
       } catch (Exception e){
           System.out.println(e);
       }  
   }
   public void rockSound() {
        try {
           stream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Koala_Resources/Rock.wav"));
            rock = AudioSystem.getClip();
            rock.open(stream); 
            rock.start(); 
       } catch (Exception e){
           System.out.println(e);
       }  
       
   }
}
