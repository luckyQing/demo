package com.liyulin.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.liyulin.webservice.dto.CountryReqBody;
import com.liyulin.webservice.dto.CountryRespBody;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://www.liyulin.com/webservice";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryReqBody")
	@ResponsePayload
	public CountryRespBody getCountry(@RequestPayload CountryReqBody request) {
		CountryRespBody response = new CountryRespBody();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}