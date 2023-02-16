/*
 * Class to prompt user to select game settings including difficulty
 */

import java.util.Scanner;

public class HangmanSettingsSelect {
  int difficulty;
  Scanner scan = new Scanner(System.in);

  public HangmanSettingsSelect() {
    this.difficulty = 0;
  }

  public int getDifficulty() {
    while (difficulty == 0) {
      try {
        System.out.print("Enter Game Difficulty (Easy (e), Medium (m), Hard (h), Exit (x)): ");
        String selection = scan.nextLine();

        if (selection.charAt(0) == 'e') {
          difficulty = 1;
        } else if (selection.charAt(0) == 'm') {
          difficulty = 2;
        } else if (selection.charAt(0) == 'h') {
          difficulty = 3;
        } else if (selection.charAt(0) == 'x') {
          break;
        } else {
          System.out.println("Invaid Input, Please Try Again");
        }

      } catch (Exception e) {
        System.out.println("Invalid Input, Please Try Again");
      }
    }
    return difficulty;
  }
}
