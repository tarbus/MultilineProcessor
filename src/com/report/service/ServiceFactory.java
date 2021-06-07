package com.report.service;

import com.report.service.impl.ContractIdToCustomerIdService;
import com.report.service.impl.GeozoneToBuildDurationService;
import com.report.service.impl.GeozoneToCustomerIdService;

/*
 * Factory class to create objects based on the string input
 */
public class ServiceFactory {
	
	public MultilineProcessorI createProcessorService(String str) {
		if(str == null || str.isEmpty()) {
			return null;
		}
		if("ContractIdToCustomerId".equals(str)) {
			return new ContractIdToCustomerIdService();
		}
		if("GeozoneToBuildDuration".equals(str)) {
			return new GeozoneToBuildDurationService();
		}
		if("GeozoneToCustomerId".equals(str)) {
			return new GeozoneToCustomerIdService();
		}
		
		return null;
	}
}
