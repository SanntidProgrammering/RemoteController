/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * denne klassen skal KUN lytte etter update fra et Observable object (gui
 * kontroller) som skal sende data inn, behandlet av Datahandler
 *
 * @author lars-harald
 */
public class SendDataFromGui implements Runnable {

    private Datahandler datahandler;
    private Thread t;

    public SendDataFromGui(Datahandler datahandler) {
        this.datahandler = datahandler;
    }

    public void start() {
        t = new Thread(this, "SendDataFromGuiThread");
        t.start();
    }

    @Override
    public void run() {

        while (datahandler.shouldThreadRun()) {
            this.checkSendDataAvailable(); // should block until data send available
            this.sendDataToSocket(datahandler.getDataFromGui());
            Main.enumStateEvent = SendEventState.FALSE;
        }
    }

    public synchronized void checkSendDataAvailable() {
        while (Main.enumStateEvent == SendEventState.FALSE) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SendDataFromGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        notifyAll();
    }

    private void sendDataToSocket(byte[] data) {
        new UDPsender().send(Main.IPADDRESS, data, Main.SENDPORT);
    }
}
