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
    Datahandler handler;
    DatagramSocket receiveSocket;
    int port;
    Thread t;
    
    public UDPreceiver(Datahandler handler, int port){
        this.handler = handler;
        this.port = port;
    }
    
    public void start(){
        t = new Thread(this,"UDPReceiverThread");
        t.start();
    }
    
    public void run() {
        try {
            receiveSocket = new DatagramSocket(port);
            
            DatagramPacket receivePacket = new DatagramPacket(new byte[6],6);
            
            while(handler.getThreadStatus()){
                receiveSocket.receive(receivePacket);
                try {
                    handler.setReceivedData(receivePacket.getData());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            receiveSocket.close();
            System.out.println("receivesocket closed");
        }
    }
    
}
    
    

