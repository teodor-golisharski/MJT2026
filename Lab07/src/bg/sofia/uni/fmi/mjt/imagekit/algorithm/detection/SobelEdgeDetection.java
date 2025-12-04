package bg.sofia.uni.fmi.mjt.imagekit.algorithm.detection;

import bg.sofia.uni.fmi.mjt.imagekit.algorithm.ImageAlgorithm;
import bg.sofia.uni.fmi.mjt.imagekit.utils.ApplicationConstants;

import java.awt.image.BufferedImage;

import static bg.sofia.uni.fmi.mjt.imagekit.utils.ApplicationConstants.MINUS_TWO;

public class SobelEdgeDetection implements EdgeDetectionAlgorithm {

    private final int[][] gX = {
            {-1, 0, 1},
            {MINUS_TWO, 0, 2},
            {-1, 0, 1}
    };

    private final int[][] gY = {
            {-1, MINUS_TWO, -1},
            {0, 0, 0},
            {1, 2, 1}
    };

    private final ImageAlgorithm imageAlgorithm;

    public SobelEdgeDetection(ImageAlgorithm grayscaleAlgorithm) {
        if (grayscaleAlgorithm == null) {
            throw new IllegalArgumentException("Image algorithm cannot be null.");
        }
        this.imageAlgorithm = grayscaleAlgorithm;
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image is null.");
        }

        BufferedImage gray = imageAlgorithm.process(image);
        int width = gray.getWidth();
        int height = gray.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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

                int g = (int) Math.round(Math.min(ApplicationConstants.LUMINANCE, Math.sqrt(gx * gx + gy * gy)));
                int rgb = (g << ApplicationConstants.RED_SHIFT) | (g << ApplicationConstants.GREEN_SHIFT) | g;
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
        return rgb & ApplicationConstants.RGB_MASK;
    }
}
