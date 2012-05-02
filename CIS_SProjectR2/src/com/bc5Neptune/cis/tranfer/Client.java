package com.bc5Neptune.cis.tranfer;
import com.bc5Neptune.cis.bll.ProcessImage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Client {

    public int defaultPort = 2411;
    public String ip = "192.168.10.70";
    public Socket socketString;
    public Socket socketImage;

    public Client() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client client = new Client();
        client.connect();
    }

    public void connect() {
        try {
            socketString = new Socket(ip, defaultPort);
            TextMessage msg = new TextMessage(socketString.getInputStream());
            //connect socket image
            socketImage = new Socket(ip, defaultPort);

            if (socketString == socketImage) {
                System.out.println("I'm gona die");
            }
            String strShow = msg.receive();
            System.out.println(strShow);
            sendImage();
            //initlization receive class
            Receive receive = new Receive(socketImage, socketString);
            receive.start();//start listening


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendImage() {
        ProcessImage img = new ProcessImage();
        BufferedImage image = null;
        image = img.readImage("/home/enclaveit/ngoc2.jpg");
        byte[] byteArray = img.bufferedImageToByteArray(image);
        try {
            ByteMessage msgByte = new ByteMessage(socketImage.getOutputStream(), byteArray);
            msgByte.send();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Receive extends Thread {

    Socket socketImage;
    Socket socketString;

    public Receive(Socket socketImage, Socket socketString) {
        this.socketImage = socketImage;
        this.socketString = socketString;
    }

    @Override
    public void run() {
        //listen to message
        FPSCounter obj = new FPSCounter();
        obj.setFPS(60);//60 frame per second
        while (true) {
            obj.BeginCount();
            try {
                TextMessage msg = new TextMessage(socketString.getInputStream());
                String message = msg.receive();
                if (message != null) {
                    System.out.println("Information of this person: " + message);

                    if (message.charAt(0) != '#') {
                        ByteMessage msgByte = new ByteMessage(socketImage.getInputStream());
                        byte[] b = msgByte.receive();
                        // System.out.println("Waiting for get information again");

                        //write a image to home folder
                        if (b != null) {
                            BufferedImage bufferedImage = new ProcessImage().byteArrayToBufferedImage(b);
                            ImageIO.write(bufferedImage, "jpg", new File("/home/enclaveit/picture_from_server.jpg"));
                            System.out.println("Save Image Sucessfully");
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.EndCount();
        }
    }
}
