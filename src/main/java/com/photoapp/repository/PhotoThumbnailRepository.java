package com.photoapp.repository;

import com.photoapp.domain.PhotoThumbnail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoThumbnailRepository extends CrudRepository<PhotoThumbnail, Long> {

    List<PhotoThumbnail> findAll();
}
