package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;
import java.awt.image.BufferedImage;

public class SobelEdgeDetection implements EdgeDetectionAlgorithm {

    private final ImageAlgorithm imageAlgorithm;

    public SobelEdgeDetection(ImageAlgorithm grayscaleAlgorithm) {
        if (grayscaleAlgorithm == null) {
            throw new IllegalArgumentException("Image algorithm cannot be null.");
        }
        this.imageAlgorithm = grayscaleAlgorithm;
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:MagicNumber"})
    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image is null.");
        }

        BufferedImage gray = imageAlgorithm.process(image);
        int width = gray.getWidth();
        int height = gray.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int[][] gX = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };

        int[][] gY = {
                {-1, -2, -1},
                { 0,  0,  0},
                { 1,  2,  1}
        };

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                double gx = 0;
                double gy = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = getGray(gray, x + j, y + i);
                        gx += pixel * gX[i + 1][j + 1];
                        gy += pixel * gY[i + 1][j + 1];
                    }
                }

                int g = (int) Math.round(Math.min(255, Math.sqrt(gx * gx + gy * gy)));

                int rgb = (g << 16) | (g << 8) | g;
                result.setRGB(x, y, rgb);
            }
        }

        return result;
    }

    private int getGray(BufferedImage img, int x, int y) {
        if (x < 0 || y < 0 || x >= img.getWidth() || y >= img.getHeight()) {
            return 0;
        }
        int rgb = img.getRGB(x, y);
        return rgb & 0xFF;
    }
}
