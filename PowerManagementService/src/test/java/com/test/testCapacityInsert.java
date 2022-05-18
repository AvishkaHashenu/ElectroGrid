package com.test;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import com.Model.*;

class testCapacityInsert {

	@Test
	void test() {

		
		assertEquals( "Inserted successfully", new Power_Capacity().insertCapacity("2", "2023-02-04", "eco", "111"));

	}

}
