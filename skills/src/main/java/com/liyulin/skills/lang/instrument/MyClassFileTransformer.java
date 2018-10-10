package com.liyulin.skills.lang.instrument;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import lombok.extern.slf4j.Slf4j;

/**
 * 将class写入文件保存<br>
 * 1、META-INF/MANIFEST.MF中最后面添加“Premain-Class：com.agent.MyClassFileTransformer”<br>
 * 2、“-javaagent:E:/agent.jar”
 * 
 * @author luckytom
 * @version 1.0 2017年12月28日 下午10:55:15
 */
@Slf4j
public class MyClassFileTransformer implements ClassFileTransformer {

	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new MyClassFileTransformer());
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if (className.startsWith("代理模式")) {
			// 既不是java也不是sun开头的
			// 导出代码
			String fileName = className + ".class";
			writeClazzToFile("E:/agent_exported/", fileName, classfileBuffer);
		}
		return classfileBuffer;
	}

	/**
	 * @param dirPath 目录以/结尾，且必须存在
	 * @param fileName
	 * @param data
	 */
	private void writeClazzToFile(String dirPath, String fileName, byte[] data) {
		try {
			File file = new File(dirPath + fileName);
			if (file.isDirectory()) {
				if (!file.exists()) {
					file.mkdirs();
				}
			} else if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			try (FileOutputStream fos = new FileOutputStream(file);) {
				fos.write(data);
			}
		} catch (Exception e) {
			log.error("writeClazzToFile Exception", e);
		}
	}
	
}