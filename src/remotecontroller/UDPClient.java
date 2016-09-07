/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mgrib
 */
public class UDPClient {
    private InetAddress IPAddress; // 158.38.199.18
    private String ip;
    private DatagramSocket clientSocket;
    
    public UDPClient(String IpAddress){
         this.ip = IpAddress;
    }
    
    public void connect(){
        try {
            IPAddress = InetAddress.getByName(this.ip);
            clientSocket = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void send(ArrayList<Boolean> data){
        this.connect();
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(buffer);
            out.writeObject(data);
            out.close();
            buffer.close();
            DatagramPacket packet = new DatagramPacket(buffer.toByteArray(), 
                                        buffer.size(), 
                                        IPAddress,
                                        9876);
            clientSocket.send(packet);
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
