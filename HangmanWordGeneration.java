/*
 * Class handles selecting word that will be guessed
 */

import java.io.RandomAccessFile;
import java.util.Random;

public class HangmanWordGeneration {
  String word;
  HangmanSettingsSelect settings = new HangmanSettingsSelect();
  Random random = new Random();

  public HangmanWordGeneration() {
  }

  public String getWord() {
    return word;
  }

  public void setWord(String wordLength) {
    try {
      // opens file
      RandomAccessFile file = new RandomAccessFile("./word_lists/len_" + settings.getWordLength() + "_words.txt", "r");
      // Selects a random line from file
      // Each line is the word length + 2 as there are 2 values at the end of each line (I think for escape characters)
      long rand = (long)Math.floor(random.nextLong(file.length()) / (settings.getWordLength() + 2)) * (settings.getWordLength() + 2);
      // sets line to position or rand
      file.seek(rand);

      // reads line starting at 'seek'
      word = file.readLine();

      file.close();
    } catch (Exception e) {
      System.out.println("there");
    }
  }
}
