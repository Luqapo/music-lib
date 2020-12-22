package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.domain.Playlist;
import org.mapstruct.Mapping;

@Mapper
public interface PlaylistMapper {
	PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);
	
	@Mapping(target = "user_id", source = "user.id")
	PlaylistDTO playlistToDTO(Playlist playlist);
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "songs", ignore = true)
	Playlist playlistDTOToPlaylist(PlaylistDTO playlistDTO);
}
