package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import bg.sofia.uni.fmi.mjt.file.exception.EmptyFileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckEmptyFileTest {

    private final CheckEmptyFile step = new CheckEmptyFile();

    @Test
    void testProcessThrowsWhenFileIsNull() {
        EmptyFileException ex = assertThrows(
                EmptyFileException.class,
                () -> step.process(null)
        );

        assertEquals("Input file or its content is empty or null", ex.getMessage());
    }

    @Test
    void testProcessThrowsWhenFileContentIsEmpty() {
        File f = new File("");

        EmptyFileException ex = assertThrows(
                EmptyFileException.class,
                () -> step.process(f)
        );

        assertEquals("Input file or its content is empty or null", ex.getMessage());
    }

    @Test
    void testProcessReturnsFileWhenNotEmpty() {
        File f = new File("correct");

        File result = step.process(f);

        assertSame(f, result);
    }
}