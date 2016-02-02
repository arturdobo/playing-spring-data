package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Address;
import com.github.arturdobo.playing.springdata.persistence.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends AbstractEntityJpaRepository<Person> {
	long countByLastName(String lastName);

	int deleteByFirstName(String lastName);

	List<Person> removeByLastName(String lastName);

	List<Person> findAllByLastNameOrderByFirstNameDesc(String lastName);

	Person findOneByLastName(String lastName);

	Optional<Person> findFirstByAddress_City(String city);

	Slice<Person> findTop4ByAddress_City(String city, Pageable pageable);

	List<Person> findAllByAddress(Address address);

	Person findFirstByOrderByFirstName();
}
