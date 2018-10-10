package com.liyulin.hibernate.validator.valueextraction;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.ValueExtractor;

import org.hibernate.validator.internal.engine.valueextraction.ValueExtractorDescriptor;

import com.liyulin.hibernate.validator.config.HibernateValidatorConfiguration;
import com.liyulin.hibernate.validator.entity.Req;

/**
 * {@link Req}泛型参数T校验生效
 * 
 * <p>
 * 在{@link HibernateValidatorConfiguration#validator}中配置
 *
 * @author liyulin
 * @date 2018年9月28日下午11:06:41
 */
public class ReqBodyExtractor implements ValueExtractor<Req<@ExtractedValue ?>> {

	public static final ValueExtractorDescriptor DESCRIPTOR = new ValueExtractorDescriptor(new ReqBodyExtractor());

	private ReqBodyExtractor() {
	}

	@Override
	public void extractValues(Req<?> originalValue, ValueReceiver receiver) {
		receiver.value(null, originalValue.getBody());
	}

}
