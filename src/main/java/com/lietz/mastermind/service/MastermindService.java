package com.lietz.mastermind.service;

import com.lietz.mastermind.model.Mastermind;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class MastermindService {
  private Mastermind mastermind;
  Logger logger = LoggerFactory.getLogger(MastermindService.class);

  public MastermindService() {
    this.mastermind = new Mastermind();
  }

  public Mastermind getMastermindInstance() {
    return mastermind;
  }

  public String makeAGuess(String guess) {
    List<String> parsedGuess = Arrays.asList(guess.split(","));
    logger.info("getting parsed guess: " + parsedGuess);

    if (mastermind.isGameOver()) {
      return "Game is already over!";
    }
    if (parsedGuess.size() != mastermind.getSecretCode().size()) {
      return "Invalid guess! Please choose 4 colours.";
    }

    String feedback = generateFeedback(mastermind.getSecretCode(), parsedGuess);
    logger.info("feedback generated: " + feedback);

    mastermind.getGuesses().add(String.join(", ", parsedGuess) + " - " + feedback);
    logger.info("Showing guess: " + parsedGuess);

    mastermind.setUsedAttempts(mastermind.getUsedAttempts() + 1);
    logger.info("Showing attempts used: " + mastermind.getUsedAttempts());

    if (parsedGuess.equals(mastermind.getSecretCode())) {
      mastermind.setGameOver(true);

      logger.info("setting game over when win");
      return "Correct! You win!";
    } else if (mastermind.getUsedAttempts() >= mastermind.getMaxAttempts()) {
      mastermind.setGameOver(true);
      logger.info("setting game over when out of attempts");
      return "Out of attempts! The code was: " + mastermind.getSecretCode();
    }
    return feedback;
  }

  private String generateFeedback(List<String> secretCode, List<String> guess) {

    int correctPositions = 0;
    int correctColors = 0;

    for (int i = 0; i < secretCode.size(); i++) {
      if (secretCode.get(i).equals(guess.get(i))) {
        correctPositions++;
      }
    }
    for (int i = 0; i < guess.size(); i++) {
      if (secretCode.contains(guess.get(i))) {
        correctColors++;
      }
    }
    return "Colors on the correct position: " + correctPositions + " Correct colors : " + correctColors;
  }

  public void resetGame() {

    this.mastermind = new Mastermind();
    logger.info("resetting game");
  }
}
