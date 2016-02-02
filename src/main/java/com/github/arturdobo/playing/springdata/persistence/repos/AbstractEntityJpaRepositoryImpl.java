package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.AbstractEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class AbstractEntityJpaRepositoryImpl<T extends AbstractEntity> extends SimpleJpaRepository<T, Integer>
		implements AbstractEntityJpaRepository<T> {

	private final EntityManager em;

	public AbstractEntityJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

	public AbstractEntityJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
	}

	@Override
	public boolean isAttached(T entity) {
		return em.contains(entity);
	}

	@Override
	public void detach(T entity) {
		em.detach(entity);
	}

	@Override
	public T merge(T entity) {
		return em.merge(entity);
	}
}
