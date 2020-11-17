package com.casmota.tenpinbowling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.casmota.tenpinbowling.domain.BowlingScore;
import com.casmota.tenpinbowling.domain.Roll;
import com.casmota.tenpinbowling.exception.IncorrectFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casmota.tenpinbowling.domain.DataInputFile;
import com.casmota.tenpinbowling.domain.Bowler;

@Service
public class TenPinBowlingServiceImpl implements TenPinBowlingService {

	@Autowired
	private GameScoreService gameScoreService;

	public TenPinBowlingServiceImpl() {}

	//Needed for integration tests
	public TenPinBowlingServiceImpl(GameScoreService gss) {
		super();

		if (gameScoreService == null)
			gameScoreService = gss;
	}

	Logger log = LoggerFactory.getLogger(TenPinBowlingServiceImpl.class);

	@Override
	public BowlingScore createBowlingScore(DataInputFile dataInputFile) {
		List<Bowler> bowlers = new ArrayList<>();
		Map<String, List<Roll>> rollsMap = dataInputFile.getRollsMap();

		try {
			for (Map.Entry<String, List<Roll>> entry : rollsMap.entrySet()) {
				Bowler bowler = new Bowler(entry.getKey());
				bowler.setFrames(gameScoreService.computeGameScore(entry.getValue()));
				bowlers.add(bowler);
			}
		} catch (IncorrectFrameException e) {
			log.error(e.getMessage());
			System.exit(0);
		}

		BowlingScore bowlingScore = new BowlingScore();
		bowlingScore.setBowlers(bowlers);

		return bowlingScore;
	}

}
