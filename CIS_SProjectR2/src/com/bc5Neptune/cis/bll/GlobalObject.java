/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import com.bc5Neptune.cis.gui.PFaceCustom;
import com.bc5Neptune.cis.gui.PFaceDetection;
import com.bc5Neptune.cis.gui.PFaceRecognition;

/**
*
* @author enclaveit
*/
public class GlobalObject {
    /* the object of process image clas 
     * you can use it to process with a image
     */
    public static ProcessImage GLImage = new ProcessImage();
    /*
     * the object of process file class
     * you can use it to process with a file
     */
    public static ProcessFile GLFile = new ProcessFile();
    /* the object of ImageList class
     * you can use it to load images from a folder 
     */
    public static ImageList GLImageList = new ImageList();
    
    /* the object of face detection form */
    public static PFaceDetection GLPDetect;
    
    /* the object of face recognition form */
    
    public static PFaceRecognition GLPReg;
    /* the object of face recognition */
    public static FaceRecognition GLReg = new FaceRecognition();
    
    /* the object of custom face form */
    public static PFaceCustom GLPCustom;
    
}