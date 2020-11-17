package com.casmota.tenpinbowling.domain;

import java.util.List;
import java.util.Map;

public class DataInputFile {

	Map<String, List<Roll>> rollsMap;

	public DataInputFile(Map<String, List<Roll>> rollsMap) {
		super();
		this.rollsMap = rollsMap;
	}

	public Map<String, List<Roll>> getRollsMap() {
		return rollsMap;
	}

	public void setRollsMap(Map<String, List<Roll>> rollsMap) {
		this.rollsMap = rollsMap;
	}

}
