package com.example.TicketTrove.Service.Impl;

import com.example.TicketTrove.DTO.RequestDTO.MovieDto;
import com.example.TicketTrove.DTO.ResponseDTO.MovieResponseDto;
import com.example.TicketTrove.Model.Movie;
import com.example.TicketTrove.Repository.MovieRepository;
import com.example.TicketTrove.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Override
    public MovieResponseDto addMovie(MovieDto movieDto) throws Exception {
        Movie movie=Movie.builder()
                .movieName(movieDto.getMovieName())
                .duration(movieDto.getDuration())
                .rating(movieDto.getRating())
                .movieLanguage(movieDto.getMovieLanguage())
                .movieGenre(movieDto.getMovieGenre())
                .build();
        movieRepository.save(movie);

        MovieResponseDto responseDto=MovieResponseDto.builder()
                .movieName(movie.getMovieName())
                .movieGenre(movie.getMovieGenre())
                .duration(movie.getDuration())
                .rating(movie.getRating())
                .movieLanguage(movie.getMovieLanguage())
                .build();
        return responseDto;
    }

    @Override
    public MovieResponseDto getMovie(int id) throws Exception {
        Movie movie=movieRepository.findById(id).get();
        MovieResponseDto responseDto= MovieResponseDto.builder()
                .movieName(movie.getMovieName())
                .rating(movie.getRating())
                .duration(movie.getDuration())
                .movieLanguage(movie.getMovieLanguage())
                .movieGenre(movie.getMovieGenre())
                .build();
        return responseDto;
    }

    @Override
    public MovieResponseDto updateMovie(int id, MovieDto movieDto) throws Exception {
        Movie movie=movieRepository.findById(id).get();
        movie.setMovieName(movieDto.getMovieName());
        movie.setDuration(movie.getDuration());
        movie.setRating(movieDto.getRating());
        movie.setMovieLanguage(movieDto.getMovieLanguage());
        movie.setMovieGenre(movieDto.getMovieGenre());

        movieRepository.save(movie);

        MovieResponseDto responseDto= MovieResponseDto.builder()
                .movieName(movie.getMovieName())
                .rating(movie.getRating())
                .duration(movie.getDuration())
                .movieLanguage(movie.getMovieLanguage())
                .movieGenre(movie.getMovieGenre())
                .build();
        return responseDto;
    }

    @Override
    public String deleteMovie(int id) throws Exception {
        movieRepository.deleteById(id);
        return "Movie deleted successfully....";
    }
}
