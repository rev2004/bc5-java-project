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
            instance = this;
            System.out.println("Initlization application was successfully");
        } else {
            System.out.println("Error: Initlization application");
        }
        // align cetern for this form
        Container c = new Container();
        setLocationRelativeTo(c);
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
        jToolBar2 = new javax.swing.JToolBar();
        toolStartServer = new javax.swing.JButton();
        toolStopServer = new javax.swing.JButton();
        mainTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpenFile = new javax.swing.JMenuItem();
        menuOpenFolder = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        viewFaceDetection = new javax.swing.JMenuItem();
        viewFaceRecognition = new javax.swing.JMenuItem();
        menuSearch = new javax.swing.JMenuItem();
        menuLogin = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuAddNewPerson = new javax.swing.JMenuItem();
        menuAdministrator = new javax.swing.JMenuItem();
        menuEditPersonInformation = new javax.swing.JMenuItem();
        menuExportToDatabase = new javax.swing.JMenuItem();
        menuDeletePerson = new javax.swing.JMenuItem();
        menuChangePassword = new javax.swing.JMenuItem();
        menuServer = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
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

        jToolBar2.setRollover(true);

        toolStartServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Play/Play_24x24.png"))); // NOI18N
        toolStartServer.setFocusable(false);
        toolStartServer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolStartServer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolStartServerActionPerformed(evt);
            }
        });
        jToolBar2.add(toolStartServer);

        toolStopServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/must_have_icon_set/Stop/Stop_24x24.png"))); // NOI18N
        toolStopServer.setFocusable(false);
        toolStopServer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolStopServer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolStopServerActionPerformed(evt);
            }
        });
        jToolBar2.add(toolStopServer);

        jToolBar1.add(jToolBar2);

        jPanel1.setBackground(java.awt.Color.gray);
        jPanel1.setForeground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Tutorials");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Go through turotials");

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Overview");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Get an overview of the features");

        jLabel5.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("About Face Recognition");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Information about this software");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tutorial/tutorial.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/overview/overview.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/about/about.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(136, 594, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        mainTab.addTab("Home Page", jPanel1);

        jMenu1.setText("File");

        menuOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Picture_16x16.png"))); // NOI18N
        menuOpenFile.setText("Open File");
        menuOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenFileActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpenFile);

        menuOpenFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Open_16x16.png"))); // NOI18N
        menuOpenFolder.setText("Open Folder");
        menuOpenFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenFolderActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpenFolder);

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

        viewFaceDetection.setText("Face Detection");
        viewFaceDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFaceDetectionActionPerformed(evt);
            }
        });
        jMenu2.add(viewFaceDetection);

        viewFaceRecognition.setText("Face Recognition");
        viewFaceRecognition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFaceRecognitionActionPerformed(evt);
            }
        });
        jMenu2.add(viewFaceRecognition);

        menuSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search_16x16.png"))); // NOI18N
        menuSearch.setText("Search Box");
        menuSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSearchActionPerformed(evt);
            }
        });
        jMenu2.add(menuSearch);

        menuLogin.setText("Login");
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        jMenu2.add(menuLogin);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tools");

        menuAddNewPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add_16x16.png"))); // NOI18N
        menuAddNewPerson.setText("Add New Person");
        menuAddNewPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddNewPersonActionPerformed(evt);
            }
        });
        jMenu3.add(menuAddNewPerson);

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

        menuExportToDatabase.setText("Export to Database");
        menuExportToDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExportToDatabaseActionPerformed(evt);
            }
        });
        jMenu3.add(menuExportToDatabase);

        menuDeletePerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete_16x16.png"))); // NOI18N
        menuDeletePerson.setText("Delete Person");
        menuDeletePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeletePersonActionPerformed(evt);
            }
        });
        jMenu3.add(menuDeletePerson);

        menuChangePassword.setText("Change Password");
        menuChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangePasswordActionPerformed(evt);
            }
        });
        jMenu3.add(menuChangePassword);

        menuServer.setText("Server Management");
        menuServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuServerActionPerformed(evt);
            }
        });
        jMenu3.add(menuServer);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Help");

        menuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Help_16x16.png"))); // NOI18N
        menuHelp.setText("Help Contents");
        menuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHelpActionPerformed(evt);
            }
        });
        jMenu6.add(menuHelp);

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
            .addComponent(mainTab)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainTab, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void menuExportToDatabaseActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExportToDatabaseActionPerformed
        // TODO add your handling code here:
        addTab("Export to Database", "Open Export to Database", new PExportToDatabase(), mainTab);
    }//GEN-LAST:event_menuExportToDatabaseActionPerformed

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
        addTab("Change Password", "Open Change Password", new PChangePassword(), mainTab);
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

    private void menuLoginActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
        // TODO add your handling code here:
        addTab("Login", "Open Login", new PLogin(), mainTab);
    }//GEN-LAST:event_menuLoginActionPerformed

    private void menuLogoutActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(this, "Code logout implement here",
                "Warning", JOptionPane.CLOSED_OPTION,
                JOptionPane.WARNING_MESSAGE);
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

    private void toolStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolStartServerActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(this, "Please implement here", "Modify", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_toolStartServerActionPerformed

    private void toolStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolStopServerActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(this, "Please implement here", "Modify", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_toolStopServerActionPerformed

    private void menuOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenFileActionPerformed
        // TODO add your handling code here:
         JOptionPane.showConfirmDialog(this, "Please implement here", "Modify", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_menuOpenFileActionPerformed

    private void menuOpenFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenFolderActionPerformed
        // TODO add your handling code here:
         JOptionPane.showConfirmDialog(this, "Please implement here", "Modify", JOptionPane.CLOSED_OPTION);
    }//GEN-LAST:event_menuOpenFolderActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public static javax.swing.JTabbedPane mainTab;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuAddNewPerson;
    private javax.swing.JMenuItem menuAdministrator;
    private javax.swing.JMenuItem menuChangePassword;
    private javax.swing.JMenuItem menuDeletePerson;
    private javax.swing.JMenuItem menuEditPersonInformation;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuExportToDatabase;
    private javax.swing.JMenuItem menuHelp;
    private javax.swing.JMenuItem menuLogin;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenuItem menuOpenFile;
    private javax.swing.JMenuItem menuOpenFolder;
    private javax.swing.JMenuItem menuSearch;
    private javax.swing.JMenuItem menuServer;
    private javax.swing.JButton toolAddNewPerson;
    private javax.swing.JButton toolDeletePerson;
    private javax.swing.JButton toolEditPerson;
    private javax.swing.JButton toolFaceDetection;
    private javax.swing.JButton toolFaceRecog;
    private javax.swing.JButton toolStartServer;
    private javax.swing.JButton toolStopServer;
    private javax.swing.JMenuItem viewFaceDetection;
    private javax.swing.JMenuItem viewFaceRecognition;
    // End of variables declaration//GEN-END:variables
}
