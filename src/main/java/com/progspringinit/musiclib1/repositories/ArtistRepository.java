package com.progspringinit.musiclib1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progspringinit.musiclib1.domain.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Optional<Artist> getFirstByFirstNameAndLastName(String firstName, String lastName);
	Optional<Artist> getFirstByFirstName(String firstName);
	Optional<Artist> getFirstByNick(String nick);
}
