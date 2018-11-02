package com.liyulin.excel.easyexcel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * excel服务
 *
 * @author liyulin
 * @date 2018年10月27日下午3:17:01
 */
@Slf4j
public class ExcelService {

	private List<OrderDto> init() {
		int count = 100;
		List<OrderDto> data = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			data.add(new OrderDto(i, "商品" + i, i));
		}
		return data;
	}

	/**
	 * 导出excel
	 * 
	 * @throws FileNotFoundException
	 */
	public void exportExcel() throws FileNotFoundException {
		String path = getClass().getResource("/").getPath();
		log.info("path={}", path);
		List<OrderDto> data = init();
		try (OutputStream out = new FileOutputStream(path + "商品导出test.xlsx");) {
			ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
			Sheet sheet1 = new Sheet(1, 1, OrderDto.class);
			writer.write(data, sheet1);
			writer.finish();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// FIXME:buyNum转化异常
	public void importExcel() {
		String path = ExcelService.class.getResource("/").getPath();
		log.info("path={}", path);

		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(path + "商品导入测试.xlsx"));) {
			AnalysisEventListener<OrderDto> listener = new AnalysisEventListener<OrderDto>() {

				@Override
				public void invoke(OrderDto productDto, AnalysisContext context) {
					log.info("{}", productDto);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext context) {

				}

			};

			ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

			excelReader.read(new Sheet(1, 1, OrderDto.class));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
