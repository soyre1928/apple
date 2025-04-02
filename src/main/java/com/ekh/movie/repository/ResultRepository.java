package com.ekh.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekh.movie.openapi.DAO.ResultDAO;
import com.ekh.movie.openapi.DTO.MovieDTO;

public interface ResultRepository extends JpaRepository<ResultDAO, Long>{

}
