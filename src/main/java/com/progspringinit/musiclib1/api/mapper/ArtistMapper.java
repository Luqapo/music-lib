package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.progspringinit.musiclib1.api.model.ArtistDTO;
import com.progspringinit.musiclib1.domain.Artist;
import org.mapstruct.Mapping;


@Mapper
public interface ArtistMapper {
	ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);
	
	ArtistDTO artistToDTO(Artist artist);
	@Mapping(target = "songs", ignore = true)
	Artist artistDTOToArtist(ArtistDTO artistDTO);
}
