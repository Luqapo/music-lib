package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.progspringinit.musiclib1.api.model.SongDTO;
import com.progspringinit.musiclib1.domain.Song;

@Mapper
public interface SongMapper {

	SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);
	
	SongDTO songToDTO(Song song);
	@Mapping(target = "artists", ignore = true)
	Song songDTOToSong(SongDTO songDTO);
}
