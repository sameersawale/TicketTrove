package com.example.TicketTrove.Service;

import com.example.TicketTrove.DTO.RequestDTO.TicketDto;
import com.example.TicketTrove.DTO.ResponseDTO.TicketResponseDto;

import java.util.List;

public interface TicketService {

    public TicketResponseDto bookTicket(TicketDto ticketDto);

    public List<TicketResponseDto> listOfAllTickets(int userId) throws Exception;
}
