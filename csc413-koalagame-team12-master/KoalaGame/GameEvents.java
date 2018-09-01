/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KoalaGame;

import java.awt.event.KeyEvent;
import java.util.Observable;

/**
 *
 * @author jrettinghouse
 */
public class GameEvents extends Observable {
    int type;
    final int key = 1;
    Object event;
// 
    public void setValue(KeyEvent e) {
        // for key presses
        type = key;
        event = e;
        setChanged(); 
        notifyObservers(this);
    }
    

    public void setValue(String msg) {
    }

    public int getType() {
        return this.type;
    }

    public Object getEvent() {
        return this.event;
    }
}
