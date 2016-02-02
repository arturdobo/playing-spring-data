package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CompanyRepositoryCustom {
	List<Company> getCompaniesWithEmployeesCountMoreThan(int number);
}
