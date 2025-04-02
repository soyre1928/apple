package com.ekh.movie.openapi.DAO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private String overview;
	private double popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private boolean video;
	private double vote_average;
	private int vote_count;
}
