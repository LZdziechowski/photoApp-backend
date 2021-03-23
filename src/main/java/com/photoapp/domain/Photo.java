package com.photoapp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "photo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Photo {

    private Long id;
    private String name;
    private ZonedDateTime creationDateTime;
    private String filePath;
    private PhotoThumbnail photoThumbnail;

    public Photo(String name, ZonedDateTime creationDateTime) {
        this.name = name;
        this.creationDateTime = creationDateTime;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    @NotNull
    public String getName() {
        return name;
    }

    @Column(name = "created_date")
    @NotNull
    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_thumbnail_id")
    public PhotoThumbnail getPhotoThumbnail() {
        return photoThumbnail;
    }
}
