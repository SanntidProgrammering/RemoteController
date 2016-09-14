/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.nio.ByteBuffer;

/**
 *
 * @author mgrib
 */
public class Controller {

    private Datahandler dataHandler;
    // private String odroid Mac Adress
    // private String ip address
    //
    private Datahandler datahandler;
    private Byte[] sendingArray;
    
    
    public Controller(){
        //client = new UDPClient("158.38.199.18");
        this.dataHandler = new Datahandler();
        
        
    }
    public void setFwd(boolean value){ 
        System.out.println("Set FWD");
    }
    public void setLeft(boolean value){  
        System.out.println("Set LEFT");
    }
    public void setRev(boolean value){  
        System.out.println("Set REV");
    }
    public void setRight(boolean value){ 
        System.out.println("Set RIGHT");
    }
    public void setLeftServo(boolean value){  
        System.out.println("Set LEFT SERVO");
    }
    public void setRightServo(boolean value){  
        System.out.println("Set RIGHT SERVO");
    }
    public void setStart(boolean value){  
        System.out.println("Set START");
    }
    public void setAuto(boolean value){    
        System.out.println("Set AUTO");
    }
    
    
}

