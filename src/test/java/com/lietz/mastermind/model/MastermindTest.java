package com.lietz.mastermind.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MastermindTest {

  private static final List<String> COLORS = List.of("red", "green", "blue", "yellow", "orange", "purple");
  private List<String> generatedCode;

  @BeforeEach
  void beforeEach() {
    Mastermind mastermind = new Mastermind();
    generatedCode = mastermind.generateSecretCode();
  }

  @Test
  void shouldGenerateSecretCodeOfFourColours() {

    assertThat(generatedCode).withFailMessage(
            "Generated code should not be empty, should have size 4, and should contain four colours from the list of colours")
        .isNotEmpty()
        .hasSize(4)
        .allMatch(COLORS::contains);
  }

  @RepeatedTest(5)
  void shouldGenerateCodeThatDoesNotHaveDuplicates() {
    assertThat(generatedCode).doesNotHaveDuplicates();
  }
}