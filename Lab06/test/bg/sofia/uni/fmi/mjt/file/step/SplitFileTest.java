package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SplitFileTest {

    private SplitFile step = new SplitFile();

    @Test
    void testProcessThrowsWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> step.process(null));
    }

    @Test
    void testProcessReturnsEmptySetWhenContentIsEmpty() {
        File file = new File("");

        Set<File> result = step.process(file);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcessReturnsEmptySetWhenContentIsWhitespaceOnly() {
        File file = new File("    \n\t   ");

        Set<File> result = step.process(file);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcessSplitsWordsCorrectly() {
        File file = new File("hello world test");

        Set<File> result = step.process(file);

        assertEquals(3, result.size());
        assertTrue(result.contains(new File("hello")));
        assertTrue(result.contains(new File("world")));
        assertTrue(result.contains(new File("test")));
    }

    @Test
    void testProcessTrimsLeadingAndTrailingWhitespace() {
        File file = new File("   hello   world   ");

        Set<File> result = step.process(file);

        assertEquals(2, result.size());
        assertTrue(result.contains(new File("hello")));
        assertTrue(result.contains(new File("world")));
    }

    @Test
    void testProcessRemovesDuplicateWords() {
        File file = new File("a b a c b c");

        Set<File> result = step.process(file);

        assertEquals(3, result.size());
        assertTrue(result.contains(new File("a")));
        assertTrue(result.contains(new File("b")));
        assertTrue(result.contains(new File("c")));
    }
}