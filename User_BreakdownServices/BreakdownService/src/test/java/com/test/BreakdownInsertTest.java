package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Breakdown;

class BreakdownInsertTest {

	@Test
	void test() {
		assertEquals(new Breakdown().insertBreakdown("Pannala","Power down at 10 am","0773456123","1") , "Breakdown Reported Successfully!!!");
	}

}
