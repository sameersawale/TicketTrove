package com.example.TicketTrove.DTO.RequestDTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ScreenDto {

    private Date showDate;

    private Date showTime;

    private int theaterId;

    private int movieId;

    private int classicSeatPrice;

    private int executiveSeatPrice;
}
