/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import static com.bc5Neptune.cis.bll.GlobalObject.GLImage;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author phu.huynh
 */
public class IconList {

    /*
     * name of a image file
     */
    String name;
    /*
     * buffered of a image
     */
    BufferedImage image;
    /*
     * width that it's used to resize a image
     */
    int width;
    /*
     * height that it's used to resize a image
     */
    int height;

    /*
     * icon of image
     */
    ImageIcon icon;

    public IconList(String name,
            BufferedImage image,
            int width,
            int height) {
        this.name = name;
        this.image = image;
        this.width = width;
        this.height = height;

    }
    public IconList(){
    }

    public void setName(String fileImage) {
        this.name = fileImage;
    }

    public String getName() {
        return name;
    }

    public void setImage(BufferedImage image) {

        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
    /*
     * get image array buffer
     */

    public ImageIcon getIconResize() {
        /*
         * scale a image
         */
        image = GLImage.resize(image, width, height);
        /*
         * convert from bufferdimage to imageicon
         */
        icon = new ImageIcon(image);
        return icon;
    }
    public ImageIcon getIcon() {
        /*
         * convert from bufferdimage to imageicon
         */
        icon = new ImageIcon(image);
        return icon;
    }
}
