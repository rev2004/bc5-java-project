/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GApplication.java
 *
 * Created on Mar 23, 2012, 9:22:52 AM
 */
package com.bc5Neptune.cis.gui;

import java.awt.BorderLayout;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import static com.bc5Neptune.cis.bll.GlobalObject.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author phu.huynh
 */
public class GApplication extends JFrame {

    public static GApplication instance;

    /**
     * Creates new form GApplication
     */
    public GApplication() {


        if (instance == null) {
            initComponents();
            //make permision for user login
            makePermision();
            instance = this;

            System.out.println("Initlization application was successfully");
        } else {
            System.out.println("Error: Initlization application");
        }
        // align cetern for this form
        Container c = new Container();
        setLocationRelativeTo(c);
        //show user who is login
        btnLogout.setText(GLogin.userNameAccess + "  ");
    }

    //if user is admin: the permision is all of function 
    //if user is employee: the permision is...
    //admin: role = 1, employee: role = 1, 
    public void makePermision() {
        int ch = GLogin.role;
        switch (ch) {
            case 1: {
                //all of function
                //do nothing in here
                break;
            }
            case 2: {
                //employee
                ruleEmployee(false);
                break;
            }


        }
    }

    public void ruleEmployee(boolean status) {

        //for menu 
        menuAdministrator.setEnabled(status);
        menuServer.setEnabled(status);
        menuDeletePerson.setEnabled(status);
        menuAddNewPerson.setEnabled(status);
        menuEditPersonInformation.setEnabled(status);
        menuSearch.setEnabled(status);
        //for tool
        toolAddNewPerson.setEnabled(status);
        toolDeletePerson.setEnabled(status);
        toolEditPerson.setEnabled(status);
        toolSearch.setEnabled(status);
        toolServer.setEnabled(status);


    }

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
                for (int i = 1; i < tabPane.getTabCount(); i++) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        toolAddNewPerson = new javax.swing.JButton();
        toolEditPerson = new javax.swing.JButton();
        toolDeletePerson = new javax.swing.JButton();
        toolFaceDetection = new javax.swing.JButton();
        toolFaceRecog = new javax.swing.JButton();
        toolSearch = new javax.swing.JButton();
        toolChangePassword = new javax.swing.JButton();
        toolServer = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        mainTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblTutorial = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblOverview = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblAbout = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        viewFaceDetection = new javax.swing.JMenuItem();
        viewFaceRecognition = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuSearch = new javax.swing.JMenuItem();
        menuAddNewPerson = new javax.swing.JMenuItem();
        menuAdministrator = new javax.swing.JMenuItem();
        menuEditPersonInformation = new javax.swing.JMenuItem();
        menuDeletePerson = new javax.swing.JMenuItem();
        menuChangePassword = new javax.swing.JMenuItem();
        menuServer = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuOverview = new javax.swing.JMenuItem();
        menuTutorials = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.gray);

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToolBar1.setPreferredSize(new java.awt.Dimension(124, 100));

        toolAddNewPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Add/Add_24x24.png"))); // NOI18N
        toolAddNewPerson.setFocusable(false);
        toolAddNewPerson.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolAddNewPerson.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolAddNewPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolAddNewPersonActionPerformed(evt);
            }
        });
        jToolBar1.add(toolAddNewPerson);

        toolEditPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Edit/Edit_24x24.png"))); // NOI18N
        toolEditPerson.setFocusable(false);
        toolEditPerson.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolEditPerson.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolEditPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolEditPersonActionPerformed(evt);
            }
        });
        jToolBar1.add(toolEditPerson);

        toolDeletePerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Delete/Delete_24x24.png"))); // NOI18N
        toolDeletePerson.setFocusable(false);
        toolDeletePerson.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolDeletePerson.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolDeletePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolDeletePersonActionPerformed(evt);
            }
        });
        jToolBar1.add(toolDeletePerson);

        toolFaceDetection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detect face/detect face 32 x 32.png"))); // NOI18N
        toolFaceDetection.setFocusable(false);
        toolFaceDetection.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolFaceDetection.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolFaceDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolFaceDetectionActionPerformed(evt);
            }
        });
        jToolBar1.add(toolFaceDetection);

        toolFaceRecog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/face recognition/recognize 24 x 24.png"))); // NOI18N
        toolFaceRecog.setFocusable(false);
        toolFaceRecog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolFaceRecog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolFaceRecog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolFaceRecogActionPerformed(evt);
            }
        });
        jToolBar1.add(toolFaceRecog);

        toolSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Search/Search_32x32.png"))); // NOI18N
        toolSearch.setFocusable(false);
        toolSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolSearch.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolSearchActionPerformed(evt);
            }
        });
        jToolBar1.add(toolSearch);

        toolChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chage pass/change pass 32 x 32.png"))); // NOI18N
        toolChangePassword.setFocusable(false);
        toolChangePassword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolChangePassword.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolChangePasswordActionPerformed(evt);
            }
        });
        jToolBar1.add(toolChangePassword);

        toolServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/server/server 32 x 32.png"))); // NOI18N
        toolServer.setFocusable(false);
        toolServer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolServer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolServerActionPerformed(evt);
            }
        });
        jToolBar1.add(toolServer);
        jToolBar1.add(filler1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font("Cantarell", 2, 15));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Hello: ");
        jLabel3.setFocusable(false);
        jPanel2.add(jLabel3);

        btnLogout.setForeground(java.awt.Color.red);
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Log Out/Log Out_24x24.png"))); // NOI18N
        btnLogout.setText("Plato   ");
        btnLogout.setFocusable(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout);

        jToolBar1.add(jPanel2);

        jPanel1.setBackground(java.awt.Color.gray);
        jPanel1.setForeground(java.awt.Color.white);

        lblTutorial.setFont(new java.awt.Font("Cantarell", 1, 18));
        lblTutorial.setForeground(java.awt.Color.white);
        lblTutorial.setText("Tutorials");
        lblTutorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTutorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTutorialMouseClicked(evt);
            }
        });

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Go through turotials");

        lblOverview.setFont(new java.awt.Font("Cantarell", 1, 18));
        lblOverview.setForeground(java.awt.Color.white);
        lblOverview.setText("Overview");
        lblOverview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOverview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOverviewMouseClicked(evt);
            }
        });

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Get an overview of the features");

        lblAbout.setFont(new java.awt.Font("Cantarell", 1, 18));
        lblAbout.setForeground(java.awt.Color.white);
        lblAbout.setText("About Face Recognition");
        lblAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAboutMouseClicked(evt);
            }
        });

        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Information about this software");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutorial/tutorial.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/overview/overview.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/about/about.png"))); // NOI18N

        lblHelp.setFont(new java.awt.Font("Cantarell", 1, 18));
        lblHelp.setForeground(java.awt.Color.white);
        lblHelp.setText("Help");
        lblHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHelpMouseClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/help/help 32 x 32.png"))); // NOI18N

        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("Using the help system");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(lblHelp)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblAbout)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOverview)
                            .addComponent(jLabel2)
                            .addComponent(lblTutorial, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(604, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverview)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTutorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel7))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHelp)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAbout)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        mainTab.addTab("Home Page", jPanel1);

        jMenu1.setText("File");

        menuLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Log Out_16x16.png"))); // NOI18N
        menuLogout.setText("Logout");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(menuLogout);

        menuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cancel_16x16.png"))); // NOI18N
        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        viewFaceDetection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detect face/detect face 16 x 16.png"))); // NOI18N
        viewFaceDetection.setText("Face Detection");
        viewFaceDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFaceDetectionActionPerformed(evt);
            }
        });
        jMenu2.add(viewFaceDetection);

        viewFaceRecognition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/face recognition/recognize 16 x 16.png"))); // NOI18N
        viewFaceRecognition.setText("Face Recognition");
        viewFaceRecognition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFaceRecognitionActionPerformed(evt);
            }
        });
        jMenu2.add(viewFaceRecognition);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tools");

        menuSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search_16x16.png"))); // NOI18N
        menuSearch.setText("Search Box");
        menuSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSearchActionPerformed(evt);
            }
        });
        jMenu3.add(menuSearch);

        menuAddNewPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add_16x16.png"))); // NOI18N
        menuAddNewPerson.setText("Add New Person");
        menuAddNewPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddNewPersonActionPerformed(evt);
            }
        });
        jMenu3.add(menuAddNewPerson);

        menuAdministrator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/admin/administrator 16 x 16.png"))); // NOI18N
        menuAdministrator.setText("Administrator");
        menuAdministrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAdministratorActionPerformed(evt);
            }
        });
        jMenu3.add(menuAdministrator);

        menuEditPersonInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit_16x16.png"))); // NOI18N
        menuEditPersonInformation.setText("Edit Person Information");
        menuEditPersonInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditPersonInformationActionPerformed(evt);
            }
        });
        jMenu3.add(menuEditPersonInformation);

        menuDeletePerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete_16x16.png"))); // NOI18N
        menuDeletePerson.setText("Delete Person");
        menuDeletePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeletePersonActionPerformed(evt);
            }
        });
        jMenu3.add(menuDeletePerson);

        menuChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chage pass/change pass 16 x 16.png"))); // NOI18N
        menuChangePassword.setText("Change Password");
        menuChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangePasswordActionPerformed(evt);
            }
        });
        jMenu3.add(menuChangePassword);

        menuServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/server/server 16 x 16.png"))); // NOI18N
        menuServer.setText("Server Management");
        menuServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuServerActionPerformed(evt);
            }
        });
        jMenu3.add(menuServer);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Help");

        menuOverview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/overview/overview 16 x 16.png"))); // NOI18N
        menuOverview.setText("Overview");
        menuOverview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOverviewActionPerformed(evt);
            }
        });
        jMenu6.add(menuOverview);

        menuTutorials.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutorial/tutorial 16 x 16.png"))); // NOI18N
        menuTutorials.setText("Tutorials");
        menuTutorials.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTutorialsMouseClicked(evt);
            }
        });
        menuTutorials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTutorialsActionPerformed(evt);
            }
        });
        jMenu6.add(menuTutorials);

        menuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/help/help 16 x 16.png"))); // NOI18N
        menuHelp.setText("Help Contents");
        menuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHelpActionPerformed(evt);
            }
        });
        jMenu6.add(menuHelp);

        menuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/about/about 16 x 16.png"))); // NOI18N
        menuAbout.setText("About");
        menuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAboutActionPerformed(evt);
            }
        });
        jMenu6.add(menuAbout);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addComponent(mainTab, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTab, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        private void viewFaceDetectionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFaceDetectionActionPerformed
            // TODO add your handling code here:
            addTab("Face Detection", "Open Face Detection", GLPDetect, mainTab);
    }//GEN-LAST:event_viewFaceDetectionActionPerformed

    private void viewFaceRecognitionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFaceRecognitionActionPerformed
        // TODO add your handling code here:
        addTab("Face Recognition", "Open Face Recognition", GLPReg, mainTab);
    }//GEN-LAST:event_viewFaceRecognitionActionPerformed

    private void menuAddNewPersonActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddNewPersonActionPerformed
        // TODO add your handling code here:
        addTab("Add New Person", "Open Add New Person", new PNewPerson(), mainTab);
    }//GEN-LAST:event_menuAddNewPersonActionPerformed

    private void menuAdministratorActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAdministratorActionPerformed
        // TODO add your handling code here:
        addTab("Addministrator", "Open Addministrator", new PAdministrator(), mainTab);
    }//GEN-LAST:event_menuAdministratorActionPerformed

    private void menuEditPersonInformationActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditPersonInformationActionPerformed
        // TODO add your handling code here:
        addTab("Edit Person Information", "Open Edit Person Information", new PEditPersonInformation(), mainTab);
    }//GEN-LAST:event_menuEditPersonInformationActionPerformed

    private void menuDeletePersonActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeletePersonActionPerformed
        // TODO add your handling code here:
        addTab("Delete Person", "Open Delete Person", new PDeletePerson(), mainTab);
    }//GEN-LAST:event_menuDeletePersonActionPerformed

    private void menuSearchActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSearchActionPerformed
        // TODO add your handling code here:
        addTab("Information Searching", "Open Information Searching", new PSearch(), mainTab);
    }//GEN-LAST:event_menuSearchActionPerformed

    private void menuChangePasswordActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangePasswordActionPerformed
        // TODO add your handling code here:
        //  addTab("Change Password", "Open Change Password", new PChangePassword(), mainTab);
        GChangePassword objPass = new GChangePassword();
        objPass.setPreferredSize(new Dimension(597, 295));
        objPass.setVisible(true);
    }//GEN-LAST:event_menuChangePasswordActionPerformed

    private void menuHelpActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuHelpActionPerformed
        // TODO add your handling code here:
        addTab("Help Contents", "Open Help Contents", new PHelp(), mainTab);
    }//GEN-LAST:event_menuHelpActionPerformed

    private void menuAboutActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAboutActionPerformed
        // TODO add your handling code here:
        addTab("About", "Open About", new PAbout(), mainTab);
    }//GEN-LAST:event_menuAboutActionPerformed

    private void menuExitActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuLogoutActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(menuLogout, "Do you want to logout?", "Question",
                JOptionPane.YES_NO_OPTION);
        if (check == 0) {
            this.setVisible(false);
            GApplication.instance = null;
            new GLogin().setVisible(true);
        }

    }//GEN-LAST:event_menuLogoutActionPerformed

    private void menuServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuServerActionPerformed
        // TODO add your handling code here:
        addTab("Server", "Open Server", new PServer(), mainTab);
    }//GEN-LAST:event_menuServerActionPerformed

    private void toolAddNewPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolAddNewPersonActionPerformed
        // TODO add your handling code here:
        addTab("Add New Person", "Open Add New Person", new PNewPerson(), mainTab);
    }//GEN-LAST:event_toolAddNewPersonActionPerformed

    private void toolEditPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolEditPersonActionPerformed
        // TODO add your handling code here:
        addTab("Edit Person Information", "Open Edit Person Information", new PEditPersonInformation(), mainTab);
    }//GEN-LAST:event_toolEditPersonActionPerformed

    private void toolDeletePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolDeletePersonActionPerformed
        // TODO add your handling code here:
        addTab("Delete Person", "Open Delete Person", new PDeletePerson(), mainTab);
    }//GEN-LAST:event_toolDeletePersonActionPerformed

    private void toolFaceDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolFaceDetectionActionPerformed
        // TODO add your handling code here:
        addTab("Face Detection", "Open Face Detection", GLPDetect, mainTab);
    }//GEN-LAST:event_toolFaceDetectionActionPerformed

    private void toolFaceRecogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolFaceRecogActionPerformed
        // TODO add your handling code here:
        addTab("Face Recognition", "Open Face Recognition", GLPReg, mainTab);
    }//GEN-LAST:event_toolFaceRecogActionPerformed

    private void lblTutorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTutorialMouseClicked
        // TODO add your handling code here:
        addTab("Tutorials", "Open Tutorials", new PTutorials(), mainTab);
    }//GEN-LAST:event_lblTutorialMouseClicked

    private void lblAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAboutMouseClicked
        // TODO add your handling code here:
        addTab("About", "Open About", new PAbout(), mainTab);
    }//GEN-LAST:event_lblAboutMouseClicked

    private void lblOverviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOverviewMouseClicked
        // TODO add your handling code here:
        addTab("Overview", "Open Overview", new POverview(), mainTab);
    }//GEN-LAST:event_lblOverviewMouseClicked

    private void menuOverviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOverviewActionPerformed
        // TODO add your handling code here:
        addTab("Overview", "Open Overview", new POverview(), mainTab);
    }//GEN-LAST:event_menuOverviewActionPerformed

    private void toolServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolServerActionPerformed
        // TODO add your handling code here:
        addTab("Server", "Open Server", new PServer(), mainTab);
    }//GEN-LAST:event_toolServerActionPerformed

    private void toolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolSearchActionPerformed
        // TODO add your handling code here:
        addTab("Information Searching", "Open Information Searching", new PSearch(), mainTab);
    }//GEN-LAST:event_toolSearchActionPerformed

    private void toolChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolChangePasswordActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        //  addTab("Change Password", "Open Change Password", new PChangePassword(), mainTab);
        GChangePassword objPass = new GChangePassword();
        objPass.setPreferredSize(new Dimension(597, 295));
        objPass.setVisible(true);

    }//GEN-LAST:event_toolChangePasswordActionPerformed

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        // TODO add your handling code here:
        addTab("Help Contents", "Open Help Contents", new PHelp(), mainTab);
    }//GEN-LAST:event_lblHelpMouseClicked

    private void menuTutorialsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTutorialsMouseClicked
        // TODO add your handling code here:
        addTab("Tutorials", "Open Tutorials", new PTutorials(), mainTab);
    }//GEN-LAST:event_menuTutorialsMouseClicked

    private void menuTutorialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTutorialsActionPerformed
        // TODO add your handling code here:
        addTab("Tutorials", "Open Tutorials", new PTutorials(), mainTab);
    }//GEN-LAST:event_menuTutorialsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(menuLogout, "Do you want to logout?", "Question",
                JOptionPane.YES_NO_OPTION);
        if (check == 0) {
            this.setVisible(false);
            GApplication.instance = null;
            new GLogin().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        try {
            UIManager.setLookAndFeel(new com.nilo.plaf.nimrod.NimRODLookAndFeel());
            final NimRODTheme nt = new NimRODTheme("../CIS_SProjectR2/lib/Snow.theme");
            final NimRODLookAndFeel nf = new NimRODLookAndFeel();
            NimRODLookAndFeel.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nf);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
//        /* Create and display the form */
        JFrame.setDefaultLookAndFeelDecorated(true);
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new GApplication().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAbout;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblOverview;
    private javax.swing.JLabel lblTutorial;
    public static javax.swing.JTabbedPane mainTab;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuAddNewPerson;
    private javax.swing.JMenuItem menuAdministrator;
    private javax.swing.JMenuItem menuChangePassword;
    private javax.swing.JMenuItem menuDeletePerson;
    private javax.swing.JMenuItem menuEditPersonInformation;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuHelp;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenuItem menuOverview;
    private javax.swing.JMenuItem menuSearch;
    private javax.swing.JMenuItem menuServer;
    private javax.swing.JMenuItem menuTutorials;
    private javax.swing.JButton toolAddNewPerson;
    private javax.swing.JButton toolChangePassword;
    private javax.swing.JButton toolDeletePerson;
    private javax.swing.JButton toolEditPerson;
    private javax.swing.JButton toolFaceDetection;
    private javax.swing.JButton toolFaceRecog;
    private javax.swing.JButton toolSearch;
    private javax.swing.JButton toolServer;
    private javax.swing.JMenuItem viewFaceDetection;
    private javax.swing.JMenuItem viewFaceRecognition;
    // End of variables declaration//GEN-END:variables
}
