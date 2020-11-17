package com.casmota.tenpinbowling;

import com.casmota.tenpinbowling.controller.ScoreOutputCtrl;
import com.casmota.tenpinbowling.controller.TenPinBowlingCtrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.casmota.tenpinbowling.domain.DataInputFile;
import com.casmota.tenpinbowling.domain.BowlingScore;

@SpringBootApplication
public class TenPinBowlingApp implements CommandLineRunner {

	@Autowired
	TenPinBowlingCtrl tenPinBowlingCtrl;

	@Autowired
	ScoreOutputCtrl scoreOutputCtrl;

	private static Logger log = LoggerFactory.getLogger(TenPinBowlingApp.class);

	public static void main(String[] args) {
		SpringApplication.run(TenPinBowlingApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String inputFilePath = null;
		if (args.length > 0) {
			inputFilePath = args[0];
			DataInputFile dataInputFile = tenPinBowlingCtrl.importDataFile(inputFilePath);
			BowlingScore bowlingScore = tenPinBowlingCtrl.getBowlingScore(dataInputFile);
			scoreOutputCtrl.printBowlingScore(bowlingScore);
		} else {
			log.error("None argument encontered. Please, inform file path on application argument.");
		}
	}
}
