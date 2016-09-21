/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lars-harald
 */
public class UDPreceiver implements Runnable {
    private ReceiveDataObservable handler;
    private DatagramSocket receiveSocket;
    private int port;
    private Thread t;

    
    public UDPreceiver(DataInterface handler, int port){
        if(handler instanceof ReceiveDataObservable){
        this.handler = (ReceiveDataObservable) handler;
        }
        this.port = port;

    }
    
    public void start(){
        t = new Thread(this,"UDPReceiverThread");
        t.start();
    }
    
    public void run() {
        if(handler != null) {
            try {
                receiveSocket = new DatagramSocket(port);
                DatagramPacket receivePacket = new DatagramPacket(new byte[6],6);
                
                while(handler.getThreadStatus()){
                    receiveSocket.receive(receivePacket);
                    handler.setData(receivePacket.getData());
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
    
    

