/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * denne klassen skal KUN lytte etter update fra et Observable object (gui
 * kontroller) som skal sende data inn, behandlet av Datahandler
 *
 * @author lars-harald
 */
public class SendDataObserver implements Observer, DataInterface {

    private Observable ob; //////////////////////*************************** endres til subklasse!!!!!!!!!!
    private byte[] data;
    private boolean threadStatus;
    private boolean dataChanged;

    public SendDataObserver(Observable ob) {
        this.data = new byte[6];
        this.ob = ob;

    }

    @Override
    public void update(Observable obs, Object obj) {
        if (obs == this.ob) {
            byte[] b = this.deserializeData(obj);
            this.setData(b);
            if (dataChanged) {
                this.sendDataToSocket(this.getData());
            } else {
                System.out.println("could not set data and send in update, senddataobserver");
            }
            
        }
    }

    @Override
    public void setData(byte[] data) {

        if (data != null) {
            if (data.length == this.data.length) {
                this.data = data;
                dataChanged = true;
            }
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public boolean getThreadStatus() {
        return this.threadStatus;
    }

    public void setThreadStatus(boolean state) {
        this.threadStatus = state;
    }

    private byte[] deserializeData(Object obj) {
        ObjectOutputStream os = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveDataObservable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void sendDataToSocket(byte[] data) {
        new UDPsender().send(Main.IPADDRESS, data, Main.SENDPORT);
        dataChanged = false;
    }
}
