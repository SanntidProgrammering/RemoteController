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

/*
Sending protocol:
Byte 0-x: [bit 0, bit 1, bit 2, bit 3, bit 4, bit 5, bit 6, bit 7]

Bit 1 = ON/Active
bit 2 = Off/Not active

Byte 0:[stopp, fwd, rev, left, right, bit 5, bit 6, bit 7]
Byte 1:[left motor speed]
Byte 2:[right motor speed]
Byte 3:[left servo,right servo, auto/manual, start program,...,request feedback]
Byte 4:[sensitivity]
Byte 5:[reservert]

Receiving protocol:
Byte 0: Pixy x value lowbyte
Byte 1: Pixy x value highbyte
Byte 2: Pixy y value lowbyte
Byte 3: Pixy y value highbyte
Byte 4: Distance sensor
Byte 5: reserved


 */
public class Datahandler {

    private byte[] receivedData;
    private byte[] sendData;

    private boolean startThreads;

    public Datahandler() {
        this.receivedData = new byte[6];
        this.sendData = new byte[6];
    }

    public void setReceivedData(byte[] receivedData) throws IllegalArgumentException {
        if (receivedData.length != this.receivedData.length) {
            throw new IllegalArgumentException("wrong byte array passed to setReceivedData");
        } else {
            this.receivedData = receivedData;
        }
    }

    public synchronized byte[] getValues(String id) {
        byte[] returnArray = null;

        if (id.equals("GUI")) {
            returnArray = this.receivedData;
        } else if (id.equals("UDP")) {
            returnArray = this.sendData;
        }
        notifyAll();
        return returnArray;
    }

    public synchronized void setValues(String id, byte[] newByteArray) {

        if (id.equals("GUI")) {
            this.sendData = newByteArray;
        } else if (id.equals("UDP")) {
            this.receivedData = newByteArray;
        }
        notifyAll();
    }

    public boolean getThreadStatus() {
        return startThreads;
    }

}
