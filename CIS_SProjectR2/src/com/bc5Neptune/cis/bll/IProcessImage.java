/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.awt.image.BufferedImage;

/**
 *
 * @author huynhtanphu
 */
public interface IProcessImage {
    /*
     * set gray for a image
     */

    public IplImage setGray(IplImage image);

    /*
     * crop a image
     */
    public BufferedImage crop(BufferedImage image, int x, int y, int width, int height);

    /*
     * resize a image
     */
    public BufferedImage resize(BufferedImage image, int width, int height);

    /*
     * convert to .jpg
     */
    public BufferedImage toJPG(BufferedImage image);

    /*
     * convert to .pgm
     */
    public IplImage toPGM(IplImage image);
    /*
     * save a image
     */
    public BufferedImage toPGM(BufferedImage image);

    public void save(BufferedImage image, String path);
     /*
     * save a image with type
     * 1: .jpg
     * 2: .pgm
     * 3: .png
     */
    public void save(BufferedImage image, String path,int type);
}
