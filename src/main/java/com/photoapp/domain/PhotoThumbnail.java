package com.photoapp.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "photo_thumbnail")
@NoArgsConstructor
@Setter
public class PhotoThumbnail {

    private Long id;
    private String name;
    private String filePath;

    public PhotoThumbnail(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

}
