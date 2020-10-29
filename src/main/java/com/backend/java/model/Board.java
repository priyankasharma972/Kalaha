package com.backend.java.model;

import com.backend.java.service.GameImplementation;

public class Board {
	private GameImplementation gameImplementation;
	private int pit;

	public int getPit() {
		return pit;
	}

	public void setPit(int pit) {
		this.pit = pit;
	}

	public GameImplementation getGameImplementation() {
		return gameImplementation;
	}

	public void setGameImplementation(GameImplementation gameImplementation) {
		this.gameImplementation = gameImplementation;
	}

}
