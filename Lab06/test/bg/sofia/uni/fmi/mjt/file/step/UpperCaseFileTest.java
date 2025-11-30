package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpperCaseFileTest {
    private final UpperCaseFile step = new UpperCaseFile();

    @Test
    void testProcessThrowsWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> step.process(null));
    }

    @Test
    void testProcessConvertsToUpperCase() {
        String word = "Hello, World!";
        String wordToUpper = word.toUpperCase();
        File f = new File(wordToUpper);

        File result = step.process(f);
        assertSame(result, f);
    }
}