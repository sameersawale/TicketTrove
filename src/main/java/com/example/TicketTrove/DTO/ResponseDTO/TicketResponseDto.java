package com.example.TicketTrove.DTO.ResponseDTO;

import jdk.dynalink.linker.LinkerServices;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class TicketResponseDto {

    private String ticketId;

    private String username;

    private String seats;

    private Date showDate;

    private LocalTime showTime;

    private String theaterName;

    private String movieName;

    private int totalPrice;

    private String message;

}