package com.liyulin.skills.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 文件处理工具类
 * 
 * @author liyulin
 * @date 2013/03/19
 */
public class FileUtil {

	/**
	 * 新建一个文件夹
	 * 
	 * @param fileName 文件夹名
	 */
	public static void createFile(String fileName) {
		File file = new File(fileName);
		if (file.mkdir()) {
			JOptionPane.showMessageDialog(null, "新建文件夹成功！");
		} else {
			JOptionPane.showMessageDialog(null, "新建文件夹失败！");
		}
	}

	/**
	 * 新建多个文件夹
	 * 
	 * @param fileName 文件名
	 */
	public static void createFiles(String fileName) {
		File file = new File(fileName);
		if (file.mkdirs()) {
			JOptionPane.showMessageDialog(null, "新建文件夹成功！");
		} else {
			JOptionPane.showMessageDialog(null, "新建文件夹失败！");
		}
	}

	/**
	 * 新建一个文件
	 * 
	 * @param fileName 文件名
	 */
	public static void createOneFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
			JOptionPane.showMessageDialog(null, "新建文件成功！");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "新建文件失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个文件
	 * 
	 * @param fileName 文件名
	 */
	public static void deleteOneFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.delete()) {
			JOptionPane.showMessageDialog(null, "删除文件成功！");
		} else {
			JOptionPane.showMessageDialog(null, "删除文件失败！");
		}
	}

	/**
	 * 修改文件(包括文件夹)名字
	 * 
	 * @param fileOldName 原文件名
	 * @param fileNewName 新文件名
	 */
	public static void reNameFile(String fileOldName, String fileNewName) {
		File file = new File(fileOldName);
		if (file.exists()) {
			file.renameTo(new File(fileNewName));
			JOptionPane.showMessageDialog(null, "修改文件夹名字成功！");
		} else {
			JOptionPane.showMessageDialog(null, "修改文件夹名字失败！");
		}
	}

	/**
	 * 得到目录下文件的所有绝对路径名
	 * 
	 * @param fileName 路径
	 */
	public static void getAbsolutePathFileName(String fileName) {
		File file = new File(fileName);
		File[] arrayList;
		if (file.exists()) {
			arrayList = file.listFiles();
			System.out.println(fileName + "下所有文件的绝对路劲如下：");
			for (File str : arrayList) {
				System.out.println("\t" + str);
			}
			JOptionPane.showMessageDialog(null, "成功！");
		} else {
			JOptionPane.showMessageDialog(null, "失败！");
		}
	}

	/**
	 * 得到目录下文件的所有相对路径名
	 * 
	 * @param fileName 目录
	 */
	public static void getPathFileName(String fileName) {
		File file = new File(fileName);
		String[] arrayList;
		if (file.exists()) {
			arrayList = file.list();
			System.out.println(fileName + "下所有文件的相对路劲如下：");
			for (String str : arrayList) {
				System.out.println("\t" + str);
			}
			JOptionPane.showMessageDialog(null, "成功！");
		} else {
			JOptionPane.showMessageDialog(null, "失败！");
		}
	}

	// 得到目录下全部文件的相对路径名（递归）
	static int num = 0;// 用于控制输出提示信息

	/**
	 * 获得路径下所有的文件名
	 * 
	 * @param fileName 路径
	 */
	public static void getAllPathFileName(String fileName) {
		File file = new File(fileName);
		if (num == 0 && file.isDirectory()) {// 输出提示信息
			System.out.println(fileName + "下所有文件的相对路劲如下：");
			num++;
		}
		/****************************** 输出所有文件名 *******************************/
		File[] arrayList;
		if (file.exists()) {
			arrayList = file.listFiles();
			for (File str : arrayList) {
				System.out.println("\t" + str);
				if (str.isDirectory()) {// 如果是一个目录，则递归
					getAllPathFileName(str.getPath());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "失败！");
		}
	}

	/**
	 * 返回所有目录
	 * 
	 * @param fileName 路径
	 */
	public static void getAllPathFile(String fileName) {
		File file = new File(fileName);
		List<File> allPathFile = new ArrayList<File>();// 返回所有目录
		File[] arrayList;
		if (file.exists()) {
			arrayList = file.listFiles();
			for (File str : arrayList) {
				allPathFile.add(str);
				if (str.isDirectory()) {// 如果是一个目录，则递归
					getAllPathFileName(str.getPath());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "失败！");
		}
	}

	/**
	 * 删除目录下所有文件（递归）
	 * 
	 * @param fileName 文件名
	 */
	public static void deleteAllFile(String fileName) {
		File file = new File(fileName);

		if (!file.isDirectory()) {// 如果文件夹里面没有文件，则删之
			file.delete();
		} else if (file.isDirectory()) {// 如果文件夹里面有文件，则遍历所有文件，并一一删之
			File[] fileList = file.listFiles();
			for (File deleteFile : fileList) {// 遍历所有文件，并一一删之
				if (!deleteFile.isDirectory()) {// 如果文件夹里面没有文件，则删之
					deleteFile.delete();
				} else if (deleteFile.isDirectory()) {// 递归循环，如果文件夹里面有文件，则遍历所有文件，并一一删之
					deleteAllFile(deleteFile + "");
					deleteFile.delete();// 最后删除检查的目录文件夹
				}
			}
			file.delete();// 删除欲删除的文件夹的根文件
		}
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "删除成功！");
		} else {
			JOptionPane.showMessageDialog(null, "删除失败！");
		}
	}

	/**
	 * 写文件操作, 操作的是字符流
	 * 
	 * @param fileName 文件名
	 * @param str 要写入的内容
	 * @throws IOException
	 */
	public static void write(String fileName, String str) throws IOException {
		OutputStream os = null;
		DataOutputStream out = null;
		try {
			os = new FileOutputStream(fileName, true);
			out = new DataOutputStream(os);
			out.writeBytes(str);
		} finally {
			if (os != null) {
				os.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 返回文件中的内容
	 * 
	 * @param fileName 文件名
	 * @return String
	 */
	public static String readFile(String fileName) {
		FileReader read = null;
		BufferedReader bf = null;
		int str;
		String array = "";
		try {
			read = new FileReader(new File(fileName));
			bf = new BufferedReader(read);
			// 逐行读取
			/******************************* 逐行读取 ****************************/
			// String str;
			// while ((str = bf.readLine()) != null) {
			// array += str;
			// }
			/******************************* 逐个字节读取 ****************************/
			while ((str = bf.read()) != -1) {
				array += String.valueOf((char) str);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	/**
	 * 复制一个文件
	 *     
	 * @param sourceFileName 源文件名
	 * @param destinationFileName 目标文件名
	 */
	public static void copyFile(String sourceFileName,
			String destinationFileName) {
		String source = FileUtil.readFile(sourceFileName);// 存储文件中原来的内容

		File file = new File(destinationFileName);
		FileWriter writer = null;
		BufferedWriter buff = null;
		try {
			writer = new FileWriter(file);
			buff = new BufferedWriter(writer);
			buff.write(source);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制一个文件夹 
	 *     1、标准文件（文本、图片等）
	 *     2、文件夹 
	 *     3、压缩文件（.zip、.rar、视频等）
	 *     
	 * @param sourceFileName 源文件名
	 * @param destinationFileName 目标文件名
	 */
	public static void copyFolder(String sourceFileName,
			String destinationFileName) {
		File fileSource = new File(sourceFileName);

		if (fileSource.isFile()) {// 如果是文件，不是目录，则直接复制
			FileUtil.copyFile(sourceFileName, destinationFileName + "/"
					+ fileSource.getName());
		} else if (fileSource.isDirectory()) {// 如果是一个目录，则先生成该目录，遍历所有文件，并一一复制
			File fileDestination = new File(destinationFileName + "/"
					+ fileSource.getName());
			fileDestination.mkdir();

			File[] fileList = fileSource.listFiles();
			for (File copyFile : fileList) {// 遍历所有文件，并一一复制
				copyFolder(copyFile.getAbsolutePath().replace('\\', '/'),
						fileDestination.getAbsolutePath().replace('\\', '/'));
			}
		}
	}

	public static void main(String[] args) {
		// CreateFile.createFile("D:/java文件夹");
		// CreateFile.createFiles("D:/java文件夹/java");
		// CreateFile.deleteFile("D:/java文件夹/java");
		// CreateFile.createOneFile("D:/java文件夹/java.txt");
		// CreateFile.deleteOneFile("D:/java文件夹/java.txt");
		// CreateFile.getAbsolutePathFileName("D:/java文件夹");
		// CreateFile.getPathFileName("D:/java文件夹/java");
		// CreateFile.deleteAllFile("D:/java文件夹");
		// CreateFile.reNameFile("D:/java文件夹/java", "D:/java文件夹/c++/1.txt");
		// CreateFile.reNameFile("D:/java文件夹/c++/1.txt");
		// CreateFile.write("D:/电影文件夹/人员.txt", "dd\ndd\nddd\n");
		// CreateFile.copyFile("D:/java文件夹/1.jpg", "D:/java文件夹/c++/1.jpg");
		// CreateFile.getAllPathFileName("D:/java文件夹");
		// System.out.println(CreateFile.readFile("D:/java文件夹/c++/a.txt"));
		// File file = new File("D:/java文件夹/c++/u.txt");
		// CreateFile.getAllPathFile("D:/java文件夹");
		// System.out.println("数组中的元素：");
		// for(File str:allPathFile){
		// System.out.println(str);
		// }
		// FileOperation.copyFolder("D:/java文件夹/豆.docx", "D:/工具");
	}
}