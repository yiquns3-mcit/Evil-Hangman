import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EvilSolutionTest {

    private ArrayList<String> wordList;

    public void EvilSolutionTest(){
        try {
            this.wordList = dictionaryToList("test/testDic.txt");
        } catch (IOException e) {
            System.out.println("Invalid wordlist");
        }
    }

    @Test
    public void testIsSolvedInitial() {
        EvilSolutionTest();
        EvilSolution test = new EvilSolution(wordList);
        assertFalse(test.isSolved());
    }

    @Test
    public void testPrintProgress() {
        PrintStream originalOutput = System.out;

        OutputStream testOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(testOut);
        System.setOut(printStream);

        EvilSolutionTest();
        EvilSolution test1 = new EvilSolution(wordList);
        test1.printProgress();

        assertEquals("_ _ _ _ \n", testOut.toString());
        System.setOut(originalOutput);
    }

    @Test
    public void testAddGuess() {
        EvilSolutionTest();
        EvilSolution test = new EvilSolution(wordList);
        // input: a\nb\ne\nt\nl\ns\nr\np\n
        // round 1
        char guess = 'a';
        boolean guessCorrect = test.addGuess(guess);
        String bestKey = test.getBestKey();
        assertEquals("--a-", bestKey);
        assertTrue(guessCorrect);
        // round 2
        guess = 'b';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("--a-", bestKey);
        assertFalse(guessCorrect);
        // round 3
        guess = 'e';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("-ea-", bestKey);
        assertTrue(guessCorrect);
        // round 4
        guess = 't';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("-ea-", bestKey);
        assertFalse(guessCorrect);
        // round 5
        guess = 'l';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("-eal", bestKey);
        assertTrue(guessCorrect);
        // round 6
        guess = 's';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("-eal", bestKey);
        assertFalse(guessCorrect);
        // round 7
        guess = 'r';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("-eal", bestKey);
        assertFalse(guessCorrect);
        // round 8
        guess = 'p';
        guessCorrect = test.addGuess(guess);
        bestKey = test.getBestKey();
        assertEquals("peal", bestKey);
        assertTrue(guessCorrect);
    }

    private static ArrayList<String> dictionaryToList(String filename) throws IOException {
        FileInputStream fileInput = new FileInputStream(filename);
        Scanner fileReader = new Scanner(fileInput);

        ArrayList<String> wordList = new ArrayList<>();

        while (fileReader.hasNext()) {
            wordList.add(fileReader.next());
        }

        return wordList;
    }
}