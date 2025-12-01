package bg.sofia.uni.fmi.mjt.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    void testConstructorSetsContent() {
        File file = new File("Hello world");
        assertEquals("Hello world", file.getContent());
    }

    @Test
    void testConstructWithNullContent() {
        assertThrows(IllegalArgumentException.class, () -> new File(null));
    }

    @Test
    void testSetContentWithNullValue() {
        File file = new File("Some content");
        assertThrows(IllegalArgumentException.class, () -> file.setContent(null));
    }

    @Test
    void testSetContentChangesContent() {
        File file = new File("Old content");
        file.setContent("New content");
        assertEquals("New content", file.getContent());
    }

    @Test
    void testEqualsSameContent() {
        File file1 = new File("Same content");
        File file2 = new File("Same content");

        assertEquals(file1, file2);
        assertEquals(file1.hashCode(), file2.hashCode());
    }

    @Test
    void testEqualsDifferentContent() {
        File file1 = new File("Content1");
        File file2 = new File("Content2");
        assertNotEquals(file1, file2);
        assertNotEquals(file1.hashCode(), file2.hashCode());

    }
}