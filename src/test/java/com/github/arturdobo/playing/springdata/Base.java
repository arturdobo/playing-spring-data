package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.config.JpaConfiguration;
import com.github.arturdobo.playing.springdata.persistence.model.Address;
import com.github.arturdobo.playing.springdata.persistence.model.Person;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
public class Base {
	protected static Address address1;
	protected static Address address2;
	protected static Address address3;
	protected static Address address4;

	protected static Person person1;
	protected static Person person2;
	protected static Person person3;
	protected static Person person4;

	@BeforeClass
	public static void beforeClass() {
		address1 = new Address("Jakas 1", "00-111", "Jakies 1");
		address2 = new Address("Jakas 2", "00-222", "Jakies 2");
		address3 = new Address("Jakas 3", "00-222", "Jakies 2");
		address4 = address3;

		person1 = new Person("Jan", "Nowak", address1);
		person2 = new Person("Kazimierz", "Kowalski", address2);
		person3 = new Person("Henryk", "Dąbrowski", address3);
		person4 = new Person("Janusz", "Kowalski", address4);
	}
}
