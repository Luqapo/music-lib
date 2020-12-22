package com.progspringinit.musiclib1.api.model;

import lombok.Data;

@Data
public class SongDTO {
    private Long id;
    private String title;
    private String genre;
    private String ismn;
    private String year;
    private String publisher;
}