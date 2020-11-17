package com.casmota.tenpinbowling.service;

import com.casmota.tenpinbowling.domain.BowlingScore;
import org.springframework.stereotype.Service;

import com.casmota.tenpinbowling.domain.Frame;
import com.casmota.tenpinbowling.domain.Bowler;

@Service
public class ScoreOutputServiceImpl implements ScoreOutputService {

	private static String TITLE = "\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

	private int score = 0;

	@Override
	public String getBowlingScore(BowlingScore bowlingScore) {
		StringBuilder sb = new StringBuilder();

		sb.append(TITLE);

		bowlingScore.getBowlers().stream().forEach(bowler -> {
			sb.append(bowler.getName() + "\n");
			sb.append("Pinfalls");

			bowler.getFrames().stream().forEach(frame -> {
				if (frame.isStrike()) {
					sb.append(generateFrame(2, frame));//Strike
				} else if (frame.isSpare()) {
					sb.append(generateFrame(1, frame));//Spare
				} else {
					sb.append(generateFrame(0, frame));//Basic
				}
			});

			sb.append(getAmountScore(bowler));
		});

		return sb.toString();
	}


	private String generateFrame(int frameType, Frame frame) {
		if (frameType == 0){//Basic
			return "\t" + frame.getRolls().get(0).getValue() + "\t" + frame.getRolls().get(1).getValue();
		} else if (frameType == 1) {//Spare
			StringBuilder spare = new StringBuilder("\t");
			spare.append(frame.getRolls().get(0).getValue() + "\t" + "/");
			if (frame.getRolls().size() == 3) {
				spare.append("\t" + frame.getRolls().get(2).getValue());
			}
			return spare.toString();
		} else {//Strike
			StringBuilder strike = new StringBuilder();

			if (frame.getRolls().size() < 3) {
				strike.append("\t\t" + "X");
			} else {
				strike.append("\t" + "X");
				if (frame.getRolls().get(1).getQtPins() == 10) {
					strike.append("\t" + "X");
				} else {
					strike.append("\t" + frame.getRolls().get(1).getValue());
				}
				if (frame.getRolls().get(2).getQtPins() == 10) {
					strike.append("\t" + "X");
				} else {
					strike.append("\t" + frame.getRolls().get(2).getValue());
				}
			}
			return strike.toString();
		}
	}

	private String getAmountScore(Bowler bowler) {
		StringBuilder stringBuilder = new StringBuilder("\nScore");
		bowler.getFrames().stream().forEach(frame -> {
			incScore(frame.getScore());
			stringBuilder.append("\t\t" + getAmountScore());
		});
		setScore(0);
		return stringBuilder.append("\n").toString();
	}

	private void incScore(int score) {
		this.score += score;
	}

	private int getAmountScore() {
		return this.score;
	}

	private void setScore(int score) {
		this.score = score;
	}

}
