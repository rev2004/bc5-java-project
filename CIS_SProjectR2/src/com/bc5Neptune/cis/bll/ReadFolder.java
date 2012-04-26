/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.bll;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author phu.huynh
 */
public class ReadFolder {
    //String GIF = "gif";
    //String PNG = "png";

    String JPG = "jpg";
    //String BMP = "bmp";
    String JPEG = "jpeg";
    String XML = "xml";
    String DAT = "dat";
    String PGM = "pgm";
    String type = "";
    private String pathFolder;
    private String[] files;
    private Integer[] filesInt;
    File dir;

    public ReadFolder() {
        System.out.println("Init ReadFolder class");
    }
    /*
     * constructor to initlization pathFolder
     */

    public ReadFolder(String pathFolder, String type) {
        this.pathFolder = pathFolder;
        this.type = type;

    }

    /*
     * read all of file in a folder with condition is type
     */
    public String[] read(int sort) {
        dir = new File(this.pathFolder);
        ArrayList<String> imageNames = new ArrayList<String>();
        for (final File imgFile : dir.listFiles()) {
            if (accept(imgFile)) {
                //System.out.println(imgFile.getName());
                imageNames.add(imgFile.getName());
            }
        }
        int size = imageNames.size();
        files = new String[size];
        for (int i = 0; i < size; i++) {
            files[i] = imageNames.get(i);
            System.out.println(files[i]);
        }
        if (sort == 1) {
            sortByIncrease(); //sort by increase
        }
        if (sort == 2) {
            //sort by decrease
        }//no sort
        return files;
    }
    
    /*
     * read all of file in a folder with condition is type
     */
    public Integer[] readInt() {
        dir = new File(this.pathFolder);
        ArrayList<String> imageNames = new ArrayList<String>();
        for (final File imgFile : dir.listFiles()) {
            if (accept(imgFile)) {
                //System.out.println(imgFile.getName());
                imageNames.add(imgFile.getName());
            }
        }
        int size = imageNames.size();
        filesInt = new Integer[size];
        for (int i = 0; i < size; i++) {
            int lastIndex = imageNames.get(i).indexOf(".");
            String str = imageNames.get(i).substring(0, lastIndex);
            filesInt[i] = new Integer(str);
            System.out.println(filesInt[i]);
        }
        java.util.Arrays.sort(filesInt);
        for (int i = 0; i < filesInt.length; i++) {
            System.out.println(filesInt[i]);
        }
        return filesInt;
    }

    /* order array */
    public void sortByIncrease() {
        java.util.Arrays.sort(files, java.text.Collator.getInstance());
        System.out.println("-----------------After sort by increase----------------");
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    public void setPathFolder(String path) {
        this.pathFolder = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*
     * get name of files
     */
    public String[] getFileNames() {
        return files;
    }

    public String getPathFolder() {
        return pathFolder;
    }

    public String getType() {
        return type;
    }

    /*
     * Accept extention .jpg, jpeg
     */
    public boolean accept(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                return false;
            }
            String extension = getExtension(file);
            if (extension != null && isSupported(extension)) {
                return true;
            }
        }
        return false;
    }
    /*
     * get extention
     */

    private String getExtension(File file) {
        if (file != null) {
            String filename = file.getName();
            int dot = filename.lastIndexOf('.');
            if (dot > 0 && dot < filename.length() - 1) {
                return filename.substring(dot + 1).toLowerCase();
            }
        }
        return null;
    }
    /*
     * dir is supported
     */

    private boolean isSupported(String ext) {
        System.out.println("Debug here");
        boolean result = false;
        if (type.equals("image")) {
//        return ext.equalsIgnoreCase(GIF) || ext.equalsIgnoreCase(PNG) ||
//                ext.equalsIgnoreCase(JPG) || ext.equalsIgnoreCase(BMP) ||
//                ext.equalsIgnoreCase(JPEG);
            if (ext.equalsIgnoreCase(JPG) || ext.equalsIgnoreCase(JPEG)) {
                result = true;
            } else {
                result = false;
            }
        } else if (type.equals("xml")) {
            if (ext.equalsIgnoreCase(XML)) {
                result = true;
            } else {
                result = false;
            }
        } else if (type.equals("dat")) {
            result = true;
        }
        return result;
    }
}