/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.math.BigInteger;
import java.util.Arrays;

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
public class Datahandler {

    private byte[] dataFromArduino;
    private byte[] dataFromGui;
    private boolean dataFromArduinoAvaliable = false;
    private boolean dataFromGuiAvailable = false;
    private boolean threadStatus;
    private int pixyXvalue;
    private int pixyYvalue;
    private int distanceSensor;
    private byte requestCodeFromArduino;

    private boolean enableAUV;

    public Datahandler() {
        this.dataFromArduino = new byte[6];
        this.dataFromGui = new byte[6];
    }

    //*****************************************************************
    //********** PRIVATE METHODS AREA**********************************
    private byte setBit(byte b, int bit) {
        return b |= 1 << bit;
    }

    private byte releaseBit(byte b, int bit) {
        return b &= ~(1 << bit);
    }

    //*****************************************************************
    //********************** THREAD STATUS METHODS*********************
    public boolean isThreadStatus() {
        return threadStatus;
    }

    public void setThreadStatus(boolean threadStatus) {
        this.threadStatus = threadStatus;
    }

    //*****************************************************************
    //*************** FROM ARDUINO METHODS*****************************
    public synchronized byte[] getDataFromArduino() {
        this.dataFromArduinoAvaliable = false;
        return this.dataFromArduino;

    }

    public synchronized void handleDataFromArduino(byte[] data) {
        // check if the array is of the same length and the requestcode has changed
        if (data.length == this.dataFromArduino.length && data[5] != this.getRequestCodeFromArduino()) {
            this.dataFromArduino = data;
            this.setDistanceSensor(data[4]);
            this.setRequestCodeFromArduino(data[5]);
            this.setPixyXvalue(new BigInteger(Arrays.copyOfRange(data, 0, 2)).intValue());
            this.setPixyYvalue(new BigInteger(Arrays.copyOfRange(data, 2, 4)).intValue());
            this.dataFromArduinoAvaliable = true;

        }
        notifyAll();
    }
    
    public boolean isDataFromArduinoAvailable(){
        return this.dataFromArduinoAvaliable;
    }

    public int getPixyXvalue() {
        return pixyXvalue;
    }

    public void setPixyXvalue(int pixyXvalue) {
        this.pixyXvalue = pixyXvalue;
    }

    public int getPixyYvalue() {
        return pixyYvalue;
    }

    public void setPixyYvalue(int pixyYvalue) {
        this.pixyYvalue = pixyYvalue;
    }

    public int getDistanceSensor() {
        return distanceSensor;
    }

    public void setDistanceSensor(int distanceSensor) {
        this.distanceSensor = distanceSensor;
    }

    public byte getRequestCodeFromArduino() {
        return requestCodeFromArduino;
    }

    public void setRequestCodeFromArduino(byte requestCodeFromArduino) {
        this.requestCodeFromArduino = requestCodeFromArduino;
    }

    //****************************************************************
    //************** FROM GUI METHODS*********************************
    public byte[] getDataFromGui() {
        return this.dataFromGui;
    }
    
    public void stopAUV(){
        
    }

    public void goFwd() {
        
    }

    public void goRew(){
        
    }
    
    public void goLeft(){
        
    }
    
    public void goRight() {
        
    }
    
    public void setLeftMotorSpeed(byte speed) {
        
    }
    
    public void setRightMotorSpeed(byte speed) {
        
    }
    
    public void setLeftServo(){
        
    }
    
    public void resetLeftServo(){
        
    }
    
    public void setRightServo(){
        
    }
    
    public void resetRightServo(){
        
    }
    
    public void AUVmanualMode(){
        
    }
    
    public void AUVautoMode(){
        
    }
       
    public void enableAUV() {

    }

    public void disableAUV() {

    }
    
    public void setSensitivity(byte sensetivity){
        
    }
    
    public byte getSensitivity(){
        return 0;
    }
    
    public byte getRequestCode(){
        return 0;
    }
    
    public void incrementRequestCode(){
        
    }
    

}


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
*/
