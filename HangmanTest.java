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
    Boolean game_loop = true;
    // int strikes = 1;
    int correct = 0;
    boolean playAgain = false;



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


    /*
     * End Difficulty Selection
     */

    // generates word that will be guessed
    // String word = words[random.nextInt(10)];
    System.out.println("Word To Guess: " + settings.getWord());


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
      } else if (settings.getStrikes() > 0) {
        // Sets up encoded word
        gameFunction.setEncodedWord(settings.getWord(), guesses);

        System.out.println("Word: " + gameFunction.getEncodedWord());
        System.out.println("Strikes Remaining: " + settings.getStrikes());

        correct = gameFunction.getUserGuess(settings.getWord(), guesses, correct);

      } else {
        System.out.println("Game Over! Word, " + settings.getWord().toUpperCase() + ", Was Not Guessed!");

        game_loop = false;
      }
    }

    scan.close();
  }
}