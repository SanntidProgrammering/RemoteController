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

    static final String IPADDRESS ="192.168.0.101"; //"192.168.0.101"; //"10.16.4.27"; //"192.168.0.103";  //Fugl"158.38.199.58";  // Jørg"10.16.5.58";
    static final int RECEIVEPORT = 9877;
    static final int SENDPORT = 9876; //9876
    static final int VIDEOPORT = 8765;
    //static SendEventState enumStateEvent = SendEventState.FALSE;



    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Datahandler datahandler = new Datahandler();
        //datahandler.setThreadStatus(true);
        
        //GUIController controller = new GUIController();

        UDPreceiver udpReceiver;
        ReceiveDataObservable receiveDataObservable;
        ReceiveVideoObservable receiveVideoObservable;
        VideoReceiver videoReceiver;
        

        // create the subject  of receiving data to GUI
        receiveDataObservable = new ReceiveDataObservable();
        receiveVideoObservable = new ReceiveVideoObservable();
        // create the UDP thread, puts data into subject
        udpReceiver = new UDPreceiver(receiveDataObservable,RECEIVEPORT);
        udpReceiver.start();
        videoReceiver = new VideoReceiver(receiveVideoObservable, VIDEOPORT);
        videoReceiver.start();

        GUI gui = new GUI();
        gui.setVisible(true);
        
        receiveDataObservable.addObserver(gui);
        receiveVideoObservable.addObserver(gui);


    }


}
