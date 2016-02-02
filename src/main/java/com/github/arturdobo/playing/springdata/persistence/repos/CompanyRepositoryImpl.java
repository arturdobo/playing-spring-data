package com.github.arturdobo.playing.springdata.persistence.repos;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import com.github.arturdobo.playing.springdata.persistence.model.Company_;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom {
	@Autowired
	private EntityManager em;

	@Override
	public List<Company> getCompaniesWithEmployeesCountMoreThan(int number) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> query = cb.createQuery(Company.class);
		Root<Company> from = query.from(Company.class);

		query.select(from)
		     .where(cb.greaterThan(cb.size(from.get(Company_.employees)), number));

		return em.createQuery(query).getResultList();
	}
}
