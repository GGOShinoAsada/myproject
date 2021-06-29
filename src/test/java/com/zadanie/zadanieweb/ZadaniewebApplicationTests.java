package com.zadanie.zadanieweb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.internal.SessionFactoryOptionsBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceUnit;

@SpringBootTest
@Primary
class ZadaniewebApplicationTests {
	@Autowired
	private SessionFactory factory;
    private void setFactory(){

	}
	@Test
	void contextLoads() {

		try {
			Session session = factory.openSession();
			Assert.assertNotNull(session);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
