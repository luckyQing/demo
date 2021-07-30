package com.liyulin.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.liyulin.excel.easyexcel.listener.OrderListener;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        ExcelWriter excelWriter = null;
        try {
            WriteSheet writeSheet = EasyExcel.writerSheet("表1.商品").build();
            excelWriter = EasyExcel
                    .write(path + "商品导出test.xlsx", OrderDTO.class)
                    .build()
                    .write(null, writeSheet);

            int totalCount = 100;
            int pageSize = 256;
            for (int i = 0; i < totalCount; i++) {
                List<OrderDTO> data = new ArrayList<>(totalCount);
                for (int page = 0; page < pageSize; page++) {
                    data.add(new OrderDTO(i, "商品" + pageSize, i, new Date()));
                }
                excelWriter.write(data, writeSheet);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    public void importExcel() {
        String path = ExcelService.class.getResource("/").getPath();
        log.info("path={}", path);
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(path + "商品导入测试.xlsx", OrderDTO.class, new OrderListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0)
                    .autoTrim(true)
                    .headRowNumber(1)
                    .build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

}