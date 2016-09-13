/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecontroller;

/**
 *
 * @author mgrib
 */
public class Controller {
    private TempGUI gui;
    private UDPClient client;
    private Protocol protocol;
    // private String odroid Mac Adress
    // private String ip address
    //
    
    public Controller(){
        gui = new TempGUI();  //new GUI();
        gui.setController(this);
        gui.setVisible(true);
        client = new UDPClient("158.38.199.18");
        
    }
}

