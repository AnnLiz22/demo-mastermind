package com.lietz.mastermind.controller;

import com.lietz.mastermind.service.MastermindService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MastermindController {
  private final MastermindService mastermindService;
  Logger logger = LoggerFactory.getLogger(MastermindController.class);
  @GetMapping("/")
  public String showGame(Model model) {

    List<String> processedGuesses = mastermindService.getMastermindInstance()
        .getGuesses();
    logger.info("getting processed guesses " + processedGuesses);

    model.addAttribute("game", mastermindService.getMastermindInstance());
    model.addAttribute("processedGuesses", processedGuesses);
    return "mastermind";
  }

  @PostMapping("/guess")
  public String makeGuess(@RequestParam String guess, Model model) {

    String feedback = mastermindService.makeAGuess(guess);

    model.addAttribute("game", mastermindService.getMastermindInstance());
    model.addAttribute("feedback", feedback);
    return "mastermind";
  }

  @PostMapping("/reset")
  public String resetGame() {
    mastermindService.resetGame();
    return "redirect:/";
  }

}
