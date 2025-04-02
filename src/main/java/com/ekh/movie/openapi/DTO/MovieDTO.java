package com.ekh.movie.openapi.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDTO {
	
	private int page;
	private List<ResultDTO> results;
	
}
