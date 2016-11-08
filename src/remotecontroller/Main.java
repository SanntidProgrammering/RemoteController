/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import org.opencv.core.Core;

/**
 *
 * @author lars-harald
 *
 * this class starts the system
 */
public class Main {

    static final String IPADDRESS = "10.16.5.164";  // Jørg"10.16.5.58";
    static final int RECEIVEPORT = 5000;
    static final int SENDPORT = 9876;
    static SendEventState enumStateEvent = SendEventState.FALSE;



    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Datahandler datahandler = new Datahandler();
        datahandler.setThreadStatus(true);

        //UDPreceiver udpReceiver;
        //ReceiveDataObservable receiveDataObserver;

        // create the subject  of receiving data to GUI
        //receiveDataObserver = new ReceiveDataObservable(datahandler);
        // create the UDP thread, puts data into subject
        //udpReceiver = new UDPreceiver(receiveDataObserver,RECEIVEPORT);
        //udpReceiver.start();

        GUI gui = new GUI();
        gui.setHandler(datahandler);
        gui.setVisible(true);

        //SendDataFromGui sendDataFromGui = new SendDataFromGui(datahandler);
        //sendDataFromGui.start();

    }


}
