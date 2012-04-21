/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author huynhtanphu
 */
public interface IProcessFile {
    /*
     * create new file
     */

    public void create(String path);

    /*
     * delelte a file
     */
    public void delete(String path);

    /*
     * copy file
     */
    public void copy(String originalPath, String detinationPath);

    /*
     * create new folder
     */
    public void createFolder(String path);

    /*
     * delete folder
     */
    public void deleteFolder(String path);

    public void deleteFolder(File folder);

    /*
     * copy folder
     */
    public void copyFolder(String originalPath, String detinationPath);

    /*
     * append data into a file
     */
    public void append(File file, String data);

    /*
     * load data traning face
     */
    public IplImage[] loadDatFace(String path, String[] nameFileArr);

    /*
     * store training face data
     */
    public void storeTrainingData(int nTrainFaces, int nEigens,
            CvMat projectedTrainFaceMat,
            CvMat eigenValMat, IplImage[] eigenVectArr,
            IplImage pAvgTrainImg);

    public void loadTraningData(String path);
    /*
     * convert a array of image to the dat files
     */

    public void imageToDat(String path);

    public void imagesToDat(String[] pathArr);

    public void imageToDat(BufferedImage image);

    public void imagesToDat(BufferedImage[] imageArr);

    public void imageToDat(IplImage image);

    public void imagesToDat(IplImage[] imageArr);

    /*
     * store eigenvector
     */
    public void storeEigenVector(String path, IplImage[] eigenVectArr);
    /*
     * store eigenvalue, pavgtraningface, projectTraning matrix
     */

    public void storeEigenValue(String path,CvMat eigenValMat);

    public void storeProjected(String path, CvMat projectedTrainFaceMat);

    /* store pepple */
    public void storePerson(String path, String[] listPerson);
    
    public void storeAvgImage(String path, IplImage pAvgTrainImg);

    /*
     * load data
     */
    public IplImage[] loadEigenVector(String path);
    /*
     * store eigenvalue, pavgtraningface, projectTraning matrix
     */

    public CvMat loadEigenValue(String path);

    public CvMat loadProjected(String path);

    public IplImage loadAvgImage(String path);
    
    public ArrayList<String> loadPerson(String path, int nTrainFaces);
    
    /* load image icon in a dat file at index */
    public ImageIcon loadIconFace(String path, String name,  int index);
    public BufferedImage loadBufferedFace(String path,String name,  int index);
    public void saveBuffImageToDAT(BufferedImage[] bfImg, String ID, String path);
    public void createDatFile(String name);
}
