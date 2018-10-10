package com.liyulin.hibernate.validator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 *
 * @author liyulin
 * @version 1.0 2018年4月7日 下午6:36:24
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("test").genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(false).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.liyulin")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("cc", "https://swagger.io", "1634753825@qq.com");
		return new ApiInfoBuilder().title("API接口文档").description("swagger2").contact(contact).version("1.0").build();
	}

}
