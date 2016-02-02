package com.github.arturdobo.playing.springdata;

import com.github.arturdobo.playing.springdata.persistence.model.Company;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class CustomCompanyRepositoryTests extends Base {
	@Before
	public void setUp() throws Exception {
		prepareCompanies();
	}

	@Test
	public void testShouldReturnCorrectNumberOfCompanies() throws Exception {
		List<Company> moreThan1 = companyRepository.getCompaniesWithEmployeesCountMoreThan(1);
		assertThat(moreThan1, hasSize(1));
	}
}
