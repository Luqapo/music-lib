package com.progspringinit.musiclib1.services;

import java.util.List;
import com.github.fge.jsonpatch.JsonPatch;
import com.progspringinit.musiclib1.api.model.SongDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;

public interface SongService {
	SongsListDTO getAllSongs();
	SongDTO getById(Long id);
	List<SongDTO> getByArtist(Long artistId);
	SongDTO createNewSong(SongDTO songDTO);
	SongDTO updateSong(Long id, SongDTO songDTO);
	SongDTO patchSong(Long id, JsonPatch patch);
	void deleteSongById(Long id);
}
