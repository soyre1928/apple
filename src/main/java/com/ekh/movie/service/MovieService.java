package com.ekh.movie.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ekh.movie.openapi.DTO.MovieDTO;
import com.ekh.movie.openapi.DTO.ResultDTO;
import com.ekh.movie.repository.ResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private static final String url = "https://api.themoviedb.org/3/search/movie?query=";
    private static final String key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTgwOWNhNjk1ZGM1MjNhNWVhZGRhMDE1NTlmNTMxNSIsIm5iZiI6MTc0MzQwODAxOS4wMTcsInN1YiI6IjY3ZWE0YjkzN2E3NTI5NGI3NmM2ZDEwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e_2ALQjMwsQEaEAvzUTgRfKdYLsPJcMB9yleCP87Csc";
    private final ResultRepository resultRepository;


	public MovieDTO save(String qurey) throws JsonProcessingException {
		
		RestTemplate restTemplate = new RestTemplate();

		String urlQuery = url + qurey;
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + key);
        headers.set("accept", "application/json");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieDTO> response = restTemplate.exchange(
    		urlQuery,
            HttpMethod.GET,
            entity,
            MovieDTO.class
        );
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        MovieDTO movieDTO = response.getBody();
        return movieDTO;
         
	}

}
