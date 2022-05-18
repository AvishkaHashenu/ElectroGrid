package com.test;

import model.TechniciansAttendance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TechnicianAttendanceTest {

	@Test
	void test() {
		assertEquals(new TechniciansAttendance().insertTechnicianAttendance("001", "1", "0"),"Inserted successfully" );
	}

}
