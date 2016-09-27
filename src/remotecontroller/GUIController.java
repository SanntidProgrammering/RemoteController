/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.nio.ByteBuffer;
import java.util.TimerTask;

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

public class GUIController extends TimerTask {
    
    
    public GUIController(){
        
    }
    @Override
    public void run(){
        System.out.println("Request data");
    }
    
    public void setFwd(boolean value){ 
       if(value){
        System.out.println("Set FWD");
       }
       else{
           System.out.println("Not FWD");
       }
    }
    public void setLeft(boolean value){  
        if(value){
        System.out.println("Set LEFT");
       }
       else{
           System.out.println("Not LEFT");
       }
    }
    public void setRev(boolean value){  
        if(value){
        System.out.println("Set Rev");
       }
       else{
           System.out.println("Not Rev");
       }
    }
    public void setRight(boolean value){ 
        if(value){
        System.out.println("Set RIGHT");
       }
       else{
           System.out.println("Not RIGHT");
       }
    }
    public void setLeftServo(boolean value){  
        System.out.println("Set LEFT SERVO");
    }
    public void setRightServo(boolean value){  
        System.out.println("Set RIGHT SERVO");
    }
    public void setStart(boolean value){  
        if (value){
            System.out.println("START SYSTEM");
        }
        else if(!value){
            System.out.println("STOP SYSTEM");
        }
    }
    public void setAuto(boolean value){
        if (value){
            System.out.println("AUTO MODE ON");
        }
        else if(!value){
            System.out.println("MANUAL MODE ON");
        }
      
    }
    public void setSens(int sens){
        System.out.println("New sens = " + sens);
    }
    
    
    
}

