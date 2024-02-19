package com.example.TicketTrove.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheaterResponseDto {

    private String name;

    private String address;

    private String city;
}
