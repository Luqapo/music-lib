
package com.progspringinit.musiclib1.services;

import com.progspringinit.musiclib1.api.model.ArtistDTO;
import com.progspringinit.musiclib1.api.model.ArtistListDTO;

public interface ArtistService {
	ArtistListDTO getAllArtists();
	ArtistDTO getById(Long id);
	ArtistDTO getByNick(String nick);
	ArtistDTO createNewArtist(ArtistDTO artistDTO);
	ArtistDTO updateArtist(Long id, ArtistDTO artistDTO);
	void deleteArtistById(Long id);
}
