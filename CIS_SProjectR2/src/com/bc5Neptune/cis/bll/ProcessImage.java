package com.bc5Neptune.cis.bll;

import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.UIManager;

/**
 *
 * @author enclaveit
 */
public class ProcessImage implements IProcessImage {

    @Override
    public IplImage setGray(IplImage image) {

        IplImage grayImg = IplImage.create(image.width(), image.height(),
                IPL_DEPTH_8U, 1);

        /*
         * convert to gray color
         */
//            param.setRaw(true);
//            ImageEncoder encoder = ImageCodec.createImageEncoder("PNM", fileOutputStream, param);
//            encoder.encode(bufferedImage);
        cvCvtColor(image, grayImg, CV_BGR2GRAY);

        /*
         * scale the grayscale (to speed up face detection)
         */
        IplImage smallImg = IplImage.create(grayImg.width(),
                grayImg.height(), IPL_DEPTH_8U, 1);

        cvResize(grayImg, smallImg, CV_INTER_LINEAR);

        /*
         * equalize the small grayscale
         */
        IplImage equImg = IplImage.create(smallImg.width(), smallImg.height(),
                IPL_DEPTH_8U, 1);
        cvEqualizeHist(smallImg, equImg);
        return equImg;
    }

    @Override
    public BufferedImage crop(BufferedImage image,
            int x,
            int y,
            int width,
            int height) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BufferedImage toJPG(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IplImage toPGM(IplImage image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(BufferedImage image, String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(BufferedImage image, String path, int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BufferedImage resize(BufferedImage image, int width, int height) {

        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(width, height, type);
        Graphics2D g2 = dst.createGraphics();
        // Fill background for scale to fit.  
        g2.setBackground(UIManager.getColor("Panel.background"));
        g2.clearRect(0, 0, width, height);
        double xScale = (double) width / image.getWidth();
        double yScale = (double) height / image.getHeight();
        // Scaling options:  
        // Scale to fit - image just fits in label.  
        double scale = Math.min(xScale, yScale);
        // Scale to fill - image just fills label.  
        //double scale = Math.max(xScale, yScale);  

        int width_new = (int) (scale * image.getWidth());
        int height_new = (int) (scale * image.getHeight());
        int x = (width - width_new) / 2;
        int y = (height - height_new) / 2;
        g2.drawImage(image, x, y, width_new, height_new, null);
        g2.dispose();
        return dst;
    }

    @Override
    public BufferedImage toPGM(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //convert from byte[] to buffered image
    public BufferedImage byteArrayToBufferedImage(byte[] byteImage) {
        BufferedImage image = null;
        InputStream in = new ByteArrayInputStream(byteImage);
        try {
            image = ImageIO.read(in);

        } catch (IOException ex) {
            Logger.getLogger(ProcessImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;
    }
    /*convert from buffered image to byte[] */

    public byte[] bufferedImageToByteArray(BufferedImage bufferedImage) {
        byte[] byteImage = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            byteImage = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcessImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteImage;
    }

    /* read a image with a path */
    public BufferedImage readImage(String path) {
        File file = new File(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(ProcessImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    
    //convet from bufferedImage to IplImage
    public IplImage toIplImage(BufferedImage bufferedImage){
        IplImage iplImage = new IplImage();
        iplImage = IplImage.createFrom(bufferedImage);
        return iplImage;
    }
}
