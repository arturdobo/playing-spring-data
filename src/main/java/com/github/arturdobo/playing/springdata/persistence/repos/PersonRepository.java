package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Person;

import java.util.List;

public interface PersonRepository extends AbstractEntityJpaRepository<Person> {
	long countByLastName(String lastName);

	int deleteByFirstName(String lastName);

	List<Person> removeByLastName(String lastName);

	List<Person> findAllByLastNameOrderByFirstNameDesc(String lastName);

	Person findOneByLastName(String lastName);

}
