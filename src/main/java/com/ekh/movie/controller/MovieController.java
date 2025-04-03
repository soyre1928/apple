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
	
	
	@GetMapping("/movie/search/{query}")
	public void save(@PathVariable(value = "query") String query) throws JsonProcessingException {
		movieService.save(query);
	}
	
	@GetMapping("/movie/search/{query}/{year}")
	public MovieDTO moiveDate(@PathVariable(value = "query") String query,@PathVariable(value = "year") String year) throws JsonProcessingException {
		return movieService.moiveDate(query,year);
	}
	
	@GetMapping("/movie/find/query")
	public ResultDTO findMovie(@PathVariable(value = "query")String query) throws JsonProcessingException {
		return movieService.findMovie(query);
	}

}
