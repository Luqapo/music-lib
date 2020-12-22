package com.progspringinit.musiclib1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progspringinit.musiclib1.domain.Playlist;
import com.progspringinit.musiclib1.domain.User;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	public List<Playlist> findByUser(User user);
}
