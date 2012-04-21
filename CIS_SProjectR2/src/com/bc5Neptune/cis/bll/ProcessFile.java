package com.bc5Neptune.cis.bll;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.bc5Neptune.cis.config.Config;
import com.googlecode.javacpp.Pointer;
import com.googlecode.javacv.cpp.opencv_core.CvFileStorage;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author enclaveit
 */
public class ProcessFile implements IProcessFile {

    @Override
    public void create(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Could not create file");
                return;
            }
        }
        System.out.println("Created file " + path + "ok");
    }

    @Override
    public void delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            System.out.println("delete a file at a path " + path);
        }
    }

    @Override
    public void copy(String originalPath, String detinationPath) {
        try {

            File f1 = new File(originalPath);
            File f2 = new File(detinationPath);
            InputStream in = new FileInputStream(f1);
            // For Append the file.
            // OutputStream out = new FileOutputStream(f2,true);
            // For Overwrite the file.
            OutputStream outFl = new FileOutputStream(f2);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                outFl.write(buf, 0, len);
            }
            in.close();
            outFl.close();
            // System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * create a folder
     */
    @Override
    public void createFolder(String path) {
        try {
            File dir = new File(path);
            if (dir.exists() == false) {
                dir.mkdir();
            }
        } catch (Exception ex) {
            System.err.println("Can't create Folder!");
        }
    }

    /*
     * delete folder
     */
    @Override
    public void deleteFolder(String path) {
        // The file/directory exists, so if a directory delete all of the
        // contents ...
        File folder = new File(path);
        if (folder.exists()) {
            if (folder.isDirectory()) {
                for (File childFile : folder.listFiles()) {
                    deleteFolder(childFile); // recursive call (good enough for now until
                    // we need something better)
                }
                // Now an empty directory ...
            }

            folder.delete();
            System.out.println("delete a folder at path " + folder.getAbsoluteFile());
        }
    }

    @Override
    public void deleteFolder(File folder) {
        // The file/directory exists, so if a directory delete all of the
        // contents ...
        if (folder.exists()) {
            if (folder.isDirectory()) {
                for (File childFile : folder.listFiles()) {
                    deleteFolder(childFile); // recursive call (good enough for now until
                    // we need something better)
                }
                // Now an empty directory ...
            }

            folder.delete();
            System.out.println("delete a folder at path " + folder.getAbsoluteFile());
        }
    }

    @Override
    public void copyFolder(String originalPath, String detinationPath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void append(File file, String data) {
        try {
            PrintStream p = new PrintStream(new BufferedOutputStream(
                    new FileOutputStream(file, true)));
            p.println(data);
            p.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public IplImage[] loadDatFace(String path, String[] nameFileArr) {
        final int NUM_FILE = 10;
        String[] files = nameFileArr; //get all of dat files in a folder

        IplImage[] images = new IplImage[NUM_FILE * files.length];
        int g = 0;
        for (int j = 0; j < files.length; j++) {

            try {
                // Open the file that is the first 
                // command line parameter
                FileInputStream fstream = new FileInputStream(path + "/" + files[j]);
                // Get the object of DataInputStream
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                //Read File Line By Line
                // int i = 0;
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    System.out.println(strLine);
                    //Base64 decoder = new Base64();
                    byte[] imgBytes = Base64.decode(strLine);
                    InputStream inputStream = new ByteArrayInputStream(imgBytes);
                    BufferedImage bimg = ImageIO.read(inputStream);
                    images[g] = IplImage.createFrom(bimg);
                    g++;
                }

                //Close the input stream
                //in.close();
            } catch (Exception e) {//Catch exception if any
                System.err.println("Error : " + e);
            }
        }
        return images;
    }

    @Override
    public void storeTrainingData(int nTrainFaces,
            int nEigens,
            CvMat projectedTrainFaceMat,
            CvMat eigenValMat,
            IplImage[] eigenVectArr,
            IplImage pAvgTrainImg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadTraningData(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imageToDat(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imagesToDat(String[] pathArr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imageToDat(BufferedImage image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imagesToDat(BufferedImage[] imageArr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imageToDat(IplImage image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imagesToDat(IplImage[] imageArr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void storeEigenValue(String path, CvMat eigenValMat) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + "eigenVal.xml";
        CvFileStorage fileStorage = new CvFileStorage();
        fileStorage = cvOpenFileStorage(fileName, // filename
                null, // memory storage
                CV_STORAGE_WRITE, // flags
                null); // encoding

        String varname = "eigenVectArr";
        cvWrite(fileStorage, // file storage
                varname, // name
                eigenValMat, // value
                cvAttrList()); // attributes

        // release the file-storage interface
        try {
            cvReleaseFileStorage(fileStorage);
            System.out.println("Store eigenVal " + fileName
                    + "successfully!");
        } catch (Exception ex) {
            System.err.println("Can't save eigenVal file" + ex);
        }

    }

    @Override
    public void storeProjected(String path, CvMat projectedTrainFaceMat) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + "projectedTrainFace.xml";
        CvFileStorage fileStorage = new CvFileStorage();
        fileStorage = cvOpenFileStorage(fileName, // filename
                null, // memory storage
                CV_STORAGE_WRITE, // flags
                null); // encoding

        String varname = "projectedTrainFaceMat";
        cvWrite(fileStorage, // file storage
                varname, // name
                projectedTrainFaceMat, // value
                cvAttrList()); // attributes

        // release the file-storage interface
        try {
            cvReleaseFileStorage(fileStorage);
            System.out.println("Store projectedTrainFaceMat " + fileName
                    + "successfully!");
        } catch (Exception ex) {
            System.err.println("Can't save projectedTrainFaceMat file" + ex);
        }

    }

    @Override
    public void storeAvgImage(String path, IplImage pAvgTrainImg) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + "avgTrainFace.xml";
        CvFileStorage fileStorage = new CvFileStorage();
        fileStorage = cvOpenFileStorage(fileName, // filename
                null, // memory storage
                CV_STORAGE_WRITE, // flags
                null); // encoding

        String varname = "pAvgTrainImg";
        cvWrite(fileStorage, // file storage
                varname, // name
                pAvgTrainImg, // value
                cvAttrList()); // attributes

        // release the file-storage interface
        try {
            cvReleaseFileStorage(fileStorage);
            System.out.println("Store avgTrainFace " + fileName
                    + "successfully!");
        } catch (Exception ex) {
            System.err.println("Can't save avgTrainFace file" + ex);
        }
    }

    @Override
    public ImageIcon loadIconFace(String path, String name, int index) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + name + ".dat";
        BufferedImage image = null;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            int seek = 0;
            while ((strLine = br.readLine()) != null) {
                seek++;
                // Print the content on the console
                System.out.println(strLine);
                Base64 decoder = new Base64();
                byte[] imgBytes = decoder.decode(strLine);

                if (seek == index) {
                    InputStream inByte = new ByteArrayInputStream(imgBytes);
                    image = ImageIO.read(inByte);
                    break;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        ImageIcon icon = new ImageIcon(image);
        return icon;

    }

    /* save eigenvector */
    @Override
    public void storeEigenVector(String path, IplImage[] eigenVectArr) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int nEigens = eigenVectArr.length;
        String eigenName = "eigenVect_"; //name of eigenvector node
        System.out.println("The number of eigenvector is " + nEigens);
        // create a file-storage interface

        for (int i = 0; i < nEigens; i++) {

            String fileName = path + i + ".xml";
            System.out.println("writing data into eigenVect_" + i);

            CvFileStorage fileStorage = new CvFileStorage();
            fileStorage = cvOpenFileStorage(fileName, // filename
                    null, // memory storage
                    CV_STORAGE_WRITE, // flags
                    null); // encoding

            String varname = "eigenVect_" + i;
            cvWrite(fileStorage, // file storage
                    varname, // name
                    eigenVectArr[i], // value
                    cvAttrList()); // attributes

            // release the file-storage interface
            try {
                cvReleaseFileStorage(fileStorage);
                System.out.println("Store eigenVect_ " + i + " " + fileName
                        + "successfully!");
            } catch (Exception ex) {
                System.err.println("Can't save eigenVector file" + ex);
            }
        }
    }

    @Override
    public void storePerson(String path, String[] listPerson) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + "person.xml";
        CvFileStorage fileStorage = new CvFileStorage();
        fileStorage = cvOpenFileStorage(fileName, // filename
                null, // memory storage
                CV_STORAGE_WRITE, // flags
                null); // encoding

        for (int i = 0; i < listPerson.length; i++) {
            String varname = "personID_" + (i + 1);

            int firstDot = listPerson[i].indexOf("."); //cut .dat

            String namePerson = listPerson[i].substring(0, firstDot);
            System.out.println("name of person " + namePerson);
            cvWriteString(fileStorage, // file storage
                    varname, // name
                    namePerson, // string
                    0); // quote
        }
        // release the file-storage interface
        try {
            cvReleaseFileStorage(fileStorage);
            System.out.println("Store person " + fileName
                    + "successfully!");
        } catch (Exception ex) {
            System.err.println("Can't save person file" + ex);
        }
    }

    @Override
    public CvMat loadEigenValue(String path) {
        //throw new UnsupportedOperationException("Not supported yet.");
        CvMat eigenValMat = new CvMat();
        CvFileStorage fileStorage = new CvFileStorage();

        // open file dataface.xml
        String fileName = path + "eigenVal.xml";
        fileStorage = cvOpenFileStorage(fileName, // path of
                // projectedTrainFace.xml
                null, // memstorage
                CV_STORAGE_READ, // flags
                null); // encoding

        /* if can not open file */
        if (fileStorage == null) {
            System.out.println("can't open training file "
                    + fileName);
            return null;
        }
        Pointer pointer = cvReadByName(fileStorage, // fs
                null, // map
                "eigenValMat", // nmae
                cvAttrList()); // attributes
        eigenValMat = new CvMat(pointer);
        System.out.println("Load eigenValue successfully");
        return eigenValMat;
    }

    @Override
    public CvMat loadProjected(String path) {
        //throw new UnsupportedOperationException("Not supported yet.");

        CvMat projectedTrainFaceMat = new CvMat();
        CvFileStorage fileStorage = new CvFileStorage();

        // open file dataface.xml
        String fileName = path + "projectedTrainFace.xml";
        fileStorage = cvOpenFileStorage(fileName, // path of
                // projectedTrainFace.xml
                null, // memstorage
                CV_STORAGE_READ, // flags
                null); // encoding

        /* if can not open file */
        if (fileStorage == null) {
            System.out.println("can't open training file "
                    + fileName);
            return null;
        }
        Pointer pointer = cvReadByName(fileStorage, // fs
                null, // map
                "projectedTrainFaceMat", // name
                cvAttrList()); // attributes
        projectedTrainFaceMat = new CvMat(pointer);
        System.out.println("Load projectedTrainFace successfully");
        return projectedTrainFaceMat;

    }

    @Override
    public IplImage loadAvgImage(String path) {
        //throw new UnsupportedOperationException("Not supported yet.");
        IplImage pAvgTrainImg = new IplImage();
        CvFileStorage fileStorage = new CvFileStorage();

        // open file dataface.xml
        String fileName = path + "avgTrainFace.xml";
        fileStorage = cvOpenFileStorage(fileName, // path of
                // projectedTrainFace.xml
                null, // memstorage
                CV_STORAGE_READ, // flags
                null); // encoding

        /* if can not open file */
        if (fileStorage == null) {
            System.out.println("can't open training file "
                    + fileName);
            return null;
        }
        Pointer pointer = cvReadByName(fileStorage, null, // map
                "pAvgTrainImg", cvAttrList()); // attributes
        pAvgTrainImg = new IplImage(pointer);
        System.out.println("Load avgTrainFace successfully");
        return pAvgTrainImg;
    }

    @Override
    public ArrayList<String> loadPerson(String path, int nTrainFaces) {
        ArrayList<String> listPerson = new ArrayList<String>();
        CvFileStorage fileStorage = new CvFileStorage();

        // open file dataface.xml
        String fileName = path + "person.xml";
        fileStorage = cvOpenFileStorage(fileName, // path of
                // projectedTrainFace.xml
                null, // memstorage
                CV_STORAGE_READ, // flags
                null); // encoding

        /* if can not open file */
        if (fileStorage == null) {
            System.out.println("can't open training file "
                    + fileName);
            return null;
        }
        for (int i = 0; i < nTrainFaces; i++) {
            String varname = "personID_" + (i + 1);
            String strPersonName = cvReadStringByName(fileStorage, // fs
                    null, // map
                    varname, "");
            /* add person in the list */
            listPerson.add(strPersonName);
        }

        System.out.println("Load listPerson successfully");
        return listPerson;
    }

    @Override
    public IplImage[] loadEigenVector(String path) {
        // throw new UnsupportedOperationException("Not supported yet.");
        //int SORT_INCREASE = 1;
        ReadFolder objRead = new ReadFolder(path, "xml");
        Integer[] files = objRead.readInt();
        IplImage[] eigenVectArr = new IplImage[files.length];
        for (int i = 0; i < files.length; i++) {
            CvFileStorage fileStorage = new CvFileStorage();

            // open file dataface.xml
            String fileName = path + files[i] + ".xml";
            fileStorage = cvOpenFileStorage(fileName, // path of
                    // projectedTrainFace.xml
                    null, // memstorage
                    CV_STORAGE_READ, // flags
                    null); // encoding

            /* if can not open file */
            if (fileStorage == null) {
                System.out.println("can't open training file "
                        + fileName);
                return null;
            }
            String varname = "eigenVect_" + i;
            Pointer pointer = cvReadByName(fileStorage, null, // map
                    varname, cvAttrList()); // attributes
            eigenVectArr[i] = new IplImage(pointer);
        }
        System.out.println("Load eigenVectArr successfully");

        return eigenVectArr;
    }

    @Override
    public BufferedImage loadBufferedFace(String path, String name, int index) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + name + ".dat";
        BufferedImage image = null;
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            int seek = 0;
            while ((strLine = br.readLine()) != null) {
                seek++;
                // Print the content on the console
                System.out.println(strLine);
                Base64 decoder = new Base64();
                byte[] imgBytes = decoder.decode(strLine);

                int possition = index % 10;
                if (possition == 0) {
                    possition = 10;
                }
                if (seek == possition) {
                    InputStream inByte = new ByteArrayInputStream(imgBytes);
                    image = ImageIO.read(inByte);
                    break;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return image;
    }

    public BufferedImage[] loadBufferedFace(String path, String name) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String fileName = path + name + ".dat";
        BufferedImage[] image = new BufferedImage[10];
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            int seek = 0;

            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                System.out.println(strLine);
                Base64 decoder = new Base64();
                byte[] imgBytes = decoder.decode(strLine);
                InputStream inByte = new ByteArrayInputStream(imgBytes);
                image[seek] = ImageIO.read(inByte);
                seek++;
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return image;
    }

    @Override
    public void saveBuffImageToDAT(BufferedImage[] bfImg, String ID, String path) {
        FileWriter fw = null;
        String str = null;
        try {
            fw = new FileWriter(new File(path + ID + ".dat"));
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < 10; i++) {
            try {
                ImageIO.write(bfImg[i], "jpg", baos);
            } catch (IOException ex) {
                Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytesOut = baos.toByteArray();
            String tmp = Base64.encode(bytesOut);
            if (i < 10) {
                System.out.println(tmp);
                tmp = tmp + "\n";
            }
            try {
                fw.write(tmp);
            } catch (IOException ex) {
                Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createDatFile(String name) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("../CIS_SProjectR2/data/facedat/" + name + ".dat"));
            for (int i = 0; i < 10; i++) {

                //File file = new File("/home/enclaveit/s1/" + (i + 1) + ".jpg");

                //image = ImageIO.read(file);
                String path = "../CIS_SProjectR2/data/tmp/" + (i + 1) + ".pgm";
                IplImage cvImage = cvLoadImage(path);
                BufferedImage bImage = cvImage.getBufferedImage();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", baos);
                byte[] bytesOut = baos.toByteArray();

                String tmp = Base64.encode(bytesOut);
                if (i < 9) {
                    System.out.println(tmp);
                    //System.out.println(tmp.length());
                    tmp = tmp + "\n";
                }
                fw.write(tmp);
                fw.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcessFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
