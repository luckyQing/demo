package com.liyulin.skills.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 注解处理器
 *
 * @author liyulin
 * @date 2019年3月3日下午12:57:37
 */
@Slf4j
@AutoService(Processor.class)
public class MyCacheProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 业务逻辑处理
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyCache.class);
	    for (Element element : elements) {
	    	log.info("element={}", element);
	    }
		return true;
	}

}