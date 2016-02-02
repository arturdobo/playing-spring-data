package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.arturdobo.playing.springdata.persistence.CompanySpecifications.hasAddress;
import static com.github.arturdobo.playing.springdata.persistence.CompanySpecifications.isNationalityOf;
import static com.github.arturdobo.playing.springdata.persistence.CompanySpecifications.isPolish;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static org.springframework.data.jpa.domain.Specifications.where;

public class CompanyRepositoryTests extends Base {
	@Before
	public void setUp() throws Exception {
		prepareCompanies();
	}

	@Test
	public void testShouldReturnOnlyCompaniesOfGivenNationality() throws Exception {
		List<Company> pl = companyRepository.findAll(isPolish());
		assertThat(pl, hasSize(2));

		List<Company> de = companyRepository.findAll(isNationalityOf(Company.Nationality.DE));
		assertThat(de, hasSize(1));

		List<Company> fr = companyRepository.findAll(isNationalityOf(Company.Nationality.FR));
		assertThat(fr, empty());
	}
	
	@Test
	public void testShoulndReturnOnlyPlCompaniesWithAddress() throws Exception {
		List<Company> companies = companyRepository.findAll(where(isPolish()).and(hasAddress()));

		assertThat(companies, empty());
	}
}
