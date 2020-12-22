package com.progspringinit.musiclib1.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.progspringinit.musiclib1.api.model.ArtistDTO;
import com.progspringinit.musiclib1.api.model.ArtistListDTO;
import com.progspringinit.musiclib1.services.ArtistService;

@RestController
@RequestMapping("/api/artist/")
public class ArtistController {
	private ArtistService artistService;

	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}


	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ArtistListDTO getAllArtists() {
		return artistService.getAllArtists();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ArtistDTO getById(@PathVariable Long id) {
		return artistService.getById(id);
	}
	
	@PostMapping
	public ArtistDTO createNewArtist(@RequestBody ArtistDTO artistDTO) {
		return artistService.createNewArtist(artistDTO);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ArtistDTO updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
		return artistService.updateArtist(id, artistDTO);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteArtist(@PathVariable Long id) {
		artistService.deleteArtistById(id);
	}
}
