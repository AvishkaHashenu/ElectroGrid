package com.test;

import model.Payment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentInsertTest {

	@Test
	void test() {
		assertEquals(new Payment().insertPayment("0010", "001","0","cash", "20200120", "25","User","100", "0"), "Inserted successfully");
		
	}

}
