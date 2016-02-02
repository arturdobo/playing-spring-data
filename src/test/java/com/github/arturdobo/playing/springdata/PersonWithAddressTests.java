package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.persistence.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.junit.Assert.assertThat;

public class PersonWithAddressTests extends Base {
	@Before
	public void setUp() throws Exception {
		preparePersons();
	}

	@Test
	public void testShouldReturnPersonFromCity() throws Exception {
		Optional<Person> person = personRepository.findFirstByAddress_City(address1.getCity());


		assertThat(person.isPresent(), is(true));
		assertThat(person.get().getFirstName(), equalTo(person1.getFirstName()));
	}
	
	@Test
	public void testShouldReturnEmptyWhenThereIsNoSuchPerson() throws Exception {
		assertThat(personRepository.findFirstByAddress_City("asfd").isPresent(), is(false));
	}
	
	@Test
	public void testShouldReturnSliceOfFirst4Persons() throws Exception {
		Slice<Person> slice = personRepository.findTop4ByAddress_City(address3.getCity(), new PageRequest(0, 2));

		assertThat(slice.getContent(), hasItems(hasProperty("firstName", equalTo(person2.getFirstName())),
		                                        hasProperty("firstName", equalTo(person3.getFirstName()))));
		assertThat(slice.getNumber(), equalTo(0));
		assertThat(slice.hasNext(), is(true));
	}
}
