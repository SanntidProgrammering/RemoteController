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
class DaemonThread implements Runnable
    {
    protected volatile boolean runnable = false;
    int count = 0;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    private Thread stream;
    private VideoReceiver receiver;
    
    JPanel jPanel1;
    
    public DaemonThread(JPanel videoPanel){
    this.jPanel1 = videoPanel;
    receiver = new VideoReceiver();
    stream = new Thread(receiver);
    stream.start();
    
    //webSource =new VideoCapture(0);         
    }
    
    public void realseSource(){
        //webSource.release();
    }
    public void connectCam(){
        //webSource.grab();
    }
    public void setRunnable(boolean value){
        this.runnable = value;
    }

    
    @Override
    public  void run()
    {
        synchronized(this)
        {
            while(runnable)
            {
		BufferedImage buff = (BufferedImage) receiver.getImage();
                if(buff != null){
                    Graphics g=jPanel1.getGraphics();
                    if (g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null))
                    {}
                }
                
		if(this.runnable == false){ // if(runnable == false){
                    
		    System.out.println("Going to wait()");
                    while(!runnable){
                        
                    }
                    //try {
                    //    this.wait();
                    //    } 
                    //catch (InterruptedException ex) {
                    //    Logger.getLogger(DaemonThread.class.getName()).log(Level.SEVERE, null, ex);
                    //    }
		}
	    }
			 
        }
    }
}
   