package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CompanyRepository extends AbstractEntityJpaRepository<Company>, CompanyRepositoryCustom {
	List<Company> findAll(Specification<Company> specification);
}
