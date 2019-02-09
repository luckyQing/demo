package com.liyulin.webservice.cfx.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liyulin.webservice.cfx.service.IShoppingService;
import com.liyulin.webservice.cfx.service.IUserService;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;
	@Autowired
	private IUserService userService;
	@Autowired
	private IShoppingService shoppingService;

	/**
	 * <h2>修复bug：</h2>
	 * <pre>
	 * Bug Description:
	 * Parameter 1 of constructor in org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration required a bean of type 'org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath' that could not be found.
	 * The following candidates were found but could not be injected:
	 * - Bean method 'dispatcherServletRegistration' in 'DispatcherServletAutoConfiguration.DispatcherServletRegistrationConfiguration' not loaded because DispatcherServlet Registration found servlet registration bean dispatcherServletRegistration
	 * Action:
	 * Consider revisiting the entries above or defining a bean of type 'org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath' in your configuration.
	 * </pre>
	 * 
	 * <h2>解决方案：</h2>
	 * <p>
	 * https://stackoverflow.com/questions/28024606/how-to-use-cxf-soap-servlet-aside-spring-rest-servlet
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServletRegistration() {
		return new ServletRegistrationBean<>(new CXFServlet(),"/*");
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