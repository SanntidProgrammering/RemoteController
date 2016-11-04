/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mgrib
 */
public class VideoReceiver implements Runnable {
    
    private DatagramSocket serverSocket;
    private BufferedImage scaledImage = null;
    
    public VideoReceiver()
    {
    }
    
    public void run()
    {
        try {
            serverSocket = new DatagramSocket(9876);
            BufferedImage buff;
            
            byte[] receiveData = new byte[57654];
            
            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("Receiving");
                this.scale(receiveData, 640, 480);

                //receiveData = this.scale(receiveData, 640, 480);
                //this.saveImage(receiveData);
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveImage(byte[] data)
    {
        try {
        FileOutputStream fos = new FileOutputStream("image.bmp");
            try {
                fos.write(data);
            }
            finally {
                fos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(UDPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scale(byte[] fileData, int width, int height) {
    	ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        
    	try {
    		BufferedImage img = ImageIO.read(in);
    		if(height == 0) {
    			height = (width * img.getHeight())/ img.getWidth(); 
    		}
    		if(width == 0) {
    			width = (height * img.getWidth())/ img.getHeight();
    		}
                
                Image toolkitImage = img.getScaledInstance(width, height, 
                Image.SCALE_SMOOTH);

                 // width and height are of the toolkit image
                BufferedImage newImage = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_ARGB);
                Graphics g = newImage.getGraphics();
                g.drawImage(toolkitImage, 0, 0, null);
                g.dispose();
                
                scaledImage = newImage;


    		
    	} catch (IOException e) {
    		
    	}
    }
    
    public BufferedImage getImage()
    {
        return scaledImage;
    }
}