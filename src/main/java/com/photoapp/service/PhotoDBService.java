package com.photoapp.service;

import com.photoapp.domain.Photo;
import com.photoapp.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoDBService {

    private final PhotoRepository photoRepository;

    public List<Photo> getAllPhoto () {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhoto(final Long id) {
        return photoRepository.findById(id);
    }

    public Photo savePhoto(final Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(final Long id) {
        photoRepository.deleteById(id);
    }
}
