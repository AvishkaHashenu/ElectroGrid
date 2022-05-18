package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.User;

class UserInsertTest {

	@Test
	void test() {
		assertEquals(new User().insertUser("Avishka","123456","1166345086","Kur45","983459843V","0713485876","reset123","Adm"), "User Account Created Successfully!!!");
	}

}
