package com.bc5Neptune.cis.bll;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import com.googlecode.javacpp.FloatPointer;
import com.googlecode.javacpp.PointerPointer;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_legacy.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author enclaveit
 */
public class Training {
    /* array of face images */
    
    final int SORT_INCREASE = 1;    
    IplImage[] faceImgArr;
    /* face training */
    int nTrainFaces;
    /* the number of eigen */
    int nEigens;
    /* projected train faces */
    CvMat projectedTrainFaceMat;
    /* eigenvector */
    String path;
    /* list of person */
    String[] listPerson;

    /**
     * load image data, process and save it in facedata.xml
     * 
     * @param pathImageTrain
     *            ../data/
     */
    public Training(String path) {
        this.path = path;
        
    }
    
    public void learn() {
        /* process file */
        ProcessFile file = new ProcessFile();
        /* doPCA in class Algorithym */
        Algorithm al = new Algorithm();
        
        ProcessImage img = new ProcessImage();
        /* load training data from path */
        System.out.println("Loading the training face in " + path);
        ReadFolder objRead = new ReadFolder(path, "dat");
        /* load face array */
        listPerson = objRead.read(SORT_INCREASE);
        
        faceImgArr = file.loadDatFace(path, listPerson);

        /* get the number of the face */
        nTrainFaces = faceImgArr.length;
        /* get the number of the eigen */
        nEigens = nTrainFaces - 1;

        /* if totalTrainFace < 3 will return */
        if (nTrainFaces < 3) {
            System.out.println("the training need 3 or more training faces\n"
                    + "input file contains only "
                    + nTrainFaces);
            return;
        }

        /* do Principal Component Analysis on the training faces */
        IplImage[] tmpArr = new IplImage[nTrainFaces];
        for (int i = 0; i < nTrainFaces; i++) {
            tmpArr[i] = img.setGray(faceImgArr[i]);
        }
        al.doPCA(nTrainFaces, nEigens, tmpArr);
        
        System.out.println("projecting the training images onto the PCA subspace");
        /* preparing the training faces onto the PCA */
        projectedTrainFaceMat = cvCreateMat(
                nTrainFaces, // rows
                nEigens, // cols
                CV_32FC1); // type, 32-bit float, 1 channel

        // initialize the training face matrix
        for (int i = 0; i < nTrainFaces; i++) {
            for (int j = 0; j < nEigens; j++) {
                // init 0.0 for all of value
                projectedTrainFaceMat.put(i, j, 0.0);
            }
        }

        /* pass-by-reference for prtTrainFaceMtr */
        final FloatPointer floatPointer = new FloatPointer(nEigens);
        for (int i = 0; i < nTrainFaces; i++) {
            IplImage tmp = img.setGray(faceImgArr[i]);
            
            cvEigenDecomposite(tmp, // input
                    // object.
                    nEigens, // Number of eigen
                    // objects.
                    new PointerPointer(al.eigenVectArr), // eigInput
                    0, // Input/output flags.
                    null, // userData (Pointer)
                    al.pAvgTrainImg, // Averaged object.
                    floatPointer); // coeffs (FloatPointer)

            for (int j = 0; j < nEigens; j++) {
                projectedTrainFaceMat.put(i, j, floatPointer.get(j));
            }
        }


        /* deallocate memory for face training */
        faceImgArr = null;
        /* store the recognition data as an facedata.xml file */
        file.storeEigenVector("../CIS_SProjectR2/data/facexml/eigenVec/", al.eigenVectArr);
        file.storeEigenValue("../CIS_SProjectR2/data/facexml/eigenVal/", al.eigenValMat);
        file.storeAvgImage("../CIS_SProjectR2/data/facexml/avgTrainFace/", al.pAvgTrainImg);
        file.storePerson("../CIS_SProjectR2/data/facexml/person/", listPerson);
        file.storeProjected("../CIS_SProjectR2/data/facexml/projectedTrainFace/", projectedTrainFaceMat);
        
    }
}
