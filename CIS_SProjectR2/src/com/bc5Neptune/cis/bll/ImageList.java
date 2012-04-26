/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.itextpdf.text.Image;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author phu.huynh
 */
public final class ImageList {

    /*
     * a array of the name of images
     */
    String[] namesArr;
    /*
     * a path of folder that it's contained images
     */
    String pathFolder;
    /*
     * a array of buffer image
     */
    BufferedImage image;
    /*
     * load image using OpenCV
     */
    public IplImage cvImage;

    /*
     * the number of image
     */
    public void init(String[] imagesArr, String pathFolder) {
        this.namesArr = imagesArr;
        this.pathFolder = pathFolder;
        /*
         * load images using openCV
         */
    }

    public ImageList() {
    }

    public void setFileImages(String[] namesArr) {
        this.namesArr = namesArr;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    public String getFileNames(int index) {
        return namesArr[index];
    }

    public String[] getFileNames() {
        return namesArr;
    }

    public int getLength() {
        return namesArr.length;
    }

    public IplImage getCVImage() {
        ProcessImage proImg = new ProcessImage();
        cvImage = proImg.toIplImage(image);
        return cvImage;
    }

    public BufferedImage getImage() {
        return image;
    }
    /*
     * load a image
     */

    public void load(int index) {
        // = new IplImage();
        String path = pathFolder + namesArr[index];
        File file = new File(path);


        try {
            System.out.println(Runtime.getRuntime().freeMemory());
            image = ImageIO.read(file);

        } catch (IOException ex) {
            Logger.getLogger(ImageList.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Load file from: " + path);

        if (image == null) {
            System.out.println("Loading image fail! " + path);
        }
    }
}