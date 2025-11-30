package bg.sofia.uni.fmi.mjt.file.step;

import bg.sofia.uni.fmi.mjt.file.File;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintFilesTest {
    private PrintFiles step = new PrintFiles();

    @Test
    void testProcessThrowsWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> step.process(null));
    }

    @Test
    void testProcessReturnsSameCollection() {
        File f1 = new File("hello");
        File f2 = new File("world");
        List<File> files = List.of(f1, f2);

        Collection<File> result = step.process(files);

        assertSame(files, result);
    }
}