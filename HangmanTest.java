/*
* Author: Zachary Stewart
* Date: 02/14/2023
*/

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class HangmanTest {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    ArrayList<String> guesses = new ArrayList<String>();
    Boolean game_loop = true;
    int strikes = 1;
    int correct = 0;
    boolean playAgain = false;
    HangmanGameFunctions gameFunction = new HangmanGameFunctions();



    /*
    * Difficulty selection
    */
    HangmanSettingsSelect settings = new HangmanSettingsSelect();
    settings.setDifficulty();
    settings.setWordLength();
    settings.setWord();

    if (settings.getDifficulty() == 0) {
      scan.close();
      return;
    // Calcultes number of strikes
    } else {
      strikes = settings.getStrikes();
      // gameFunction.setStrikes(strikes);
    }

    System.out.println("Difficulty: " + settings.getDifficulty());

    /*
     * End Difficulty Selection
     */

    // generates word that will be guessed
    // String word = words[random.nextInt(10)];
    System.out.println(settings.getWord());


    while (game_loop) {
      if (correct >= settings.getWord().length()) {
        System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Correctly Guessed!");

        /* Will prompt player if they want to play again */
        // System.out.print("Play Again? (y/n)");
        // String replay = scan.nextLine();
        // if (replay.charAt(0) == 'y') {
        //   playAgain = true;
        // }


        game_loop = false;

      // Kills game after number of strikes equals 0
      } else if (strikes > 0) {
        /*
        * Build encoded word
        */
        gameFunction.setEncodedWord(settings.getWord(), guesses);

        System.out.println("Word: " + gameFunction.getEncodedWord());
        System.out.println("Strikes Remaining: " + strikes);

        try {
          // Gets user input
          System.out.print("Enter Guess (1 Letter): ");
          String selection = scan.nextLine();
          selection = selection.toLowerCase();

          // Sets user guess to the first letter entered
          String guess = String.valueOf(selection.charAt(0));
          // System.out.println(guess);

          // Checks if letter has already been guessed
          if (guesses.contains(guess)) {
            System.out.println("Letter Already Guessed");
          // Checks if the guess letter is found in the word
          } else {
            guesses.add(guess);
            Collections.sort(guesses);

            boolean does_contain = false;
            for (int i = 0; i < settings.getWord().length(); i++) {
              if (settings.getWord().charAt(i) == guess.charAt(0)) {
                does_contain = true;
                correct++;
              }
            }

            if (does_contain) {
              System.out.println("Guess Correct");
            } else {
              System.out.println("Guess Wrong");
              // reduces the number of strikes left
              strikes--;
            }
          }

          /*
           * Colors output for ease of use
           */

          // System.out.print("Current Gueses: [");
          String guessSummary = "Current Gueses: [";

          for (int i = 0; i < guesses.size(); i++) {
            if (guesses.get(i).equals("a") ||
                guesses.get(i).equals("e") ||
                guesses.get(i).equals("i") ||
                guesses.get(i).equals("o") ||
                guesses.get(i).equals("u")) {
              guessSummary += "\u001B[35m " + guesses.get(i) + "\u001B[0m";
            } else {
              guessSummary += "\u001B[36m " + guesses.get(i) + "\u001B[0m";
            }
            if (i == guesses.size() - 1) {
              guessSummary += " ";
            } else {
              guessSummary += ", ";
            }
          }
          guessSummary += "]";

          System.out.println(guessSummary);

          // System.out.println("Current Guesses: " + guesses);
        } catch (Exception e) {
          System.out.println("Invalid Input");
        }
      } else {
        System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Was Not Guessed!");

        game_loop = false;
      }
    }

    scan.close();
  }
}