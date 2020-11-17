package com.casmota.tenpinbowling.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.casmota.tenpinbowling.exception.InvalidParameterException;
import com.casmota.tenpinbowling.exception.RollValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.casmota.tenpinbowling.exception.DataFileException;
import com.casmota.tenpinbowling.domain.Roll;

class DataInputServiceImplTest {

	@InjectMocks
	private DataInputServiceImpl dataInputService;

	@Mock
	private DataInputServiceImpl dataInputValidation;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		for (int i = 0; i <= 10; i++) {
			Mockito.when(dataInputValidation.checkRoll(Integer.toString(i))).thenReturn(Integer.toString(i));
		}

		InvalidParameterException ipe01 = new InvalidParameterException("InvalidParameterException (Unit test)");
		Mockito.when(dataInputValidation.checkRoll("-5")).thenThrow(ipe01);

		Mockito.when(dataInputValidation.checkRoll("F")).thenReturn("F");

		RollValueException ide = new RollValueException("RollValueException (Expected in the unit test)");
		Mockito.when(dataInputValidation.checkRoll("K")).thenThrow(ide);

		InvalidParameterException ipe02 = new InvalidParameterException("InvalidParameterException (Unit test)");
		Mockito.when(dataInputValidation.checkRoll("-9")).thenThrow(ipe02);
	}

	@Test
	void testFileReading_Sample() {
		List<Roll> firstBowler = new ArrayList<>();
		List<Roll> secondBowler = new ArrayList<>();

		firstBowler.add(new Roll("F"));
		firstBowler.add(new Roll("3"));
		firstBowler.add(new Roll("9"));
		firstBowler.add(new Roll("7"));
		firstBowler.add(new Roll("10"));

		secondBowler.add(new Roll("0"));
		secondBowler.add(new Roll("1"));
		secondBowler.add(new Roll("4"));
		secondBowler.add(new Roll("17"));
		secondBowler.add(new Roll("9"));
		secondBowler.add(new Roll("32"));
		secondBowler.add(new Roll("10"));

		Map<String, List<Roll>> rollMap = dataInputService.readDataFile("src/test/resources/integration-tests/IT001_sample_input.txt");

		assertTrue(rollMap.containsKey("Jeff"));
		assertTrue(rollMap.containsKey("John"));

		assertFalse(rollMap.get("Jeff").containsAll(firstBowler));
		assertFalse(rollMap.get("John").containsAll(secondBowler));
	}

	@Test
	void testFileReading_Perfect() {
		List<Roll> rolls = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			rolls.add(new Roll("10"));
		}

		Map<String, List<Roll>> rollMap = dataInputService.readDataFile("src/test/resources/integration-tests/IT002_perfect_score.txt");

		assertTrue(rollMap.containsKey("Jacob"));
		assertFalse(rollMap.get("Jacob").containsAll(rolls));
	}

	@Test
	void testFileReading_Zero() {
		List<Roll> rolls = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			rolls.add(new Roll("0"));
		}

		Map<String, List<Roll>> rollMap = dataInputService.readDataFile("src/test/resources/integration-tests/IT003_zero_score.txt");

		assertTrue(rollMap.containsKey("Lucas"));
		assertFalse(rollMap.get("Lucas").containsAll(rolls));
	}

	@Test
	@ExpectSystemExit
	void testFileReading_Invalid() {
		dataInputService.readDataFile("src/test/resources/unit-tests/UT003_datainvalid.txt");
	}

	@Test
	void testFileReading_WrongPath() {
		Assertions.assertThrows(DataFileException.class, () -> {
			dataInputService.readDataFile("/wrong/path");
		});
	}

	@Test
	void testFileReading_BadFormat() {
		Assertions.assertThrows(DataFileException.class, () -> {
			dataInputService.readDataFile("src/test/resources/unit-tests/UT001_badformat.txt");
		});
	}

	@Test
	void testFileReading_Empty() {
		Assertions.assertThrows(DataFileException.class, () -> {
			dataInputService.readDataFile("src/test/resources/unit-tests/UT002_empty.txt");
		});
	}

}
