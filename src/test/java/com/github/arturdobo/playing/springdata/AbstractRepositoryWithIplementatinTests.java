package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.persistence.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

public class AbstractRepositoryWithIplementatinTests extends Base {

	@Before
	public void setUp() throws Exception {
		preparePersons();
	}

	@Test
	@Transactional
	public void testShouldCheckIsEntityIsAttached() throws Exception {
		Person person = personRepository.findFirstByOrderByFirstName();

		assertThat(personRepository.isAttached(person), is(true));
	}

	@Test
	@Transactional
	public void testShouldReattachEntity() throws Exception {
		Person person = personRepository.findFirstByOrderByFirstName();
		personRepository.detach(person);
		assertThat(personRepository.isAttached(person), is(false));

		person = personRepository.merge(person);
		assertThat(personRepository.isAttached(person), is(true));
	}


}
