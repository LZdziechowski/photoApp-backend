package com.photoapp.domain;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ThumbnailFactory {

    public PhotoThumbnail createThumbnailPhoto(Photo photo) throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream(Files.readAllBytes(Paths.get(photo.getFilePath())));
        BufferedImage img = ImageIO.read(in);
        Image scaledImage = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        BufferedImage imageBuff = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imageBuff, "jpg", buffer);
        String thumbPath = photo.getFilePath() + "_thumb";
        Files.write(Paths.get(thumbPath), buffer.toByteArray());
        return new PhotoThumbnail(photo.getName() + "_thumb", thumbPath);
    }
}
