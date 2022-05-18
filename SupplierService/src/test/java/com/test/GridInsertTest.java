package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Grid;

class GridInsertTest {

	@Test
	void test() {
		assertEquals(new Grid().insertGrid("Grid5","solar","Nonrenewable","200","Colombo","0765648927","4") , "Grid inserted successfully");
	}


}
