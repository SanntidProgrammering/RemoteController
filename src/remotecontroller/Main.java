/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.concurrent.Semaphore;

/**
 *
 * @author lars-harald
 * 
 * this class starts the system
 */
public class Main {
    
    public static void main(String[] args){
        Datahandler datahandler = new Datahandler();
        Semaphore semaphore = new Semaphore(0);
        GUIController guiController = new GUIController(semaphore);

    }
}
