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

Byte 0-x: [bit 0, bit 1, bit 2, bit 3, bit 4, bit 5, bit 6, bit 7]
* 
Bit 1 = ON/Active
bit 2 = Off/Not active
Byte 0:[stopp, fwd, rev, left, right, bit 5, bit 6, bit 7]
Byte 1:[left motor speed]
Byte 2:[right motor speed]
Byte 3:[left servo,right servo, auto/manual, start program,...,request feedback]
Byte 4:[sensitivity]
Byte 5:[reservert]
Receiving protocol:
* 
Byte 0: Pixy x value lowbyte
Byte 1: Pixy x value highbyte
Byte 2: Pixy y value lowbyte
Byte 3: Pixy y value highbyte
Byte 4: Distance sensor
Byte 5: reserved

*/ 

public class GuiController {

    private Datahandler dataHandler;
    // private String odroid Mac Adress
    // private String ip address
    //
    private Datahandler datahandler;
    private Byte[] sendingArray;
    
    
    public GuiController(){
        //client = new UDPClient("158.38.199.18");
        this.dataHandler = new Datahandler();
        
        
    }
    public void setFwd(boolean value){ 
       //this.dataHandler.setBit(1, 1);
       if(value){
        System.out.println("Set FWD");
       }
       else{
           System.out.println("Not FWD");
       }
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
    public void setSens(int sens){
        System.out.println("New sens = " + sens);
    }
    
    
    
}

