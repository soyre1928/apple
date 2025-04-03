package com.ekh.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekh.movie.openapi.DTO.MovieDTO;
import com.ekh.movie.openapi.DTO.ResultDTO;
import com.ekh.movie.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
	
	private final MovieService movieService;
	
	
	@GetMapping("/movie/search/{qurey}")
	public void save(@PathVariable(value = "qurey") String qurey) throws JsonProcessingException {
		movieService.save(qurey);
	}

}
