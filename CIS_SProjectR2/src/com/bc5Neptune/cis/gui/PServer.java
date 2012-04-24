/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PServer.java
 *
 * Created on Apr 18, 2012, 2:01:53 PM
 */
package com.bc5Neptune.cis.gui;

import com.bc5Neptune.cis.bll.HorizontalIconRender;
import java.awt.event.InputEvent;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.Point;
import java.awt.Rectangle;
import com.bc5Neptune.cis.tranfer.ClientInfor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import com.bc5Neptune.cis.bll.FaceDetection;
import com.bc5Neptune.cis.bll.IconList;
import com.bc5Neptune.cis.bll.ProcessImage;
import com.bc5Neptune.cis.bll.VerticalIconRender;
import com.bc5Neptune.cis.tranfer.ByteMessage;
import com.bc5Neptune.cis.tranfer.FPSCounter;
import com.bc5Neptune.cis.tranfer.Server;
import com.bc5Neptune.cis.tranfer.TextMessage;
import com.googlecode.javacv.HandMouse;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static com.bc5Neptune.cis.bll.GlobalObject.*;

/**
 *
 * @author phu.huynh
 */
public class PServer extends javax.swing.JPanel {

    Server server;
    ArrayList<IconList> faceArr = new ArrayList<IconList>();
    BufferedImage imgServer;
    public static PCustomFaceServer objCustomFace;
    /* save image that could not recognition */
    public static ArrayList<BufferedImage> noRecogArr = new ArrayList<BufferedImage>();
    /* save a image that could not recogniton in a list*/
    ArrayList<IconList> noRegIconArr = new ArrayList<IconList>();

    /* custom face array*/
    public static ArrayList<IconList> customFaceArr = new ArrayList<IconList>();
    /* save client when software can't recognition */
    public static ArrayList<ClientInfor> clientSaveArr = new ArrayList<ClientInfor>();

    public static void renderCustomFace() {
        lstCustomFaces = new JList(customFaceArr.toArray());
        //create render
        HorizontalIconRender ren = new HorizontalIconRender();
        lstCustomFaces.setCellRenderer(ren);
        //set view
        scrollCustomFaces.setViewportView(lstCustomFaces);
        lstCustomFaces.revalidate();//clean
        lstCustomFaces.repaint();//repainta

        //add listener
        lstCustomFaces.addMouseListener(new CustomFaceListener());
    }

    public void renderNoFaceRecog() {
        int size = noRecogArr.size();
        if (size > 0) {
            noRegIconArr.add(new IconList("No Recognition", noRecogArr.get(size - 1), 92, 112));
        }
        //add on list
        lstFaceNotRecog = new JList(noRegIconArr.toArray());
        //create render
        VerticalIconRender ren = new VerticalIconRender();
        lstFaceNotRecog.setCellRenderer(ren);
        //set view
        scrollFaceNotRecog.setViewportView(lstFaceNotRecog);
         lstFaceNotRecog.revalidate();//clean
        lstFaceNotRecog.repaint();//repainta
        //right click menu for no recognition
        lstFaceNotRecog.addMouseListener(new NoRecognitionListener());
    }

    /** Creates new form PServer */
    public PServer() {
        //init image to draw

        initComponents();
        //invisible btnStop button
        btnStop.setEnabled(false);
        //show custom face
        pnlShow.setLayout(new BoxLayout(pnlShow, BoxLayout.PAGE_AXIS));
        pnlShow.repaint(); //repaint a image
        pnlShow.revalidate(); //clear
        objCustomFace = new PCustomFaceServer();
        pnlShow.add(objCustomFace);
        btnStop.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                 btnStart.setEnabled(true);
                 btnStop.setEnabled(false);
                 server.stop();
                 
            }
            
        });
        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtPort.getText().equals("") || txtHost.getText().equals("")) {
                    JOptionPane.showConfirmDialog(null,
                            "Please input information", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    //don't have permission when click a lots
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    final int port = Integer.parseInt(txtPort.getText());
                    final String ip = txtHost.getText();
                    server = new Server(port, ip);
                    //create a new thread to run server
                    new Thread() {

                        @Override
                        public void run() {
                            //if (server.isStart()) {//check state of server
                            txtLog.append("--------------SERVER INFOR---------------\n");
                            txtLog.append("> Server started at\n");
                            txtLog.append("> Host Address: " + ip + "\n");
                            txtLog.append("> Port: " + port + "\n");


                            //}
                            server.start();
                        }
                    }.start();
                }

            }
        });

        //create a new thread to update information form server
        //detect face and show it into lstFace
        new Thread() {

            @Override
            public void run() {
                FPSCounter obj = new FPSCounter();
                obj.setFPS(60);
                String message = null;
                while (true) {
                    obj.BeginCount();
                    if (server != null) {
                        //show log message from server
                        message = server.getLog();
                        if (!message.equals("")) {
                            txtLog.append(message);
                            server.setLog("");
                        }

                        //update image to list
                        if (server.getImage() != null) {

                            //resize a image if it to big
                            BufferedImage bufferedImg = server.getImage();
                            int width = bufferedImg.getWidth();
                            int height = bufferedImg.getHeight();
                            System.out.println("Width: " + width + " Height: " + height);
                            boolean resize = false;
                            if (width > 500) {
                                width = 500;
                                resize = true;
                            }
                            if (height > 500) {
                                height = 500;
                                resize = true;
                            }
                            if (resize) {
                                ProcessImage objImg = new ProcessImage();
                                bufferedImg = objImg.resize(bufferedImg, width, height);
                                Server.log += "--------------RESIZE IMAGE-----------------\n";
                                System.out.println("Resize a image");
                                Server.log += "> Resized a image with dimentions 300 X 200\n";
                            }
                            //show a image that server was taken in a JLabel

                            imgServer = server.getImage();
                            objCustomFace.setImage(imgServer);
                            objCustomFace.repaint();//repaint again
                            //MODIFY: here
                            //ImageIcon icon = new ImageIcon(imgServer);
                            //lblShowImage.setIcon(icon);

                            Server.log += "--------------SHOW IMAGE-----------------\n";
                            Server.log += "> Show a image to screen\n";
                            //set null for a image at server
                            server.setNull();
                            Server.log += "--------------DETECTION FACES-----------------\n";
                            server.log += "> Detecting face ...\n";
                            //detect face for this image
                            FaceDetection objDetect = new FaceDetection();
                            ArrayList<BufferedImage> faceArr = new ArrayList<BufferedImage>();
                            faceArr = objDetect.detectFace(bufferedImg);


                            //show faces that detected into JList

                            int length = faceArr.size();
                            server.log += "> The number of faces that found is: " + length + "\n";
                            IconList[] iconArr = new IconList[length];
                            for (int i = 0; i < length; i++) {
                                iconArr[i] = new IconList("Auto Detection",
                                        faceArr.get(i),
                                        92,
                                        112);
                            }
                            if (length > 0) {
                                lstFaceServer = new JList(iconArr);
                                VerticalIconRender render = new VerticalIconRender();
                                lstFaceServer.setCellRenderer(render);
                                scrollFaceServer.setViewportView(lstFaceServer);
                                server.log += "> Update faces to left list\n";
                            } else {//could not detect face
                                //save a image that could not recogniton in a list
                                //wait for a person custom face then recognition again
                                noRecogArr.add(imgServer);
                                //show it into list
                                //IconList iconArr = new
                                int size = noRecogArr.size();
                                noRegIconArr.add(new IconList("No Recognition", noRecogArr.get(size - 1), 92, 112));
                                //add on list
                                lstFaceNotRecog = new JList(noRegIconArr.toArray());
                                //create render
                                VerticalIconRender ren = new VerticalIconRender();
                                lstFaceNotRecog.setCellRenderer(ren);
                                //set view
                                scrollFaceNotRecog.setViewportView(lstFaceNotRecog);
                            }

                            //recognition faces
                            Server.log += "--------------RECOGNITION FACE-----------------\n";

                            for (int i = 0; i < length; i++) {
                                server.log += "> Begin recognition face: " + (i + 1) + "\n";
                                GLReg.recognizeFile(faceArr.get(i));
                                ArrayList<String> faceNearest = new ArrayList<String>();
                                faceNearest = GLReg.getNearestPerson();
                                server.log += "> End recognition face: " + (i + 1) + "\n";
                                for (int j = 0; j < faceNearest.size(); j++) {
                                    System.out.println("nearest face: " + faceNearest.get(j));
                                    server.log += "> Nearest face: " + faceNearest.get(j) + "\n";
                                }

                                //send information to client
                                int index = Server.indexClient;
                                try {
                                    server.log += "--------------SEND INFOR TO CLIENT-----------------\n";
                                    server.log += "> Send information\n";
                                    ClientInfor clientCon = Server.clientArr.get(index);
                                    OutputStream osString = clientCon.socketString.getOutputStream();

                                    if (faceNearest.size() > 0) {
                                        TextMessage msg = new TextMessage(osString, faceNearest.get(length - 1));
                                        msg.send();//begin send message
                                        System.out.println("Send Information");
                                        server.log += faceNearest.get(length - 1) + "\n";

                                        //send image
                                        server.log += "> Send Image\n";

                                        byte[] byteImage = new ProcessImage().bufferedImageToByteArray(bufferedImg);

                                        ByteMessage byteMgs = new ByteMessage(clientCon.socketImage.getOutputStream(), byteImage);
                                        byteMgs.send();

                                    } else {
                                        TextMessage msg = new TextMessage(osString, "> No nearest person, please wait for processing");
                                        msg.send();

                                        //save a image that could not recogniton in a list
                                        //wait for a person custom face then recognition again
                                        noRecogArr.add(imgServer);
                                        //show it into list
                                        renderNoFaceRecog();
                                        //save client
                                        System.out.println("Add client to recognize face again");
                                        clientSaveArr.add(clientCon);

                                    }


                                } catch (IOException ex) {
                                    Logger.getLogger(PServer.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                        }
                    }

                    obj.EndCount();
                }
            }
        }.start();




    }

    class NoRecognitionListener extends MouseAdapter {

        JPopupMenu rightMenu = new JPopupMenu("Right menu");

        public NoRecognitionListener() {


            /*
             * Detelete This Image
             */
            JMenuItem deleteFace = new JMenuItem("Detelete This Image");
            deleteFace.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
            deleteFace.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    final int index = PServer.lstFaceNotRecog.getSelectedIndex();
                    if (index >= 0) {
                        PServer.noRecogArr.remove(index);
                        renderNoFaceRecog();
                    }
                }
            });
            rightMenu.add(deleteFace);

            /*
             * Delete All of Images
             */
            JMenuItem deleteAllOfFace = new JMenuItem("Delete All of Images");
            deleteAllOfFace.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
            deleteAllOfFace.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int count = noRecogArr.size();
                    while (count > 0) {
                        noRecogArr.remove(0);
                        count = noRecogArr.size();
                    }
                    renderNoFaceRecog();
                }
            });
            rightMenu.add(deleteAllOfFace);


        }
        /*
         * set icon for right click menu
         */

        public ImageIcon getIcon(String path) {
            ImageIcon icon = new ImageIcon(path);
            return icon;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getModifiers()) {
                case InputEvent.BUTTON1_MASK: {
                    if (e.getClickCount() == 2) {
                        /*
                         * double click
                         */
                        int index = PServer.lstFaceNotRecog.getSelectedIndex();
                        if (index >= 0) {
                            BufferedImage bufferedImage = PServer.noRecogArr.get(index);
                            PServer.objCustomFace.setImage(bufferedImage);
                            //repaint
                            PServer.objCustomFace.repaint();
                        }
                        System.out.println("Double click me");
                    }
                    break;
                }
                case InputEvent.BUTTON2_MASK: {

                    System.out.println("That's the MIDDLE button");

                    break;
                }
                case InputEvent.BUTTON3_MASK: {
                    System.out.println("That's the RIGHT button");
                    //rightMenu.pack();
                    rightMenu.show(e.getComponent(), e.getX(), e.getY());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        PServer test = new PServer();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(test);
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.setVisible(true);
    }

    static class CustomFaceListener extends MouseAdapter {

        JPopupMenu rightMenu = new JPopupMenu("Right menu");

        public CustomFaceListener() {


            /*
             * Recognize This Image
             */
            JMenuItem recogFace = new JMenuItem("Recognize This Image");
            //recogFace.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
            recogFace.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    final int index = PServer.lstCustomFaces.getSelectedIndex();
                    PServer.lstFaceNotRecog.setSelectedIndex(0);
                    final int indexImage = PServer.lstFaceNotRecog.getSelectedIndex();
                    if (index >= 0) {
                        final BufferedImage image = PServer.customFaceArr.get(index).getImage();
                        new Thread() {

                            @Override
                            public void run() {
                                if (PServer.noRecogArr.size() > 0) {
                                    GLReg.recognizeFile(image);
                                    ArrayList<String> faceNearest = new ArrayList<String>();
                                    faceNearest = GLReg.getNearestPerson();
                                    for (int j = 0; j < faceNearest.size(); j++) {
                                        System.out.println("nearest face: " + faceNearest.get(j));
                                    }
                                    int size = faceNearest.size();
                                    if (faceNearest.size() > 0) {
                                        //send infor to client
                                        System.out.println("------------index of client-----------------" + indexImage);
                                        if (clientSaveArr.size() > 0) {
                                            
                                            ClientInfor clientInfo = clientSaveArr.get(indexImage);

                                            try {
                                                Server.log += "---------SEND TO CLIENT AGAIN ----------\n";
                                                TextMessage msg = new TextMessage(clientInfo.socketString.getOutputStream(),
                                                        faceNearest.get(size - 1));
                                                Server.log += "The information to client:" + faceNearest.get(size - 1);
                                                msg.send();
                                                
                                                //send a image to client
                                                
                                            } catch (IOException ex) {
                                                Logger.getLogger(PServer.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                    } else {
                                        System.out.println("Could not found nearest person");
                                    }
                                } else {
                                    JOptionPane.showConfirmDialog(null,
                                            "Could not detect a client to recognize again",
                                            "Warning", JOptionPane.CLOSED_OPTION);
                                }
                            }
                        }.start();
                    }
                }
            });
            rightMenu.add(recogFace);

            /*
             * Detelete This Image
             */
            JMenuItem deleteFace = new JMenuItem("Detelete This Image");

            deleteFace.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
            deleteFace.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final int index = PServer.lstCustomFaces.getSelectedIndex();
                            if (index >= 0) {
                                customFaceArr.remove(index);
                                PServer.renderCustomFace();
                            }
                        }
                    });
            rightMenu.add(deleteFace);

            /*
             * Delete All of Images
             */
            JMenuItem deleteAllOfFace = new JMenuItem("Delete All of Images");

            deleteAllOfFace.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
            deleteAllOfFace.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int count = customFaceArr.size();
                            while (count > 0) {
                                customFaceArr.remove(0);
                                count = customFaceArr.size();
                            }
                            PServer.renderCustomFace();

                        }
                    });
            rightMenu.add(deleteAllOfFace);
        }
        /*
         * set icon for right click menu
         */

        public ImageIcon getIcon(String path) {
            ImageIcon icon = new ImageIcon(path);
            return icon;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (e.getModifiers()) {
                case InputEvent.BUTTON1_MASK: {
                    if (e.getClickCount() == 2) {
                        /*
                         * double click
                         */
                        int index = PServer.lstFaceNotRecog.getSelectedIndex();
                        if (index >= 0) {
                            BufferedImage bufferedImage = PServer.noRecogArr.get(index);
                            PServer.objCustomFace.setImage(bufferedImage);
                            //repaint
                            PServer.objCustomFace.repaint();
                        }
                        System.out.println("Double click me");
                    }
                    break;
                }
                case InputEvent.BUTTON2_MASK: {

                    System.out.println("That's the MIDDLE button");

                    break;
                }
                case InputEvent.BUTTON3_MASK: {
                    System.out.println("That's the RIGHT button");
                    //rightMenu.pack();
                    rightMenu.show(e.getComponent(), e.getX(), e.getY());
                    break;
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        scrollFaceServer = new javax.swing.JScrollPane();
        lstFaceServer = new javax.swing.JList();
        scrollFaceNotRecog = new javax.swing.JScrollPane();
        lstFaceNotRecog = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        pnlShow = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        scrollCustomFaces = new javax.swing.JScrollPane();
        lstCustomFaces = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        jSplitPane1.setDividerLocation(50);
        jSplitPane1.setDividerSize(1);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel2.setText("Host");

        txtHost.setText("192.168.10.70");
        txtHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHostActionPerformed(evt);
            }
        });

        jLabel3.setText("Port");

        txtPort.setText("2411");

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Play/Play_16x16.png"))); // NOI18N

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Stop/Stop_16x16.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStart)
                        .addComponent(btnStop))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(jPanel2);

        jSplitPane2.setDividerLocation(250);
        jSplitPane2.setDividerSize(1);

        scrollFaceServer.setViewportView(lstFaceServer);

        scrollFaceNotRecog.setViewportView(lstFaceNotRecog);

        jLabel1.setText("Face Detected");

        jLabel4.setText("Not Recognition");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollFaceNotRecog, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(scrollFaceServer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(scrollFaceServer, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(scrollFaceNotRecog, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel4);

        jSplitPane3.setDividerLocation(400);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        javax.swing.GroupLayout pnlShowLayout = new javax.swing.GroupLayout(pnlShow);
        pnlShow.setLayout(pnlShowLayout);
        pnlShowLayout.setHorizontalGroup(
            pnlShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        pnlShowLayout.setVerticalGroup(
            pnlShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jSplitPane3.setTopComponent(pnlShow);

        lstCustomFaces.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        lstCustomFaces.setVisibleRowCount(1);
        scrollCustomFaces.setViewportView(lstCustomFaces);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollCustomFaces, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollCustomFaces, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Custom Faces", jPanel1);

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Log", jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );

        jSplitPane3.setRightComponent(jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHostActionPerformed
    }//GEN-LAST:event_txtHostActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JList lstCustomFaces;
    public static javax.swing.JList lstFaceNotRecog;
    private static javax.swing.JList lstFaceServer;
    private javax.swing.JPanel pnlShow;
    public static javax.swing.JScrollPane scrollCustomFaces;
    private javax.swing.JScrollPane scrollFaceNotRecog;
    private javax.swing.JScrollPane scrollFaceServer;
    private javax.swing.JTextField txtHost;
    public javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
