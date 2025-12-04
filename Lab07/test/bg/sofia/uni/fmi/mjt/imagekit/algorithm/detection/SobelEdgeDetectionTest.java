package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SobelEdgeDetectionTest {

    @Test
    void testConstructorThrowsWhenGrayscaleAlgorithmIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SobelEdgeDetection(null));
    }
    @Test
    void testProcessThrowsWhenImageIsNull() {
        ImageAlgorithm stub = mock(ImageAlgorithm.class);
        SobelEdgeDetection sobel = new SobelEdgeDetection(stub);

        assertThrows(IllegalArgumentException.class, () -> sobel.process(null));
    }

    @Test
    void testProcessUsesGrayscaleAlgorithm() {
        ImageAlgorithm grayscale = mock(ImageAlgorithm.class);

        BufferedImage input = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        BufferedImage gray = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);

        when(grayscale.process(input)).thenReturn(gray);

        SobelEdgeDetection sobel = new SobelEdgeDetection(grayscale);
        sobel.process(input);

        verify(grayscale, times(1)).process(input);
    }
}