
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.tranfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 *
 * @author HuynhTanPhu
 */
public class TextMessage {
    private OutputStream os;
    private InputStream is;
    private String message;
    public TextMessage(OutputStream os, String message)
    {
        this.os = os;
        this.message = message;
    }
    public TextMessage(InputStream is)
    {
        this.is = is;
    }
    
    public void send()
    {
        try
        {
           byte[] b = message.getBytes();
           os.write(b);
        }catch(IOException oe)
        {
            System.out.println("Loi: Goi tin nhan bi loi" + oe);
        }
    }
    public String receive()
    {
        String message = null;
        try
        {
             FPSCounter obj = new FPSCounter();
             while(true)
             {
                obj.BeginCount();
                int num = is.available();
                if (num > 0)
                {
                    byte[] b = new byte[num];
                    int result = is.read(b); //Doc du lieu tu inputstream, luu vao mang b, tra so phan tu cua mang cho n
                    if (result == -1 ) break;
                    String str = new String(b);
                    message = str;
                    break;
                }
                obj.EndCount();
             }
        }
        catch(IOException ie)
        {
            System.out.println("Loi: Nhan tin nhan bi loi" + ie);  
        }
        return message;
    }
}
