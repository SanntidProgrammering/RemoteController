/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author lars-harald
 */
public class UDPreceiver implements Runnable {
    private ReceiveDataObservable observer;
    private DatagramSocket receiveSocket;
    private int port;
    private Thread t;

    
    public UDPreceiver(DataInterface dataInterface, int port){
        if(dataInterface instanceof ReceiveDataObservable){
        this.observer = (ReceiveDataObservable) dataInterface;
        }
        this.port = port;
    }
    
    public void start(){
        t = new Thread(this,"UDPReceiverThread");
        t.start();
    }
    
    public void run() {
        if(observer != null) {
            try {
                receiveSocket = new DatagramSocket(port);
                DatagramPacket receivePacket = new DatagramPacket(new byte[6],6);
                
                while(observer.isThreadStatus()){
                    receiveSocket.receive(receivePacket);
                    observer.setData(receivePacket.getData());
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                receiveSocket.close();
                System.out.println("receivesocket closed");
            }
        } else {
            System.out.println("receive datahandler not created in udpreceiver thread");
        }
    }
    
}
    
    

