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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mgrib
 */
public class UDPsender {
    private InetAddress IPAddress; // 158.38.199.18
    private DatagramSocket clientSocket;
    
    /*
    * create new UDP Client
    */
    public UDPsender() {

    }
    
    /*
    * connect method
    */
    private void connect(String ipAddress){
        try {
            IPAddress = InetAddress.getByName(ipAddress);
            clientSocket = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /* 
    * send byte[] packet to socket 
    */
    public void send(String ipAddress, byte[] data){
        this.connect(ipAddress);
         try {
            DatagramPacket packet = new DatagramPacket(data, 
                                        data.length, 
                                        IPAddress,
                                        9876);
            clientSocket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             clientSocket.close();
         }
    }
    
    
}
