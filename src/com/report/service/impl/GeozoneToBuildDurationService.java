package com.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.report.service.MultilineProcessorI;

/*
 * Service class to get build duration per geozone
 */
public class GeozoneToBuildDurationService implements MultilineProcessorI {
	
	
	public void geozoneToAverageBuildCalculation(List<String> multiLine) {
		Map<String, Double[]> geozoneToAverageBuild = new HashMap<String, Double[]>();
		
		for(int i=0; i<multiLine.size(); i++) {
			String geozone = multiLine.get(i).split(",")[2];
			
			String buildDurationString = multiLine.get(i).split(",")[5].split("s")[0];
			Double buildDuration = Double.valueOf(buildDurationString);
			Double[] buildDurationArray = geozoneToAverageBuild.get(geozone);
			if(buildDurationArray != null) {
				double numberOfBuilds = buildDurationArray[1];
				double average = buildDurationArray[0];
				buildDurationArray[0] = ((average*numberOfBuilds) + buildDuration) / (numberOfBuilds+1);
				buildDurationArray[1] = numberOfBuilds+1;
			} else {
				buildDurationArray = new Double[2];
				buildDurationArray[0] = buildDuration;
				buildDurationArray[1] = 1.0;
			}
			geozoneToAverageBuild.put(geozone, buildDurationArray);
		}
		
		System.out.println("The average buildduration for each geozone:");
		for(String key : geozoneToAverageBuild.keySet()) {
			System.out.println(key + ": " + geozoneToAverageBuild.get(key)[0]);
		}
	}

	@Override
	public void processMultiline(List<String> multiLine) {
		geozoneToAverageBuildCalculation(multiLine);
		
	}
}
