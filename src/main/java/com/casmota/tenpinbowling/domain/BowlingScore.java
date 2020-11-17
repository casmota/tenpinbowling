package com.casmota.tenpinbowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingScore {

	List<Bowler> bowlers;

	public BowlingScore() {
		super();
		this.bowlers = new ArrayList<>();
	}

	public BowlingScore(List<Bowler> bowlers) {
		super();
		this.bowlers = bowlers;
	}

	public List<Bowler> getBowlers() {
		return bowlers;
	}

	public void setBowlers(List<Bowler> bowlers) {
		this.bowlers = bowlers;
	}

}
