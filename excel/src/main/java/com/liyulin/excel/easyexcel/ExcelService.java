package com.liyulin.excel.easyexcel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;

import lombok.extern.slf4j.Slf4j;

/**
 * excel服务
 *
 * @author liyulin
 * @date 2018年10月27日下午3:17:01
 */
@Slf4j
public class ExcelService {

	/**
	 * 导出excel
	 * 
	 * @throws FileNotFoundException
	 */
	public void exportExcel() throws FileNotFoundException {
		String path = getClass().getResource("/").getPath();
		log.info("path={}", path);
		try (OutputStream out = new FileOutputStream(path + "商品导出test.xlsx");) {
			ExcelWriter writer = EasyExcelFactory.getWriter(out);
			Sheet sheet1 = new Sheet(1, 1, OrderDto.class);

			int totalCount = 100;
			int pageSize = 256;
			for (int i = 0; i < totalCount; i++) {
				List<OrderDto> data = new ArrayList<>(totalCount);
				for (int page = 0; page < pageSize; page++) {
					data.add(new OrderDto(i, "商品" + pageSize, i));
				}
				writer.write(data, sheet1);
			}
			writer.finish();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void importExcel() {
		String path = ExcelService.class.getResource("/").getPath();
		log.info("path={}", path);

		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(path + "商品导入测试.xlsx"));) {
			AnalysisEventListener<OrderDto> listener = new AnalysisEventListener<OrderDto>() {

				@Override
				public void invoke(OrderDto orderDto, AnalysisContext context) {
					log.info("{}", orderDto);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext context) {

				}

			};

			EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, OrderDto.class), listener);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}