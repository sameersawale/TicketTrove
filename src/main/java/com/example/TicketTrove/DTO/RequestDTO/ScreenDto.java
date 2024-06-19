package com.example.TicketTrove.DTO.RequestDTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
public class ScreenDto {

    private Date showDate;

    private LocalTime showTime;

    private int theaterId;

    private int movieId;

    private int classicSeatPrice;

    private int executiveSeatPrice;
}
