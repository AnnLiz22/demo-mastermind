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
  void shouldReturnCorrectMessageWhenMakeAGuess() {

    List<String> secretCode = List.of("red", "green", "yellow", "orange");
    String guess = "red,green,yellow,orange";
    when(mastermind.getSecretCode()).thenReturn(secretCode);
    when(mastermind.getMaxAttempts()).thenReturn(8);

    String result = mastermindService.makeAGuess(guess);

    Assertions.assertEquals("Correct! You win!", result);

    verify(mastermind).setGameOver(true);
  }

  @Test
  void resetGame() {
  }
}