package com.example.TicketTrove.Service;

import com.example.TicketTrove.DTO.RequestDTO.MovieDto;
import com.example.TicketTrove.DTO.ResponseDTO.MovieResponseDto;

public interface MovieService{

    public MovieResponseDto addMovie(MovieDto movieDto) throws Exception;

    public MovieResponseDto getMovie(int id) throws Exception;

    public MovieResponseDto updateMovie(int id, MovieDto movieDto) throws Exception;

    public String deleteMovie(int id) throws Exception;

}
