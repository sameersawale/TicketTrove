package com.example.TicketTrove.Controller;

import com.example.TicketTrove.DTO.RequestDTO.TicketDto;
import com.example.TicketTrove.DTO.ResponseDTO.TicketResponseDto;
import com.example.TicketTrove.Repository.TicketRepository;
import com.example.TicketTrove.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?>bookTicket(@RequestBody TicketDto ticketDto){
        TicketResponseDto ticketResponseDto=ticketService.bookTicket(ticketDto);
        return new ResponseEntity<>(ticketResponseDto, HttpStatus.CREATED);
    }
}
