package com.backend.java.service;

import com.backend.java.model.Player;

/**
 * Enumeration defined for the Player's different states
 */
enum Status {
	Player1, Player2, Player1Turn, Player2Turn, Player1WinsTheGame, Player2WinsTheGame, ItsADraw
}

public class GameImplementation {

	static final int numberOfPits = 14;
	static final int numberOfStonesPerPit = 6;

	private int[] board;
	private Status status;
	private String message;
	private Player player1;
	private Player player2;

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	/**
	 * @return an initialized game with board set(all 14 pits)
	 */
	public static GameImplementation init() {
		GameImplementation gameImplementation = new GameImplementation();
		int[] board = new int[numberOfPits];
		for (int i = 0; i < board.length; i++) {
			if (i == 6 || i == 13) {
				continue;
			}
			board[i] = numberOfStonesPerPit;
		}
		gameImplementation.setBoard(board);
		gameImplementation.setPlayer1(new Player(1, 6));
		gameImplementation.setPlayer2(new Player(2, 13));
		gameImplementation.setStatus(Status.Player1Turn);
		gameImplementation.setMessage("Let's Play KALAHA");
		return gameImplementation;
	}

	/**
	 * @param pit
	 * @return true if Player1 selects pit from 1-5 and if Player2 selects pit from
	 *         7-12
	 */
	public boolean isAValidMove(int pit) {
		if (status.equals(Status.Player1Turn)) {
			return pit >= 0 && pit <= 5;
		} else if (status.equals(Status.Player2Turn)) {
			return pit >= 7 && pit <= 12;
		}
		return false;
	}

	/**
	 * @param pit returns the updated game with status and is the main method used
	 *            by Controller
	 * 
	 */
	public void playTheGame(int pit) {
		if (this.status.equals(Status.Player1Turn)) {
			playTheGame(pit, player1);
		} else if (this.status.equals(Status.Player2Turn)) {
			playTheGame(pit, player2);
		}
	}

	/**
	 * @param pit
	 * @param player
	 *  This method handles all the rules of game
	 */
	private void playTheGame(int pit, Player player) {
		int stones = countStones(pit);
		int pitWhereLastStoneIsDroppped = pit;
		while (stones > 0) {
			int nextPit = checkNextPit(pitWhereLastStoneIsDroppped);
			if (isBigPit(nextPit) && !player.checkPlayerOwnKalahaBigPit(nextPit)) {
				pitWhereLastStoneIsDroppped = nextPit;
				continue;
			}
			board[nextPit] += 1;
			stones -= 1;
			pitWhereLastStoneIsDroppped = nextPit;
		}

		if (player.checkPlayerPit(pitWhereLastStoneIsDroppped) && board[pitWhereLastStoneIsDroppped] == 1) {
			board[player.getKalahaBigPit()] += board[pitWhereLastStoneIsDroppped]
					+ board[12 - pitWhereLastStoneIsDroppped];
			board[pitWhereLastStoneIsDroppped] = 0;
			board[12 - pitWhereLastStoneIsDroppped] = 0;
		}

		if (!player.checkPlayerOwnKalahaBigPit(pitWhereLastStoneIsDroppped))
			updatePlayerNextTurn();

		whoWinstheGame();
	}

	/**
	 * 
	 * This method updates the status based on current status
	 */
	private void updatePlayerNextTurn() {

		if (status.equals(Status.Player1Turn)) {
			status = Status.Player2Turn;
			return;
		}
		if (status.equals(Status.Player2Turn)) {
			status = Status.Player1Turn;
			return;
		}

	}

	/**
	 * @param pit
	 * @return true if the entered pit is either 6 or 13
	 */
	public boolean isBigPit(int pit) {
		return pit == 6 || pit == 13;
	}

	/**
	 * @param pit
	 * @return total stones for the entered pit
	 */
	private int countStones(int pit) {
		int stones = board[pit];
		board[pit] = 0;
		return stones;
	}

	/**
	 * @param pit the next pit in the series making sure its never more than 13
	 * @return
	 */
	private int checkNextPit(int pit) {
		pit += 1;
		return pit % numberOfPits;

	}

	/**
	 * 
	 * This method decides the win of a player or a draw state
	 */

	private void whoWinstheGame() {
		int sumOfStonesInPitA = 0;
		for (int i = 0; i < 6; i++) {
			sumOfStonesInPitA += board[i];
		}
		int sumOfStonesInPitB = 0;
		for (int i = 7; i < 13; i++) {
			sumOfStonesInPitB += board[i];
		}
		if (sumOfStonesInPitA == 0 || sumOfStonesInPitB == 0) {
			board[player1.getKalahaBigPit()] += sumOfStonesInPitA;
			board[player2.getKalahaBigPit()] += sumOfStonesInPitB;
			if (board[player1.getKalahaBigPit()] > board[player2.getKalahaBigPit()]) {
				status = Status.Player1WinsTheGame;
			} else if (board[player1.getKalahaBigPit()] < board[player2.getKalahaBigPit()]) {
				status = Status.Player2WinsTheGame;
			} else {
				status = Status.ItsADraw;
			}
			resetBoardAfterFinalResult();
		}

	}

	/**
	 * 
	 * This method would reset the board with all pits turned to 0 after one player
	 * wins
	 */
	private void resetBoardAfterFinalResult() {
		for (int i = 0; i < numberOfPits; i++) {
			if (i != 6 && i != 13)
				board[i] = 0;
		}
	}

}
