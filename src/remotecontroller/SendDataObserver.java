/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.Observable;
import java.util.Observer;

/**
 * denne klassen skal KUN lytte etter update fra et Observable object (gui kontroller) som skal sende data inn, behandlet av Datahandler
 * @author lars-harald
 */
public class SendDataObserver implements Observer,DataInterface {
    private Observable ob; //////////////////////*************************** endres til subklasse!!!!!!!!!!
    private byte[] data;
    private boolean dataReceiveAvaliavle = false;
    private boolean dataSendAvailable = false;

    private boolean startThreads;

    public SendDataObserver(Observable ob) {
        this.data = new byte[6];
        this.ob = ob;

    }
    
    public void update(Observable obs, Object object){
        if(obs == this.ob){
        new UDPsender().send(Main.IPADDRESS,, Main.SENDPORT);
        }
    }

    public byte[] getData() {
        return this.data;
    }
   
    public void setData(byte[] data){
        // check if the array is of the same length and the requestcode has changed
        if(data.length == this.data.length && data[5] != this.data[5]) {
            this.data = data;
        }
    }
    
    public boolean getDataSendAvailable(){
        return dataSendAvailable;
    }
    
    public boolean getDataReceiveAvaliable(){
        return dataReceiveAvaliavle;
    }

    public boolean getThreadStatus() {
        return startThreads;
    }
    
    public void setThreadStatus(boolean state){
       this.startThreads = state;
   }
    
    public void setRequestBit(){
        data[3] = this.setBit(data[3], 7);
    }
    
    public void releaseRequestBit(){
        data[3] = this.releaseBit(data[3], 7);
    }
    
    private byte setBit(byte b, int bit){
        return b |= 1 << bit;
    }
    
    private byte releaseBit(byte b, int bit){
        return b &= ~(1 << bit);
    }

}