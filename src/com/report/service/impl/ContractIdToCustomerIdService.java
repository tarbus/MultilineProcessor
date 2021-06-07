package com.report.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.report.service.MultilineProcessorI;

/*
 * Service class to get unique customerIds for each contractId
 */
public class ContractIdToCustomerIdService implements MultilineProcessorI{
	

	public void uniqueCustomersForContractId(List<String> multiLine) {
		Map<String, Set<String>> contractToCustomer = new HashMap<String, Set<String>>();
		
		for(int i=0; i<multiLine.size(); i++) {
			String contractId = multiLine.get(i).split(",")[1];
			String customerId = multiLine.get(i).split(",")[0];
			Set<String> customersForContractId = contractToCustomer.getOrDefault(contractId, new HashSet<String>());
			customersForContractId.add(customerId);
			contractToCustomer.put(contractId, customersForContractId);
		}
		
		System.out.println("The number of unique customerId for each contractId:");
		for(String key : contractToCustomer.keySet()) {
			System.out.print(key + ": ");
			for(String customer : contractToCustomer.get(key)) {
				System.out.print(customer+ ",");
			}
			System.out.println();
		}
	}

	@Override
	public void processMultiline(List<String> multiLine) {
		uniqueCustomersForContractId(multiLine);
		
	}
}
