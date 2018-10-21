package com.liyulin.skills.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * stream实例
 *
 * @author liyulin
 * @date 2018年10月15日下午11:15:55
 */
@Slf4j
public class StreamTest {

	@Test
	public void testRemoveSuffixWithRela() {
		String[] tableNames = { "t_user", "t_user_rela", "t_product", "t_product_rela" };
		String relaSuffix = "rela";
		tableNames = Stream.of(tableNames).filter(name -> (!name.endsWith(relaSuffix))).toArray(String[]::new);
		Stream.of(tableNames).forEach(log::info);
	}
	
	@Test
	public void testParallelStream() {
		int count = 100;
		List<Integer> data = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			data.add(i);
		}

		// fork/join并行处理
		data.parallelStream().forEach(item -> {
			log.info("{}", item);
		});
	}

	@Test
	public void test() {
		List<StockSkuDTO> stockSkuDTOList = new ArrayList<>();
		for (long i = 1; i <= 200; i++) {
			for (long j = 1; j <= 3; j++) {
				StockSkuDTO stockSkuDTO = new StockSkuDTO();
				stockSkuDTO.setSkuId(i);
				stockSkuDTO.setBuyNum(j);
				stockSkuDTOList.add(stockSkuDTO);
			}
		}
		// 分组
		ConcurrentMap<Long, ConcurrentLinkedQueue<StockSkuDTO>> group = new ConcurrentHashMap<>();
		stockSkuDTOList.parallelStream().forEach(item -> {
			ConcurrentLinkedQueue<StockSkuDTO> queue = group.get(item.getSkuId());
			
			if (null == queue) {
				queue = new ConcurrentLinkedQueue<>();
				Queue<StockSkuDTO> preQueue = group.putIfAbsent(item.getSkuId(), queue);
				if (null != preQueue) {
					queue = group.get(item.getSkuId());
				}
			}
			queue.add(item);
		});
		// 并行遍历分组
		Collection<ConcurrentLinkedQueue<StockSkuDTO>> groupQueueList = group.values();
		groupQueueList.parallelStream().forEach(groupQueue->{
			StockSkuDTO head = groupQueue.peek();
			log.info("skuId={}", head.getSkuId());
			groupQueue.forEach(sku->{
				log.info("sku={}", sku);
			});
		});
		
		long reduceStockCount = stockSkuDTOList.parallelStream().mapToLong(item->{return item.getBuyNum();}).sum();
		log.info("reduceStockCount={}", reduceStockCount);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class StockSkuDTO {

		/** 表t_product_stock_deduce_info f_id */
		private Long stockDeduceInfoId;
		/** 主订单id */
		private Long mainBillId;
		/** 商品id */
		private Long productId;
		/** 商品sku id */
		private Long skuId;
		/** 商品购买数量 */
		private Long buyNum;
		/** 操作时间 */
		private Date time;

	}
	
}
