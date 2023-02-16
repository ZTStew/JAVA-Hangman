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

    String[] words = {"ash", "cycle", "exhibition", "exploration", "fraction", "mechanism", "player", "swing", "tank", "year"};
    ArrayList<String> guesses = new ArrayList<String>();
    Boolean game_loop = true;
    int strikes = 1;
    int correct = 0;

    /*
     * Difficulty selection
    */
    HangmanSettingsSelect settings = new HangmanSettingsSelect();
    int difficulty = settings.getDifficulty();

    // return if difficulty is 0
    if (difficulty == 0) {
      scan.close();
      return;
    // Calcultes number of strikes
    } else if (difficulty == 1) {
      strikes = 10;
    } else if (difficulty == 2) {
      strikes = 5;
    } else if (difficulty == 3) {
      strikes = 3;
    }

    System.out.println("Difficulty: " + difficulty);

    /*
     * End Difficulty Selection
     */

    // generates word that will be guessed
    String word = words[random.nextInt(10)];
    System.out.println(word);


    while (game_loop) {
      if (correct >= word.length()) {
        System.out.println("Game Over! Word, " + word.toUpperCase() + ", Correctly Guessed!");
        game_loop = false;

      // Kills game after number of strikes equals 0
      } else if (strikes > 0) {
        /*
        * Build encoded word
        */

        String encoded = "";

        for (int i = 0; i < word.length(); i++) {
          if(guesses.contains(Character.toString(word.charAt(i)))) {
            if (i == 0) {
              encoded += word.charAt(i) + " ";
            } else if (i == word.length() - 1) {
              encoded += " " + word.charAt(i);
            } else {
              encoded += " " + word.charAt(i) + " ";
            }
          } else {
            if (i == 0) {
              encoded += "_ ";
            } else if (i == word.length() - 1) {
              encoded += " _";
            } else {
              encoded += " _ ";
            }
          }
        }

        System.out.println("Word: " + encoded);
        System.out.println("Strikes Remaining: " + strikes);

        try {
          // Gets user input
          System.out.print("Enter Guess (1 Letter): ");
          String selection = scan.nextLine();

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
            for (int i = 0; i < word.length(); i++) {
              if (word.charAt(i) == guess.charAt(0)) {
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
        System.out.println("Game Over! Word, " + word.toUpperCase() + ", Was Not Guessed!");

        game_loop = false;
      }
    }


    scan.close();
  }
}