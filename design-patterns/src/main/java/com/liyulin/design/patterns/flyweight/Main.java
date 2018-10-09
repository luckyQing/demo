package com.liyulin.design.patterns.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		ReportManagerFactory rmf = new ReportManagerFactory();
		IReportManager rm = rmf.getFinancialReportManager("A");
		log.info(rm.createReport());
	}
	
}
