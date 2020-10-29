package com.backend.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.java.model.Board;
import com.backend.java.service.GameImplementation;

/**
 * REST End Points for Kalaha Game
 *
 */
@RestController
@RequestMapping("/kalahagame")
public class GameController {

	@GetMapping("/start")
	public GameImplementation startGame() {
		return GameImplementation.init();

	}

	@PostMapping(value = "/play", consumes = { "application/json" })
	public GameImplementation play(@RequestBody Board playBoard) {
		GameImplementation game = playBoard.getGameImplementation();

		int pit = playBoard.getPit();
		if (game.isAValidMove(pit)) {
			playBoard.getGameImplementation().playTheGame(pit);
		} else {
			game.setMessage("It is a wrong move!");
		}
		return game;
	}

}
