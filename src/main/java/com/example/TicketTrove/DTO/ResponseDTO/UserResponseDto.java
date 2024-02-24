package com.example.TicketTrove.DTO.ResponseDTO;

import com.example.TicketTrove.Model.Role;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNo;

    private String address;

    private Set<Role> roles;
}
