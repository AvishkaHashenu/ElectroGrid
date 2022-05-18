package com.test;


import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import util.CalcUtility;

class checkConsumptionCalculation {
	

	@Test
	void test() {
		
		int[] result = CalcUtility.calculateUsedUnits(399, "1166345077", "2022-07-04");
		//399 - 299 = 100
		
		assertEquals(101 , result[1]);

	}

}
