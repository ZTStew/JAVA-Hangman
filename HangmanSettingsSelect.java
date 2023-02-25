/*
* Class to prompt user to select game settings including difficulty, word length, word, strikes
*/

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class HangmanSettingsSelect {
  int difficulty;
  int wordLength;
  String word;
  int strikes;

  Random random = new Random();
  Scanner scan = new Scanner(System.in);

  /* Constructor */
  public HangmanSettingsSelect() {
    this.difficulty = 0;
    this.wordLength = 0;
    this.strikes = 0;
    this.word = "";
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
        selection = selection.toLowerCase();

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
    this.setGameStrikes();
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
          System.out.println(TextColor.Red + "Invalid Input: Please Enter A Number Between 3 and 10" + TextColor.Reset);
        } else {
          this.wordLength = selection;
        }

      } catch (Exception e) {
        System.out.println(TextColor.Red + "Invalid Input, Please Try Again" + TextColor.Reset);
        scan.next();
      }
    }
  }


  /* Selects the word being guessed */
  public String getWord() {
    return word;
  }

  public void setWord() {
    try {
      // opens file
      RandomAccessFile file = new RandomAccessFile("./word_lists/len_" + this.wordLength + "_words.txt", "r");
      // Selects a random line from file
      // Each line is the word length +1 or +2 to account for each line's escape characters
      // Due to how each OS records line end codes, windows needs to be adjusted by +2 and Linux needs to be adjuested by +1
      // Identifies OS to handel different end line variables
      String os = System.getProperty("os.name").toLowerCase();
      long rand = 0;
      if (os.contains("windows")) {
        /* Windows */
        rand = (long)Math.floor(Math.floor(Math.random() * (file.length() - 2)) / (this.wordLength + 2)) * (this.wordLength + 2);
        System.out.println("OS = Windows");
      } else {
        /* Linux */
        rand = (long)Math.floor(Math.floor(Math.random() * (file.length() - 1)) / (this.wordLength + 1)) * (this.wordLength + 1);
        System.out.println("OS != Windows");
      }
      // sets line to position or rand
      file.seek(rand);

      // reads line starting at 'seek'
      word = file.readLine();

      file.close();
    } catch (Exception e) {
      System.out.println("ERROR: " + e);
    }
  }


  /* Strikes */
  public int getStrikes() {
    return this.strikes;
  }

  public void updateStrikes() {
    this.strikes--;
  }

  private void setGameStrikes() {
    if (this.getDifficulty() == 1) {
      this.strikes = 10;
    } else if (this.getDifficulty() == 2) {
      this.strikes = 5;
    } else if (this.getDifficulty() == 3) {
      this.strikes = 3;
    }
  }

  /* Method prints out the word being guessed, the strikes remaining, the number of correct guesses */
  public void readStatus(int correct) {
    System.out.println("Word To Guess: " + TextColor.Purple + this.word + TextColor.Reset);
    System.out.println("Strikes Remaining: " + this.strikes + TextColor.Reset);
    System.out.println("Letters Correct: " + correct + TextColor.Reset);
  }
}
