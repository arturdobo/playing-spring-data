package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.config.JpaConfiguration;
import com.github.arturdobo.playing.springdata.persistence.model.Address;
import com.github.arturdobo.playing.springdata.persistence.model.Company;
import com.github.arturdobo.playing.springdata.persistence.model.Person;
import com.github.arturdobo.playing.springdata.persistence.repos.CompanyRepository;
import com.github.arturdobo.playing.springdata.persistence.repos.PersonRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class)
@Ignore
public class Base {
	private static Company comp1;
	private static Company comp2;
	private static Company comp3;
	protected static Address address1;
	protected static Address address2;
	protected static Address address3;

	protected static Person person1;
	protected static Person person2;
	protected static Person person3;
	protected static Person person4;

	private static boolean run;

	@BeforeClass
	public static void before() {
		address1 = new Address("Jakas 1", "00-111", "Jakies 1");
		address2 = new Address("Jakas 2", "00-222", "Jakies 2");
		address3 = new Address("Jakas 3", "00-222", "Jakies 2");

		person1 = new Person("Jan", "Nowak", address1);
		person2 = new Person("Kazimierz", "Kowalski", address2);
		person3 = new Person("Henryk", "Dąbrowski", address3);
		person4 = new Person("Janusz", "Kowalski", address3);

		comp1 = new Company("Comp1", null, Collections.singletonList(person1), Company.Nationality.PL);
		comp2 = new Company("Comp2", null, Arrays.asList(person2, person3), Company.Nationality.PL);
		comp3 = new Company("Comp3", null, Collections.singletonList(person4), Company.Nationality.DE);
	}

	@Autowired
	protected PersonRepository personRepository;

	@Autowired
	protected CompanyRepository companyRepository;

	@After
	public void cleanUp() {
		companyRepository.deleteAll();
		assertThat(companyRepository.findAll(), empty());

		personRepository.deleteAll();
		assertThat(personRepository.findAll(), empty());
	}

	protected void preparePersons() {
		List<Person> persons = personRepository.save(Arrays.asList(person1, person2, person3, person4));
		assertThat(persons, everyItem(hasProperty("id", not(equalTo(0)))));
	}

	protected void prepareCompanies() {
		List<Company> companies = companyRepository.save(Arrays.asList(comp1, comp2, comp3));
		assertThat(companies, everyItem(hasProperty("id", not(equalTo(0)))));
	}

}
