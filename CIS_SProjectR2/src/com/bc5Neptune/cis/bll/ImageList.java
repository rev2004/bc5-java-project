/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
     * a array of buffer image
     */
    BufferedImage[] imagesArr;
    /*
     * load image using OpenCV
     */
    public IplImage[] cvImagesArr;
    /*
     * a path of folder that it's contained images
     */
    String pathFolder;
    /*
     * the number of image
     */

    public void init(String[] imagesArr, String pathFolder) {
        this.namesArr = imagesArr;
        this.pathFolder = pathFolder;
        /*
         * load images using openCV
         */

        cvload();
        /*
         * convert from IplImage to bufferedImage
         */
        toBufferedImage();
        System.out.println("Convert ok");

    }

    public ImageList() {
    }

    public void setFileImages(String[] namesArr) {
        this.namesArr = namesArr;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    /*
     * get image array buffer
     */
    public BufferedImage[] getImageList() {
        return imagesArr;
    }

    public BufferedImage getImageList(int index) {
        return imagesArr[index];
    }

    public String getFileNames(int index) {
        return namesArr[index];
    }

    public String[] getFileNames() {
        return namesArr;
    }

    public int getLength() {
        return imagesArr.length;
    }

    public IplImage[] getImageListCV() {
        return cvImagesArr;
    }

    public void load() {
        imagesArr = new BufferedImage[namesArr.length];
        for (int i = 0; i < namesArr.length; i++) {
            //path of images
            String path = pathFolder + namesArr[i];
            File file = new File(path);
            if (!file.exists()) {
                continue;
            }
            try {

                imagesArr[i] = ImageIO.read(file);
                System.out.println("Load file from: " + path);
            } catch (IOException ex) {
                System.out.println("Could not load file: " + path + ex);
            }
        }
    }

    public void cvload() {
        cvImagesArr = new IplImage[namesArr.length];

        for (int i = 0; i < namesArr.length; i++) {
            String path = pathFolder + namesArr[i];
            cvImagesArr[i] = cvLoadImage(path);

            System.out.println("Load file from: " + path);

            if (cvImagesArr[i] == null) {
                System.out.println("Loading image fail! " + path);
            }
        }
        toBufferedImage();
    }

    public void toBufferedImage() {
        imagesArr = new BufferedImage[namesArr.length];
        for (int i = 0; i < namesArr.length; i++) {
            imagesArr[i] = cvImagesArr[i].getBufferedImage();
        }
        System.out.println("Convert to BufferedImage is ok");
    }
}