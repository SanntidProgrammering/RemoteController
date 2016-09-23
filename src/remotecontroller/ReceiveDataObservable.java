/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lars-harald
 */
public class ReceiveDataObservable extends Observable implements DataInterface {

    private boolean threadStatus;
    private int pixyXvalue;
    private int pixyYvalue;
    private int distanceSensor;
    private byte requestCode;

    public ReceiveDataObservable() {
        super();
    }

    /**
     * new data has been set from the udpreceiver thread (data from net)
     *
     * @param data
     */
    public void setData(byte[] data) {
        // check if the array is of the same length and the requestcode has changed
        if (data.length == 6) {
            this.handleReceivedData(data);
            super.setChanged();
            super.notifyObservers();
        }
    }

    private void handleReceivedData(byte[] data) {
        this.setDistanceSensor(data[4]);
        this.setRequestCode(data[5]);
        this.setPixyXvalue(new BigInteger(Arrays.copyOfRange(data, 0, 2)).intValue());
        this.setPixyYvalue(new BigInteger(Arrays.copyOfRange(data, 2, 4)).intValue());
    }

    public boolean isThreadStatus() {
        return threadStatus;
    }

    public void setThreadStatus(boolean threadStatus) {
        this.threadStatus = threadStatus;
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

    public byte getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(byte requestCode) {
        this.requestCode = requestCode;
    }

}
