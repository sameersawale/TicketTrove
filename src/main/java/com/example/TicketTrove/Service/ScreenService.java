package com.example.TicketTrove.Service;

import com.example.TicketTrove.DTO.RequestDTO.ScreenDto;
import com.example.TicketTrove.DTO.ResponseDTO.ScreenResponseDto;
import com.example.TicketTrove.Model.Screen;

public interface ScreenService {

    public ScreenResponseDto addScreen(ScreenDto screenDto) throws Exception;

    public ScreenResponseDto updateScreen(ScreenDto screenDto) throws Exception;

    public ScreenResponseDto getById(int id) throws Exception;

    public String deleteById(int id) throws Exception;
}
