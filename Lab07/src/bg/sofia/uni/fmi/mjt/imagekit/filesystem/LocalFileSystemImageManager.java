package bg.sofia.uni.fmi.mjt.imagekit.filesystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class LocalFileSystemImageManager implements FileSystemImageManager {

    private boolean isSupportedImage(String fileName) {
        String name = fileName.toLowerCase();
        return name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".bmp") || name.endsWith(".jpg");
    }

    @Override
    public BufferedImage loadImage(File imageFile) throws IOException {
        if (imageFile == null) {
            throw new IllegalArgumentException("File is null.");
        }
        if (!imageFile.exists() || !imageFile.isFile()) {
            throw new IOException("File does not exist or is not a regular file.");
        }

        String name = imageFile.getName().toLowerCase();
        if (!isSupportedImage(name)) {
            throw new IOException("Unsupported file extension.");
        }

        BufferedImage img = ImageIO.read(imageFile);
        if (img == null) {
            throw new IOException("Image cannot be read.");
        }
        return img;
    }

    @Override
    public List<BufferedImage> loadImagesFromDirectory(File imagesDirectory) throws IOException {
        if (imagesDirectory == null) {
            throw new IllegalArgumentException("Directory is null.");
        }
        if (!imagesDirectory.exists() || !imagesDirectory.isDirectory()) {
            throw new IOException("Directory does not exist or is not a directory.");
        }

        File[] files = imagesDirectory.listFiles();
        if (files == null) {
            throw new IOException("Directory cannot be read.");
        }

        List<BufferedImage> images = new ArrayList<>();
        for (var file : files) {
            if (!file.isFile()) {
                throw new IOException("The directory must contain only regular files.");
            }

            if (!isSupportedImage(file.getName())) {
                throw new IOException("Unsupported file format in directory.");
            }

            images.add(loadImage(file));
        }

        return images;
    }

    @Override
    public void saveImage(BufferedImage image, File imageFile) throws IOException {
        if (imageFile == null || image == null) {
            throw new IllegalArgumentException("Image or file is null.");
        }
        if (imageFile.exists()) {
            throw new IOException("File already exists.");
        }

        String format = imageFile.getName().toLowerCase();
        if (format.endsWith(".png")) {
            format = "png";
        } else if (format.endsWith(".jpeg")) {
            format = "jpeg";
        } else if (format.endsWith(".bmp")) {
            format = "bmp";
        } else if (format.endsWith(".jpg")) {
            format = "jpg";
        } else {
            throw new IOException("Unsupported image format.");
        }

        ImageIO.write(image, format, imageFile);
    }
}
