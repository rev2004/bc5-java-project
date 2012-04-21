/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Draw.java
 *
 * Created on Mar 25, 2012, 12:21:16 AM
 */
package com.bc5Neptune.cis.gui;

import com.bc5Neptune.cis.bll.IconList;
import static com.bc5Neptune.cis.bll.GlobalObject.*;
import com.bc5Neptune.cis.bll.HorizontalIconRender;
import com.bc5Neptune.cis.bll.ProcessImage;
import com.bc5Neptune.cis.bll.VerticalIconRender;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author HuynhTanPhu
 */
class FaceRegion {

    int pointCount = 0;
    Point points[] = new Point[100];
    Point points2[] = new Point[100];
    Point start = new Point();
    Point end = new Point();
    Rectangle rect = new Rectangle();
    int index = 0;
}

public class PFaceCustom extends javax.swing.JPanel {
    /*
     * the number of face to render in listbox
     */

    //get size of the images was imported
    BufferedImage image;
    public ArrayList<BufferedImage> faceListArr = new ArrayList();
    String fileName;
    public FaceRegion[] positionArr;
    int index = 0;
    public ArrayList<IconList> iconListArr = new ArrayList<IconList>();
    public int countFaces = 0;
    /*
     * condition to put a image into array
     */
    boolean put = false;

    public BufferedImage[] getFaceImages() {
        BufferedImage[] faces = new BufferedImage[faceListArr.size()];
        for (int i = 0; i < faceListArr.size(); i++) {
            faces[i] = faceListArr.get(i);
        }
        return faces;
    }

    public int getLength() {
        return faceListArr.size();
    }

    public BufferedImage getFaceImages(int index) {
        return faceListArr.get(index);
    }

    public PFaceCustom() {
        //end event of drawpanel and begin event of listfaces click
        initComponents();
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent ev) {
                positionArr[index].end = ev.getPoint();
                positionArr[index].rect.setFrameFromDiagonal(positionArr[index].start,
                        positionArr[index].end);
                put = true;
                repaint();

            }//end mouse drag
        });
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                positionArr[index].start = e.getPoint();
            }

            public void mouseReleased(MouseEvent ev) {
                countFaces++;
                positionArr[index].points[positionArr[index].pointCount] = positionArr[index].start;

                positionArr[index].points2[positionArr[index].pointCount] = ev.getPoint();
                positionArr[index].pointCount++;
                
                positionArr[index].rect.setFrameFromDiagonal(positionArr[index].start,
                        positionArr[index].start);
                updateListFace();


            }

            @Override
            public void mouseExited(MouseEvent e) {
                /*
                 * when mouse exit then update face
                 */
                updateListFace();
            }
        });


    }

    public void updateListFace() {
        /*
         * the face images in here
         */
        int length = getLength(); //length of array images
        //objSmallImg = new SmallImage[length];
        /*
         * inilization a object of icon class show it in a listbox
         */
        for (int i = iconListArr.size(); i < length; i++) {
            iconListArr.add(new IconList("unknow", //name of a image
                    getFaceImages(i),//buffer of a image
                    92, //width
                    112)); //height
        }

        System.out.println("..............." + length);
        /*
         * set value of render
         */
        GLPDetect.lstFaceImages = new JList(iconListArr.toArray());
        HorizontalIconRender renderer = new HorizontalIconRender();
        //set cell heighy
        GLPDetect.lstFaceImages.setFixedCellHeight(92);
        //set sell width
        GLPDetect.lstFaceImages.setFixedCellWidth(200);

        GLPDetect.lstFaceImages.setCellRenderer(renderer);

        GLPDetect.scrollFaceImages.setViewportView(GLPDetect.lstFaceImages);

        /*
         * event click of lstFaceImages
         */
        GLPDetect.lstFaceImages.addMouseListener(new FaceListListener());
        GLPDetect.scrollFaceImages.getHorizontalScrollBar().setValue(100);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //repaint();
        if (image != null && positionArr[index] != null) {
            g.drawImage(image, 0, 0, this);
            // super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            // Draw line being dragged.
            g2.setPaint(Color.red);
            g2.draw(positionArr[index].rect);
            // Draw lines between points in arrays.
            g2.setPaint(Color.blue);
            Rectangle r = new Rectangle();
            
            for (int i = 0; i < positionArr[index].pointCount; i++) {
                r.setFrameFromDiagonal(positionArr[index].points[i], positionArr[index].points2[i]);
                // g2.fill(r);
                System.out.println("Draw me");
                /*
                 * if toggle in PFaceDetection = true. it will show region
                 */
                if (GLPDetect.toggleRegion) {
                    g2.drawRect(r.x, r.y, r.width, r.height);
                }
                /*
                 * put a image into a array
                 */
                g2.create();
                BufferedImage img = null;
                if (getLength() < countFaces && put == true && i == positionArr[index].pointCount - 1) {

                    img = image.getSubimage(r.x, r.y, r.width, r.height);
//                    /* resize image 92 X 112 */
                    ProcessImage objImg = new ProcessImage();
                    BufferedImage objBuffered = objImg.resize(img, 92, 112);

                    System.out.println("Put a image");
                    faceListArr.add(objBuffered);
                    // File file = new File("c://2.jpg");
                    // FileOutputStream out = new FileOutputStream(file);
                    g.drawImage(img, 0, 0, this);
                    //Set border for rectangel */
                    g2.drawRect(r.x + 1, r.y + 1, r.width, r.height);
                    JLabel lable = new JLabel();
                    ImageIcon icon = new ImageIcon(img);
                    lable.setIcon(icon);
                    this.add(lable);
                    put = false;
                }


            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 102, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

class FaceListListener extends MouseAdapter {

    JPopupMenu rightMenu = new JPopupMenu("Right menu");
    JPopupMenu popupInfo = new JPopupMenu();

    public FaceListListener() {

        //set size for popup panel

        // popupInfo.setPreferredSize(new Dimension(PEditRegion.WIDTH, PEditRegion.HEIGHT));
        popupInfo.setLayout(new BoxLayout(popupInfo, BoxLayout.PAGE_AXIS));


        /*
         * information menu
         */
        JMenuItem addMenu = new JMenuItem("Add Information");
        //addMenu.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
        addMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Information");
//                PEditRegion objEdit = new PEditRegion();
//                int index =GLPDetect.lstFaceImages.getSelectedIndex();
//
//                BufferedImage[] objBuffered = PDrawPanel.getInstance().getFaceImages();
//                ImageIcon icon = new ImageIcon(objBuffered[index]);
//
//                objEdit.lblFace.setIcon(icon);
//                popupInfo.removeAll();
//                popupInfo.add(objEdit);
//                popupInfo.show(e.(), e.getX(), e.getY());

            }
        });
        rightMenu.add(addMenu);
        /*
         * delete menu
         */
        JMenuItem deleteMenu = new JMenuItem("Delete face");
        deleteMenu.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
        deleteMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = GLPDetect.lstFaceImages.getSelectedIndex();

                if (index >= 0) {
                    GLPDetect.lstFaceImages.remove(index);
                    GLPCustom.faceListArr.remove(index);
                    GLPCustom.iconListArr.remove(index);
                    /*
                     * after delete a face then update list face
                     */
                    GLPCustom.updateListFace();
                    /*
                     * reduce counting of face
                     */
                    GLPCustom.countFaces--;
                    //PDrawPanel.getInstance().position[index].pointCount = 0;
                    System.out.println("delete face");
                }
            }
        });
        rightMenu.add(deleteMenu);

        /*
         * delete all of face menu
         */
        JMenuItem deleteAllMenu = new JMenuItem("Delete All of faces");
        deleteAllMenu.setIcon(getIcon("../CIS_SProjectR2/src/icon/Delete_16x16.png"));
        deleteAllMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = 0;
                int count = GLPCustom.getLength();
                while (count > 0) {
                    GLPCustom.faceListArr.remove(0);
                    count = GLPCustom.getLength();
                    GLPCustom.iconListArr.remove(0);
                }
                /*
                 * after delete a face then update list face
                 */
                GLPCustom.updateListFace();
                /*
                 * reduce counting of face
                 */
                GLPCustom.countFaces = 0;
                //PDrawPanel.getInstance().position[index].pointCount = 0;
                System.out.println("Delete All of faces");

            }
        });
        rightMenu.add(deleteAllMenu);

        /*
         * recognition menu
         */
        JMenuItem recogMenu = new JMenuItem("Add this face to recognition");
        recogMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * update list face
                 */
//                int index =GLPDetect
//                        .lstFaceImages.getSelectedIndex();


//                objReg.listFace.add(PDrawPanel
//                        .getInstance().faceImages.get(index));
                IconList objSmallImage = (IconList) GLPDetect.lstFaceImages.getSelectedValue();

                GLPReg.objSmall.add(objSmallImage);

                /*
                 * update list face
                 */
                GLPReg.updateListFace();
                /*
                 * open new recognition tab
                 */
                System.out.println("recognition face");
                GApplication.instance.addTab("Face Recognition",
                        "Open Face Recognition",
                        GLPReg,
                        GApplication.instance.mainTab);


            }
        });
        rightMenu.add(recogMenu);

        /*
         * recognition menu
         */
        JMenuItem addTraning = new JMenuItem("Add this traning face");
        addTraning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /* click add to traning face */
                int index = GLPDetect.lstFaceImages.getSelectedIndex();
                IconList icon = (IconList) GLPDetect.lstFaceImages.getSelectedValue();
                // you must type the id number for face image

                //if (!"unknow".equals(icon.getName())) {
                GLPDetect.iconTrainArr.add(icon);
                //after add to traning face then remove face at index
                GLPCustom.iconListArr.remove(index);
                GLPCustom.faceListArr.remove(index);
                GLPCustom.countFaces--;
                GLPCustom.updateListFace();

                // setcell render for traning list face
                GLPDetect.lstTrainFace = new JList(GLPDetect.iconTrainArr.toArray());
                VerticalIconRender render = new VerticalIconRender();
                GLPDetect.lstTrainFace.setCellRenderer(render);
                GLPDetect.scrollTrainFace.setViewportView(GLPDetect.lstTrainFace);

                //update list of combobox
                int exist = 0;//the item still exist
                String cmbNewName = icon.getName();
                for (int i = 0; i < GLPDetect.cmbTrainFace.getItemCount(); i++) {
                    String oldName = (String) GLPDetect.cmbTrainFace.getItemAt(i);
                    if (cmbNewName.equalsIgnoreCase(oldName)) {
                        exist++;
                    }
                }
                if (exist == 0) { //not exist then add it to combobox
                    GLPDetect.cmbTrainFace.addItem(cmbNewName);
                    //  }

                    //}else{
                    //icon.setName("");
                    System.out.println("you must change a name to add to traning face");
                }
            }
        });
        rightMenu.add(addTraning);

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
                    PEditRegion objEdit = new PEditRegion();
                    int index = GLPDetect.lstFaceImages.getSelectedIndex();

                    BufferedImage[] objBuffered = GLPCustom.getFaceImages();
                    ImageIcon icon = new ImageIcon(objBuffered[index]);

                    objEdit.lblFace.setIcon(icon);
                    IconList objSmall = (IconList) GLPDetect.lstFaceImages.getSelectedValue();
                    objEdit.tblEditRegion.setValueAt(objSmall.getName(), 0, 0);
                    /*
                     * set position to show window
                     */

                    popupInfo.removeAll();
                    popupInfo.add(objEdit);
                    popupInfo.show(e.getComponent(), e.getX(), e.getY());


                    //objSmall.setFileImage("Double click at me");


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
