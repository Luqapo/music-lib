package com.progspringinit.musiclib1.api.model;

import lombok.Data;

@Data
public class PlaylistDTO {
	private Long id;
	private Long user_id;
	private String name;
}
