package com.bc5Neptune.cis.config;
/**
*
* @author enclaveit
*/
public class Config {
	/** path of data image */
	public static final String PATH_DATA_IMAGE = "../CIS_SProjectR2/data/image/";

	/** path of image tmp */
	public static final String PATH_TMP = "../CIS_SProjectR2/data/tmp/";

	/** path of xml database */
	public static final String PATH_DATA_TRAIN = "../CIS_SProjectR2/data/facexml/";

	/** path of training face */
	public static final String PATH_IMAGE_TRAIN = "../CIS_SProjectR2/data/pathTrainingFace.txt";

	/** path of test face */
	public static final String PATH_IMAGE_TEST = "../CIS_SProjectR2/data/checkFace.txt";

	/** path of detect face */
	public static final String PATH_TMP_DETECT = "../CIS_SProjectR2/data/tmp/image001.jpg";

	/** path of tmp recognize face */
	public static final String PATH_TMP_RECOG = "../CIS_SProjectR2/data/tmp/face.jpg";

	/** path of recognize face */
	public static final String PATH_IMAGE_RECOG = "../CIS_SProjectR2/data/tmp/face.pgm";

	/** path of detect front face */
	public static final String PATH_DETECT_FD1 = "../CIS_SProjectR2/data/haarcascade_frontalface_alt2.xml";

	/** path of detect left-right face */
	public static final String PATH_DETECT_FD2 = "../CIS_SProjectR2/data/haarcascade_profileface.xml";

	/** path of temp folder for save image from database */
	public static final String PATH_TMP_DB = "../CIS_SProjectR2/data/tmp/imagedb/";

	/** path of file which store path of image in temp folder above */
	public static final String PATH_TMP_IMG = "../CIS_SProjectR2/data/tmp/imagedb/pathTmpImage.txt";
}
