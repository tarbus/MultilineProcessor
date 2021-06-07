package com.report.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.report.service.MultilineProcessorI;

/*
 * Service class to get unique customerIds per geozone
 */
public class GeozoneToCustomerIdService implements MultilineProcessorI {
	
	
	public void uniqueCustomersForGeozone(List<String> multiLine) {
		Map<String, Set<String>> geozoneToCustomer = new HashMap<String, Set<String>>();
		
		for(int i=0; i<multiLine.size(); i++) {
			String customerId = multiLine.get(i).split(",")[0];
			String geozone = multiLine.get(i).split(",")[2];
			Set<String> customersForGeozone = geozoneToCustomer.getOrDefault(geozone, new HashSet<String>());
			customersForGeozone.add(customerId);
			geozoneToCustomer.put(geozone, customersForGeozone);
		}
		
		System.out.println("The number of unique customerId for each geozone:");
		for(String key : geozoneToCustomer.keySet()) {
			System.out.print(key + ": ");
			for(String customer : geozoneToCustomer.get(key)) {
				System.out.print(customer+ ",");
			}
			System.out.println();
		}
	}

	@Override
	public void processMultiline(List<String> multiLine) {
		uniqueCustomersForGeozone(multiLine);
	}
}
