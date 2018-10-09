package com.liyulin.design.patterns.flyweight;

public class EmployeeReportManager implements IReportManager {
	
	protected String tenantId=null;
	
	public EmployeeReportManager(String tenantId){
		this.tenantId=tenantId;
	}
	
	@Override
	public String createReport() {
		return "This is a employee report";
	}
	
}
