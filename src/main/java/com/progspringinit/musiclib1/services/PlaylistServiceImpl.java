package com.progspringinit.musiclib1.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.progspringinit.musiclib1.api.mapper.PlaylistMapper;
import com.progspringinit.musiclib1.api.mapper.SongMapper;
import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.api.model.PlaylistsListDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;
import com.progspringinit.musiclib1.domain.Playlist;
import com.progspringinit.musiclib1.domain.Song;
import com.progspringinit.musiclib1.domain.User;
import com.progspringinit.musiclib1.repositories.PlaylistRepository;
import com.progspringinit.musiclib1.repositories.SongRepository;
import com.progspringinit.musiclib1.repositories.UserRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	PlaylistRepository playlistRepository;
	UserRepository userRepository;
	SongRepository songRepository;
	PlaylistMapper playlistMapper;
	SongMapper songMapper;

	public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserRepository userRepository,
			SongRepository songRepository, PlaylistMapper playlistMapper, SongMapper songMapper) {
		this.playlistRepository = playlistRepository;
		this.userRepository = userRepository;
		this.songRepository = songRepository;
		this.playlistMapper = playlistMapper;
		this.songMapper = songMapper;
	}

	@Override
	public PlaylistsListDTO getAllPlaylist(Long userId) {
		User user = userRepository.findById(userId).get();
	
		return new PlaylistsListDTO(playlistRepository.findByUser(user)
				.stream()
				.map(playlistMapper::playlistToDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public PlaylistDTO getById(Long id) {
		
		return playlistMapper.playlistToDTO(playlistRepository.findById(id).get());
	}

	@Override
	public PlaylistDTO createNewPlaylist(PlaylistDTO playlistDTO, Long userId) {
		
		User user = userRepository.findById(userId).get();
		Playlist playlist = playlistMapper.playlistDTOToPlaylist(playlistDTO);
		playlist.setUser(user);
		Playlist savedPlaylist = playlistRepository.save(playlist);
		
		return playlistMapper.playlistToDTO(savedPlaylist);
	}

	@Override
	public PlaylistDTO updatePlaylist(Long id, PlaylistDTO playlistDTO) {
		
		Playlist playlist = playlistMapper.playlistDTOToPlaylist(playlistDTO);
		playlist.setId(id);
		Playlist savedPlaylist = playlistRepository.save(playlist);
		return playlistMapper.playlistToDTO(savedPlaylist);
	}

	@Override
	public void deletePlaylistById(Long id) {
		
		playlistRepository.deleteById(id);
	}

	@Override
	public PlaylistDTO addSong(Long id, Long songId) {
		
		Playlist playlist = playlistRepository.getOne(id);
		Song song = songRepository.getOne(songId);
		playlist.getSongs().add(song);
		Playlist savedPlaylist = playlistRepository.save(playlist);
		return playlistMapper.playlistToDTO(savedPlaylist);
	}

	@Override
	public SongsListDTO getPlaylistSongs(Long playlistId) {
		
		Playlist playlist = playlistRepository.getOne(playlistId);
		return new SongsListDTO(playlist.getSongs()
				.stream()
				.map(songMapper::songToDTO)
				.collect(Collectors.toList()));
	}

}
