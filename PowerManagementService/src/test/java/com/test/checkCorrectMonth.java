package com.test;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import util.CalcUtility;

class checkCorrectMonth {


	@Test
	void test() {
		
		//Getting month as a word according to the given integer
		String result = CalcUtility.getMonthForInt(3);
		
		assertEquals( "March", result);

	}

}
