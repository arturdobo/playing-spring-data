package com.github.arturdobo.playing.springdata.persistence;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import com.github.arturdobo.playing.springdata.persistence.model.Company_;
import org.springframework.data.jpa.domain.Specification;

public final class CompanySpecifications {
	private CompanySpecifications() {
	}

	public static Specification<Company> isPolish() {
		return isNationalityOf(Company.Nationality.PL);
	}

	public static Specification<Company> isNationalityOf(Company.Nationality nationality) {
		return (root, query, cb) -> cb.equal(root.get(Company_.nationality), nationality);
	}

	public static Specification<Company> hasAddress() {
		return (root, query, cb) -> cb.isNotNull(root.get(Company_.address));
	}
}
