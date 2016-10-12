/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.util.Arrays;

/**
 *
 * @author mgrib
 */
public class SendData {
    
    public SendData(){
        
    }
    
    public void sendDataToSocket(byte[] data) {
        //System.out.println("Inne i send data: ");
        //System.out.println(Arrays.toString(data));
        new UDPsender().send(Main.IPADDRESS, data, Main.SENDPORT);
    }
}
