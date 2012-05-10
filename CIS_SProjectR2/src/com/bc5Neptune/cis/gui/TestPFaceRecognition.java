package com.bc5Neptune.cis.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPFaceRecognition {

	
	PFaceRecognition R = new PFaceRecognition();
	@Test
	public void testGetLength() {
		R.getLength();
	}

	@Test
	public void testUpdateListFace() {
		R.updateListFace();
	}

	@Test
	public void testLoadImage() {
		R.loadImage();
	}

}
