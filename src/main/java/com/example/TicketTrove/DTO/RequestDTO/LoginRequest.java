package com.example.TicketTrove.DTO.RequestDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String email;

    private String password;
}
