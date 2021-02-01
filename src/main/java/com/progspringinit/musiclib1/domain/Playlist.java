package com.progspringinit.musiclib1.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "playlists")
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany
	private Set<Song> songs = new HashSet<>();
	
	public Playlist() {}
}
