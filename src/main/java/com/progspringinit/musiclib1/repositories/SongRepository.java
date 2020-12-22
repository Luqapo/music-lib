package com.progspringinit.musiclib1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progspringinit.musiclib1.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
	List<Song> getByArtists(Long artistId);
}
