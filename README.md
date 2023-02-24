Created By: Zachary Stewart

Date: 02/14/2023

---

Description:
- Program is designed to mimic the game Hangman
- The game should take a single letter from the user each round
- The letter should be checked if that letter matches a randomlly selected word
- Word will be selected from an array of predefined words that are shown to the user in a _ _ _ _ _ pattern based on the count of letters
- If the letter does match one of the characters in the word, add it to the letter to the displayed mystery word
- - For example, guess = 'a' | word = 'flag' would show: _ _ a _.
- If the letter does not match the selected word, add a strike against the user
- The game ends when the user successfully completes the word or the number of strikes exceeds a set amount
- Before the game begins, the user is prompted to select a difficulty which influences the number of stikes they can get before losing the game
  - Easy - 13 strikes
  - Medium - 10 strikes
  - Hard - 7 strikes

TODO:
 * Need to brake program into method files
 * reset words, strikes
 * Need to add JavaFX to file