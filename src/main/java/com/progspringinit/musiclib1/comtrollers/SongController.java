package com.progspringinit.musiclib1.comtrollers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.github.fge.jsonpatch.JsonPatch;
import com.progspringinit.musiclib1.api.model.SongDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;
import com.progspringinit.musiclib1.services.SongService;

@RestController
@RequestMapping("/api/song/")
public class SongController {
	private SongService songService;
	
	
	public SongController(SongService songService) {
		this.songService = songService;
	}


	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public SongsListDTO getAllSongs() {
		
		return songService.getAllSongs();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public SongDTO getById(@PathVariable Long id) {
		return songService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SongDTO createNewSong(@RequestBody SongDTO songDTO) {
		return songService.createNewSong(songDTO);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public SongDTO updateSong(@PathVariable Long id, @RequestBody SongDTO songDTO) {
		return songService.updateSong(id, songDTO);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	@ResponseStatus(HttpStatus.OK)
	public SongDTO patchSong(@PathVariable Long id, JsonPatch patch) {
		return songService.patchSong(id, patch);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSong(@PathVariable Long id) {
		songService.deleteSongById(id);
	}
}
