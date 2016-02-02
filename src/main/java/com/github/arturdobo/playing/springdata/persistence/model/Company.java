package com.github.arturdobo.playing.springdata.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends AbstractEntity {
	private String name;

	@OneToOne
	private Address address;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId")
	private List<Person> employees;
}
