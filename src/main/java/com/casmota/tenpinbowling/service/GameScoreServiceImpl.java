package com.casmota.tenpinbowling.service;

import java.util.ArrayList;
import java.util.List;

import com.casmota.tenpinbowling.domain.Roll;
import com.casmota.tenpinbowling.exception.IncorrectFrameException;
import org.springframework.stereotype.Service;
import com.casmota.tenpinbowling.domain.Frame;

@Service
public class GameScoreServiceImpl implements GameScoreService {
		
	public GameScoreServiceImpl() {}

	@Override
	public List<Frame> computeGameScore(List<Roll> rollList) {
		checkFrameRolls(rollList);

		List<Frame> frames = new ArrayList<>();

		int currPos = 0;
		for (int p = 0; p < 10; p++) {
			if (isStrike(rollList, currPos)) {
				frames.add(generateFrame(2, rollList, currPos, p));
				if (p == 10 - 1) {
					currPos += 2;
				} else {
					currPos++;
				}
			} else if (isSpare(rollList, currPos)) {
				frames.add(generateFrame(1, rollList, currPos, p));
				currPos += 2;
			} else {
				frames.add(generateFrame(0, rollList, currPos,0));
				currPos += 2;
			}
			checkFrameInferiorLimit(rollList, currPos, p);
		}

		checkFrameSuperiorLimit(rollList, currPos);

		return frames;
	}


	private Frame generateFrame(int frameType, List<Roll> rolls, int currPos, int position) {
		if (frameType == 0) { //Basic Frame
			Frame frame = new Frame();
			frame.getRolls().add(rolls.get(currPos));
			frame.getRolls().add(rolls.get(currPos + 1));
			frame.setScore(rolls.get(currPos).getQtPins() + rolls.get(currPos + 1).getQtPins());
			frame.setSpare(false);
			frame.setStrike(false);
			return frame;
		} if (frameType == 1) {//Spare Frame
			Frame frame = generateFrame(0, rolls, currPos, 0);
			if (position == 10 - 1) {
				frame.getRolls().add(rolls.get(currPos + 2));
			}
			frame.setScore(10 + rolls.get(currPos + 2).getQtPins());
			frame.setSpare(true);
			frame.setStrike(false);
			return frame;
		} else {//Strike Frame
			Roll roll01 = rolls.get(currPos);
			Roll roll02 = rolls.get(currPos + 1);
			Roll roll03 = rolls.get(currPos + 2);

			Frame frame = new Frame();
			frame.getRolls().add(roll01);
			if (position == 10 - 1) {
				frame.getRolls().add(roll02);
				frame.getRolls().add(roll03);
			}
			frame.setScore(10 + roll02.getQtPins() + roll03.getQtPins());
			frame.setSpare(false);
			frame.setStrike(true);
			return frame;
		}

	}

	private boolean isStrike(List<Roll> rolls, int currPos) {
		return rolls.get(currPos).getQtPins() == 10;
	}

	private boolean isSpare(List<Roll> rolls, int currPos) {
		return rolls.get(currPos).getQtPins() + rolls.get(currPos + 1).getQtPins() == 10;
	}

	@Override
	public void checkFrameRolls(List<Roll> listRoll) {
		if (listRoll.size() < 11) {
			throw new IncorrectFrameException("Less than 10 frames in a bowler game. Please, check the data input file.");
		}

		if (listRoll.size() > 21) {
			throw new IncorrectFrameException("More than 10 frames in a bowler game. Please, check the data input file.");
		}
	}

	@Override
	public void checkFrameSuperiorLimit(List<Roll> listRoll, int currPos) {
		if (listRoll.size() - 1 > currPos) {
			throw new IncorrectFrameException("More than 10 frames in a bowler game. Please, check the data input file.");
		}
	}

	@Override
	public void checkFrameInferiorLimit(List<Roll> listRoll, int currPos, int position) {
		if (listRoll.size() - 1 <= currPos && position < 10 - 1) {
			throw new IncorrectFrameException("Less than 10 frames in a bowler game. Please, check the data input file.");
		}
	}

}
