package com.casmota.tenpinbowling.service;

import java.util.List;

import com.casmota.tenpinbowling.domain.Roll;
import com.casmota.tenpinbowling.domain.Frame;

public interface GameScoreService {

	/**
	 * Returns the computed score in a list of frames
	 *
	 * @param a roll list
	 * @return a frame list
	 */
	List<Frame> computeGameScore(List<Roll> rollList);


	/**
	 * Validate the frame's rolls
	 *
	 * @param a roll list
	 *
	 */
	void checkFrameRolls(List<Roll> rollList);

	/**
	 * Check the frame's superior limit
	 *
	 * @param a roll list
	 *
	 */
	void checkFrameSuperiorLimit(List<Roll> rollList, int currPos);

	/**
	 * Check the frame's inferior limit
	 *
	 * @param a roll list
	 *
	 */
	void checkFrameInferiorLimit(List<Roll> rollList, int currPos, int position);
}
