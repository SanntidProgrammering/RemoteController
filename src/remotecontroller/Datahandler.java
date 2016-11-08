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
    public boolean shouldThreadRun() {
        return threadStatus;
    }

    public void setThreadStatus(boolean threadStatus) {
        this.threadStatus = threadStatus;
    }

    //*****************************************************************
    //*************** FROM ARDUINO METHODS*****************************
    public void handleDataFromArduino(byte[] data) {
        // check if the array is of the same length and the requestcode has changed
        if (data.length == this.dataFromArduino.length && data[5] != this.getRequestCodeFromArduino()) {
            this.dataFromArduino = data;
            this.setDistanceSensor(data[4]);
            this.setRequestCodeFromArduino(data[5]);
            this.setPixyXvalue(new BigInteger(Arrays.copyOfRange(data, 0, 2)).intValue());
            this.setPixyYvalue(new BigInteger(Arrays.copyOfRange(data, 2, 4)).intValue());
            this.dataFromArduinoAvaliable = true;

        }
    }

    public boolean isDataFromArduinoAvailable() {
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
        //Main.enumStateEvent = SendEventState.FALSE;
        return this.dataFromGui;

    }

    public void stopAUV() {
        dataFromGui[0] = this.setBit(dataFromGui[0], 0);
        this.sendData();
    }

    public void releaseStopAUV() {
        dataFromGui[0] = this.releaseBit(dataFromGui[0], 0);
        this.sendData();
    }

    public void goFwd() {
        dataFromGui[0] = this.setBit(dataFromGui[0], 1);
        this.sendData();
    }

    public void releaseGoFwd() {
        dataFromGui[0] = this.releaseBit(dataFromGui[0], 1);
        this.sendData();
    }

    public void goRew() {
        dataFromGui[0] = this.setBit(dataFromGui[0], 2);
        this.sendData();
    }

    public void releaseGoRew() {
        dataFromGui[0] = this.releaseBit(dataFromGui[0], 2);
        this.sendData();
    }

    public void goLeft() {
        dataFromGui[0] = this.setBit(dataFromGui[0], 3);
        this.sendData();
    }

    public void releaseGoLeft() {
        dataFromGui[0] = this.releaseBit(dataFromGui[0], 3);
        this.sendData();
    }

    public void goRight() {
        dataFromGui[0] = this.setBit(dataFromGui[0], 4);
        this.sendData();
    }

    public void releaseGoRight() {
        dataFromGui[0] = this.releaseBit(dataFromGui[0], 4);
        this.sendData();
    }

    public void setLeftMotorSpeed(byte speed) {
        dataFromGui[1] = speed;
        this.sendData();
    }

    public void setRightMotorSpeed(byte speed) {
        dataFromGui[2] = speed;
        this.sendData();
    }

    public void setLeftServo() {
        dataFromGui[3] = this.setBit(dataFromGui[3], 0);
        this.sendData();
    }

    public void resetLeftServo() {
        dataFromGui[3] = this.releaseBit(dataFromGui[3], 0);
        this.sendData();
    }

    public void setRightServo() {
        dataFromGui[3] = this.setBit(dataFromGui[3], 1);
        this.sendData();
    }

    public void resetRightServo() {
        dataFromGui[3] = this.releaseBit(dataFromGui[3], 1);
        this.sendData();
    }

    public void AUVmanualMode() {
        dataFromGui[3] = this.releaseBit(dataFromGui[3], 2);
        this.sendData();
    }

    public void AUVautoMode() {
        dataFromGui[3] = this.setBit(dataFromGui[3], 2);
        this.sendData();
    }

    public void enableAUV() {
        dataFromGui[3] = this.setBit(dataFromGui[3], 3);
        this.sendData();
    }

    public void disableAUV() {
        dataFromGui[3] = this.releaseBit(dataFromGui[3], 3);
        this.sendData();
    }

    public void setSensitivity(byte sensetivity) {
        dataFromGui[4] = sensetivity;
        this.sendData();
    }

    public byte getSensitivity() {
        return dataFromGui[4];
    }

    public byte getRequestCode() {
        return dataFromGui[5];
    }

    public void incrementRequestCode() {
        dataFromGui[5]++;
        this.sendData();
    }

    public void sendData() {
        new UDPsender().send(Main.IPADDRESS, dataFromGui, Main.SENDPORT);


    }

}
