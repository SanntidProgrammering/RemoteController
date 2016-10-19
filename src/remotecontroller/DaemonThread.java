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
    
    JPanel jPanel1;
    
    public DaemonThread(JPanel videoPanel){
    this.jPanel1 = videoPanel;
    webSource =new VideoCapture(1);         
    }
    
    public void realseSource(){
        webSource.release();
        this.jPanel1.setVisible(false);
    }
    public void connectCam(){
        this.jPanel1.setVisible(true);
        webSource.grab();
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
                if(webSource.grab())
                {
		    	try
                        {
                            webSource.retrieve(frame);
                           
			    Highgui.imencode(".bmp", frame, mem);
			    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));

			    BufferedImage buff = (BufferedImage) im;
                           
			    Graphics g=jPanel1.getGraphics();

			    if (g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() -150 , 0, 0, buff.getWidth(), buff.getHeight(), null))
			    
			    if(runnable == false)
                            {
			    	System.out.println("Going to wait()");
			    	this.wait();
			    }
			 }
			 catch(Exception ex)
                         {
			    //System.out.println("Error");
                         }
                }
            }
        }
     }
   }