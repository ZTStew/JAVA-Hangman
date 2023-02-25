/*
 * Class handles various game functions as well as getting input from the user
 */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class HangmanGameFunctions {
  int strikes;
  String encoded;

  HangmanSettingsSelect settings;

  Scanner scan = new Scanner(System.in);


  public HangmanGameFunctions(HangmanSettingsSelect settings) {
    this.settings = settings;
  // public HangmanGameFunctions(int strikes) {
    // this.strikes = strikes;
  }


  /*
   * Contains checks to determine if the game is in 1 of 3 states:
   * Game still going
   * Game won
   * Game lost
   * Continue playing (after win or loss)
   */
  public boolean isGameOver() {

    return true;
  }

  /* Gets word encoded */
  public String getEncodedWord() {
    return this.encoded;
  }

  /* Builds encoded word to show: "_ o _ d" */
  public void setEncodedWord(String word, ArrayList<String> guesses) {
    // resets encoded
    this.encoded = "";

    // loops through word
    for (int i = 0; i < word.length(); i++) {
      // if guesses contains a character found at the index of the word...
      if(guesses.contains(Character.toString(word.charAt(i)))) {
        if (i == 0) {
          this.encoded += word.charAt(i) + " ";
        } else if (i == word.length() - 1) {
          this.encoded += " " + word.charAt(i);
        } else {
          this.encoded += " " + word.charAt(i) + " ";
        }
      // if guesses does not contain the character found at index of the word, add an '_'
      } else {
        if (i == 0) {
          this.encoded += "_ ";
        } else if (i == word.length() - 1) {
          this.encoded += " _";
        } else {
          this.encoded += " _ ";
        }
      }
    }
  }


  /* Get user guess */
  public int getUserGuess(String word, ArrayList<String> guesses, int correct) {
    try {
      String guess = "";
      while (true) {
        // Gets user input
        System.out.print("Enter Guess (1 Letter): " + TextColor.Green);
        String selection = scan.nextLine();
        // standardizes user input
        selection = selection.toLowerCase();
        System.out.print(TextColor.Reset);

        // checks if user input is between a-z
        if (selection.charAt(0) >= 'a' && selection.charAt(0) <= 'z') {
          // Sets user guess to the first letter entered
          guess = String.valueOf(selection.charAt(0));
          break;
        } else {
          System.out.println(TextColor.Red + "Guess Needs To Be Between: a-z" + TextColor.Reset);
        }
      }
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
          this.settings.updateStrikes();
        }
      }

      /* Gets array of previously guessed letters */
      this.generateGuessSummary(guesses);

    } catch (Exception e) {
      System.out.println("Invalid Input");
    }
    return correct;
  }


  /* Loops through `guesses` and returns a reading of what letters have already been guessed */
  public void generateGuessSummary(ArrayList<String> guesses) {
    String guessSummary = "Current Gueses: [";

    for (int i = 0; i < guesses.size(); i++) {
      if (guesses.get(i).equals("a") ||
          guesses.get(i).equals("e") ||
          guesses.get(i).equals("i") ||
          guesses.get(i).equals("o") ||
          guesses.get(i).equals("u")) {
        guessSummary += TextColor.Purple + " " + guesses.get(i) + TextColor.Reset;
      } else {
        guessSummary += TextColor.Cyan + " " + guesses.get(i) + TextColor.Reset;
      }
      if (i == guesses.size() - 1) {
        guessSummary += " ";
      } else {
        guessSummary += ", ";
      }
    }
    guessSummary += "]";

    System.out.println(guessSummary);
  }


  /* Will prompt player if they want to play again */
  // System.out.print("Play Again? (y/n)");
  // String replay = scan.nextLine();
  // if (replay.charAt(0) == 'y') {
  //   playAgain = true;
  // }
}
