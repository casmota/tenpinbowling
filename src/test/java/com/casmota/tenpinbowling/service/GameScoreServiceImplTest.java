package com.casmota.tenpinbowling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.casmota.tenpinbowling.domain.Frame;
import com.casmota.tenpinbowling.domain.Roll;

class GameScoreServiceImplTest {

	@InjectMocks
	private GameScoreServiceImpl scoreService;

	@Mock
	private GameScoreServiceImpl gameValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testScoreSample() {
		List<Roll> rolls = generateSampleRolls();
		List<Frame> frames = scoreService.computeGameScore(rolls);

		assertEquals(20, frames.get(0).getScore());
		assertEquals(19, frames.get(1).getScore());
		assertEquals(9, frames.get(2).getScore());
		assertEquals(18, frames.get(3).getScore());
		assertEquals(8, frames.get(4).getScore());
		assertEquals(10, frames.get(5).getScore());
		assertEquals(6, frames.get(6).getScore());
		assertEquals(30, frames.get(7).getScore());
		assertEquals(28, frames.get(8).getScore());
		assertEquals(19, frames.get(9).getScore());
	}

	@Test
	void testPerfectScore() {
		List<Roll> rolls = generatePerfectRolls();
		List<Frame> frames = scoreService.computeGameScore(rolls);

		for (Frame frame : frames) {
			assertEquals(frame.getScore(), 30);
		}
	}

	@Test
	void testZeroScore() {
		List<Roll> rolls = generateZeroRolls();
		List<Frame> frames = scoreService.computeGameScore(rolls);
		for (Frame frame : frames) {
			assertEquals(frame.getScore(), 0);
		}
	}

	@Test
	void testZeroScoreSpare() {
		List<Roll> rolls = generateZeroRollsSpareLastFrame();
		List<Frame> frames = scoreService.computeGameScore(rolls);
		for (int i = 0; i < 9; i++) {
			assertEquals(0, frames.get(i).getScore());
		}
		assertEquals(12, frames.get(9).getScore());
	}

	private List<Roll> generateSampleRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("7");
		rolls.add(roll);
		roll = new Roll("3");
		rolls.add(roll);
		roll = new Roll("9");
		rolls.add(roll);
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("2");
		rolls.add(roll);
		roll = new Roll("F");
		rolls.add(roll);
		roll = new Roll("6");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("1");
		rolls.add(roll);
		return rolls;
	}

	private List<Roll> generatePerfectRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("10");
		for (int i = 0; i < 12; i++) {
			rolls.add(roll);
		}
		return rolls;
	}

	private List<Roll> generateZeroRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 20; i++) {
			rolls.add(roll);
		}
		return rolls;
	}

	private List<Roll> generateZeroRollsSpareLastFrame() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 18; i++) {
			rolls.add(roll);
		}
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("2");
		rolls.add(roll);

		return rolls;
	}

}
