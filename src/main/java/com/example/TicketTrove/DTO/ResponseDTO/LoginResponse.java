package com.example.TicketTrove.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;

    private String email;
}
