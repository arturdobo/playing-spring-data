package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Company;

public interface CompanyRepository extends AbstractEntityJpaRepository<Company>, CompanyRepositoryCustom {
}
