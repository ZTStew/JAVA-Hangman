/*
 * Class handles various game functions as well as getting input from the user
 */


import java.util.Scanner;
import java.util.ArrayList;

public class HangmanGameFunctions {
  int strikes;
  String encoded;


  public HangmanGameFunctions() {
  // public HangmanGameFunctions(int strikes) {
    // this.strikes = strikes;
  }

  public void setStrikes(int strikes) {
    this.strikes = strikes;
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

  /* Getter */
  public String getEncodedWord() {
    return this.encoded;
  }

  /* Encodes word to show: "_ o _ d" intead of the word */
  public void setEncodedWord(String word, ArrayList<String> guesses) {
    this.encoded = "";

    for (int i = 0; i < word.length(); i++) {
      if(guesses.contains(Character.toString(word.charAt(i)))) {
        if (i == 0) {
          this.encoded += word.charAt(i) + " ";
        } else if (i == word.length() - 1) {
          this.encoded += " " + word.charAt(i);
        } else {
          this.encoded += " " + word.charAt(i) + " ";
        }
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


  /* Will prompt player if they want to play again */
  // System.out.print("Play Again? (y/n)");
  // String replay = scan.nextLine();
  // if (replay.charAt(0) == 'y') {
  //   playAgain = true;
  // }
}
