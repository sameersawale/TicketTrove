package com.example.TicketTrove.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String token;

    private String email;

}
