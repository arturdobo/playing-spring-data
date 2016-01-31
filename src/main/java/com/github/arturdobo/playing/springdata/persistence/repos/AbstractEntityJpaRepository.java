package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractEntityJpaRepository<T extends AbstractEntity> extends JpaRepository<T, Integer> {
}
