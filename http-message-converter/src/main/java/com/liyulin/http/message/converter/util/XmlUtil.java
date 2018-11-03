package com.liyulin.http.message.converter.util;

import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * xml工具类
 *
 * @author liyulin
 * @date 2018年11月3日上午9:23:24
 */
public final class XmlUtil {
	
	private static XmlMapper xmlMapper = null;
	
	/**
	 * java bean对象转xml
	 * @param bean
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String bean2Xml(Object bean) throws JsonProcessingException {
		if(null==xmlMapper) {
			xmlMapper = SpringUtil.getBean(XmlMapper.class);
		}
		Assert.isNull(xmlMapper, "xmlMapper获取失败");
		
		return xmlMapper.writeValueAsString(bean);
	}
	
}
