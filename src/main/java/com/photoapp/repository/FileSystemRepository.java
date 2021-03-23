package com.photoapp.repository;

import com.photoapp.config.PhotoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Repository
public class FileSystemRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemRepository.class);

    private final Path PATH_RESOURCE = new File(this.getClass().getResource("/photosFiles").getFile()).toPath();

    public String saveFile(byte[] content, Long photoId) throws IOException {
        Path newFile = Paths.get(PATH_RESOURCE + "/photo_" + photoId);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }

    public boolean deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
            return true;
        } catch (NoSuchFileException e) {
            LOGGER.error("NoSuchFileException");
            return false;
        } catch (DirectoryNotEmptyException a) {
            LOGGER.error("DirectoryNotEmptyException");
            return false;
        } catch (IOException e) {
            LOGGER.error("IOException" + e);
            return false;
        }
    }

    public FileSystemResource findPhotoFile(String filePath) throws PhotoNotFoundException {
        try {
            return new FileSystemResource(Paths.get(filePath));
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            throw new PhotoNotFoundException();
        }
    }

}
