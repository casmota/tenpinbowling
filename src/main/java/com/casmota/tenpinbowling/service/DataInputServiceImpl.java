package com.casmota.tenpinbowling.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.casmota.tenpinbowling.domain.Roll;
import com.casmota.tenpinbowling.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.casmota.tenpinbowling.exception.DataFileException;
import com.casmota.tenpinbowling.exception.RollValueException;

@Service
public class DataInputServiceImpl implements DataInputService {

	private final Logger log = LoggerFactory.getLogger(DataInputServiceImpl.class);
	private static final String FOUL = "f";

	public DataInputServiceImpl() {}

	public Map<String, List<Roll>> readDataFile(String path) {

		Map<String, List<Roll>> rollsData = new HashMap<>();

		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(line -> {
				String[] rowValue = line.split("\t");
				List<Roll> rolls = rollsData.get(rowValue[0]) != null ? rollsData.get(rowValue[0]) : new ArrayList<>();
				String value = checkRoll(rowValue[1]);
				rolls.add(new Roll(value));
				rollsData.put(rowValue[0], rolls);
			});
		} catch (RollValueException e) {
			log.error(e.getMessage());
			System.exit(0);
		} catch (InvalidParameterException e) {
			log.error(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			throw new DataFileException("Error reading the file. Please, confirm the file path.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DataFileException("Error reading the file. Please, confirm the file format.");
		}

		if (rollsData.isEmpty()) {
			throw new DataFileException("Data file is empty. Please, verify file content.");
		}

		return rollsData;
	}

	public String checkRoll(String pins) {
		try {
			int intPins = Integer.parseInt(pins);
			if (intPins < 0 || intPins > 10) {
				throw new InvalidParameterException("Number pins '" + pins
						+ "' is incorrect. Correct range is from 0 to 10.  Please, verify input file format.");
			}
		} catch (NumberFormatException e) {
			if (!FOUL.equalsIgnoreCase(pins)) {
				throw new RollValueException(
						"Number pins '" + pins + "' is invalid. Please, verify input file format.");
			}
		}
		return pins;
	}

}
