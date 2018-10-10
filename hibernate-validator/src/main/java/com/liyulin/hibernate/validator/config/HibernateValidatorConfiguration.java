package com.liyulin.hibernate.validator.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.liyulin.hibernate.validator.valueextraction.ReqBodyExtractor;

/**
 * hibernate validator配置
 *
 * @author liyulin
 * @date 2018年10月10日下午11:55:14
 */
@Configuration
public class HibernateValidatorConfiguration {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(@Qualifier("validator") Validator validator) {
		MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
		/** 设置validator模式为快速失败返回 */
		postProcessor.setValidator(validator);
		return postProcessor;
	}

	@Bean("validator")
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
				.addValueExtractor(ReqBodyExtractor.DESCRIPTOR.getValueExtractor())
				// 配置hibernate Validator为快速失败返回模式
				.addProperty("hibernate.validator.fail_fast", "true")
				.buildValidatorFactory();

		return validatorFactory.getValidator();
	}

}
