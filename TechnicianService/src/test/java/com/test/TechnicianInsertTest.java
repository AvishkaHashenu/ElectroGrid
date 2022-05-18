package com.test;
import model.Technician;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TechnicianInsertTest {

	@Test
	void test() {
		assertEquals(new Technician().insertTechnician("Avishka", "Iswetiya", "avi@gmail.com", "0788459878","C", "100000", "0"),"Inserted successfully");
	}

}
