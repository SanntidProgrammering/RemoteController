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

    private byte[] receivedData;
    private byte[] sendData;
    private boolean dataReceiveAvaliavle = false;
    private boolean dataSendAvailable = false;

    private boolean startThreads;

    public Datahandler() {
        this.receivedData = new byte[6];
        this.sendData = new byte[6];
    }

    public byte[] getData() {
        byte[] returnArray = null;
        String id = null; //////////////////////////////////////////////// skal vekk
        if (id.equals("RECEIVE")) {
            // check if new value is available
            if(this.getDataReceiveAvaliable()){
            returnArray = this.receivedData;
            // reset the new value available flag
            this.dataReceiveAvaliavle = false;
            }
        } else if (id.equals("SEND")) {
            // check if new value is available
            if(this.getDataSendAvailable()){
            returnArray = this.sendData;
            // reset the new value available flag
            this.dataSendAvailable = false;
            }
        }
        return returnArray;
    }
   
    public void setData(byte[] newByteArray) {
        String id = null; //////////////////////////////////////////////// skal vekk
        if (id.equals("SEND")) {
            // check if the old value has been handled first
            if(!this.getDataSendAvailable()){
            this.sendData = newByteArray;
            // set new value available flag
            dataSendAvailable = true;
            }
        } else if (id.equals("RECEIVE")) {
            // che                 ck if the old value has been handled first
            if(!this.getDataReceiveAvaliable()){
            this.receivedData = newByteArray;
            // set new value available flag
            dataReceiveAvaliavle = true;
            }
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
    
    public void setRequestBit(){
        sendData[3] = this.setBit(sendData[3], 7);
    }
    public void releaseRequestBit(){
        sendData[3] = this.releaseBit(sendData[3], 7);
    }
    
    
    public int hashCodeSendData(){
        return Arrays.hashCode(sendData);
    }
    
    public int hashCodeReceiveData(){
        return Arrays.hashCode(receivedData);
    }
    
    private byte setBit(byte b, int bit){
        return b |= 1 << bit;
    }
    private byte releaseBit(byte b, int bit){
        return b &= ~(1 << bit);
    }

}
