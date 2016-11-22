/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mgrib
 */
public class ReceiveVideoObservable extends Observable {
    private BufferedImage buff;
    

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setFrame(BufferedImage buff) {
            this.buff = buff;
            
            setChanged();
            notifyObservers();
    }


    public BufferedImage getBuff(){
    
        return this.buff;
    }


} 

