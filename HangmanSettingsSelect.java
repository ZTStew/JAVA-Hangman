/*
 * Class to prompt user to select game settings including difficulty, word length, word, strikes
 */

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class HangmanSettingsSelect {
  int difficulty;
  int wordLength;
  String word;
  int strikes;
  String encoded;

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
  /*
   * Method: 'getDifficulty'
   * Arguments: None
   * Return: int difficulty
   * 
   * Description: Returns game difficulty
   */
  public int getDifficulty () {
    return this.difficulty;
  }

  /*
   * Method: 'setDifficulty'
   * Arguments: None
   * Return: None
   * 
   * Description: Prompts user for game difficulty
   */
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
          System.exit(0);
        } else {
          System.out.println("Invalid Input, Please Try Again 1");
        }

      } catch (Exception e) {
        System.out.println("Invalid Input, Please Try Again 2");
      }
    }
    this.setGameStrikes();
  }


  /* Word Length */
  /*
   * Method: 'getWordLength'
   * Arguments: None
   * Return: int wordLength
   * 
   * Description: Returns length of word being guessed
   */
  public int getWordLength() {
    return this.wordLength;
  }

  /*
   * Method: 'setWordLength'
   * Arguments: None
   * Return: None
   * 
   * Description: Prompts user for word length
   */
  public void setWordLength() {
    while (wordLength == 0) {
      try {
        System.out.print("Enter Length of Word to Guess Between 3 and 10: ");
        int selection = scan.nextInt();
        // System.out.println(selection);

        if (selection < 3 || selection > 10) {
          System.out.println(TextColor.Red + "Invalid Input: Please Enter A Number Between 3 and 10 3" + TextColor.Reset);
        } else {
          this.wordLength = selection;
        }
        scan.nextLine();

      } catch (Exception e) {
        System.out.println(TextColor.Red + "Invalid Input, Please Try Again 4" + TextColor.Reset);
        scan.nextLine();
      }
    }
  }


  /* Selects the word being guessed */
  /*
   * Method: 'getWord'
   * Arguments: None
   * Return: String word
   * 
   * Description: Returns word being guessed
   */
  public String getWord() {
    return word;
  }

  /*
   * Method: 'setWord'
   * Arguments: None
   * Return: None
   * 
   * Description: Gets a random word from file named after word length
   */
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
  /*
   * Method: 'getStrikes'
   * Arguments: None
   * Return: int strikes
   * 
   * Description: Returns number of strikes
   */
  public int getStrikes() {
    return this.strikes;
  }

  /*
   * Method: 'updateStrikes'
   * Arguments: None
   * Return: None
   * 
   * Description: reduces the number of strikes avalible by 1
   */
  public void updateStrikes() {
    this.strikes--;
  }

  /*
   * Method: 'setGameStrikes'
   * Arguments: None
   * Return: None
   * 
   * Description: Sets number of stikes based on the selected difficulty
   */
  private void setGameStrikes() {
    if (this.getDifficulty() == 1) {
      this.strikes = 10;
    } else if (this.getDifficulty() == 2) {
      this.strikes = 5;
    } else if (this.getDifficulty() == 3) {
      this.strikes = 3;
    }
  }


  /*
   * Method: 'isPlayAgain'
   * Arguments: None
   * Return: boolean playAgain
   * 
   * Description: Promts user if they want to play another round of game
   */
  public boolean isPlayAgain() {
    System.out.print("Would You Like To Play Again? (y/n) ");
    String replay = scan.nextLine();
    replay.toLowerCase();
    if (replay.charAt(0) == 'y') {
      return true;
    }
    return false;
  } // isPlayAgain


  /* Gets word encoded */
  /*
   * Method: 'getEncodedWord'
   * Arguments: None
   * Return: String encoded
   * 
   * Description: Returns to user the encoded word
   */
  public String getEncodedWord() {
    return this.encoded;
  } // getEncodedWord

  /* Builds encoded word to show: "_ o _ d" */
  /*
   * Method: 'setEncodedWord'
   * Arguments: ArrayList<String> guesses
   * Return: None
   * 
   * Description: Encodes the word with the letter representing a guessed letter and an '_' representing an unguessed letter
   */
  public void setEncodedWord(ArrayList<String> guesses) {
    // resets encoded
    this.encoded = "";

    // loops through word
    for (int i = 0; i < this.word.length(); i++) {
      // if guesses contains a character found at the index of the word...
      if(guesses.contains(Character.toString(word.charAt(i)))) {
        if (i == 0) {
          this.encoded += this.word.charAt(i) + " ";
        } else if (i == this.word.length() - 1) {
          this.encoded += " " + this.word.charAt(i);
        } else {
          this.encoded += " " + this.word.charAt(i) + " ";
        }
      // if guesses does not contain the character found at index of the word, add an '_'
      } else {
        if (i == 0) {
          this.encoded += "_ ";
        } else if (i == this.word.length() - 1) {
          this.encoded += " _";
        } else {
          this.encoded += " _ ";
        }
      }
    }
  } // setEncodedWord


  /*
   * Method: 'getUserGuess'
   * Arguments: ArrayList<String> guesses, int correct
   * Return: int correct
   * 
   * Description: Prompts user for a guess and checks if the guess is in the word
   */
  public int getUserGuess(ArrayList<String> guesses, int correct) {
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

      // Checks if letter has already been guessed
      if (guesses.contains(guess)) {
        System.out.println("Letter Already Guessed");
      // Checks if the guess letter is found in the word
      } else {
        guesses.add(guess);
        Collections.sort(guesses);

        boolean does_contain = false;
        for (int i = 0; i < this.word.length(); i++) {
          if (this.word.charAt(i) == guess.charAt(0)) {
            does_contain = true;
            correct++;
          }
        }

        if (does_contain) {
          System.out.println("Guess Correct");
        } else {
          System.out.println("Guess Wrong");
          // reduces the number of strikes left
          this.updateStrikes();
        }
      }

      /* Gets array of previously guessed letters */
      this.generateGuessSummary(guesses);

    } catch (Exception e) {
      System.out.println("Invalid Input 5 " + e);
    }
    return correct;
  } // getUserGuess


  /* Loops through `guesses` and returns a reading of what letters have already been guessed */
  /*
   * Method: 'generateGuessSummary'
   * Arguments: ArrayList<String> guesses
   * Return: None
   * 
   * Description: Looks through list of guesses and generates a summary of already made guesses
   */
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
  } // generateGuessSummary

  /* Method prints out the word being guessed, the strikes remaining, the number of correct guesses */
  /*
   * Method: 'readStatus'
   * Arguments: int correct
   * Return: None
   * 
   * Description: Prints the word being guessed, the number of remaining strikes, and the number of correctly guessed values
   */
  public void readStatus(int correct) {
    System.out.println("Word To Guess: " + TextColor.Purple + this.word + TextColor.Reset);
    System.out.println("Strikes Remaining: " + this.strikes + TextColor.Reset);
    System.out.println("Letters Correct: " + correct + TextColor.Reset);
  } // readStatus
}
