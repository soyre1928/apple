package com.ekh.movie.openapi.DAO;

import com.ekh.movie.openapi.DTO.ResultDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results")
public class ResultDAO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;

	private boolean adult;
	private String backdrop_path;
	private int id;
	private String original_language;
	private String original_title;
	@Column(length = 500)
	private String overview;
	private double popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private boolean video;
	private double vote_average;
	private int vote_count;
	
	public static ResultDAO fromDTO(ResultDTO dto) {
		ResultDAO dao = new ResultDAO();
		dao.adult = dto.isAdult();
		dao.backdrop_path = dto.getBackdrop_path();
		dao.id = dto.getId();
		dao.original_language = dto.getOriginal_language();
		dao.original_title = dto.getOriginal_title();
		dao.overview = dto.getOverview();
		dao.popularity = dto.getPopularity();
		dao.poster_path = dto.getPoster_path();
		dao.release_date = dto.getRelease_date();
		dao.title = dto.getTitle();
		dao.video = dto.isVideo();
		dao.vote_average = dto.getVote_average();
		dao.vote_count = dto.getVote_count();
		
		return dao;
	}
}
