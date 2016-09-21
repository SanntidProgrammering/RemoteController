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
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mgrib
 */
public class UDPsender implements Runnable {
    private InetAddress IPAddress; // 158.38.199.18
    private DatagramSocket clientSocket;
    private Thread t;
    private Semaphore semaphore;
    private int port;
    private Datahandler datahandler;
    /*
    * create new UDP Client
    */
    public UDPsender(String ipAddress, Semaphore s, int port, Datahandler datahandler) {
        try {
            this.IPAddress = InetAddress.getByName(ipAddress);
            this.semaphore = s;
            this.port = port;
            this.datahandler = datahandler;
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start(){
        t = new Thread(this,"UDPSenderThread");
        t.start();
    }
    
    public void run(){
        
        this.connect();
        while(datahandler.getThreadStatus()){
            try {
                // semaphore should block until GUI sets new data and 
                semaphore.acquire();
                this.send(this.IPAddress, datahandler.getValues("UDP"),this.port);
            } catch (InterruptedException ex) {
                Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                semaphore.release();
            }
            
        }
        clientSocket.close();
    }
    
    /*
    * connect method
    */
    private void connect(){
        try {
            clientSocket = new DatagramSocket();
        }  catch (SocketException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /* 
    * send byte[] packet to socket 
    */
    public void send(InetAddress ipAddress, byte[] data, int port){
         try {
            DatagramPacket packet = new DatagramPacket(data, 
                                        data.length, 
                                        IPAddress,
                                        port);
            clientSocket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(UDPsender.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
    
}
