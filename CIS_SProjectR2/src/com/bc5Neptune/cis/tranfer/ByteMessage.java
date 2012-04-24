/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.tranfer;

/**
 *
 * @author phu.huynh
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author HuynhTanPhu
 */
public class ByteMessage {

    private OutputStream os;
    private InputStream is;
    private byte[] byteArray;

    public ByteMessage(OutputStream os, byte[] byteArray) {
        this.os = os;
        this.byteArray = byteArray;
    }

    public ByteMessage(InputStream is) {
        this.is = is;
    }

    public void send() {
        try {
            byte[] b = byteArray;
            os.write(b);
        } catch (IOException oe) {
            System.out.println("Error: send message" + oe);
        }
    }

    public byte[] receive() {
        byte[] byteArrayReceive = null;
        try {
            FPSCounter obj = new FPSCounter();
            int check = is.available(); 
            if (check > 0) {//check data, if exist then get it
                while (true) {
                    obj.BeginCount();
                    int num = is.available();
                    if (num > 0) {
                        byteArrayReceive = new byte[num];
                        int result = is.read(byteArrayReceive); //Doc du lieu tu inputstream, luu vao mang b, tra so phan tu cua mang cho n
                        if (result == -1) {
                            break;
                        }
                        break;
                    }
                    obj.EndCount();
                }
            }
        } catch (IOException ie) {
            System.out.println("Error: get message" + ie);
        }
        return byteArrayReceive;
    }
}
