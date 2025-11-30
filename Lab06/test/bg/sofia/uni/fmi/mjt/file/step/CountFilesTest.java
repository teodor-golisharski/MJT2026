package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountFilesTest {

    private final CountFiles step = new CountFiles();

    @Test
    void testProcessThrowsWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> step.process(null));
    }

    @Test
    void testProcessReturnsCorrectCountForEmptyCollection() {
        int count = step.process(List.of());
        assertEquals(0, count);
    }

    @Test
    void testProcessReturnsCorrectCountForNonEmptyCollection() {
        List<File> files = List.of(new File("a"), new File("b"), new File("c"));

        int count = step.process(files);

        assertEquals(3, count);
    }

    @Test
    void testProcessWorksWhenCollectionContainsNullElements() {
        List<File> files = Arrays.asList(new File("a"), null, new File("b"));

        int count = step.process(files);

        assertEquals(3, count);
    }
}