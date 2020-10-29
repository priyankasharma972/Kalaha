package com.backend.java.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	@Test
	public void Test1_BoardInitialization() {
		// Test the initial board set up

		GameImplementation game = GameImplementation.init();
		int[] initialBoard = new int[] { 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0 };
		assertArrayEquals(initialBoard, game.getBoard());
		assertEquals(1, game.getPlayer1().getId());
		assertEquals(6, game.getPlayer1().getKalahaBigPit());
		assertEquals(2, game.getPlayer2().getId());
		assertEquals(13, game.getPlayer2().getKalahaBigPit());
		assertEquals(Status.Player1Turn, game.getStatus());
	}

	@Test
	public void Test2_Player1_Firstmove_Pit2Selected() {
		// Test Player1's first move with Pit=2

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 6, 6, 0, 7, 7, 7, 1, 7, 7, 6, 6, 6, 6, 0 };
		game.playTheGame(2);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player2Turn, game.getStatus());

	}

	@Test
	public void Test3_Player2_FirstMove_Pit4Selected() {
		// Test Player2's first move with Pit=4

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 7, 7, 1, 8, 8, 7, 1, 7, 7, 6, 6, 6, 0, 1, };
		game.playTheGame(2);
		game.playTheGame(12);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player1Turn, game.getStatus());

	}

	@Test
	public void Test4_PlayerGetsAnotherChance_StoneFallsOnPlayerKalaha() {
		// Move where the player1 gets another chance as stone falls on his own pit

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0 };
		game.playTheGame(0);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player1Turn, game.getStatus());

	}

	@Test
	public void Test5_PlayerGetsAnotherChance_StoneFallsOnPlayerKalaha() {
		// Move where the player2 gets another chance as stone falls on his own pit

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 7, 1, 8, 8, 8, 0, 2, 8, 7, 7, 7, 7, 0, 2 };
		game.playTheGame(1);
		game.playTheGame(12);
		game.playTheGame(5);
		game.playTheGame(12);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player2Turn, game.getStatus());

	}

	@Test
	public void Test6_PlayerGetsAnotherChance_StoneFallsOnPlayerKalaha() {
		// Move where the player2 gets another chance as stone falls on his own pit

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 7, 1, 8, 8, 8, 0, 2, 8, 7, 7, 7, 7, 0, 2 };
		game.playTheGame(1);
		game.playTheGame(12);
		game.playTheGame(5);
		game.playTheGame(12);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player2Turn, game.getStatus());

	}

	@Test
	public void Test7_StonesMustNotIncrementOppositePlayerKalahaPit() {
		// Test covering the scenario where Player1 has 10 stones and Player-2's Kalaha
		// does not increment with
		// the number until Player1's stones are finished.
		// It should still continue with Player1's pits without incrementing number on
		// opposite's pit

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 9, 2, 1, 0, 10, 9, 3, 9, 8, 8, 8, 1, 2, 2 };
		game.playTheGame(1);
		game.playTheGame(12);
		game.playTheGame(2);
		game.playTheGame(11);
		game.playTheGame(3);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player2Turn, game.getStatus());

	}

	@Test
	public void Test8_StoneFallsAtAnEmptyPit() {
		// Move where the stone falls on an empty pit. In this case, the player1 gets
		// opposite pit's stones

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 0, 0, 8, 8, 8, 8, 10, 0, 8, 7, 7, 0, 7, 1 };
		game.playTheGame(0);
		game.playTheGame(1);
		game.playTheGame(7);
		game.playTheGame(0);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player2Turn, game.getStatus());

	}

	@Test
	public void Test9_StoneFallsAtAnEmptyPit() {
		// Move where the stone falls on an empty pit. In this case, the player2 gets
		// opposite pit's stones

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 9, 0, 10, 10, 0, 2, 3, 0, 0, 10, 10, 3, 10, 5 };
		game.playTheGame(5);
		game.playTheGame(11);
		game.playTheGame(1);
		game.playTheGame(7);
		game.playTheGame(4);
		game.playTheGame(8);
		game.playTheGame(1);
		game.playTheGame(7);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player1Turn, game.getStatus());

	}

	@Test
	public void Test10_WinnerOfTheGame() {
		// Stress Testing for multiple moves and also covering the winner scenario

		GameImplementation game = GameImplementation.init();
		int[] expectedBoard = new int[] { 0, 0, 0, 0, 0, 0, 39, 0, 0, 0, 0, 0, 0, 33 };
		game.playTheGame(0);
		game.playTheGame(1);
		game.playTheGame(7);
		game.playTheGame(0);
		game.playTheGame(8);
		game.playTheGame(2);
		game.playTheGame(11);
		game.playTheGame(9);
		game.playTheGame(4);
		game.playTheGame(11);
		game.playTheGame(12);
		game.playTheGame(0);
		game.playTheGame(8);
		game.playTheGame(4);
		game.playTheGame(2);
		game.playTheGame(7);
		game.playTheGame(5);
		game.playTheGame(8);
		game.playTheGame(4);
		game.playTheGame(3);
		game.playTheGame(11);
		game.playTheGame(12);
		game.playTheGame(4);
		game.playTheGame(3);
		game.playTheGame(7);
		game.playTheGame(0);
		game.playTheGame(10);
		game.playTheGame(2);
		game.playTheGame(1);
		game.playTheGame(3);
		game.playTheGame(5);
		game.playTheGame(9);
		game.playTheGame(4);
		game.playTheGame(8);
		game.playTheGame(0);
		game.playTheGame(10);
		game.playTheGame(0);
		game.playTheGame(7);
		game.playTheGame(5);
		game.playTheGame(1);
		game.playTheGame(8);
		game.playTheGame(2);
		int[] actualBoard = game.getBoard();
		assertArrayEquals(expectedBoard, actualBoard);
		assertEquals(Status.Player1WinsTheGame, game.getStatus());
	}

}
