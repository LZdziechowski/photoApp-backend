package com.photoapp.service;

import com.photoapp.domain.PhotoThumbnail;
import com.photoapp.repository.PhotoThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoThumbnailDBService {

    private final PhotoThumbnailRepository photoThumbnailRepository;

    public List<PhotoThumbnail> getAllThumbnailPhoto () {
        return photoThumbnailRepository.findAll();
    }

    public Optional<PhotoThumbnail> getThumbnailPhoto(final Long id) {
        return photoThumbnailRepository.findById(id);
    }

    public PhotoThumbnail saveThumbnailPhoto(final PhotoThumbnail photoThumbnail) {
        return photoThumbnailRepository.save(photoThumbnail);
    }

    public void deleteThumbnailPhoto(final Long id) {
        photoThumbnailRepository.deleteById(id);
    }

}
