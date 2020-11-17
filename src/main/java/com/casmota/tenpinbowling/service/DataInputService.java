package com.casmota.tenpinbowling.service;

import java.util.List;
import java.util.Map;

import com.casmota.tenpinbowling.domain.Roll;

public interface DataInputService {

	/**
	 * Reads the input data file and put into a Map object
	 * 
	 * @return A Map of bowlers and their rolls
	 */
	Map<String, List<Roll>> readDataFile(String path);

	/**
	 * Roll validation
	 *
	 * @return A correct value or throw a exception
	 */
	String checkRoll(String value);
}
