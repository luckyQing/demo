package com.liyulin.design.patterns.template;

import com.liyulin.design.patterns.template.third.IFileService;

import lombok.extern.slf4j.Slf4j;

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
