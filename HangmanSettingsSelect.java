/*
 * Class to prompt user to select game settings including difficulty
 */

import java.util.Scanner;

public class HangmanSettingsSelect {
  int difficulty;
  int wordLength;
  Scanner scan = new Scanner(System.in);

  public HangmanSettingsSelect() {
    this.difficulty = 0;
    this.wordLength = 0;
  }

  public int getDifficulty () {
    return this.difficulty;
  }

  public void setDifficulty() {
    while (this.difficulty == 0) {
      try {
        System.out.print("Enter Game Difficulty (Easy (e), Medium (m), Hard (h), Exit (x)): ");
        String selection = scan.nextLine();

        if (selection.charAt(0) == 'e') {
          this.difficulty = 1;
        } else if (selection.charAt(0) == 'm') {
          this.difficulty = 2;
        } else if (selection.charAt(0) == 'h') {
          this.difficulty = 3;
        } else if (selection.charAt(0) == 'x') {
          break;
        } else {
          System.out.println("Invaid Input, Please Try Again");
        }

      } catch (Exception e) {
        System.out.println("Invalid Input, Please Try Again");
      }
    }
    // return this.difficulty;
  }

  public int getWordlength() {
    return this.wordLength;
  }

  public void setWordLength() {
    while (wordLength == 0) {
      try {
        System.out.print("Enter Length of Word to Guess Between 3 and 10: ");
        int selection = scan.nextInt();
        // System.out.println(selection);

        if (selection < 3 || selection > 10) {
          System.out.println("\u001B[31mInvalid Input: Please Enter A Number Between 3 and 10\u001B[0m");
        } else {
          this.wordLength = selection;
        }

      } catch (Exception e) {
        System.out.println("\u001B[31mInvalid Input, Please Try Again\u001B[0m");
        scan.next();
      }
    }
    // return this.wordLength;
  }
}
