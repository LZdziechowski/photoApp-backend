package com.photoapp.mapper;

import com.photoapp.config.PhotoNotFoundException;
import com.photoapp.domain.Photo;
import com.photoapp.domain.PhotoDTO;
import com.photoapp.repository.FileSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoMapper {

    private final FileSystemRepository fileSystemRepository;

    public PhotoDTO mapToPhotoDTO(Photo photo) throws PhotoNotFoundException {
        return new PhotoDTO(
                photo.getId(),
                photo.getName(),
                photo.getPhotoThumbnail().getFilePath()
        );
    }
}
