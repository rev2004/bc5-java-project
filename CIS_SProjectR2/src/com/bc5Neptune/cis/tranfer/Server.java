package com.bc5Neptune.cis.tranfer;

import com.bc5Neptune.cis.bll.ProcessImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Server {

    /* default port of server */
    public int defaultPort = 2411;
    /* default ip is localhost: 127.0.0.1 */
    public String ip = "127.0.0.1"; //ip of localhost
    /* object of InetAddress */
    public InetAddress net;
    /* server sokcet */
    public ServerSocket ss;
    /* state of server, start or exit */
    public boolean state = false;
    /* log in server */
    public static String log = "";
    /* taking image */
    public static BufferedImage imageTaking = null;
    /* index of client */
    public static int indexClient = 0;
    /* client socket */
    public static ArrayList<ClientInfor> clientArr = new ArrayList<ClientInfor>();

    public Server() {
        
        try {
            net = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
        }
    }

    public Server(int defaultPort, String ip) {
        this.defaultPort = defaultPort;
        this.ip = ip;
        try {
            net = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
        }
    }

    public void start() {
        try {
            ss = new ServerSocket(defaultPort, 1, net);
            //update state of server
            state = true;
            System.out.println("Server start ...");
            //loop to accept connection
            while (true) {
                ClientInfor client = new ClientInfor();
                //= new Socket(); //create new socket
                client.socketString = ss.accept(); //wait for a client connecting
                client.socketImage = ss.accept();

                //add client into client array
                clientArr.add(client);
                //process for client
                log += "---------WAITING CONNECTION---------\n";
                log += "> Waiting for connection from a client...\n";
                log += "> A client connected to server: client " + clientArr.size() + "\n";
                //send a message to client that connected
//                TextMessage ms = new TextMessage(client.socketString.getInputStream());
//                String str = ms.receive();
//                System.out.println(str);
                TextMessage msg = new TextMessage(client.socketString.getOutputStream(), "> Note from server: connect successfully");
                msg.send();

                TakingImage clientProcess = new TakingImage(client.socketImage);
                //start
                clientProcess.start();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }

    public boolean isStart() {
        return state;
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return defaultPort;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.defaultPort = port;
    }
    //stop server

    public void stop() {
        try {
            ss.close();
            System.out.println("Server stoped");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy() {
       
    }

    public void exit() {
        try {
            //code implement here
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //get log 
    public String getLog() {

        return log;
    }
    //set log

    public void setLog(String log) {
        this.log = log;
    }

    //get image
    public BufferedImage getImage() {
        return imageTaking;
    }
    //set image 

    public void setImage(BufferedImage bufferedImage) {
        imageTaking = bufferedImage;
    }
    //set null for image

    public void setNull() {
        imageTaking = null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Server server = new Server();
        server.start();

    }
}

class TakingImage extends Thread {
    /* socket of client */

    Socket socketImage;

    public TakingImage(Socket s) {
        this.socketImage = s;
    }
    //send a message to a client, client connect sucessfully

    @Override
    public void run() {
        FPSCounter obj = new FPSCounter();//frame per second 
        while (true) {
            obj.BeginCount();
            BufferedImage image = null;
            try {
                ByteMessage msgByte = new ByteMessage(socketImage.getInputStream());
                ProcessImage img = new ProcessImage();
                byte[] b = msgByte.receive();
                if (b != null) {
                    image = img.byteArrayToBufferedImage(b);
                    //ImageIO.write(image, "jpg", new File("/home/enclaveit/server.jpg"));
                    Server.imageTaking = image;
                    //identify client
                    Server.indexClient = Server.clientArr.size() - 1;

                    Server.log += "---------GET A IMAGE FORM CLIENT---------\n";
                    Server.log += "> Took a image from a client\n";
                    // System.out.println("Save a image to home folder");
                    break;

                }
            } catch (IOException ex) {
                Logger.getLogger(TakingImage.class.getName()).log(Level.SEVERE, null, ex);
            }

            obj.EndCount();
        }
    }
}