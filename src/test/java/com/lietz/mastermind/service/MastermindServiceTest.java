package com.lietz.mastermind.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lietz.mastermind.model.Mastermind;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MastermindServiceTest {

  @Mock
  private Mastermind mastermind;

  @InjectMocks
  private MastermindService mastermindService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnCorrectMessageWhenGuessIsCorrect() {

    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,green,yellow,orange";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);

    Assertions.assertEquals("Correct! You win!", result);

    verify(mastermind).setGameOver(true);
  }

  @Test
  void shouldReturnCorrectMessageWhenUserChoosesToManyColours() {
    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,green,yellow,purple,blue";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);
    Assertions.assertEquals("Invalid guess! Please choose four colours.", result);

  }

  @Test
  void shouldReturnCorrectMessageWhenUserChoosesOnlyTwoColours() {
    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,green";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);
    Assertions.assertEquals("Invalid guess! Please choose four colours.", result);

  }

  @Test
  void shouldReturnCorrectMessageWhenGuessFails() {
    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,blue,purple,green";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);
    Assertions.assertEquals("Colors on the correct position : 1. Correct colors : 2", result);
    Assertions.assertFalse(mastermind.isGameOver());
  }

  @Test
  void shouldReturnCorrectMessageWhenUsedAllAttempts() {
    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,blue,purple,green";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);
    when(mastermind.getUsedAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);
    String expected = "Out of attempts! The code was: " + secretCode;
    Assertions.assertEquals(expected, result);

    verify(mastermind).setGameOver(true);
  }

  @Test
  void resetGame() {

    mastermindService = mock(MastermindService.class);
    mastermindService.resetGame();
    verify(mastermindService, times(1)).resetGame();
  }
}