package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import bg.sofia.uni.fmi.mjt.imagekit.utils.ApplicationConstants;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class LuminosityGrayscaleTest {
    private final GrayscaleAlgorithm algorithm = new LuminosityGrayscale();

    @Test
    void testProcessNullImageThrows() {
        assertThrows(IllegalArgumentException.class, () -> algorithm.process(null));
    }

    @Test
    void testProcessReturnsImageWithSameDimensions() {
        BufferedImage input = new BufferedImage(5, 7, BufferedImage.TYPE_INT_RGB);
        BufferedImage output = algorithm.process(input);

        assertEquals(5, output.getWidth());
        assertEquals(7, output.getHeight());
    }

    @Test
    void testProcessProducesCorrectGrayscaleValue() {
        int r = 100, g = 150, b = 200;
        int rgb = (r << 16) | (g << 8) | b;

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, rgb);

        BufferedImage result = algorithm.process(img);
        int outRGB = result.getRGB(0, 0);

        int expectedLuminance = Math.round(
                ApplicationConstants.RED_COEFFICIENT * r
                        + ApplicationConstants.GREEN_COEFFICIENT * g
                        + ApplicationConstants.BLUE_COEFFICIENT * b
        );
        expectedLuminance = Math.min(255, Math.max(0, expectedLuminance));

        int expectedGray = (expectedLuminance << 16)
                | (expectedLuminance << 8)
                | expectedLuminance;

        assertEquals(expectedGray, outRGB & 0xFFFFFF);
    }
}