/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PFaceRecognition.java
 *
 * Created on Mar 23, 2012, 9:58:00 AM
 */
package com.bc5Neptune.cis.gui;

import static com.bc5Neptune.cis.bll.GlobalObject.*;
import static com.bc5Neptune.cis.bll.GlobalObject.*;
import com.bc5Neptune.cis.bll.FaceRecognition;
import com.bc5Neptune.cis.bll.GlobalObject;
import com.bc5Neptune.cis.bll.VerticalIconRender;
import com.bc5Neptune.cis.bll.ReadFolder;
import com.bc5Neptune.cis.bll.IconList;
import com.bc5Neptune.cis.bll.ProcessFile;
import com.bc5Neptune.cis.bll.ProcessImage;
import com.bc5Neptune.cis.config.Config;
import com.bc5Neptune.cis.entity.PersonEntity;
import com.bc5Neptune.cis.dal.PersonDAL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author phu.huynh
 */
public class PFaceRecognition extends JPanel {
    /* array of face of images */

    public ArrayList<BufferedImage> listFace = new ArrayList<BufferedImage>();
    /* array for list cell render */
    public ArrayList< IconList> objSmall = new ArrayList<IconList>();
    /* vertical cell render */
    private VerticalIconRender render;


    /** Creates new form PFaceRecognition */
    public PFaceRecognition() {
        initComponents();
    }

    public int getLength() {
        return listFace.size();
    }

    /* get image from index */
    public BufferedImage getBufferedImage(int index) {
        return listFace.get(index);
    }

    public void updateListFace() {
        //
        int length = getLength();
        //if (getLength() > 0) {
        // objSmall = new SmallImage[length];
        //for (int i = obj; i < length; i++) {
        //  objSmall[i] = new SmallImage("unknow", getBufferedImage(i), 92, 112);
        //}
        lstFace = new JList(objSmall.toArray());
        render = new VerticalIconRender();
        lstFace.setCellRenderer(render);
        scrollPane.setViewportView(lstFace);
        lstFace.addMouseListener(new RecognitionListener());
        //}

        // if (lstFace.size())
        // SmallImage objSmall = new SmallImage(TOOL_TIP_TEXT_KEY, null, WIDTH, WIDTH)
    }

    

    public void loadImage() {
        String tempPath = new String();


        fileChooseFolder.setDialogTitle("Choose a file");
        //this.getContentPane().add(fileChooseFolder);
        fileChooseFolder.setVisible(true);
        int returnVal = fileChooseFolder.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooseFolder.getSelectedFile();
            tempPath = selectedFile.getAbsolutePath();
            int ind = tempPath.lastIndexOf(".");
            if (ind == -1) { // no file type
                JOptionPane.showConfirmDialog(this,
                        "Please choose .jpg or .jpeg type!", "Warning",
                        JOptionPane.CLOSED_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            String tmp = tempPath.substring(ind);
            if (tmp.equalsIgnoreCase(".JPG") == false // .jpg or jpeg
                    || tmp.equalsIgnoreCase(".JPEG")) {
                JOptionPane.showConfirmDialog(this,
                        "Please choose .jpg or .jpeg type!", "Warning",
                        JOptionPane.CLOSED_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                System.out.println(tempPath);
                File file = new File(tempPath);
                BufferedImage img = ImageIO.read(file);

                if (img != null) {
                    ProcessImage objImp = new ProcessImage();
                    img = objImp.resize(img, 92, 112);
                    IconList icon = new IconList(selectedFile.getName(), img, 92, 112);
                    objSmall.add(icon);
                    updateListFace();
                } else {
                    System.out.println("Could not read a image");
                }
            } catch (IOException e) {
                Logger.getLogger(PFaceRecognition.class.getName()).log(Level.SEVERE, null);
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

        fileChooseFolder = new javax.swing.JFileChooser();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btnLoadImage = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        lstFace = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        tabFaceNearist = new javax.swing.JTabbedPane();

        setName("Face Recognition"); // NOI18N

        jSplitPane1.setDividerLocation(50);
        jSplitPane1.setDividerSize(1);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBackground(java.awt.Color.gray);

        btnLoadImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Picture/Picture_32x32.png"))); // NOI18N
        btnLoadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(636, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLoadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setTopComponent(jPanel1);

        jSplitPane2.setDividerLocation(150);
        jSplitPane2.setDividerSize(1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Face List"));

        scrollPane.setViewportView(lstFace);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel4.setBackground(java.awt.Color.lightGray);

        tabFaceNearist.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabFaceNearist, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabFaceNearist, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadImageActionPerformed
        // TODO add your handling code here:
        loadImage();
    }//GEN-LAST:event_btnLoadImageActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadImage;
    private javax.swing.JFileChooser fileChooseFolder;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    public javax.swing.JList lstFace;
    private javax.swing.JScrollPane scrollPane;
    public javax.swing.JTabbedPane tabFaceNearist;
    // End of variables declaration//GEN-END:variables
}

class RecognitionListener extends MouseAdapter {

    JPopupMenu rightMenu = new JPopupMenu("Right menu");

     /**
     * add button tab to JTabbedPane
     *
     * @param nameLabel a name of tab
     * @param action a action when click on it
     * @param pane a JPanel that you want add to tab
     * @param tabPane a JTabbedPane that you want JPanel add on it
     */
    public void addTab(final String nameLabel, final String action, final JPanel pane, final JTabbedPane tabPane) {
        /*
         * add JPanel to JTabedPane
         */
        for (int i = 0; i < tabPane.getComponentCount(); i++) {
            final JPanel pn = (JPanel) tabPane.getComponent(i);
            if (pn.getName() != null) {
                if (pn.getName().equals(pane.getName())) {
                    System.out.println("This panel is exist");
                    /*
                     * show to exist tab
                     */
                    tabPane.setSelectedComponent(pn);
                    return;
                }
            }

        }
        tabPane.add(pane);
        /*
         * create button to close tab
         */
        final JButton tabCloseButton = new JButton("X");
        /*
         * set border for button
         */
        //tabCloseButton.setBorder(new EmptyBorder(2, 2, 2, 2));
        /*
         * set string for action command
         */
        tabCloseButton.setActionCommand(action);
        tabCloseButton.setBounds(120, 2, 10, 10);
        tabCloseButton.setForeground(Color.red);

        /*
         * add a button and a label to JPanel (pane)
         */
        JPanel pnlAdd = new JPanel();
        pnlAdd.setLayout(null);
        pnlAdd.setOpaque(false);
        pnlAdd.setPreferredSize(new Dimension(130, 15));
        /*
         * add a label
         */
        final JLabel lb = new JLabel(nameLabel);

        //lb.setBorder(new EmptyBorder(0, 0, 0, 0));
        lb.setPreferredSize(new Dimension(3, 10));
        lb.setBounds(0, 0, 120, 16);
        pnlAdd.add(lb);
        /*
         * add a close button
         */
        pnlAdd.add(tabCloseButton);

        /*
         * add JPanel in to tab
         */
        tabPane.setTabComponentAt(tabPane.getTabCount() - 1, pnlAdd);
        tabPane.setSelectedIndex(tabPane.getTabCount() - 1);

        /*
         * create Action listener
         */
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JButton button = (JButton) e.getSource();
                /*
                 * get action command
                 */
                final String s1 = button.getActionCommand();
                /*
                 * find current tab and close it
                 */
                for (int i = 0; i < tabPane.getTabCount(); i++) {
                    final JPanel pnl = (JPanel) tabPane.getTabComponentAt(i);
                    button = (JButton) pnl.getComponent(1); //The zero(0) is a label
                    //the first(1) is a button
                    final String s2 = button.getActionCommand();
                    /*
                     * check action command and close tab
                     */
                    if (s1.equals(s2)) {
                        tabPane.removeTabAt(i);
                        break;
                    }
                }
            }
        };
        /*
         * set action listener for close button
         */
        tabCloseButton.addActionListener(al);
    }


    public RecognitionListener() {


        /*
         * Enable Region menu
         */
        JMenuItem enableRegion = new JMenuItem("Recognition this face");
        //addMenu.setIcon(getIconResize("../CIS_SProjectR2/src/icon/Delete_16x16.png"));

        enableRegion.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Recognition this face");

                        //call recognition

                        int index = GLPReg.lstFace.getSelectedIndex();

                        BufferedImage image = GLPReg.objSmall.get(index).getImage();

                        GLReg.recognizeFile(image);
                        //JList lstFaceNearist = new JList();
                        // JPanel panel = new JPanel();
                        //panel.add(lstFaceNearist);

                        IconList objSmall = (IconList) GLPReg.lstFace.getSelectedValue();
                        String str = objSmall.getName();

                        System.out.println(".................." + str);
                        final PPersonInformation personInfor = new PPersonInformation();
                        personInfor.pnlShowInfor.setVisible(false);
                        // add infor into list
                        //Object obj = GLReg.listIDPicture.toArray();
                        personInfor.listNearestPerson.setListData(GLReg.listNearestPerson.toArray());
                        ProcessFile prFile = new ProcessFile();

                        //get image into listicon 
                        int length = GLReg.listIDPicture.size();
                        IconList[] iconArr = new IconList[length];
                        for (int i = 0; i < length; i++) {
                            String name = GLReg.listNearestPerson.get(i);
                            float confidence = GLReg.listConfidence.get(i);
                            int idPicture = GLReg.listIDPicture.get(i).intValue();
                            System.out.println("-----------------------Name = " + name + "Id = " + idPicture);
                            BufferedImage imageIcon = prFile.loadBufferedFace("../CIS_SProjectR2/data/facedat/",
                                    name, idPicture);
                            imageIcon = new ProcessImage().resize(imageIcon, 50, 70);
                            iconArr[i] = new IconList("Identiy number: " + name + " like: [" + confidence + "]%", imageIcon, 50, 70);
                        }



                        // iconArr[0] = new IconList("Recognition", imageIcon, 20, 30);
                        personInfor.listNearestPerson = new JList(iconArr);

                        VerticalIconRender render = new VerticalIconRender();
                        personInfor.listNearestPerson.setCellRenderer(render);
                        addTab(str, str, personInfor, GLPReg.tabFaceNearist);
                        personInfor.scrollPanel.setViewportView(personInfor.listNearestPerson);
                        personInfor.listNearestPerson.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseClicked(MouseEvent ev) {
                                if (ev.getClickCount() == 2) {
                                    try {
                                        int a = personInfor.listNearestPerson.getSelectedIndex();
                                        System.out.println("index" + a);
                                        String tmp = GLReg.listNearestPerson.get(a).toString();
                                        //IconList icon = (IconList) personInfor.listNearestPerson.getSelectedValue();
                                        //System.out.println("icon .. " + icon.getName());
                                        System.out.println("Double me!" + tmp);
                                        String identity = tmp.substring(0, 9);
                                        System.out.println("Indentity number: " + identity);
                                        //System.out.println(new PersonDAL().Select(pid).getFULLNAME());
                                        personInfor.txtFullName.setText(new PersonDAL().Select(identity).getFullname());
                                        personInfor.txtIdentity.setText(new PersonDAL().Select(identity).getIdentity_number());
                                        personInfor.txtBirthday.setText(new PersonDAL().Select(identity).getDob().toString());
                                        personInfor.txtHomeTown.setText(new PersonDAL().Select(identity).getHometown());
                                        personInfor.txtResidence.setText(new PersonDAL().Select(identity).getPermanent_residence());
                                        personInfor.txtEthnic.setText(new PersonDAL().Select(identity).getEthnic());
                                        personInfor.txtReligious.setText(new PersonDAL().Select(identity).getReligion());
                                        personInfor.bufferedImage = ImageIO.read(new PersonDAL().Select(identity).getImage().getBinaryStream());
                                        //resize a image
                                        int width = personInfor.bufferedImage.getWidth();
                                        int height = personInfor.bufferedImage.getHeight();
                                        boolean resize = false;
                                        if (width > 300)
                                        {
                                            width = 300;
                                            resize = true;
                                        }
                                        if (height > 200){
                                            resize = true;
                                            height = 200;
                                        }
                                        if (resize){
                                            ProcessImage proImg = new ProcessImage();
                                            personInfor.bufferedImage = proImg.resize(
                                                    personInfor.bufferedImage, //image
                                                    width, //width
                                                    height); //height
                                                  
                                        }
                                        personInfor.lblImage.setIcon(new ImageIcon( personInfor.bufferedImage));
                                        
                                        personInfor.pnlShowInfor.setVisible(true);                                
                                    } catch (IOException ex) {
                                        Logger.getLogger(RecognitionListener.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(RecognitionListener.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            }
                        });




                    }
                });


        rightMenu.add(enableRegion);
        /*
         * Disable Region menu
         */
        JMenuItem disableRegion = new JMenuItem("Recognition all of faces");
        //disableRegion.setIcon(getIconResize("../CIS_SProjectR2/src/icon/Delete_16x16.png"));

        disableRegion.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("Recognition all of faces");

                    }
                });
        rightMenu.add(disableRegion);

        /*
         * Detect This Face menu
         */
        JMenuItem detectFace = new JMenuItem("Delete this image");
        //disableRegion.setIcon(getIconResize("../CIS_SProjectR2/src/icon/Delete_16x16.png"));

        detectFace.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Delete this image");
                        int index = GLPReg.lstFace.getSelectedIndex();
                        if (index >= 0) {
                            GLPReg.objSmall.remove(index);
                            GLPReg.lstFace.repaint();
                            GLPReg.lstFace.revalidate();
                            GLPReg.updateListFace();
                        }

                    }
                });
        rightMenu.add(detectFace);

        /*
         * Detect All Faces menu
         */
        JMenuItem detectAllFaces = new JMenuItem("Delete All of faces");
        //disableRegion.setIcon(getIconResize("../CIS_SProjectR2/src/icon/Delete_16x16.png"));

        detectAllFaces.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int count = GLPReg.objSmall.size();
                        while (count > 0) {
                            GLPReg.objSmall.remove(0);
                            GLPReg.lstFace.repaint();
                            GLPReg.lstFace.revalidate();
                            count = GLPReg.objSmall.size();
                            GLPReg.updateListFace();
                        }
                        System.out.println("Delete all of faces");
                    }
                });
        rightMenu.add(detectAllFaces);
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
//                    PFaceDetection objTmp = PFaceDetection.getInstance();
//                    if (objTmp.objImglist.getLength() > 0) {
//                        int index = 0;
//
//                        ProcessImage objImg = new ProcessImage();
//                        index = objTmp.lstThumbnails.getSelectedIndex();
//                        BufferedImage tmpBuffer = objImg.scaleImg(
//                                objTmp.objImglist.getImagesBuffered(index),
//                                objTmp.pnlShowImage.getWidth(),
//                                objTmp.pnlShowImage.getHeight());
//
//                        /*
//                         * check region on a image
//                         */
//                        if (PDrawPanel.getInstance().position[index] == null) {
//                            /*
//                             * if not exist
//                             */
//                            PDrawPanel.getInstance().position[index] = new Region();
//                        }
//                        /*
//                         * if exist
//                         */
//                        PDrawPanel.getInstance().index = index;
//                        PDrawPanel.getInstance().image = tmpBuffer;
//                        PDrawPanel.getInstance().fileName = objTmp.objImglist.getFileNames(index);
//
//                        objTmp.dPanel = PDrawPanel.getInstance();
//                        objTmp.pnlShowImage.setLayout(new BoxLayout(objTmp.pnlShowImage, BoxLayout.PAGE_AXIS));
//
//                        //objTmp.pnlShowImage.removeAll(); //delete previous image
//                        objTmp.pnlShowImage.repaint(); //repaint a image
//                        objTmp.pnlShowImage.revalidate(); //clear
//                        objTmp.pnlShowImage.add(objTmp.dPanel);
//                    }
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