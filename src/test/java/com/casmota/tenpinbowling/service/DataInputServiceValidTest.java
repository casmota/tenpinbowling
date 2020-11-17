package com.casmota.tenpinbowling.service;

import com.casmota.tenpinbowling.exception.InvalidParameterException;
import com.casmota.tenpinbowling.exception.RollValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataInputServiceValidTest {

	private DataInputService validRoll;

	@BeforeEach
	public void setUp() {
		validRoll = new DataInputServiceImpl();
	}

	@Test
	void validTestRollValue_failValueIsBelow() {
		InvalidParameterException thrown = Assertions.assertThrows(InvalidParameterException.class, () -> {
			validRoll.checkRoll("-9");
		});
		assertTrue(thrown.getMessage().contains("range is from 0 to 10"));
	}

	@Test
	void validTestRollValue_failValueIsOver() {
		InvalidParameterException thrown = Assertions.assertThrows(InvalidParameterException.class, () -> {
			validRoll.checkRoll("35");
		});
		assertTrue(thrown.getMessage().contains("range is from 0 to 10"));
	}

	@Test
	void validTestRollValue_failWhenValueIsInvalid() {
		RollValueException thrown = Assertions.assertThrows(RollValueException.class, () -> {
			validRoll.checkRoll("K");
		});
		assertTrue(thrown.getMessage().contains("is invalid"));
	}

	@Test
	void validTestRollValue_ValidValues() {
		for (int i = 0; i <= 10; i++) {
			String num = Integer.toString(i);
			assertEquals(num, validRoll.checkRoll(num));
		}
		assertEquals("f", validRoll.checkRoll("f"));
		assertEquals("F", validRoll.checkRoll("F"));
	}

}
