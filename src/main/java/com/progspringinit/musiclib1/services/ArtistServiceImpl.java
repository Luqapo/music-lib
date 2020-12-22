package com.progspringinit.musiclib1.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.progspringinit.musiclib1.api.mapper.ArtistMapper;
import com.progspringinit.musiclib1.api.model.ArtistDTO;
import com.progspringinit.musiclib1.api.model.ArtistListDTO;
import com.progspringinit.musiclib1.domain.Artist;
import com.progspringinit.musiclib1.repositories.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
	ArtistRepository artistRepository;
	ArtistMapper artistMapper;

	public ArtistServiceImpl(ArtistRepository artistRepository, ArtistMapper artistMapper) {
		this.artistRepository = artistRepository;
		this.artistMapper = artistMapper;
	}

	@Override
	public ArtistListDTO getAllArtists() {
		
		return new ArtistListDTO(artistRepository.findAll()
				.stream()
				.map(artistMapper::artistToDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public ArtistDTO getById(Long id) {
		
		return artistMapper.artistToDTO(artistRepository.findById(id).get());
	}

	@Override
	public ArtistDTO getByNick(String nick) {
		
		return artistMapper.artistToDTO(artistRepository.getFirstByNick(nick).get());
	}

	@Override
	public ArtistDTO createNewArtist(ArtistDTO artistDTO) {
		
		Artist artist = artistMapper.artistDTOToArtist(artistDTO);
		Artist savedArtist = artistRepository.save(artist);
		return artistMapper.artistToDTO(savedArtist);
	}

	@Override
	public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
		
		Artist artist = artistMapper.artistDTOToArtist(artistDTO);
		artist.setId(id);
		Artist savedArtist = artistRepository.save(artist);
		return artistMapper.artistToDTO(savedArtist);
	}

	@Override
	public void deleteArtistById(Long id) {
		
		artistRepository.deleteById(id);
	}

}
