/*
* Class to prompt user to select game settings including difficulty
*/

import java.util.Scanner;

public class HangmanSettingsSelect {
  HangmanWordGeneration wordGen = new HangmanWordGeneration();
  int difficulty;
  int wordLength;
  int strikes;

  Scanner scan = new Scanner(System.in);

  /* Constructor */
  public HangmanSettingsSelect() {
    this.difficulty = 0;
    this.wordLength = 0;
    this.strikes = 0;
  }

  /* Difficulty */
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
    this.setStrikes();
    // return this.difficulty;
  }

  /* Word Length */
  public int getWordLength() {
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

    // this.wordGen.setWord(this.wordLength);

  }

  /* Strikes */
  public int getStrikes() {
    return this.strikes;
  }

  private void setStrikes() {
    if (this.getDifficulty() == 1) {
      this.strikes = 10;
    } else if (this.getDifficulty() == 2) {
      this.strikes = 5;
    } else if (this.getDifficulty() == 3) {
      this.strikes = 3;
    }
  }
}
