package com.liyulin.excel.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.liyulin.excel.easyexcel.OrderDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderListener extends AnalysisEventListener<OrderDTO> {

    private static final int BATCH_COUNT = 5;
    private List<OrderDTO> list = new ArrayList<OrderDTO>();

    @Override
    public void invoke(OrderDTO data, AnalysisContext context) {
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        list.forEach(item -> log.info("{}", item));
    }

}