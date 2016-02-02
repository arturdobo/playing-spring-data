package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.persistence.model.Person;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.junit.Assert.assertThat;

public class PersonTests extends Base {

	@Test
	public void testShouldReturnAllPersons() throws Exception {
		assertThat(personRepository.findAll(), hasSize(4));
	}

	@Test
	public void testShouldCountAllPersons() throws Exception {
		assertThat(personRepository.count(), equalTo(4L));
	}
	
	@Test
	public void testShouldCountByLastName() throws Exception {
		assertThat(personRepository.countByLastName(person2.getLastName()), equalTo(2L));
	}
	
	@Test
	@Transactional
	public void testShouldDeleteByFirstNameAndReturnDeletedPersonId() throws Exception {
		int deletedId = personRepository.deleteByFirstName(person1.getFirstName());

		assertThat(deletedId, not(equalTo(0)));
		assertThat(personRepository.count(), equalTo(3L));
	}
	
	@Test
	@Transactional
	public void testShouldDeleteByLastNameAndReturnDeletedPersons() throws Exception {
		List<Person> deleted = personRepository.removeByLastName(person2.getLastName());

		assertThat(deleted, hasSize(2));
		assertThat(deleted, hasItems(hasProperty("firstName", equalTo(person2.getFirstName())),
		                             hasProperty("firstName", equalTo(person4.getFirstName()))));
		assertThat(personRepository.count(), equalTo(2L));
	}
	
	@Test
	public void testShouldReturnPersonsSortedByFirstName() throws Exception {
		List<Person> sorted = personRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "firstName")));

		assertThat(sorted, IsIterableContainingInOrder.contains(hasProperty("firstName", equalTo(person3.getFirstName())),
		                                                        hasProperty("firstName", equalTo(person1.getFirstName())),
		                                                        hasProperty("firstName", equalTo(person4.getFirstName())),
		                                                        hasProperty("firstName", equalTo(person2.getFirstName()))));
	}
	
	@Test
	public void testShouldReturnFilteredByLastNameAndSortedByFirstNameDesc() throws Exception {
		List<Person> sorted = personRepository.findAllByLastNameOrderByFirstNameDesc(person2.getLastName());

		assertThat(sorted, IsIterableContainingInOrder.contains(hasProperty("firstName", equalTo(person2.getFirstName())),
		                                                        hasProperty("firstName", equalTo(person4.getFirstName()))));
	}

	@Test
	public void testShouldReturnPersonsPaged() throws Exception {
		Page<Person> page1 = personRepository.findAll(new PageRequest(0, 2));
		Page<Person> page2 = personRepository.findAll(new PageRequest(1, 2));

		assertThat(page1.getTotalElements(), equalTo(4L));
		assertThat(page2.getTotalElements(), equalTo(4L));

		assertThat(page1.getTotalPages(), equalTo(2));
		assertThat(page2.getTotalPages(), equalTo(2));

		assertThat(page1.getContent(), hasItems(hasProperty("firstName", equalTo(person1.getFirstName())),
		                                        hasProperty("firstName", equalTo(person2.getFirstName()))));
		assertThat(page2.getContent(), hasItems(hasProperty("firstName", equalTo(person3.getFirstName())),
		                                        hasProperty("firstName", equalTo(person4.getFirstName()))));
		
		Pageable nextPageTo1 = page1.nextPageable();

		assertThat(nextPageTo1.getOffset(), equalTo(2));
		assertThat(nextPageTo1.getPageNumber(), equalTo(1));
		assertThat(nextPageTo1.getPageSize(), equalTo(2));
	}
	
	@Test(expected = IncorrectResultSizeDataAccessException.class)
	public void testShouldThrowsExceptionIfThereIsMoreThan1SuchAsLastName() throws Exception {
		personRepository.findOneByLastName(person2.getLastName());
	}
	
}
