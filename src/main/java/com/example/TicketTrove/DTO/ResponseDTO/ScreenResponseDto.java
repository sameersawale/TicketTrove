package com.example.TicketTrove.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ScreenResponseDto {

    private Date showDate;

    private Date showTime;

    private String theaterName;

    private String movieName;

}
