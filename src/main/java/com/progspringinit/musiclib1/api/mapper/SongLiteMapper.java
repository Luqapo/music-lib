package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.progspringinit.musiclib1.api.model.SongLiteDTO;
import com.progspringinit.musiclib1.domain.Song;

@Mapper
public interface SongLiteMapper {
	SongLiteMapper INSTANCE = Mappers.getMapper(SongLiteMapper.class);
	
	SongLiteDTO songToLiteDTO(Song song);
	@Mapping(target = "genre", ignore = true)
	@Mapping(target = "ismn", ignore = true)
	@Mapping(target = "publisher", ignore = true)
	@Mapping(target = "year", ignore = true)
	@Mapping(target = "artists", ignore = true)
	Song songLiteDTOToSong(SongLiteDTO songDTO);
}
