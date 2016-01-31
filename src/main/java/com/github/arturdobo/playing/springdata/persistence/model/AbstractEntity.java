package com.github.arturdobo.playing.springdata.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {
	@Id
	@GeneratedValue
	protected int id;
}
