package com.liyulin.webservice.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.liyulin.webservice.dto.CountryDto;
import com.liyulin.webservice.dto.Currency;

@Component
public class CountryRepository {
	
	private static final Map<String, CountryDto> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		CountryDto spain = new CountryDto();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		CountryDto poland = new CountryDto();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		CountryDto uk = new CountryDto();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);
	}

	public CountryDto findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}
	
}