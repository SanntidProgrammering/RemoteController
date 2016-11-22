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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;



/**
 *
 * @author mgrib
 */
class CameraThread implements Runnable
    {
    protected volatile boolean runnable = false;
    //int count = 0;
    //VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    private Thread stream;
    private VideoReceiver receiver;
    private JPanel videoPanel;
    
    public CameraThread(JPanel videoPanel){
    this.videoPanel = videoPanel;
    //receiver = new VideoReceiver();
    stream = new Thread(receiver);
    stream.start();      
    }
    
    /**
     * Sets the runnable flag to true or false
     * 
     * @param value True or False, runnable
     */
    public void setRunnable(boolean value){
        this.runnable = value;
    }

    /**
     * Run method. Receives buffered image from VideoReceiver, checks that
     * bufferedImage is not null. Updates the panel.
     */
    @Override
    public  void run()
    {
        synchronized(this)
        {
            while(runnable)
            {
		BufferedImage buff = (BufferedImage) receiver.getImage();
                if(buff != null){
                    Graphics g=videoPanel.getGraphics();
                    g.drawImage(buff, 0, 0, videoPanel.getWidth(), videoPanel.getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null);
                }                
		if(this.runnable == false){                    
		    System.out.println("Going to wait()");
                    while(!runnable){                        
                    }                    
		}
	    }
			 
        }
    }
}
   