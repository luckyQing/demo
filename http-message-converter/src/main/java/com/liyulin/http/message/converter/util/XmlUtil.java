package com.liyulin.http.message.converter.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.liyulin.http.message.converter.config.XmlMapperSingleton;

/**
 * xml工具类
 *
 * @author liyulin
 * @date 2018年11月3日上午9:23:24
 */
public final class XmlUtil {

	/**
	 * java object对象转xml
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String bean2Xml(Object object) throws JsonProcessingException {
		return XmlMapperSingleton.getInstance().writeValueAsString(object);
	}
	
	/**
	 * xml转java object
	 * @param content
	 * @param typeReference
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T xml2Bean(String content, TypeReference<T> typeReference) throws JsonParseException, JsonMappingException, IOException  {
		return XmlMapperSingleton.getInstance().readValue(content, typeReference);
	}

}
