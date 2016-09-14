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
    
    public void run(int port) {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[6];
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            
            while()
            
        }
    }
    
}
    
    

