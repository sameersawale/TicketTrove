package com.example.TicketTrove.Service;

import com.example.TicketTrove.DTO.RequestDTO.TheaterDto;
import com.example.TicketTrove.DTO.ResponseDTO.TheaterResponseDto;
import org.springframework.stereotype.Service;

public interface TheaterService {

    public TheaterResponseDto addTheater(TheaterDto theaterDto)throws Exception;

    public TheaterResponseDto getTheaterById(int id) throws Exception;

    public TheaterResponseDto updateTheater(int id, TheaterDto theaterDto) throws Exception;

    public String deleteTheater(int id) throws Exception;
}
