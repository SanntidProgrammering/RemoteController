/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;


/**
 *
 * @author lars-harald
 *
 * this class starts the system
 */
public class Main {

    static final String IPADDRESS = "192.168.0.10";
    static final int RECEIVEPORT = 5000;
    static final int SENDPORT = 5001;
    static SendEventState enumStateEvent;



    public static void main(String[] args){
        Datahandler datahandler = new Datahandler();
        datahandler.setThreadStatus(true);

        UDPreceiver udpReceiver;
        ReceiveDataObservable receiveDataObserver;

        // create the subject  of receiving data to GUI
        receiveDataObserver = new ReceiveDataObservable(datahandler);
        // create the UDP thread, puts data into subject
        udpReceiver = new UDPreceiver(receiveDataObserver,RECEIVEPORT);
        udpReceiver.start();

        GUI gui = new GUI(datahandler);

        SendDataFromGui sendDataFromGui = new SendDataFromGui(datahandler);
        sendDataFromGui.start();

    }


}
