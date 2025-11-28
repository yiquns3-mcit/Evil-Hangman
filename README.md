# 🕹️ Evil Hangman

Evil Hangman is an enhanced version of the classic Hangman game.
Unlike the traditional version, the computer does not commit to a single hidden word.
Instead, it dynamically shifts among all valid words of the same length to make the game as difficult as possible.

---

## 📁 Project Structure

```
src/
├── EvilHangmanRunner.java
├── EvilHangman.java
├── EvilSolution.java
└── engDictionary.txt

test/
├── EvilSolutionTest.java
├── EvilHangmanTest.java
└── testDic.txt
```

All files follow the assignment requirement of no package headers.

---

## 🎮 Game Description

### ✔ Classic Hangman  
The player guesses letters, revealing parts of a hidden word.

### ✔ Evil Hangman (This Project)
- A random **word length** is selected at the start.
- The dictionary is filtered to include *all words* of that length.
- For every guessed letter:
  - Words are grouped into “**word families**” based on where the guessed letter appears.
  - Example for guess `e`:

    ```
    "e---" → ["echo"]
    "-e--" → ["heal", "belt"]
    "-ee-" → ["peel"]
    "----" → ["hazy"]
    ```

  - The program selects the **largest** family to keep the game as ambiguous as possible.
- The partial solution updates based on the best family.
- The game ends when all letters are revealed.

The user never notices the hidden word is constantly changing — hence the “evil” behavior.

---

Ensure engDictionary.txt is available in your working directory.
