package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Password;

class UserLoginTest {

	@Test
	void test() {
		assertEquals(new Password().loginUser("Kumar","123456") , "Welcome Kumar");
	}

}
