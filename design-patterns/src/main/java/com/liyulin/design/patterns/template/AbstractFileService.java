package com.liyulin.design.patterns.template;

import com.liyulin.design.patterns.template.third.IFileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板方法模式
 * <p>
 * 在一个方法中定义一个算法的骨架，而将一些步骤的实现延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中某些步骤的具体实现。
 *
 * @author liyulin
 * @date 2018年10月14日下午11:39:24
 */
@Slf4j
public abstract class AbstractFileService {

	public abstract IFileService getFileService();

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	public boolean upload(Object object) {
		validate();
		upload();
		insertLog();
		return true;
	}

	private boolean validate() {
		log.info("参数校验");
		return true;
	}

	private boolean upload() {
		return getFileService().upload();
	}

	private boolean insertLog() {
		log.info("将上传记录写入数据库");
		return true;
	}

}
