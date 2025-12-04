package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LocalFileSystemImageManagerTest {

    private LocalFileSystemImageManager manager;

    @BeforeEach
    void setUp() {
        manager = new LocalFileSystemImageManager();
    }

    @Test
    void testLoadImageWithNullFileThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> manager.loadImage(null));
    }

    @Test
    void testLoadImagesFromDirectoryNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> manager.loadImagesFromDirectory(null));
    }

    @Test
    void testLoadImageNonExistentFileThrows() {
        File file = new File("non-existent-image.png");
        assertThrows(IOException.class,
                () -> manager.loadImage(file));
    }

    @Test
    void testLoadImageUnsupportedExtensionThrows() throws IOException {
        File temp = File.createTempFile("test", ".txt");
        temp.deleteOnExit();

        assertThrows(IOException.class,
                () -> manager.loadImage(temp));
    }

    @Test
    void testLoadImagesFromDirectoryNotDirectoryThrows() throws IOException {
        File file = File.createTempFile("not-dir", ".png");
        file.deleteOnExit();

        assertThrows(IOException.class,
                () -> manager.loadImagesFromDirectory(file));
    }

    @Test
    void testLoadImagesFromDirectoryLoadsAllImages() throws IOException {
        File dir = Files.createTempDirectory("images").toFile();
        dir.deleteOnExit();

        BufferedImage img = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);

        File img1 = new File(dir, "a.png");
        File img2 = new File(dir, "b.jpg");

        ImageIO.write(img, "png", img1);
        ImageIO.write(img, "jpg", img2);

        List<BufferedImage> images = manager.loadImagesFromDirectory(dir);

        assertEquals(2, images.size());
    }

    @Test
    void testSaveImageNullThrows() {
        File file = new File("x.png");
        assertThrows(IllegalArgumentException.class,
                () -> manager.saveImage(null, file));
        assertThrows(IllegalArgumentException.class,
                () -> manager.saveImage(new BufferedImage(1, 1, 1), null));
    }

    @Test
    void testSaveImageUnsupportedExtensionThrows() {
        File file = new File("invalid_format.xyz");
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        assertThrows(IOException.class,
                () -> manager.saveImage(img, file));
    }

    @Test
    void testSaveImageCorrect() throws IOException {
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);

        File saved = File.createTempFile("save-test", ".png");
        saved.delete();

        manager.saveImage(img, saved);

        assertTrue(saved.exists());
    }

    @Test
    void testLoadImageReturnsNullFromImageIOThrows() throws IOException {
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isFile()).thenReturn(true);
        when(mockFile.getName()).thenReturn("test.png");

        File unreadable = File.createTempFile("invalid", ".png");
        unreadable.deleteOnExit();
        Files.write(unreadable.toPath(), new byte[0]);

        assertThrows(IOException.class,
                () -> manager.loadImage(unreadable));
    }
}