package com.test;
import model.Payment_Info;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentInfoInsertTest {

	@Test
	void test() {
		assertEquals(new Payment_Info().insertPayment("3", "001", "8854345", "2017-06-15"),"Inserted successfully");
	}

}
