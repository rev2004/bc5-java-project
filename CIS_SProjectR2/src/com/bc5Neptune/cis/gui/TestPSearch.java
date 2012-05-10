package com.bc5Neptune.cis.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPSearch {
	PSearch S = new PSearch();
	@Test
	public void testSearch() {
		S.search();
	}

	@Test
	public void testSearchByID() {
		S.searchByID("PID000000001");
	}

	@Test
	public void testSearchByName() {
		S.searchByName("Doan Anh Thu");
	}

}
