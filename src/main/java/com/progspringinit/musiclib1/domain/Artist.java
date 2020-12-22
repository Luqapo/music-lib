package com.progspringinit.musiclib1.domain;

import lombok.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String nick;
	@ManyToMany(mappedBy = "artists")
	private Set<Song> songs = new HashSet<>();
	public Artist(String firstName, String lastName, String nick) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nick = nick;
	}
	public Artist() {
		super();
	}
}
