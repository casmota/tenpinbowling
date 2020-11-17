package com.casmota.tenpinbowling.domain;

public class Roll {
	private static final String FOUL = "F";
	private String value;
	private int qtPins;

	public Roll(String value) {
		super();
		this.value = value;
		if (FOUL.equalsIgnoreCase(value)) {
			this.qtPins = 0;
		} else {
			this.qtPins = Integer.parseInt(value);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getQtPins() {
		return qtPins;
	}

	public void setQtPins(int qtPins) {
		this.qtPins = qtPins;
	}
}
