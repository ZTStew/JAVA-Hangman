/*
 * Class handles selecting word that will be guessed
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanWordGeneration {
  String word;

  // Scanner scan = new Scanner(System.in);

  public HangmanWordGeneration() {

  }

  public String setWord(String wordLength) {
    try {
      File file = new File("len_" + wordLength + "_words.txt");
      Scanner scan = new Scanner(file);

      while (scan.hasNextLine()) {
        String data = scan.nextLine();

        System.out.println(data);
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: " + e);
    }
    
    return "";
  }
}
