package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import java.awt.image.BufferedImage;

public class LuminosityGrayscale implements GrayscaleAlgorithm {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image is null.");
        }

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int luminance = Math.round(0.21f * r + 0.72f * g + 0.07f * b);
                luminance = Math.min(255, Math.max(0, luminance));

                int grayRGB = (luminance << 16) | (luminance << 8) | luminance;
                result.setRGB(x, y, grayRGB);
            }
        }
        return result;
    }
}
