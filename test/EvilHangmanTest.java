import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class EvilHangmanTest {

    @Test
    public void testInputLength() {
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;

        String input = "ae\na\nbc\nb\ne\nt\nl\ns\nr\np\n";
        OutputStream testOut = setupIO(input);

        EvilHangman test1 = new EvilHangman("test/testDic.txt");
        test1.start();

        String output = testOut.toString();

        int count = output.split("Please enter a single character", -1).length - 1;
        assertEquals(2,count);

        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testInputRepeat() {
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;

        String input = "a\na\nb\nb\ne\ne\nt\nl\ns\nr\np\n";
        OutputStream testOut = setupIO(input);

        EvilHangman test1 = new EvilHangman("test/testDic.txt");
        test1.start();

        String output = testOut.toString();

        int count = output.split("You've already guessed that.", -1).length - 1;
        assertEquals(3,count);

        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testInputIsAlphabet() {
        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;

        String input = "1\n@\n(\n+\na\nb\ne\nt\nl\ns\nr\np\n";
        OutputStream testOut = setupIO(input);

        EvilHangman test1 = new EvilHangman("test/testDic.txt");
        test1.start();

        String output = testOut.toString();

        int count = output.split("Please enter an alphabetic character.", -1).length - 1;
        assertEquals(4,count);

        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    private OutputStream setupIO(String input){
        InputStream testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);

        OutputStream testOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(testOut);
        System.setOut(printStream);

        return testOut;
    }
}