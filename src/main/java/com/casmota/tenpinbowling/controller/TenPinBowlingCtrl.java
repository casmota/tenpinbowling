package com.casmota.tenpinbowling.controller;

import com.casmota.tenpinbowling.domain.BowlingScore;
import com.casmota.tenpinbowling.domain.Roll;
import com.casmota.tenpinbowling.exception.DataFileException;
import com.casmota.tenpinbowling.service.DataInputService;
import com.casmota.tenpinbowling.service.DataInputServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.casmota.tenpinbowling.domain.DataInputFile;
import com.casmota.tenpinbowling.service.TenPinBowlingService;
import com.casmota.tenpinbowling.service.TenPinBowlingServiceImpl;

import java.util.List;
import java.util.Map;

@Controller
public class TenPinBowlingCtrl {

	@Autowired
	TenPinBowlingService tenPinBowlingService;

	@Autowired
	DataInputService dataInputService;

	Logger log = LoggerFactory.getLogger(DataInputServiceImpl.class);

	public TenPinBowlingCtrl() {}

	//Needed for integration tests
	public TenPinBowlingCtrl(DataInputService dis, TenPinBowlingService tps) {
		super();

		if (dataInputService == null)
			dataInputService = dis;

		if (tenPinBowlingService == null)
			tenPinBowlingService = tps;
	}

	public DataInputFile importDataFile(String filePath) {
		DataInputFile dataInputFile = null;
		try {
			Map<String, List<Roll>> rollsMap = dataInputService.readDataFile(filePath);
			dataInputFile = new DataInputFile(rollsMap);
		} catch (DataFileException e) {
			log.error(e.getMessage());
			System.exit(0);
		}
		return dataInputFile;
	}

	public BowlingScore getBowlingScore(DataInputFile dataInputFile) {
		BowlingScore bowlingScore = tenPinBowlingService.createBowlingScore(dataInputFile);
		return bowlingScore;
	}

}
