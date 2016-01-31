package com.github.arturdobo.playing.springdata.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractEntity {
	private String street;

	private String zipCode;

	private String city;
}
