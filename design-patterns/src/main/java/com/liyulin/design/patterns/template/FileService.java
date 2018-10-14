package com.liyulin.design.patterns.template;

import com.liyulin.design.patterns.template.third.IFileService;
import com.liyulin.design.patterns.template.third.impl.FastDFSService;

public class FileService extends AbstractFileService {

	@Override
	public IFileService getFileService() {
		return new FastDFSService();
	}

}
