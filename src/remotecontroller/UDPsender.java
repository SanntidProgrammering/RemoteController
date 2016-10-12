/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mgrib
 */
public class UDPsender {
    //  ip address 158.38.199.18
    private DatagramSocket clientSocket;
    /*
    * create new UDP Client
    */
    public UDPsender() {
           // nothing to do here
    }
    
    /*
    * init method
    */
    private void init(){
        try {
            clientSocket = new DatagramSocket();
        }  catch (SocketException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /* 
    * send byte[] packet to socket 
    */
    public void send(String ipAddress, byte[] data, int port){
        this.init();
         try {
            
            DatagramPacket packet = new DatagramPacket(data, 
                                        data.length, 
                                        InetAddress.getByName(ipAddress),
                                        port);
            clientSocket.send(packet);
             //System.out.println(Arrays.toString(data));
            System.out.println("UDP send");
        } catch (IOException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        clientSocket.close();
        }
    }
    
    
    
    
}
