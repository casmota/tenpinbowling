package com.casmota.tenpinbowling.controller;

import com.casmota.tenpinbowling.domain.BowlingScore;
import com.casmota.tenpinbowling.service.ScoreOutputService;
import com.casmota.tenpinbowling.service.ScoreOutputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ScoreOutputCtrl {

	@Autowired
	ScoreOutputService scoreOutputService;

	public ScoreOutputCtrl() {}

	//Needed for integration tests
	public ScoreOutputCtrl(ScoreOutputService sos) {
		super();

		if (scoreOutputService == null)
			scoreOutputService = sos;
	}

	public void printBowlingScore(BowlingScore bowlingScore) {
		System.out.print(scoreOutputService.getBowlingScore(bowlingScore));
	}

}
