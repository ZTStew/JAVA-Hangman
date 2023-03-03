/*
* Author: Zachary Stewart
* Date: 02/14/2023
*/

import java.util.Scanner;
import java.util.ArrayList;

public class HangmanTest {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    ArrayList<String> guesses = new ArrayList<String>();
    Boolean gameLoop = true;
    // int strikes = 1;
    int correct = 0;
    boolean playAgain = true;


    while (playAgain) {
      /*
       * Difficulty selection
       */
      HangmanSettingsSelect settings = new HangmanSettingsSelect();
      settings.setDifficulty();
      settings.setWordLength();
      settings.setWord();

      // System.out.println("Word To Guess: " + TextColor.Purple + settings.getWord() + TextColor.Reset);

      playAgain = false;
      gameLoop = true;

      while (gameLoop) {
        if (correct >= settings.getWord().length()) {
          System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Correctly Guessed!");

          playAgain = settings.isPlayAgain();

          if (playAgain) {
            // reset correct
            correct = 0;
            // reset guesses
            guesses = new ArrayList<String>();
          }

          gameLoop = false;

        // Kills game after number of strikes equals 0
        } else if (settings.getStrikes() > 0) {
          // Sets up encoded word
          settings.setEncodedWord(guesses);


          System.out.println("Word: " + settings.getEncodedWord());
          // System.out.print("");
          // System.out.println("Strikes Remaining: " + settings.getStrikes());

          correct = settings.getUserGuess(guesses, correct);
          settings.readStatus(correct);

        } else {
          System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Was Not Guessed!");

          playAgain = settings.isPlayAgain();

          if (playAgain) {
            // reset correct
            correct = 0;
            // reset guesses
            guesses = new ArrayList<String>();
          }

          gameLoop = false;
        }
      } // while(gameLoop)
    } // while(playAgain)
    scan.close();
  } // main
} // HangmanTest