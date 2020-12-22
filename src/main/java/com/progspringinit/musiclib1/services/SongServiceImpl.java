package com.progspringinit.musiclib1.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.progspringinit.musiclib1.api.mapper.SongMapper;
import com.progspringinit.musiclib1.api.model.SongDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;
import com.progspringinit.musiclib1.domain.Song;
import com.progspringinit.musiclib1.exceptions.SongNotFoundException;
import com.progspringinit.musiclib1.repositories.SongRepository;

@Service
public class SongServiceImpl implements SongService {
	SongRepository songRepository;
	SongMapper songMapper;
	private ObjectMapper objectMapper = new ObjectMapper();

	public SongServiceImpl(SongRepository songRepository, SongMapper songMapper) {
		this.songRepository = songRepository;
		this.songMapper = songMapper;
	}

	@Override
	public SongsListDTO getAllSongs() {
		
		return new SongsListDTO(songRepository.findAll()
				.stream()
				.map(songMapper::songToDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public SongDTO getById(Long id) {
		
		return songMapper.songToDTO(songRepository.findById(id).get());
	}

	@Override
	public List<SongDTO> getByArtist(Long artistId) {
		
		return songRepository.getByArtists(artistId)
				.stream()
				.map(songMapper::songToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public SongDTO createNewSong(SongDTO songDTO) {
		
		Song song = songMapper.songDTOToSong(songDTO);
		Song savedSong = songRepository.save(song);
		return songMapper.songToDTO(savedSong);
	}

	@Override
	public SongDTO updateSong(Long id, SongDTO songDTO) {
		
		Song song = songMapper.songDTOToSong(songDTO);
		song.setId(id);
		Song savedSong = songRepository.save(song);
		return songMapper.songToDTO(savedSong);
	}

	@Override
	public void deleteSongById(Long id) {
		
		songRepository.deleteById(id);
	}

	@Override
	public SongDTO patchSong(Long id, JsonPatch patch) {
		try {
			Song song = songRepository.findById(id).orElseThrow(SongNotFoundException::new);
			Song songPatched = applyPatchToSong(patch, song);
			Song savedSong = songRepository.save(songPatched);
			return songMapper.songToDTO(savedSong);
		} catch (SongNotFoundException e)  {
			System.out.println("Song not found!!!");
			return null;
		} catch (JsonProcessingException | JsonPatchException e) {
			System.out.println("Internal Error!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private Song applyPatchToSong(JsonPatch patch, Song targetSong) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetSong, JsonNode.class));
        return objectMapper.treeToValue(patched, Song.class);
    }
}
