package com.liyulin.excel;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.liyulin.excel.easyexcel.ExcelService;

public class ExcelServiceTest {

	@Test
	public void testExport() throws FileNotFoundException {
		ExcelService excelService = new ExcelService();
		excelService.exportExcel();
	}
	
	@Test
	public void testImportExcel() {
		ExcelService excelService = new ExcelService();
		excelService.importExcel();
	}
	
}
