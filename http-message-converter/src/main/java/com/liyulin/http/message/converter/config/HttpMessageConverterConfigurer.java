package com.liyulin.http.message.converter.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class HttpMessageConverterConfigurer implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(buildJsonHttpMessageConverter());
		converters.add(buildXmlHttpMessageConverter());
	}

	private FastJsonHttpMessageConverter buildJsonHttpMessageConverter() {
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty, 
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteBigDecimalAsPlain);

		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		return fastJsonHttpMessageConverter;
	}

	private MappingJackson2XmlHttpMessageConverter buildXmlHttpMessageConverter() {
		return new MappingJackson2XmlHttpMessageConverter(XmlMapperSingleton.getInstance());
	}

}