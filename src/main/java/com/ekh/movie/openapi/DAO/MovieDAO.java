//package com.ekh.movie.openapi.DAO;
//
//import java.util.List;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "movies")
//public class MovieDAO {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private int page;
//
//    @OneToMany(mappedBy = "movie")
//    private List<ResultDAO> results;
//}
