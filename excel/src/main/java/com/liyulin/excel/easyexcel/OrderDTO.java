package com.liyulin.excel.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	@ExcelProperty(value = "订单id", index = 0)
	private long id;

	@ExcelProperty(value = "商品名称", index = 1)
	private String name;

	@ExcelProperty(value = "购买数量", index = 2)
	private double buyNum;

}