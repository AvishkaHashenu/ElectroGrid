package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Supplier;


class SupplierInsertTest {

	@Test
	void test() {
		assertEquals(new Supplier().insertSupplier("Supplier6","Colombo","1951951956","0775566778") , "Supplier inserted successfully");
	}

}
