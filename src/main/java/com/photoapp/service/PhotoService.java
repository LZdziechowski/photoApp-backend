package com.photoapp.service;

import com.photoapp.config.PhotoNotFoundException;
import com.photoapp.config.TimeZoneConfig;
import com.photoapp.domain.Photo;
import com.photoapp.domain.PhotoDTO;
import com.photoapp.domain.PhotoThumbnail;
import com.photoapp.domain.ThumbnailFactory;
import com.photoapp.mapper.PhotoMapper;
import com.photoapp.repository.FileSystemRepository;
import com.photoapp.repository.PhotoRepository;
import com.photoapp.repository.PhotoThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final FileSystemRepository fileSystemRepository;
    private final PhotoRepository photoRepository;
    private final PhotoThumbnailRepository photoThumbnailRepository;
    private final TimeZoneConfig timeZoneConfig;
    private final ThumbnailFactory thumbnailFactory;
    private final PhotoMapper photoMapper;

    public Photo savePhoto(byte[] content, String photoName) throws IOException {
        Photo photo = photoRepository.save(new Photo(photoName, timeZoneConfig.getCurrentDateTime(TimeZoneConfig.TIMEZONE_UTC)));
        String path = fileSystemRepository.saveFile(content, photo.getId());
        photo.setFilePath(path);
        PhotoThumbnail photoThumbnail = thumbnailFactory.createThumbnailPhoto(photo);
        photoThumbnailRepository.save(photoThumbnail);
        photo.setPhotoThumbnail(photoThumbnail);
        return photoRepository.save(photo);
    }

    public FileSystemResource getPhotoFile(Long photoId) throws PhotoNotFoundException {
        Optional<Photo> photo = photoRepository.findById(photoId);
        String path = photo.orElseThrow(PhotoNotFoundException::new).getFilePath();
        return fileSystemRepository.findPhotoFile(path);
    }

    public FileSystemResource getPhotoThumbFile(Long photoThumbId) throws PhotoNotFoundException {
        Optional<PhotoThumbnail> photoThumbnail = photoThumbnailRepository.findById(photoThumbId);
        String path = photoThumbnail.orElseThrow(PhotoNotFoundException::new).getFilePath();
        return fileSystemRepository.findPhotoFile(path);
    }

    public List<PhotoDTO> getPhotoThumbFiles() {
        return photoRepository.findAll().stream()
                .filter(photo -> photo.getPhotoThumbnail() != null)
                .map(wrapper(photoMapper::mapToPhotoDTO))
                .collect(Collectors.toList());
    }


    public boolean deletePhoto(Long photoId) throws PhotoNotFoundException {
        Optional<Photo> photo = photoRepository.findById(photoId);
        String path = photo.orElseThrow(PhotoNotFoundException::new).getFilePath();
        String pathThumb = photo.get().getPhotoThumbnail().getFilePath();
        photoRepository.deleteById(photoId);
        boolean resultPhoto = fileSystemRepository.deleteFile(path);
        boolean resultThumb = fileSystemRepository.deleteFile(pathThumb);
        return resultPhoto && resultThumb;
    }

    private <T, R, E extends Exception>
    Function<T, R> wrapper(ThrowingConsumer<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
