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
      HangmanGameFunctions gameFunction = new HangmanGameFunctions(settings);
      settings.setDifficulty();
      settings.setWordLength();
      settings.setWord();

      if (settings.getDifficulty() == 0) {
        scan.close();
        return;
      }

      System.out.println("Word To Guess: " + TextColor.Purple + settings.getWord() + TextColor.Reset);

      playAgain = false;
      gameLoop = true;

      while (gameLoop) {
        if (correct >= settings.getWord().length()) {
          System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Correctly Guessed!");

          /* Will prompt player if they want to play again */
          System.out.print("Would You Like To Play Again? (y/n) ");
          String replay = scan.nextLine();
          if (replay.charAt(0) == 'y') {
            playAgain = true;

            // reset correct
            correct = 0;
            // reset guesses
            guesses = new ArrayList<String>();
          }

          gameLoop = false;

        // Kills game after number of strikes equals 0
        } else if (settings.getStrikes() > 0) {
          // Sets up encoded word
          gameFunction.setEncodedWord(settings.getWord(), guesses);


          System.out.println("Word: " + gameFunction.getEncodedWord());
          // System.out.println("Strikes Remaining: " + settings.getStrikes());

          correct = gameFunction.getUserGuess(settings.getWord(), guesses, correct);
          settings.readStatus(correct);

        } else {
          System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Was Not Guessed!");

          System.out.print("Would You Like To Play Again? (y/n)");
          String replay = scan.nextLine();
          if (replay.charAt(0) == 'y') {
            playAgain = true;
          }

          gameLoop = false;
        }
      } // while(gameLoop)
    } // while(playAgain)
    scan.close();
  } // main
} // HangmanTest