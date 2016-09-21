/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.Observable;

/**
 *
 * @author lars-harald
 */
public class ReceiveDataObserver extends Observable implements DataInterface{
    
    private byte[] receiveData = new byte[6];
    private boolean threadStatus;
    
    public ReceiveDataObserver(){
        super();
    }
    
    public void setData(byte[] data){
        // check if the array is of the same length and the requestcode has changed
        if(data.length == this.receiveData.length && data[5] != receiveData[5]) {
            this.receiveData = data;
            super.setChanged();
            super.notifyObservers();
        }
    }
    
    public byte[] getData(){
        return this.receiveData;
    }
    
    public boolean getThreadStatus(){
        return this.threadStatus;
    }
}
