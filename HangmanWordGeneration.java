/*
 * Class handles selecting word that will be guessed
 */

// import java.io.File;
import java.io.RandomAccessFile;
// import java.util.Scanner;

public class HangmanWordGeneration {
  String word;

  // Scanner scan = new Scanner(System.in);

  public HangmanWordGeneration() {

  }

  public void setWord(String wordLength) {
    try {
      RandomAccessFile file = new RandomAccessFile("len_" + wordLength + "_words.txt", "r");
      System.out.println(file.length());

      // File file = new File("len_" + wordLength + "_words.txt");
      // Scanner scan = new Scanner(file);

      // while (scan.hasNextLine()) {
      //   String data = scan.nextLine();

      //   System.out.println(data);
      // }
      // scan.close();
      file.close();
    } catch (Exception e) {
      System.out.println("ERROR: " + e);
    }
    
    // return "";
  }
}
