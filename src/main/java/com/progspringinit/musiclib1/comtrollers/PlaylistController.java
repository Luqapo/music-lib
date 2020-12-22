package com.progspringinit.musiclib1.comtrollers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.api.model.PlaylistsListDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;
import com.progspringinit.musiclib1.services.PlaylistService;

@RestController
@RequestMapping("/api/playlist/")
public class PlaylistController {
	PlaylistService playlistService;

	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PlaylistsListDTO getAllPlaylists(@PathVariable Long userId) {
		return playlistService.getAllPlaylist(userId);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public PlaylistDTO getPlayistById(@PathVariable Long id) {
		return playlistService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public PlaylistDTO createNewPlaylist(@RequestBody PlaylistDTO playlistDTO, @RequestParam Long userId) {
		return playlistService.createNewPlaylist(playlistDTO, userId);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public PlaylistDTO updatePlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
		return playlistService.updatePlaylist(id, playlistDTO);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePlaylist(@PathVariable Long id) {
		playlistService.deletePlaylistById(id);
	}
	
	@PostMapping("addSong")
	@ResponseStatus(HttpStatus.OK)
	public void addSong(@RequestParam Long playlistId, @RequestParam Long songId) {
		playlistService.addSong(playlistId, songId);
	}
	
	@GetMapping("songs")
	@ResponseStatus(HttpStatus.OK)
	public SongsListDTO getPlaylistSongs(@RequestParam Long playlistId) {
		return playlistService.getPlaylistSongs(playlistId);
	}
}
