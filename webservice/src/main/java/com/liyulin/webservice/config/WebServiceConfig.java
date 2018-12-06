package com.liyulin.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liyulin.webservice.service.IShoppingService;
import com.liyulin.webservice.service.IUserService;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private IUserService userService;
	@Autowired
	private IShoppingService shoppingService;

	@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/*");
	}

	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, userService);
		endpoint.publish("/user");
		return endpoint;
	}
	
	@Bean
	public Endpoint shoppingEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, shoppingService);
		endpoint.publish("/shopping");
		return endpoint;
	}

}