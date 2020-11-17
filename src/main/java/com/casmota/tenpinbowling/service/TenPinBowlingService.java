package com.casmota.tenpinbowling.service;

import com.casmota.tenpinbowling.domain.BowlingScore;
import com.casmota.tenpinbowling.domain.DataInputFile;

public interface TenPinBowlingService {

	/**
	 * Returns the game BowlingScore
	 *
	 * @param the file data object
	 * @return the BowlingScore object
	 */
	BowlingScore createBowlingScore(DataInputFile dataInputFile);

}
