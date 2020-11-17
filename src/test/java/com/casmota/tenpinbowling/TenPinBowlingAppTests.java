package com.casmota.tenpinbowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.casmota.tenpinbowling.controller.TenPinBowlingCtrl;
import com.casmota.tenpinbowling.domain.BowlingScore;
import com.casmota.tenpinbowling.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.casmota.tenpinbowling.exception.DataFileException;
import com.casmota.tenpinbowling.domain.DataInputFile;

class TenPinBowlingAppTests {

	private ScoreOutputServiceImpl scoreOutputCtrl;
	private TenPinBowlingCtrl tenPinBowlingCtrl;

	private DataInputService dataInputService;
	private TenPinBowlingService tenPinBowlingService;
	private GameScoreService gameScoreService;

	@BeforeEach
	public void setUpStreams() {
		dataInputService = new DataInputServiceImpl();
		gameScoreService = new GameScoreServiceImpl();
		tenPinBowlingService = new TenPinBowlingServiceImpl(gameScoreService);

		scoreOutputCtrl = new ScoreOutputServiceImpl();
		tenPinBowlingCtrl = new TenPinBowlingCtrl(dataInputService, tenPinBowlingService);
	}

	@Test
	void IT001_SampleInput() throws IOException {

		DataInputFile dataInputFile = tenPinBowlingCtrl.importDataFile("src/test/resources/integration-tests/IT001_sample_input.txt");
		BowlingScore bowlingScore = tenPinBowlingCtrl.getBowlingScore(dataInputFile);
		String sb = scoreOutputCtrl.getBowlingScore(bowlingScore);

		String fileContent = readFileToString("src/test/resources/integration-tests/IT001_sample_input_BowlingScore.txt");
		assertEquals(fileContent, sb);
	}

	@Test
	void IT002_PerfectScore() throws IOException {

		DataInputFile dataInputFile = tenPinBowlingCtrl.importDataFile("src/test/resources/integration-tests/IT002_perfect_score.txt");
		BowlingScore bowlingScore = tenPinBowlingCtrl.getBowlingScore(dataInputFile);
		String sb = scoreOutputCtrl.getBowlingScore(bowlingScore);

		String fileContent = readFileToString("src/test/resources/integration-tests/IT002_perfect_score_BowlingScore.txt");
		assertEquals(fileContent, sb);
	}

	@Test
	void IT003_ZeroScore() throws IOException {

		DataInputFile dataInputFile = tenPinBowlingCtrl.importDataFile("src/test/resources/integration-tests/IT003_zero_score.txt");
		BowlingScore bowlingScore = tenPinBowlingCtrl.getBowlingScore(dataInputFile);
		String sb = scoreOutputCtrl.getBowlingScore(bowlingScore);

		String fileContent = readFileToString("src/test/resources/integration-tests/IT003_zero_score_BowlingScore.txt");
		assertEquals(fileContent, sb);
	}

	private static String readFileToString(String outputFile) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try (Stream<String> stream = Files.lines(Paths.get(outputFile))) {
			stream.forEach(line -> {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			});
		} catch (IOException e) {
			throw new DataFileException("Error reading the file. Please, confirm file path.");
		}

		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

}
