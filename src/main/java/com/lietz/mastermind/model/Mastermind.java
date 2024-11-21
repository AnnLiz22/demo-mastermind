package com.lietz.mastermind.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class Mastermind {
  private List <String> secretCode;
  private int maxAttempts;
  private int usedAttempts;
  private boolean isGameOver;
  private List<String> guesses = new ArrayList<>();
  private static final List<String> COLORS = List.of("red", "green", "blue", "yellow", "orange", "purple");
  private int codeLength = 4;
  Logger logger = LoggerFactory.getLogger(Mastermind.class);

  public Mastermind() {
    this.secretCode = generateSecretCode();
    this.maxAttempts = 8;
    this.isGameOver = false;
  }

  public List<String> generateSecretCode(){
    Random random = new Random();
    List<String> tempSecretCode = new ArrayList<>();
    String color = COLORS.get(random.nextInt(COLORS.size()));
    tempSecretCode.add(color);

    while (tempSecretCode.size() < codeLength) {
      color = COLORS.get(random.nextInt(COLORS.size()));
      if(!tempSecretCode.contains(color)){

        tempSecretCode.add(color);
    }}
    secretCode = new ArrayList<>(tempSecretCode);
    logger.info("Secret code generated: " + secretCode);
    return secretCode ;
  }
}
