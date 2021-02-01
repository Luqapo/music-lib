package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.domain.Playlist;
import com.progspringinit.musiclib1.repositories.SongRepository;
import com.progspringinit.musiclib1.repositories.UserRepository;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {SongLiteMapper.class})
public interface PlaylistMapper {
	PlaylistMapper INSTANCE = Mappers.getMapper(PlaylistMapper.class);
	
	@Mapping(target = "user_id", source = "user.id")
	@Mapping(target = "songsDTOList", source = "playlist.songs")
	PlaylistDTO playlistToDTO(Playlist playlist);
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "songs", ignore = true)
	Playlist playlistDTOToPlaylist(
			PlaylistDTO playlistDTO,
			@Context UserRepository userRepository,
			@Context SongRepository songRepository);
	
	@AfterMapping
	default void map(
			@MappingTarget Playlist target,
			PlaylistDTO playlistDTO,
			@Context UserRepository userRepository,
			@Context SongRepository songRepository) {
		target.setUser(userRepository.findById(playlistDTO.getUser_id()).orElseThrow());
		playlistDTO.getSongsDTOList().forEach(songLiteDTO -> {
			target.getSongs().add(songRepository.getOne(songLiteDTO.getId()));
		});
	}
}
