package com.casmota.tenpinbowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.casmota.tenpinbowling.exception.IncorrectFrameException;
import com.casmota.tenpinbowling.service.GameScoreServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.casmota.tenpinbowling.domain.Roll;

class GameScoreServiceValidTest {

	private GameScoreServiceImpl gameValidator;

	private static String LESS_FRAMES_MESSAGE = "Less than 10 frames in a bowler game. Please, check the data input file.";
	private static String MORE_FRAMES_MESSAGE = "More than 10 frames in a bowler game. Please, check the data input file.";

	@BeforeEach
	public void setUp() {
		gameValidator = new GameScoreServiceImpl();
	}

	@Test
	void validTestGameRolls_failRollsBelow() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(IncorrectFrameException.class, () -> {
			gameValidator.checkFrameRolls(rolls);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void validTestGameRolls_failRollsOver() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(IncorrectFrameException.class, () -> {
			gameValidator.checkFrameRolls(rolls);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void validTestGameMoreFrames_failRollsOver() {
		int currPos = 23;
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(IncorrectFrameException.class, () -> {
			gameValidator.checkFrameSuperiorLimit(rolls, currPos);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void validTestGameLessFrames_failRollsBelow() {
		List<Roll> rolls = new ArrayList<>();
		int currPos = 25;
		int position = 8;
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(IncorrectFrameException.class, () -> {
			gameValidator.checkFrameInferiorLimit(rolls, currPos, position);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

}
