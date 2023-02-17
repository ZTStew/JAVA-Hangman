/*
 * Class handles selecting word that will be guessed
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanWordGeneration {
  String word;

  Scanner scan = new Scanner(System.in);

  public HangmanWordGeneration() {

  }

  public String setWord(String wordLength) {
    
    File file = new File("len_" + wordLength + "_words.txt");
    
    return "";
  }
}
