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
public class ReceiveDataObservable extends Observable implements DataInterface {


    private final Datahandler datahandler;
    
    public ReceiveDataObservable(Datahandler datahandler) {
        super();
        this.datahandler = datahandler;
    }

    /**
     * new data has been set from the udpreceiver thread (data from net)
     *
     * @param data
     */
    @Override
    public void setData(byte[] data) {
        // check if the array is of the same length and the requestcode has changed
        if (data.length == 6) {
            this.datahandler.handleDataFromArduino(data);
            super.setChanged();
            super.notifyObservers();
        }
    }
    
    public boolean shouldChildOfThisRun(){
        return datahandler.shouldThreadRun();
    }
}
