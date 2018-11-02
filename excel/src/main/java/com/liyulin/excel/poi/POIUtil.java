package com.liyulin.excel.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

/**
 * 用POI读写Excel文件
 * 
 * @author liyulin
 * @version 1.0 2013-10-27 下午5:41:47
 */
@Slf4j
public class POIUtil {

	/**
	 * 获取excel表格单元的值
	 * 
	 * @param cell
	 * @return String
	 */
	private static String getCellValue(Cell cell) {
		String object = null;
		switch (cell.getCellTypeEnum()) {
		case BLANK:
			object = "";
			break;
		case BOOLEAN:
			object = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			object = String.valueOf(cell.getErrorCellValue());
			break;
		case FORMULA:
			object = String.valueOf(cell.getCellFormula());
			break;
		case NUMERIC:
			object = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			object = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return object;
	}

	/**
	 * 读取excel表格
	 * 
	 * @param filename
	 *            excel文件名
	 */
	public static void readExcel(String filename) {
		Workbook wb = null;
		try {
			// xls文件
			if (filename.toLowerCase().endsWith("xls")) {
				wb = new HSSFWorkbook(new FileInputStream(filename));
			}

			// xlsx文件
			if (filename.toLowerCase().endsWith("xlsx")) {
				wb = new XSSFWorkbook(new FileInputStream(filename));
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		Sheet sheet = wb.getSheetAt(0);// 第1张表
		Row row = null;
		for (int i = 0, size = sheet.getLastRowNum(); i < size; i++) {
			row = sheet.getRow(i);// 第i行
			row.forEach(cell -> {
				log.info("{}", getCellValue(cell));
			});
		}
	}

	/**
	 * 写excel2003文件
	 * 
	 * @param filename
	 *            excel2003文件名
	 */
	public static void writeExcel(String filename, List<StudentDto> dataSet) {
		try (Workbook wb = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(filename);) {
			// 创建表格
			Sheet sheet = wb.createSheet("学生信息01");
			sheet.setColumnWidth(3, 3766);// 设置第4列的列宽
			// sheet.autoSizeColumn(column);
			/*--------------------------------------- 表头 ---------------------------------------*/
			// 创建行
			Row rowHead = sheet.createRow(0);
			// 设置行高
			rowHead.setHeightInPoints(30);

			// 生成一个样式
			CellStyle style1 = wb.createCellStyle();
			// 设置这些样式
			style1.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
			style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style1.setBorderBottom(BorderStyle.THIN);
			style1.setBorderLeft(BorderStyle.THIN);
			style1.setBorderRight(BorderStyle.THIN);
			style1.setBorderTop(BorderStyle.THIN);
			style1.setAlignment(HorizontalAlignment.CENTER);// x方向对齐样式
			style1.setVerticalAlignment(VerticalAlignment.CENTER);// y方向对齐样式
			// 生成一个字体
			Font font = wb.createFont();
			font.setColor(HSSFColorPredefined.VIOLET.getIndex());
			font.setFontHeightInPoints((short) 12);
			font.setBold(true);
			// 把字体应用到当前的样式
			style1.setFont(font);

			// 创建单元格
			Cell cell1 = rowHead.createCell(0);
			cell1.setCellStyle(style1);
			cell1.setCellValue("姓名");

			Cell cell2 = rowHead.createCell(1);
			cell2.setCellStyle(style1);
			cell2.setCellValue("年龄");

			Cell cell3 = rowHead.createCell(2);
			cell3.setCellStyle(style1);
			cell3.setCellValue("性别");

			Cell cell4 = rowHead.createCell(3);
			cell4.setCellStyle(style1);
			cell4.setCellValue("家庭地址");

			// 生成并设置另一个样式
			CellStyle style2 = wb.createCellStyle();
			style2.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);
			style2.setBorderRight(BorderStyle.THIN);
			style2.setBorderTop(BorderStyle.THIN);
			style2.setAlignment(HorizontalAlignment.CENTER);// x方向对齐样式
			style2.setVerticalAlignment(VerticalAlignment.CENTER);// y方向对齐样式
			// 生成另一个字体
			Font font2 = wb.createFont();
			font.setBold(true);
			// 把字体应用到当前的样式
			style2.setFont(font2);

			for (int i = 0, size = dataSet.size(); i < size; i++) {
				StudentDto student = dataSet.get(i);
				// 创建行
				Row rowBody = sheet.createRow(1 + i);
				// 设置行高
				rowBody.setHeightInPoints(20);
				Cell cell = rowBody.createCell(0);
				cell.setCellStyle(style2);
				cell.setCellValue(student.getName());

				cell = rowBody.createCell(1);
				cell.setCellStyle(style2);
				cell.setCellValue(student.getAge());

				cell = rowBody.createCell(2);
				cell.setCellStyle(style2);
				cell.setCellValue(student.getSex());

				cell = rowBody.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellValue(student.getAddress());
			}

			// 写到输出流
			wb.write(fos);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) throws IOException {
		List<StudentDto> dataSet = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			StudentDto student = new StudentDto("张三" + i, i, "男", "深圳南山区" + i);
			dataSet.add(student);
		}
		File file = new File("d:/test/poi1.xlsx");
		if (!file.exists()) {
			file.createNewFile();
		}
		POIUtil.writeExcel(file.getPath(), dataSet);

		// readExcel("E:/cx.xls");
	}
}