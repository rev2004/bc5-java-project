/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.app;

/**
 *
 * @author noah
 */
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import com.sun.media.sound.JARSoundbankReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WaitingApp { //Main class
    //Declare GUI components

    static JFrame frmMain;
    static Container pane;
    static JProgressBar barDo;
    static JLabel wait;
    Thread t;

    public WaitingApp() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        //Create all components
        frmMain = new JFrame("Wating for loading application!");
        frmMain.setSize(400, 200); //Window size 300x100 pixels
        frmMain.setLocationRelativeTo(pane);
        pane = frmMain.getContentPane();
        pane.setLayout(null); //Use the null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit when X is clicked
        barDo = new JProgressBar(0, 100); //Min value: 0 Max value: 100	
        wait = new JLabel("Loading...");

        //Add components to pane
        pane.add(barDo);
        pane.add(wait);

        //Position controls (X, Y, width, height)
        barDo.setBounds(80, 80, 280, 20);
        wait.setBounds(80, 50, 100, 20);

        //Make frame visible
        frmMain.setResizable(false); //No resize
        frmMain.setVisible(true);

    }

    public static void main(String[] args) { //Main void
        //Set Look and Feel

        //Add action listeners
        //btnDo.addActionListener(new btnDoAction()); //Add the button's action
        //WaitingApp p = new WaitingApp();
        //p.mul();
        
    }

    public void mul() {
        // for (int i = 0; i < 5; i++) {
        t = new Thread(new thread1());
        t.start();
        //  }
    }
    public void exit(){
        t.stop();
        frmMain.setVisible(false);
    }
    //The action
//	public static class btnDoAction implements ActionListener{
//		public void actionPerformed (ActionEvent e){
//			new Thread(new thread1()).start(); //Start the thread
//		}
//	}
    //The thread

    public static class thread1 implements Runnable {

        public void run() {
            for (int loop = 0; ; loop++) {
                for (int i = 0; i <= 100; i++) { //Progressively increment variable i
                    barDo.setValue(i); //Set value
                    barDo.repaint(); //Refresh graphics
                    try {
                        Thread.sleep(50);
                    } //Sleep 50 milliseconds
                    catch (InterruptedException err) {
                    }
                }
            }
            //frmMain.setVisible(false);
        }
    }
}
