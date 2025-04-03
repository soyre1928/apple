package com.ekh.movie.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ekh.movie.openapi.DAO.ResultDAO;
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


	public void save(String qurey) throws JsonProcessingException {
		
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
        
        if (movieDTO != null && movieDTO.getResults() != null) {
            List<ResultDAO> resultDAOs = new ArrayList<>();
            
            for (ResultDTO dto : movieDTO.getResults()) {
                ResultDAO dao = new ResultDAO();
                
                dao.setAdult(dto.isAdult());
                dao.setBackdrop_path(dto.getBackdrop_path());
                dao.setId(dto.getId());
                dao.setOriginal_language(dto.getOriginal_language());
                dao.setOriginal_title(dto.getOriginal_title());
                dao.setOverview(dto.getOverview());
                dao.setPopularity(dto.getPopularity());
                dao.setPoster_path(dto.getPoster_path());
                dao.setRelease_date(dto.getRelease_date());
                dao.setTitle(dto.getTitle());
                dao.setVideo(dto.isVideo());
                dao.setVote_average(dto.getVote_average());
                dao.setVote_count(dto.getVote_count());
                
                resultDAOs.add(dao);
            }
            
            resultRepository.saveAll(resultDAOs);
        }
         
	}
	
	public ResultDAO resultSave(ResultDTO resultDTO) {
		ResultDAO resultDAO = new ResultDAO();

	    resultDAO.setAdult(resultDTO.isAdult());
	    resultDAO.setBackdrop_path(resultDTO.getBackdrop_path());
	    resultDAO.setId(resultDTO.getId());
	    resultDAO.setOriginal_language(resultDTO.getOriginal_language());
	    resultDAO.setOriginal_title(resultDTO.getOriginal_title());
	    resultDAO.setOverview(resultDTO.getOverview());
	    resultDAO.setPopularity(resultDTO.getPopularity());
	    resultDAO.setPoster_path(resultDTO.getPoster_path());
	    resultDAO.setRelease_date(resultDTO.getRelease_date());
	    resultDAO.setTitle(resultDTO.getTitle());
	    resultDAO.setVideo(resultDTO.isVideo());
	    resultDAO.setVote_average(resultDTO.getVote_average());
	    resultDAO.setVote_count(resultDTO.getVote_count());
	    
	    return resultDAO;
	}
	

}
