package com.example.TicketTrove.DTO.RequestDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheaterDto {

    private String name;

    private String address;

    private String city;

    private int classicSeatsCount;

    private int executiveSeatsCount;
}
