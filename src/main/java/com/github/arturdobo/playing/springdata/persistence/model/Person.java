package com.github.arturdobo.playing.springdata.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractEntity{
	private String firstName;

	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
}
