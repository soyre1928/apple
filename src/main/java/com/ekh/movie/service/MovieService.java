package com.ekh.movie.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ekh.movie.openapi.DAO.ResultDAO;
import com.ekh.movie.openapi.DTO.MovieDTO;
import com.ekh.movie.openapi.DTO.ResultDTO;
import com.ekh.movie.repository.ResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private static final String url = "https://api.themoviedb.org/3/search/movie?=&include_adult=true&language=ko-KR&query=";
    private static final String key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTgwOWNhNjk1ZGM1MjNhNWVhZGRhMDE1NTlmNTMxNSIsIm5iZiI6MTc0MzQwODAxOS4wMTcsInN1YiI6IjY3ZWE0YjkzN2E3NTI5NGI3NmM2ZDEwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e_2ALQjMwsQEaEAvzUTgRfKdYLsPJcMB9yleCP87Csc";
    private final ResultRepository resultRepository;


	public void save(String query) throws JsonProcessingException {
		
		RestTemplate restTemplate = new RestTemplate();

		String urlQuery = url + query;
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

        MovieDTO movieDTO = response.getBody();

        
        if (movieDTO != null && movieDTO.getResults() != null) {
            List<ResultDAO> resultDAOs = movieDTO.getResults().stream().map(ResultDAO::fromDTO).collect(Collectors.toList());
            resultRepository.saveAll(resultDAOs);
        }      
	} 


	public MovieDTO moiveDate(String query, String year) {
		RestTemplate restTemplate = new RestTemplate();

		String urlQuery = url + query + "&year=" + year;
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

        MovieDTO movieDTO = response.getBody();
        return movieDTO;
	}


	public List<ResultDAO> findMovie(String query) {
		
		List<ResultDAO> results = resultRepository.findByTitleContaining(query);
		
		if(!results.isEmpty()) {
			return results;
		}
		RestTemplate restTemplate = new RestTemplate();

		String urlQuery = url + query;
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + key);
        headers.set("accept", "application/json");

        HttpEntity<MovieDTO> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieDTO> response = restTemplate.exchange(urlQuery,HttpMethod.GET,entity,MovieDTO.class);
		
        MovieDTO movieDTO = response.getBody();
        
        List<ResultDAO> results2 = movieDTO.getResults().stream()
        	    .map(resultDTO -> {
        	        ResultDAO resultDAO = new ResultDAO();
        	        resultDAO.setId(resultDTO.getId());
        	        resultDAO.setTitle(resultDTO.getTitle());
        	        return resultDAO;
        	    }).collect(Collectors.toList());
        	resultRepository.saveAll(results2);

        	return results2;
		
	}

}
