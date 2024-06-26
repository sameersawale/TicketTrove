package com.example.TicketTrove.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
public class ScreenResponseDto {

    private Date showDate;

    private LocalTime showTime;

    private String theaterName;

    private String movieName;

}
