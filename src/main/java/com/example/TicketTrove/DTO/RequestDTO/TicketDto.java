package com.example.TicketTrove.DTO.RequestDTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketDto {

    private int showId;

    private List<String> requestedSeats;
}
