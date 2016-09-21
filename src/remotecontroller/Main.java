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
    

    
    public static void main(String[] args){
        UDPreceiver udpReceiver;
        ReceiveDataObservable receiveDataObserver;
        
        receiveDataObserver = new ReceiveDataObservable();
        udpReceiver = new UDPreceiver(receiveDataObserver,RECEIVEPORT);
        udpReceiver.start();
        
        
        
    }
    

}
