package com.liyulin.http.message.converter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class XmlMapperConfig {

	@Bean
	public XmlMapper buildXmlMapper() {
		/**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        
        XmlMapper xmlMapper = new XmlMapper();  
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.registerModule(simpleModule);
        xmlMapper.setDefaultUseWrapper(true);
        // 缩进输出
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
      
        return xmlMapper;
	}
	
}
