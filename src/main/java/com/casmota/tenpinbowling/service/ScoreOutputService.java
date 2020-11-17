package com.casmota.tenpinbowling.service;

import com.casmota.tenpinbowling.domain.BowlingScore;

public interface ScoreOutputService {

	/**
	 * Returns the final game bowlingScore text
	 *
	 * @param the bowlingScore object
	 * @return the game bowlingScore text
	 */
	String getBowlingScore(BowlingScore bowlingScore);
}
