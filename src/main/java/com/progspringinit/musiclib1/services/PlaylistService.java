package com.progspringinit.musiclib1.services;

import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.api.model.PlaylistsListDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;

public interface PlaylistService {
	PlaylistsListDTO getAllPlaylist(Long userId);
	PlaylistDTO getById(Long id);
	PlaylistDTO createNewPlaylist(PlaylistDTO playlistDTO, Long userId);
	PlaylistDTO updatePlaylist(Long id, PlaylistDTO playlistDTO);
	void deletePlaylistById(Long id);
	PlaylistDTO addSong(Long id, Long songId);
	SongsListDTO getPlaylistSongs(Long playlistId);
}
