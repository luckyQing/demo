package com.liyulin.http.message.converter.config;

import java.util.ArrayList;
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
		// 创建FastJson信息转换对象
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		// 创建Fastjosn对象并设定序列化规则
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteBigDecimalAsPlain);
		
		// 中文乱码解决方案
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);// 设定json格式且编码为UTF-8
		fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

		// 规则赋予转换对象
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		return fastJsonHttpMessageConverter;
	}
	
	private MappingJackson2XmlHttpMessageConverter buildXmlHttpMessageConverter() {
		return new MappingJackson2XmlHttpMessageConverter(XmlMapperSingleton.getInstance());
	}

}