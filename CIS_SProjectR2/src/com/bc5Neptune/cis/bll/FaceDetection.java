package com.bc5Neptune.cis.bll;

/**
 *
 * @author enclaveit
 */
import java.util.ArrayList;
import com.bc5Neptune.cis.app.App;
import com.bc5Neptune.cis.config.Config;
import com.bc5Neptune.cis.gui.PFaceCustom;
import static com.bc5Neptune.cis.bll.GlobalObject.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacpp.Loader;
import java.awt.image.BufferedImage;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;

public class FaceDetection {

    private static final String OUTPUT = Config.PATH_TMP_RECOG;
    //ArrayList<int> noFace = new ArrayList();

    /**
     * This method is used to detect a face
     */
    public int detectFace(IplImage testFace, int index) {
        int temp = 2;
        IplImage origImg = null;
        ProcessImage processImg = new ProcessImage();
        /*
         * total face
         */
        int total = 0;
        // load an image
        origImg = testFace;

        // create temp storage, used during object detection
        CvMemStorage storage = CvMemStorage.create();
        while (true) {
            IplImage equImg = processImg.setGray(origImg);
            // reload the opencv_objdetect module to work around a known bug
            Loader.load(opencv_objdetect.class);
            // instantiate a classifier cascade for face detection
            CvHaarClassifierCascade cascade;
            if ((temp % 2) == 0) {
                cascade = App.cascadeFD;
                temp++;
            } else if ((temp % 2 == 1)) {
                cascade = App.cascadeLR;
                temp++;
            } else {
                break;
            }
            System.out.println("Detecting faces...");
            CvSeq face = cvHaarDetectObjects(equImg, cascade, storage, 1.1, 3,
                    CV_HAAR_DO_CANNY_PRUNING);

            total = face.total();
            System.out.println("Found " + total + " face(s)");
            for (int i = 0; i < total; i++) {
                CvRect r = new CvRect(cvGetSeqElem(face, i));

                /*
                 * crop face image
                 */
                BufferedImage tmp = GLImageList.imagesArr[index];
                BufferedImage objBufferedImage = tmp.getSubimage(r.x(), r.y(), r.width(), r.width());

                /*
                 * resize face image with 92 height 112 type .pgm
                 */
                objBufferedImage = processImg.resize(objBufferedImage, 92, 112);
                GLPCustom.faceListArr.add(objBufferedImage);
                GLPCustom.countFaces++;
            }
            if (total > 0) {
                return total;
            }
            if ((temp == 4) && (total == 0)) {
                return 0;
            } //no found a face
        }
        // clear temp storage
        cvClearMemStorage(storage);
        return total;
    }

    /**
     * This method is used to detect faces in the list of images
     */
    public int detectFaces(final IplImage[] testFaceArr) {
        int temp = 2;
        ProcessImage processImg = new ProcessImage();
        /*
         * total face
         */
        int total = 0;

        System.out.println("> lengh array " + testFaceArr.length);
        // create temp storage, used during object detection
        CvMemStorage storage = CvMemStorage.create();
        for (int i = 0; i < testFaceArr.length; i++) {
            System.out.println("Detecting a image at " + i);
            IplImage origImg;
            origImg = testFaceArr[i];
            //set a number of faces by zero
            total = 0;
            //set a number of change a xml(file used to detect face 
            temp = 2;
            // reload the opencv_objdetect module to work around a known bug
            Loader.load(opencv_objdetect.class);
            while (true) {
                IplImage equImg;
                equImg = processImg.setGray(origImg);
                // instantiate a classifier cascade for face detection
                CvHaarClassifierCascade cascade = null;
                if ((temp % 2) == 0) {
                    cascade = App.cascadeFD;
                    temp++;
                } else if ((temp % 2 == 1)) {
                    cascade = App.cascadeLR;
                    temp++;
                }
                System.out.println("Detecting faces...");
                CvSeq face = cvHaarDetectObjects(equImg, cascade, storage, 1.1, 3,
                        CV_HAAR_DO_CANNY_PRUNING);
                total = face.total();
                System.out.println("Found " + total + " face(s)");
                for (int j = 0; j < total; j++) {
                    CvRect r = new CvRect(cvGetSeqElem(face, j));

                    /*
                     * crop face image
                     */
                    BufferedImage tmp = GLImageList.imagesArr[i];
                    BufferedImage objBufferedImage = tmp.getSubimage(r.x(), r.y(), r.width(), r.width());

                    /*
                     * resize face image with 92 height 112 type .pgm
                     */
                    objBufferedImage = processImg.resize(objBufferedImage, 92, 112);
                    GLPCustom.faceListArr.add(objBufferedImage);
                    GLPCustom.countFaces++;
                }
                if (total > 0) {
                    break;
                }
                if ((temp == 4) && (total == 0)) {
                    break;

                }
            }
        }
        // clear temp storage
        cvClearMemStorage(storage);
        return total;
    }
    
    /**
     * This method is used to detect a face
     * return a array of BufferedImage
     */
    public ArrayList<BufferedImage> detectFace(BufferedImage testFace) {
        ArrayList<BufferedImage> faceArr = new ArrayList<BufferedImage>();
        int temp = 2;
        IplImage origImg = null;
        ProcessImage processImg = new ProcessImage();
        /*
         * total face
         */
        int total = 0;
        // load an image
        
        origImg = processImg.toIplImage(testFace);

        // create temp storage, used during object detection
        CvMemStorage storage = CvMemStorage.create();
        while (true) {
            IplImage equImg = processImg.setGray(origImg);
            // reload the opencv_objdetect module to work around a known bug
            Loader.load(opencv_objdetect.class);
            // instantiate a classifier cascade for face detection
            CvHaarClassifierCascade cascade;
            if ((temp % 2) == 0) {
                cascade = App.cascadeFD;
                temp++;
            } else if ((temp % 2 == 1)) {
                cascade = App.cascadeLR;
                temp++;
            } else {
                break;
            }
            System.out.println("Detecting faces...");
            CvSeq face = cvHaarDetectObjects(equImg, cascade, storage, 1.1, 3,
                    CV_HAAR_DO_CANNY_PRUNING);

            total = face.total();
            System.out.println("Found " + total + " face(s)");
            for (int i = 0; i < total; i++) {
                CvRect r = new CvRect(cvGetSeqElem(face, i));

                /*
                 * crop face image
                 */
                BufferedImage tmp = testFace;
                BufferedImage objBufferedImage = tmp.getSubimage(r.x() + 4, r.y(), r.width() - 10, r.height());

                /*
                 * resize face image with 92 height 112 type .pgm
                 */
                objBufferedImage = processImg.resize(objBufferedImage, 92, 112);
                faceArr.add(objBufferedImage);
            }
            if (total > 0) {
                return faceArr;
            }
            if ((temp == 4) && (total == 0)) {
                return faceArr;
            } //no found a face
        }
        // clear temp storage
        cvClearMemStorage(storage);
       return faceArr;
    }
}