package bg.sofia.uni.fmi.mjt.imagekit.algorithm.grayscale;

import bg.sofia.uni.fmi.mjt.imagekit.utils.ApplicationConstants;

import java.awt.image.BufferedImage;

public class LuminosityGrayscale implements GrayscaleAlgorithm {

    @Override
    public BufferedImage process(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Image is null.");
        }

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> ApplicationConstants.RED_SHIFT) & ApplicationConstants.RGB_MASK;
                int g = (rgb >> ApplicationConstants.GREEN_SHIFT) & ApplicationConstants.RGB_MASK;
                int b = rgb & ApplicationConstants.RGB_MASK;

                int luminance = Math.round(ApplicationConstants.RED_COEFFICIENT * r
                        + ApplicationConstants.GREEN_COEFFICIENT * g
                        + ApplicationConstants.BLUE_COEFFICIENT * b);
                luminance = Math.min(ApplicationConstants.LUMINANCE, Math.max(0, luminance));

                int grayRGB = (luminance << ApplicationConstants.RED_SHIFT)
                        | (luminance << ApplicationConstants.GREEN_SHIFT)
                        | luminance;
                result.setRGB(x, y, grayRGB);
            }
        }
        return result;
    }
}
