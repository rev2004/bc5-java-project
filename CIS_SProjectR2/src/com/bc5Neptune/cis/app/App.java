package com.bc5Neptune.cis.app;

import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.bc5Neptune.cis.bll.GlobalObject.*;
import java.io.File;
import com.bc5Neptune .cis.bll.ProcessFile;
import com.bc5Neptune.cis.bll.ProcessImage;
import com.bc5Neptune.cis.bll.Training;
import com.bc5Neptune.cis.config.Config;
import com.bc5Neptune.cis.config.ConnectDB2;
import com.bc5Neptune.cis.gui.*;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
//import javax.swing.Timer;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

/**
 *
 * @author enclaveit
 */
public class App {

    public static ProcessImage objImage = new ProcessImage();
    public static CvHaarClassifierCascade cascadeFD;
    public static CvHaarClassifierCascade cascadeLR;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
		/* init application */
        GWaitingApp wp = new GWaitingApp();
        wp.mul();
        init();
        /* run application */
        run();
        wp.exit();
        
    }

    /**
     * init application
     */
    public static void init() {
        /* check cofig of application */
        
        
        /* load training data */
        //Connection connection = ConnectDB2.getConnection();
        //Training objTrain = new Training("../CIS_SProjectR2/data/facedat/");
        //objTrain.learn();
        GLReg.loadTraingData();

        /*
         * load data of face detection PATH_DETECT_FD1, PATH_DETECT_FD2
         */
        cascadeFD = new CvHaarClassifierCascade(cvLoad(Config.PATH_DETECT_FD1));
        System.out.println("Load front detection");
        cascadeLR = new CvHaarClassifierCascade(cvLoad(Config.PATH_DETECT_FD2));
        //Loader.load(opencv_objdetect.class);
        System.out.println("Load left-right detection");
    }

    /**
     * run application
     */
    public static void run() {
        /* load form face detection */
        String[] runForm = null;
//        //new GApplication();
       // GApplication.main(runForm);    
         //GFaceDetection.main(runForm);
        GLogin.main(runForm);
        //init several form
        GLPCustom = new PFaceCustom();
        GLPReg = new PFaceRecognition();
        GLPDetect = new PFaceDetection();
        
    }

    /**
     * exit application
     */
    public static void exit() {
        /*
         * Delete all of file in template folder
         */
		ProcessFile objFile = new ProcessFile();
		objFile.deleteFolder(new File(Config.PATH_TMP));

    }
}
