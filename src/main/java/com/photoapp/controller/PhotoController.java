package com.photoapp.controller;

import com.photoapp.config.PhotoNotFoundException;
import com.photoapp.domain.Photo;
import com.photoapp.domain.PhotoDTO;
import com.photoapp.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping(value = "/photo")
    public Photo addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        return photoService.savePhoto(file.getBytes(), name);
    }

    @GetMapping(value = "/photo/{photoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public FileSystemResource downloadPhoto(@PathVariable Long photoId) throws PhotoNotFoundException {
        return photoService.getPhotoFile(photoId);
    }

    @GetMapping(value = "/photoThumb/{photoThumbId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public FileSystemResource downloadPhotoThumb(@PathVariable Long photoThumbId) throws PhotoNotFoundException {
        return photoService.getPhotoThumbFile(photoThumbId);
    }

    @DeleteMapping(value = "/photo/{photoId}")
    public boolean deletePhoto(@PathVariable Long photoId) throws PhotoNotFoundException {
        return photoService.deletePhoto(photoId);
    }

    @GetMapping(value = "/photos")
    public List<PhotoDTO> getPhotos() {
        return photoService.getPhotoThumbFiles();
    }
}
