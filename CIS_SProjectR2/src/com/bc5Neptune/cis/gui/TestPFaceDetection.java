package com.bc5Neptune.cis.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPFaceDetection {
	PFaceDetection D = new PFaceDetection();
	@Test
	public void testLoadImageToLabelEvent() {
		D.loadImageToLabelEvent();
	}

	@Test
	public void testChoseFolder() {
		D.choseFolder();
	}

	@Test
	public void testRemoveE2() {
		D.removeE2();
	}

	@Test
	public void testRemoveE3() {
		D.removeE3();
	}

	@Test
	public void testRemoveE4() {
		D.removeE4();
	}

	@Test
	public void testRemoveE6() {
		D.removeE6();
	}

	@Test
	public void testRemoveE7() {
		D.removeE7();
	}

	@Test
	public void testRemoveE8() {
		D.removeE8();
	}


	@Test
	public void testCheckInput() {
		D.checkInput();
	}

	@Test
	public void testCheckCombo() {
		D.checkCombo();
	}

}
