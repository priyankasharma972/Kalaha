package com.backend.java.model;

public class Player {
	private int id;
	private int kalahaBigPit;

	public Player() {

	}

	public Player(int id, int kalahaBigPit) {
		this.id = id;
		this.kalahaBigPit = kalahaBigPit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKalahaBigPit() {
		return kalahaBigPit;
	}

	public void setKalahaBigPit(int kalahaBigPit) {
		this.kalahaBigPit = kalahaBigPit;
	}

	/**
	 * @param pit
	 * @return true if pit entered matches the Kalaha Pit
	 */
	public boolean checkPlayerOwnKalahaBigPit(int pit) {
		return kalahaBigPit == pit;
	}

	/**
	 * @param pit
	 * @return true for Player 1(id=1) if the entered pit is from 0 to 5 else
	 *         condition checks for Player2 and returns true if entered pit is from
	 *         7 to 12 Kalaha Pits 6 and 13 are not taken into consideration as
	 *         those pits cannot be selected
	 */
	public boolean checkPlayerPit(int pit) {
		if (id == 1)
			return 0 <= pit && pit <= 5;
		else
			return 7 <= pit && pit <= 12;
	}
}
