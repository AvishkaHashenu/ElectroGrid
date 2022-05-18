package com.test;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import com.Model.*;

class testInsertConsumption {

	@Test
	void test() {

		
		assertEquals( "Inserted successfully", new Power_Consumption().insertConsumption("370", "2023-01-04", "commercial", "111", "2"));

	}
}
