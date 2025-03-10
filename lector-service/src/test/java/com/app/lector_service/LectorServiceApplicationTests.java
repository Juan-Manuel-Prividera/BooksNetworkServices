package com.app.lector_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.lectorservice.LectorServiceApplication;

import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = LectorServiceApplication.class)
@ActiveProfiles("test")
class LectorServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
