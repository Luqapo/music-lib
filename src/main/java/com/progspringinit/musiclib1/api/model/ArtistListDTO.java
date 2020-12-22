package com.progspringinit.musiclib1.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtistListDTO {

	private List<ArtistDTO> artistList;
}
