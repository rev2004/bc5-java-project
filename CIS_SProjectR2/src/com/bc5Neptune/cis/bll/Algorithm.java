package com.bc5Neptune.cis.bll;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_legacy.*;
import com.googlecode.javacpp.PointerPointer;

/**
 *
 * @author enclaveit
 */
public class Algorithm {

    /* avg of image 92 x 112 */
    IplImage pAvgTrainImg;
    /* eigenvalue */
    CvMat eigenValMat;
    /* eigenvector */
    IplImage[] eigenVectArr;
    /* path of data traning */


    public void doPCA(int nTrainFaces, int nEigens, IplImage[] faceImgArr) {

        CvTermCriteria calcLimit;
        CvSize faceImgSize = new CvSize();

        // allocate the eigenvalues vector images
        faceImgSize.width(faceImgArr[0].width());
        faceImgSize.height(faceImgArr[0].height());

        System.out.println("providing images to principal component analysis, using "
                + nEigens);

        /* alocate for eigenvector */
        eigenVectArr = new IplImage[nEigens];
        for (int i = 0; i < nEigens; i++) {
            eigenVectArr[i] = cvCreateImage(
                    faceImgSize, // size - Image width and height.
                    /*
                     * Bit depth of image elements. Can be one of: IPL_DEPTH_8U
                     * - uint 8-bit integers IPL_DEPTH_8S - signed 8-bit
                     * integers IPL_DEPTH_16U - uint 16-bit integers
                     * IPL_DEPTH_16S - signed 16-bit integers IPL_DEPTH_32S -
                     * signed 32-bit integers IPL_DEPTH_32F - single precision
                     * floating-point numbers IPL_DEPTH_64F - double precision
                     * floating-point numbers
                     */
                    IPL_DEPTH_32F, // depth
                    /*
                     * Number of channels per element(pixel). Can be 1, 2, 3 or
                     * 4. The channels are interleaved, for example the usual
                     * data layout of a color image is: b0 g0 r0 b1 g1 r1 ...
                     * Although in general IPL image format can store
                     * non-interleaved images as well and some of OpenCV can
                     * process it, this function can create interleaved images
                     * only.
                     */
                    1); // channels
        }

        // allocate the eigenvalue array
        eigenValMat = cvCreateMat(1, // rows
                nEigens, // columns
                CV_32FC1); // type, 32-bit float, 1 channel

        // allocate the averaged image
        pAvgTrainImg = cvCreateImage(faceImgSize, // size
                IPL_DEPTH_32F, // depth
                1); // channels

        // set the PCA termination criterion
        calcLimit = cvTermCriteria(CV_TERMCRIT_ITER, // type
                nEigens, // maximum number of
                // iterations
                1); // accuracy to achieve - epsilon

        System.out.println("computing average image, eigenvalues and eigenvectors");
        // compute average image, eigenvalues, and eigenvalues vectors
        cvCalcEigenObjects(nTrainFaces, // nObjects
                new PointerPointer(faceImgArr), // input
                // is
                // the
                // training
                // face
                // image
                // array
                new PointerPointer(eigenVectArr), // output
                // is
                // eigenvalues
                // vectors
                CV_EIGOBJ_NO_CALLBACK, // ioFlags
                0, // ioBufSize
                null, // userData
                calcLimit,pAvgTrainImg, //
                eigenValMat.data_fl()); // eigVals

        
        System.out.println("normalizing the eigenvectors");
        
        cvNormalize(eigenValMat,// The input
                // array:eigenvalues
                eigenValMat, // The output
                // array:eigenvalues
                1, // a-The minimum/maximum value of the output array or the
                // norm of output array
                0, // b-The maximum/minimum value of the output array
                CV_L1, // The normalization type
                null); // mask- The operation mask. Makes the function consider
        // and normalize only certain array elements
    }
}
