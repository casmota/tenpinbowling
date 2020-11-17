package com.casmota.tenpinbowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casmota.tenpinbowling.domain.BowlingScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.casmota.tenpinbowling.exception.IncorrectFrameException;
import com.casmota.tenpinbowling.domain.DataInputFile;
import com.casmota.tenpinbowling.domain.Frame;
import com.casmota.tenpinbowling.domain.Roll;

class TenPinBowlingServiceImplTest {

	@InjectMocks
	private TenPinBowlingServiceImpl gameService;

	@Mock
	private GameScoreService gameScoreService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		List<Roll> rolls = generateRolls();
		List<Frame> frames = new ArrayList<>();
		Mockito.when(gameScoreService.computeGameScore(rolls)).thenReturn(frames);

		List<Roll> emptyRolls = new ArrayList<>();

		IncorrectFrameException ige = new IncorrectFrameException("IncorrectFrameException (Expected in the unit test)");
		Mockito.when(gameScoreService.computeGameScore(emptyRolls)).thenThrow(ige);
	}

	@Test
	public void createTestBowlingScore() {
		List<Roll> rolls = generateRolls();
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Jeff", rolls);
		DataInputFile dataInputFile = new DataInputFile(rollsMap);

		BowlingScore bowlingScore = gameService.createBowlingScore(dataInputFile);
		assertEquals("Jeff", bowlingScore.getBowlers().get(0).getName());
	}

	@Test
	@ExpectSystemExit
	void createTestBowlingScore_Empty() {
		List<Roll> rolls = new ArrayList<>();
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Carlo", rolls);
		DataInputFile dataInputFile = new DataInputFile(rollsMap);
		gameService.createBowlingScore(dataInputFile);
	}

	private List<Roll> generateRolls() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Roll roll = new Roll(Integer.toString(i));
			rolls.add(roll);
		}
		return rolls;
	}

}
