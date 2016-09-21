/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.Arrays;
import java.util.Observable;


/**
 *
 * @author mgrib
 */

/*
Sending protocol:
Byte 0-x: [bit 0, bit 1, bit 2, bit 3, bit 4, bit 5, bit 6, bit 7]

Bit 1 = ON/Active
bit 2 = Off/Not active

Byte 0:[stopp, fwd, rev, left, right, bit 5, bit 6, bit 7]
Byte 1:[left motor speed]
Byte 2:[right motor speed]
Byte 3:[left servo,right servo, auto/manual, start program,...,request feedback]
Byte 4:[sensitivity]
Byte 5:[reservert]

Receiving protocol:
Byte 0: Pixy x value lowbyte
Byte 1: Pixy x value highbyte
Byte 2: Pixy y value lowbyte
Byte 3: Pixy y value highbyte
Byte 4: Distance sensor
Byte 5: reserved


 */
public class Datahandler implements DataInterface {

    private byte[] data;
    private boolean dataReceiveAvaliavle = false;
    private boolean dataSendAvailable = false;

    private boolean startThreads;

    public Datahandler() {
        this.data = new byte[6];
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
