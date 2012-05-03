package com.bc5Neptune.cis.bll;

import java.util.ArrayList;
import java.io.File;
import com.googlecode.javacpp.FloatPointer;
import com.googlecode.javacpp.PointerPointer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_legacy.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

/**
 *
 * @author enclaveit
 */
public class FaceRecognition {

//    /* array of face images */
    ArrayList<IplImage> faceImgArr = new ArrayList<IplImage>();
    /* face training */
    int nTrainFaces;
    /* the number of eigen */
    int nEigens;
    /* avg of image 92 x 112 */
    IplImage pAvgTrainImg;
    /* eigenvalue */
    CvMat eigenValMat;
    /* projected train faces */
    CvMat projectedTrainFaceMat;
    /* eigenvector */
    IplImage[] eigenVectArr;

    /* list of person */
    public ArrayList<String> listPerson = new ArrayList<String>();
    public ArrayList<Integer> listIDPicture = new ArrayList<Integer>();
    public ArrayList<String> listNearestPerson = new ArrayList<String>();
    public ArrayList<Float> listConfidence = new ArrayList<Float>();

    public FaceRecognition() {
    }

    public void loadTraingData() {
        ProcessImage img = new ProcessImage();
        //process xml file
        ProcessFile file = new ProcessFile();
        pAvgTrainImg = file.loadAvgImage("../CIS_SProjectR2/data/facexml/avgTrainFace/");
        projectedTrainFaceMat = file.loadProjected("../CIS_SProjectR2/data/facexml/projectedTrainFace/");
        eigenVectArr = file.loadEigenVector("../CIS_SProjectR2/data/facexml/eigenVec/");
        eigenValMat = file.loadEigenValue("../CIS_SProjectR2/data/facexml/eigenVal/");
        nEigens = eigenVectArr.length;
        nTrainFaces = nEigens + 1;
        listPerson = file.loadPerson("../CIS_SProjectR2/data/facexml/person/", nTrainFaces);

    }

    public void recognizeFile(BufferedImage image) {
        //int length = GlobalObject.getInstance().listXMLFiles.length;
        ProcessImage img = new ProcessImage();
        //process xml file

        //clear nearest person
        listIDPicture.clear();
        listNearestPerson.clear();
        float[] projectedTestFace;
        float confidence = 0.0f;
        IplImage testFace = new IplImage();
        testFace = IplImage.createFrom(image);

        testFace = img.setGray(testFace);


        // project the test images onto the PCA subspace
        projectedTestFace = new float[nEigens];

        // project the test image onto the PCA subspace
        cvEigenDecomposite(testFace, // Input
                // object
                // have
                // type
                // IplImage[]
                nEigens, // nEigObjs
                new PointerPointer(eigenVectArr), // eigInput
                // (Pointer)
                0, // ioFlags
                null, // userData
                pAvgTrainImg, // average
                projectedTestFace); // coefficients

        final FloatPointer pConfidence = new FloatPointer(confidence);
        findNearestNeighbor(projectedTestFace, new FloatPointer(
                pConfidence));

        for (int i = 0; i < listIDPicture.size(); i++) {
            int in = listIDPicture.get(i).intValue();
            System.out.println("The nearest person at " + in);
        }
        getNearestPerson();
    }

    /**
     * find nearest faces in data training
     * 
     * @param dataTestFace
     *            data of test face
     * @param pConfi
     *            confidence value of face, pConfi pass-by-reference
     * @return
     */
    public void findNearestNeighbor(float dataTestFace[], FloatPointer pConfi) {
        double leastDistSq = 1.7976931348623157e+308; // 1.7976931348623157e+308 1.005247429203111E7 0.755247429203111E7
        // index of nearest face 
        System.out.println("----------------------------------");
        System.out.println("find nearest faces from "
                + nTrainFaces
                + " training faces");
        for (int i = 0; i < nTrainFaces; i++) {
            System.out.println("________________________Tong TotalTrainFace_________________" + (i + 1));
            double distance = 0.0; // value of type double
            for (int j = 0; j < nEigens; j++) {
                //get face training data row i and col j 
                float trainFaceDistance = (float) projectedTrainFaceMat.get(i, j);
                //calculate distance between test face and face training data 
                float d = dataTestFace[j] - trainFaceDistance;
                distance += d * d;
            }
            if (distance < leastDistSq) { // distance have to < MAX_VALUE double
                leastDistSq = distance;
                float pConfidence = (float) (1.0f - Math.sqrt(leastDistSq / // A
                        (float) (nTrainFaces * nEigens)) / 255.0f);

                if (pConfidence > 0.5) {
                    System.out.printf("________________________Lan %d  co gia tri %f \n", (i + 1), pConfidence);
                    listIDPicture.add(i + 1);
                    listConfidence.add(pConfidence);
                }
            }
        }
    }

    public ArrayList<String> getNearestPerson() {
        
        listNearestPerson.clear(); //sure empty
        int[] personArr = new int[nTrainFaces];
        //calculator index of person
        int g = 0;
        for (int i = 0; i < nTrainFaces / 10 ; i++) {
            //for (int k = 0; k < 10; k++) {
            for(int k = 0; k < 10; k++){
                personArr[g] = (i + 1);
                g++;
            }
            //}
        }
        //add truth person 
        for(int i = 0; i < listIDPicture.size(); i++){
            int index = listIDPicture.get(i);
            listNearestPerson.add(listPerson.get(personArr[index - 1] - 1));
        }
        return listNearestPerson;
    }
}
