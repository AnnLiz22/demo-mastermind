package com.lietz.mastermind.controller;

import com.lietz.mastermind.model.Mastermind;
import com.lietz.mastermind.service.MastermindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MastermindController {
  private final MastermindService mastermindService;

  @GetMapping("/")
  public String showGame(Model model) {

    model.addAttribute("game", mastermindService.getMastermindInstance());
    return "mastermind";
  }

  @PostMapping("/guess")
  public String makeGuess(@RequestParam String guess, Model model) {

    String feedback = mastermindService.makeAGuess(guess);
    Mastermind game = mastermindService.getMastermindInstance();

    model.addAttribute("game", game);
    model.addAttribute("feedback", feedback);
    return "mastermind";
  }

  @PostMapping("/reset")
  public String resetGame() {
    mastermindService.resetGame();
    return "redirect:/";
  }
}