package com.progspringinit.musiclib1.api.model;

import java.util.List;

import lombok.Data;

@Data
public class PlaylistDTO {
	private Long id;
	private Long user_id;
	private String name;
	private List<SongLiteDTO> songsDTOList;
}
