package com.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.report.service.MultilineProcessorI;
import com.report.service.ServiceFactory;

/*
 * Creates report of the queries for Multiline string rows from Input file
 */
public class CreateReport {
	
	public static void main(String a[]) {
		
		File directory = new File("./");
		List<String> multiLine = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(directory.getCanonicalPath() + "/Input"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        multiLine.add(line);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ServiceFactory serviceFactory = new ServiceFactory();
		
		MultilineProcessorI contractIdToCustomerIdService = serviceFactory.createProcessorService("ContractIdToCustomerId");
		contractIdToCustomerIdService.processMultiline(multiLine);
		
		MultilineProcessorI geozoneToCustomerIdService = serviceFactory.createProcessorService("GeozoneToBuildDuration");
		geozoneToCustomerIdService.processMultiline(multiLine);

		MultilineProcessorI geozoneToBuildDurationService = serviceFactory.createProcessorService("GeozoneToCustomerId");
		geozoneToBuildDurationService.processMultiline(multiLine);
		
	}

}
