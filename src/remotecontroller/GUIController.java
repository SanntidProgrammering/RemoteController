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
*/ 

// Datahandler mangla:
// Set og release til: Request

public class GUIController extends TimerTask {
    
    private Datahandler datahandler;
    
    public GUIController(){
    datahandler = new Datahandler();
    }
    @Override
    public void run(){
        System.out.println("Request data");
        //this.datahandler.incrementRequestCode();
    }
    
    public void setFwd(boolean value){ 
       if(value){
        System.out.println("Set FWD");
        //this.datahandler.goFwd();
       }
       else{
        System.out.println("Not FWD");
        //this.datahandler.releaseGoFwd();
       }
    }
    public void setLeft(boolean value){  
        if(value){
        System.out.println("Set LEFT");
        //this.datahandler.goLeft();
       }
       else{
           System.out.println("Not LEFT");
           //this.datahandler.releaseGoLeft();
       }
    }
    public void setRev(boolean value){  
        if(value){
           System.out.println("Set Rev");
           //this.datahandler.goRew();
       }
       else{
           System.out.println("Not Rev");
           //this.datahandler.releaseGoRew();
       }
    }
    public void setRight(boolean value){ 
        if(value){
        System.out.println("Set RIGHT");
        //this.datahandler.goRight();
       }
       else{
           System.out.println("Not RIGHT");
           //this.datahandler.releaseGoRight();
       }
    }
    public void setLeftServo(boolean value){ 
        if(value){
            System.out.println("Set LEFT SERVO");
          //this.datahandler.setLeftServo();
        }
        else{
          //this.datahandler.resetLeftServo();
        }

    }
    public void setRightServo(boolean value){  
        if(value){
            System.out.println("Set RIGHT SERVO");
            //this.datahandler.setRightServo();
        }
        else{
          //this.datahandler.resetRightServo();
        }
    }
    public void setStart(boolean value){  
        if (value){
            System.out.println("START SYSTEM");
            //this.datahandler.enableAUV();
        }
        else if(!value){
            System.out.println("STOP SYSTEM");
            //this.datahandler.disableAUV();
        }
    }
    public void setAuto(boolean value){
        if (value){
            System.out.println("AUTO MODE ON");
            //this.datahandler.AUVautoMode();
        }
        else if(!value){
            System.out.println("MANUAL MODE ON");
            //this.datahandler.AUVmanualMode();
        }
      
    }
    public void setSens(int sens){
        System.out.println("New sens = " + sens);
        //this.datahandler.setSensitivity(sens);
    }
    
    
    
}

