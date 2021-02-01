package com.progspringinit.musiclib1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.github.fge.jsonpatch.JsonPatch;
import com.progspringinit.musiclib1.api.model.PlaylistDTO;
import com.progspringinit.musiclib1.api.model.PlaylistsListDTO;
import com.progspringinit.musiclib1.api.model.SongsListDTO;
import com.progspringinit.musiclib1.services.PlaylistService;

@CrossOrigin
@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
	PlaylistService playlistService;

	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PlaylistsListDTO getAllPlaylists(@RequestParam Long userId) {
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
	
	@PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
	@ResponseStatus(HttpStatus.OK)
	public PlaylistDTO patchPlaylist(@PathVariable Long id, @RequestBody JsonPatch patch) {
		return playlistService.patchPlaylist(id, patch);
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
